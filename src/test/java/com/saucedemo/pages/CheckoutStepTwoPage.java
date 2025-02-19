package com.saucedemo.pages;

import java.math.BigDecimal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.saucedemo.constants.URLs;

public class CheckoutStepTwoPage extends BasePage {

    private final By finishButton = By.cssSelector("[data-test='finish']");
    private final By cancelButton = By.cssSelector("[data-test='cancel']");
   
    private final By itemTotal = By.cssSelector("[data-test='subtotal-label']");
    private final By tax = By.cssSelector("[data-test='tax-label']");
    private final By total = By.cssSelector("[data-test='total-label']");
    
    private final By cartItems = By.cssSelector("[data-test='inventory-item']");
    
    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }
    
    public boolean isOnCheckoutStepTwo() {
        return isOnPage(URLs.CHECKOUT_STEP_TWO_URL);
    }
    
    public int getNumberOfItemsInCart() {
        return driver.findElements(cartItems).size();
    }
    
    public void clickFinish() {
        waitForElement(finishButton).click();
    }
    
    public void clickCancel() {
        waitForElement(cancelButton).click();
    }
    
    public BigDecimal getItemTotal() {
        String totalText = waitForElement(itemTotal).getText();
        return new BigDecimal(totalText.replaceAll("[^0-9.]", ""));
    }
    
    public BigDecimal getTaxAmount() {
        String taxText = waitForElement(tax).getText();
        return new BigDecimal(taxText.replaceAll("[^0-9.]", ""));
    }
    
    public BigDecimal getTotalAmount() {
        String totalText = waitForElement(total).getText();
        return new BigDecimal(totalText.replaceAll("[^0-9.]", ""));
    }
    
    public boolean verifyTotalCalculation() {
        BigDecimal itemTotal = getItemTotal();
        BigDecimal tax = getTaxAmount();
        BigDecimal expectedTotal = itemTotal.add(tax);
        BigDecimal actualTotal = getTotalAmount();
        
        return expectedTotal.compareTo(actualTotal) == 0;
    }
}
