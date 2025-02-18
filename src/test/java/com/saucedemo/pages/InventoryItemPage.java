package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.saucedemo.constants.URLs;

public class InventoryItemPage extends InventoryPage {
    
    private final By backToProductsButton = By.cssSelector("[data-test='back-to-products']");
    
    public InventoryItemPage(WebDriver driver) {
        super(driver);
    }
    
    public void clickBackToProducts() {
        waitForElement(backToProductsButton).click();
    }
    
    public void addToCart() {
        waitForElement(InventoryPage.addToCartButtons).click();
    }

    public void removeFromCart() {
        waitForElement(InventoryPage.removeButtons).click();
    }

    // public boolean verifyProductDetails() {
    //     return waitForElement(productName).isDisplayed() &&
    //            waitForElement(productDesc).isDisplayed() &&
    //            waitForElement(productPrice).isDisplayed();
    // }
    
    public boolean isOnProductDetailsPage() {
        return isOnPage(URLs.INVENTORY_ITEM_URL);
    }
}
