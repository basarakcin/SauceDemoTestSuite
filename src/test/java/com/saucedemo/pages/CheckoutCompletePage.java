package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.saucedemo.constants.URLs;

public class CheckoutCompletePage extends BasePage {

    private final By title = By.cssSelector("[data-test='title']");
    private final By checkoutCompleteContainer = By.cssSelector("[data-test='checkout-complete-container']");
    private final By ponyExpress = By.cssSelector("[data-test='pony-express']");
    private final By thankYouHeader = By.cssSelector("[data-test='complete-header']");
    private final By completeText = By.cssSelector("[data-test='complete-text']");
    private final By backHomeButton = By.cssSelector("[data-test='back-to-products']");
    
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }
    
    public boolean isOnCheckoutComplete() {
        return isOnPage(URLs.CHECKOUT_COMPLETE_URL);
    }
    
    public void clickBackHome() {
        waitForElement(backHomeButton).click();
    }
    
    public String getThankYouMessage() {
        return waitForElement(thankYouHeader).getText();
    }
    
    public String getCompleteText() {
        return waitForElement(completeText).getText();
    }
    
    public boolean isOrderConfirmed() {
        return waitForElement(thankYouHeader).isDisplayed() &&
               waitForElement(completeText).isDisplayed() &&
               waitForElement(backHomeButton).isDisplayed() &&
               waitForElement(checkoutCompleteContainer).isDisplayed() &&
               waitForElement(ponyExpress).isDisplayed() &&
               waitForElement(title).isDisplayed();
    }
} 