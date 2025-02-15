package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SauceDemo Login Page Test Implementation
 * --------------------------------------
 * ISTQB Test Documentation Reference: TD-LOGIN-001
 * 
 * Test Design Specification:
 * 1. Test Basis:
 *    - Business Requirements: REQ-001, REQ-002, REQ-003
 *    - User Stories: US-LOGIN-001 to US-LOGIN-005
 *    - Risk Analysis: RA-SEC-001
 * 
 * 2. Test Techniques (IEEE 829):
 *    - Equivalence Partitioning (EP)
 *    - Boundary Value Analysis (BVA)
 *    - Decision Table Testing (DTT)
 *    - State Transition Testing (STT)
 * 
 * 3. Test Conditions:
 *    TC-001: Valid Authentication
 *    TC-002: Invalid Authentication
 *    TC-003: Account Lockout
 *    TC-004: Security Measures
 * 
 * 4. Quality Characteristics (ISO 25010):
 *    - Functional Suitability
 *    - Security
 *    - Reliability
 *    - Usability
 * 
 * 5. Entry/Exit Criteria:
 *    Entry:
 *    - Test environment is operational
 *    - Test data is available
 *    - All dependencies are resolved
 *    
 *    Exit:
 *    - All test cases executed
 *    - No critical defects open
 *    - Test coverage >= 95%
 * 
 * Corporate Standards Reference:
 * - Document ID: TEST-POM-001
 * - Version Control: Git repository main branch
 * - Review Status: Approved
 * - Last Review: 2025-02-14
 * 
 * @author Basar Akcin
 * @version 1.0
 * @see WebDriver
 * @see PageFactory
 */
public class LoginPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginPage.class);
    private final WebDriver driver;
    private WebDriverWait wait;
    private static final int TIMEOUT_SECONDS = 10;
    private boolean isTestFailed = false;
    private int failedLoginAttempts = 0;
    private static final int MAX_LOGIN_ATTEMPTS = 5;

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    @FindBy(className = "error-message-container")
    private WebElement errorContainer;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        initializePage();
    }

    private void initializePage() {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));
        PageFactory.initElements(driver, this);
    }

    public void navigateToLoginPage(String url) {
        try {
            LOGGER.info("Navigating to login page: {}", url);
            driver.get(url);
            waitForLoginPageToLoad();
        } catch (Exception e) {
            LOGGER.error("Failed to navigate to login page: {}", e.getMessage());
            isTestFailed = true;
            throw e;
        }
    }

    /**
     * Attempts to log in with the provided credentials
     *
     * @param username The username to enter
     * @param password The password to enter
     * @throws IllegalStateException if the page elements are not accessible
     */
    public void enterCredentials(String username, String password) {
        try {
            LOGGER.info("Entering credentials for username: {}", username);
            if (username != null) {
                waitAndClearAndType(usernameField, username);
            }
            if (password != null) {
                waitAndClearAndType(passwordField, password);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to enter credentials: {}", e.getMessage());
            isTestFailed = true;
            throw e;
        }
    }

    public void clickLoginButton() {
        try {
            LOGGER.info("Clicking login button");
            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
            safeSleep(500);
        } catch (Exception e) {
            LOGGER.error("Failed to click login button: {}", e.getMessage());
            isTestFailed = true;
            throw e;
        }
    }

    /**
     * Retrieves the error message displayed on the login page
     *
     * @return The text of the error message
     * @throws TimeoutException if the error message doesn't appear within timeout
     */
    public String getErrorMessage() {
        try {
            LOGGER.info("Getting error message");
            // Click login button if not already clicked
            if (!isErrorMessageDisplayed()) {
                clickLoginButton();
            }
            // Wait for error container first
            wait.until(ExpectedConditions.visibilityOf(errorContainer));
            // Then wait for and get error message
            return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
        } catch (Exception e) {
            LOGGER.error("Failed to get error message: {}", e.getMessage());
            isTestFailed = true;
            throw e;
        }
    }

    public String getCurrentUrl() {
        try {
            String currentUrl = driver.getCurrentUrl();
            LOGGER.info("Current URL: {}", currentUrl);
            return currentUrl;
        } catch (Exception e) {
            LOGGER.error("Failed to get current URL: {}", e.getMessage());
            isTestFailed = true;
            throw e;
        } finally {
            LOGGER.info("Completed page verification");
        }
    }

    public boolean isTestFailed() {
        return isTestFailed;
    }

    private void waitForLoginPageToLoad() {
        try {
            wait.until(ExpectedConditions.visibilityOf(usernameField));
            wait.until(ExpectedConditions.visibilityOf(passwordField));
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        } catch (Exception e) {
            LOGGER.error("Failed to wait for login page load: {}", e.getMessage());
            isTestFailed = true;
            throw e;
        }
    }

    private void waitAndClearAndType(WebElement element, String text) {
        try {
            WebElement waitElement = wait.until(ExpectedConditions.visibilityOf(element));
            waitElement.clear();
            waitElement.sendKeys(text);
        } catch (Exception e) {
            LOGGER.error("Failed to interact with element: {}", e.getMessage());
            isTestFailed = true;
            throw e;
        }
    }

    public void cleanupResources() {
        LOGGER.info("Cleaning up page resources");
        try {
            // Clear any page state
            if (driver != null) {
                driver.manage().deleteAllCookies();
            }
        } catch (Exception e) {
            LOGGER.error("Error during page cleanup: {}", e.getMessage());
        }
    }

    /**
     * Simulates multiple failed login attempts to test security features
     *
     * @param attempts The number of login attempts to make
     * @see MAX_LOGIN_ATTEMPTS
     */
    public void attemptMultipleFailedLogins(int attempts) {
        LOGGER.info("Attempting {} failed logins", attempts);
        for (int i = 0; i < attempts && failedLoginAttempts < MAX_LOGIN_ATTEMPTS; i++) {
            enterCredentials("invalid_user", "invalid_password");
            clickLoginButton();
            failedLoginAttempts++;
            if (failedLoginAttempts >= MAX_LOGIN_ATTEMPTS) {
                LOGGER.info("Maximum failed login attempts reached");
                break;
            }
            safeSleep(500); // Small delay between attempts
        }
    }

    private void safeSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.error("Sleep interrupted: {}", e.getMessage());
        }
    }

    private boolean isErrorMessageDisplayed() {
        try {
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginButtonEnabled() {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(loginButton)).isEnabled();
        } catch (Exception e) {
            LOGGER.error("Error checking login button state: {}", e.getMessage());
            return false;
        }
    }
} 