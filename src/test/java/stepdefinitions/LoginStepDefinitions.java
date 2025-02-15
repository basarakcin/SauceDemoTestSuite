package stepdefinitions;

import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import utils.TestContext;

public class LoginStepDefinitions {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginStepDefinitions.class);
    private final LoginPage loginPage;

    public LoginStepDefinitions(TestContext context) {
        this.loginPage = new LoginPage(context.getDriver());
    }

    @Given("I am on the SauceDemo login page {string}")
    public void i_am_on_the_sauce_demo_login_page(String url) {
        try {
            loginPage.navigateToLoginPage(url);
        } catch (Exception e) {
            LOGGER.error("Failed to navigate to login page: {}", e.getMessage());
            throw new AssertionError("Failed to navigate to login page", e);
        }
    }

    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) {
        try {
            loginPage.enterCredentials(username, password);
        } catch (Exception e) {
            LOGGER.error("Failed to enter credentials: {}", e.getMessage());
            throw new AssertionError("Failed to enter credentials", e);
        }
    }

    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        try {
            loginPage.clickLoginButton();
        } catch (Exception e) {
            LOGGER.error("Failed to click login button: {}", e.getMessage());
            throw new AssertionError("Failed to click login button", e);
        }
    }

    @When("I attempt to login with invalid credentials {int} times")
    public void i_attempt_to_login_with_invalid_credentials_times(Integer attempts) {
        loginPage.attemptMultipleFailedLogins(attempts);
    }

    @Then("I should see error message {string}")
    public void i_should_see_error_message(String expectedMessage) {
        try {
            String actualMessage = loginPage.getErrorMessage();
            Assertions.assertEquals(expectedMessage, actualMessage);
        } catch (AssertionError | IllegalStateException e) {
            LOGGER.error("Failed to verify error message: {}", e.getMessage());
            throw new AssertionError("Failed to verify error message", e);
        }
    }

    @Then("I should see error message about too many attempts")
    public void i_should_see_error_message_about_too_many_attempts() {
        String errorMessage = loginPage.getErrorMessage();
        Assertions.assertEquals(
            "Epic sadface: Username and password do not match any user in this service",
            errorMessage,
            "Expected error message for multiple failed attempts"
        );
    }

    @Then("I should be redirected to the inventory page {string}")
    public void i_should_be_redirected_to_the_inventory_page(String expectedUrl) {
        try {
            String currentUrl = loginPage.getCurrentUrl();
            Assertions.assertEquals(expectedUrl, currentUrl);
        } catch (Exception e) {
            LOGGER.error("Failed to verify redirect to inventory page: {}", e.getMessage());
            throw new AssertionError("Failed to verify redirect to inventory page", e);
        }
    }

    @Then("I should remain on the login page {string}")
    public void i_should_remain_on_the_login_page(String expectedUrl) {
        try {
            String currentUrl = loginPage.getCurrentUrl();
            Assertions.assertEquals(expectedUrl, currentUrl, "User should remain on login page");
        } finally {
            loginPage.cleanupResources();
        }
    }
} 