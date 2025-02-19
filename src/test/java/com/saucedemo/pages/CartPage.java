package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.saucedemo.constants.URLs;

public class CartPage extends BasePage {

    private final By cartItems = By.className("cart_item");
    private final By removeButtons = By.cssSelector("[data-test^='remove']");
    private final By continueShoppingButton = By.cssSelector("[data-test='continue-shopping']");
    private final By checkoutButton = By.cssSelector("[data-test='checkout']");

    
    public CartPage(WebDriver driver) {
        super(driver);
    }
    
    public boolean isOnCartPage() {
        return isOnPage(URLs.CART_URL);
    }
    
    public void clickContinueShopping() {
        waitForElementToBeClickableAndClick(continueShoppingButton);
    }
    
    public void clickCheckout() {
        waitForElementToBeClickableAndClick(checkoutButton);
    }
    
    public void removeItem() {
        waitForElementToBeClickableAndClick(removeButtons);
    }
    
    public int getItemCount() {
        return driver.findElements(cartItems).size();
    }
    
}
