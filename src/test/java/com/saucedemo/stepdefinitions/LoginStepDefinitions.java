package com.saucedemo.stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.WebDriver;

import com.saucedemo.config.WebDriverConfig;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.runners.PerformanceTestRunner;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinitions {
    private final WebDriver driver;
    private final LoginPage loginPage;
    private final InventoryPage inventoryPage;


    public LoginStepDefinitions() {
        this.driver = WebDriverConfig.getDriver();
        this.loginPage = new LoginPage(driver);
        this.inventoryPage = new InventoryPage(driver);
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        PerformanceTestRunner.resetTimer(); 
        loginPage.navigateTo();
        assert loginPage.isOnLoginPage() : "Not on login page";
    }

    @When("I login with {string} and {string}")
    public void iLoginWith(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should see the inventory page")
    public void iShouldSeeTheInventoryPage() {
        assert inventoryPage.isOnInventoryPage() : "Not on inventory page after successful login";
    }

    @Then("I should see an error message indicating locked out user")
    public void iShouldSeeLockedOutUserError() {
        assert loginPage.hasLockedOutUserError() : "Locked out user error not displayed";
    }

    @Then("I should see an error message indicating invalid credentials")
    public void iShouldSeeInvalidCredentialsError() {
        assert loginPage.hasInvalidCredentialsError() : "Invalid credentials error not displayed";
    }

    @Then("I should see an error message indicating required field")
    public void iShouldSeeRequiredFieldError() {
        assert loginPage.hasRequiredFieldError() : "Required field error not displayed";
    }

    @And("there should not be any visual failures")
    public void thereShouldNotBeAnyVisualFailures() {
        assert !inventoryPage.hasVisualFailures() : "Visual failures detected";
    }

    @And("the average response time should be less than {int} second")
    public void validateAverageResponseTime(int maxTimeInSeconds) {
        double averageResponseTime = PerformanceTestRunner.getAverageResponseTime();
        assertTrue(averageResponseTime < maxTimeInSeconds,
                "Average response time exceeded the limit: " + averageResponseTime + " seconds");
    }

    @io.cucumber.java.After
    public void tearDown() {
        WebDriverConfig.quitDriver(driver);
    }
}