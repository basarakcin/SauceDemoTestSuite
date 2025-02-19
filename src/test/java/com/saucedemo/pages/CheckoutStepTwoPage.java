package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.saucedemo.constants.URLs;

public class CheckoutStepTwoPage extends BasePage {

    private final By finishButton = By.id("finish");
    private final By cancelButton = By.id("cancel");
    private final By subtotalLabel = By.className("summary_subtotal_label");
    private final By taxLabel = By.className("summary_tax_label");
    private final By totalLabel = By.className("summary_total_label");
    private final By itemTotal = By.cssSelector(".summary_subtotal_label");
    private final By tax = By.cssSelector(".summary_tax_label");
    private final By total = By.cssSelector(".summary_total_label");
    
    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }
    
    public boolean isOnCheckoutStepTwo() {
        return isOnPage(URLs.CHECKOUT_STEP_TWO_URL);
    }
    
    public void clickFinish() {
        waitForElement(finishButton).click();
    }
    
    public void clickCancel() {
        waitForElement(cancelButton).click();
    }
    
    public double getItemTotal() {
        String totalText = waitForElement(itemTotal).getText();
        return Double.parseDouble(totalText.replaceAll("[^0-9.]", ""));
    }
    
    public double getTaxAmount() {
        String taxText = waitForElement(tax).getText();
        return Double.parseDouble(taxText.replaceAll("[^0-9.]", ""));
    }
    
    public double getTotalAmount() {
        String totalText = waitForElement(total).getText();
        return Double.parseDouble(totalText.replaceAll("[^0-9.]", ""));
    }
    
    public boolean verifyTotalCalculation() {
        double itemTotal = getItemTotal();
        double tax = getTaxAmount();
        double expectedTotal = itemTotal + tax;
        double actualTotal = getTotalAmount();
        return Math.abs(expectedTotal - actualTotal) < 0.01; // Using delta for double comparison
    }
}
