package utils;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import config.WebDriverConfig;

public class TestContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestContext.class);
    private final WebDriverConfig webDriverConfig;
    private boolean isTestFailed = false;

    public TestContext() {
        LOGGER.info("Initializing TestContext");
        this.webDriverConfig = WebDriverConfig.getInstance();
    }

    public WebDriver getDriver() {
        return webDriverConfig.getDriver();
    }

    public void markTestAsFailed() {
        isTestFailed = true;
    }

    public boolean isTestFailed() {
        return isTestFailed;
    }

    public synchronized void tearDown() {
        LOGGER.info("=== Starting TestContext tearDown process ===");
        try {
            WebDriver driver = webDriverConfig.getDriver();
            if (driver != null) {
                try {
                    String currentUrl = driver.getCurrentUrl();
                    LOGGER.info("Current URL before teardown: {}", currentUrl);
                    if (currentUrl.equals("https://www.saucedemo.com/")) {
                        LOGGER.info("=== Detected login page, performing additional cleanup ===");
                        driver.manage().deleteAllCookies();
                        driver.navigate().refresh();
                        LOGGER.info("Cookies cleared and page refreshed");
                    }
                } catch (Exception e) {
                    LOGGER.error("Error during pre-quit cleanup: {}", e.getMessage(), e);
                }
            } else {
                LOGGER.info("No active WebDriver instance found");
            }
            
            LOGGER.info("=== Initiating WebDriver quit ===");
            webDriverConfig.quitDriver();
            LOGGER.info("=== WebDriver quit completed ===");
        } catch (Exception e) {
            LOGGER.error("Critical error during tearDown: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to tear down test context", e);
        } finally {
            LOGGER.info("=== TestContext tearDown process complete ===");
            isTestFailed = false;
        }
    }
} 