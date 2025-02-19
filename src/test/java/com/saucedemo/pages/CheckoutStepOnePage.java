package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.saucedemo.constants.URLs;

public class CheckoutStepOnePage extends BasePage {

    private final By firstNameInput = By.cssSelector("[data-test='firstName']");
    private final By lastNameInput = By.cssSelector("[data-test='lastName']");
    private final By postalCodeInput = By.cssSelector("[data-test='postalCode']");
    private final By continueButton = By.cssSelector("[data-test='continue']");
    private final By cancelButton = By.cssSelector("[data-test='cancel']");
    private final By errorMessage = By.cssSelector("[data-test='error']");
    
    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }
    
    public boolean isOnCheckoutStepOne() {
        return isOnPage(URLs.CHECKOUT_STEP_ONE_URL);
    }
    
    public void enterFirstName(String firstName) {
        waitForElement(firstNameInput).sendKeys(firstName);
    }
    
    public void enterLastName(String lastName) {
        waitForElement(lastNameInput).sendKeys(lastName);
    }
    
    public void enterPostalCode(String postalCode) {
        waitForElement(postalCodeInput).sendKeys(postalCode);
    }
    
    public void clickContinue() {
        waitForElement(continueButton).click();
    }
    
    public void clickCancel() {
        waitForElement(cancelButton).click();
    }
    
    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
    }
    
    public String getErrorMessage() {
        return waitForElement(errorMessage).getText();
    }
    
    public boolean isErrorMessageDisplayed() {
        return !driver.findElements(errorMessage).isEmpty();
    }
}
