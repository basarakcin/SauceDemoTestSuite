package com.saucedemo.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.saucedemo.constants.ErrorMessages;
import com.saucedemo.constants.URLs;
import com.saucedemo.runners.PerformanceTestRunner;

public class LoginPage extends BasePage {

    private final By usernameInput = By.cssSelector("[data-test='username']");
    private final By passwordInput = By.cssSelector("[data-test='password']");
    private final By loginButton = By.cssSelector("[data-test='login-button']");
    private final By errorMessage = By.cssSelector("[data-test='error']");
    
    public LoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void navigateTo() {
        driver.get(URLs.BASE_URL);
    }

    public void enterUsername(String username) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(usernameInput));
        element.clear();  
        element.sendKeys(username);
    }
    
    public void enterPassword(String password) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        element.clear(); 
        element.sendKeys(password);
    }
    
    public void clickLoginButton() {
        PerformanceTestRunner.startTimer();
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        PerformanceTestRunner.stopTimer();
    }
    
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
    
    public String getErrorMessage() {
        return waitForElement(errorMessage).getText();
    }
    
    public boolean isErrorMessageDisplayed() {
        return !driver.findElements(errorMessage).isEmpty();
    }
    
    public boolean hasInvalidCredentialsError() {
        return getErrorMessage().equals(ErrorMessages.INVALID_CREDENTIALS);
    }
    
    public boolean hasLockedOutUserError() {
        return getErrorMessage().equals(ErrorMessages.LOCKED_OUT_USER);
    }
    
    public boolean isOnLoginPage() {
        return isOnPage(URLs.BASE_URL);
    }

    public boolean hasRequiredFieldError() {
        return getErrorMessage().equals(ErrorMessages.REQUIRED_FIELD);
    }
}
