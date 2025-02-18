package com.saucedemo.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    // Header elements
    protected final By menuButton = By.cssSelector("[data-test='open-menu']");
    protected final By cartIcon = By.cssSelector("[data-test='shopping-cart-link']");
    protected final By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");

    
    // Menu elements
    protected final By menuAllItems = By.cssSelector("[data-test='inventory-sidebar-link']");
    protected final By menuAbout = By.cssSelector("[data-test='about-sidebar-link']");
    protected final By menuLogout = By.cssSelector("[data-test='logout-sidebar-link']");
    protected final By menuResetAppState = By.cssSelector("[data-test='reset-sidebar-link']");

    // Footer elements
    protected final By twitterLink = By.cssSelector("[data-test='twitter-social-link']");
    protected final By facebookLink = By.cssSelector("[data-test='facebook-social-link']");
    protected final By linkedinLink = By.cssSelector("[data-test='linkedin-social-link']");
    protected final By footerCopy = By.cssSelector("[data-test='footer-copy']");


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    
    public void clickMenuButton() {
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
    }
    
    public void clickCartIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }
    
    public void logout() {
        clickMenuButton();
        wait.until(ExpectedConditions.elementToBeClickable(menuLogout)).click();
    }
    
    public void resetAppState() {
        clickMenuButton();
        wait.until(ExpectedConditions.elementToBeClickable(menuResetAppState)).click();
    }
    
    public void navigateToAllItems() {
        clickMenuButton();
        wait.until(ExpectedConditions.elementToBeClickable(menuAllItems)).click();
    }
    
    public void navigateToAbout() {
        clickMenuButton();
        wait.until(ExpectedConditions.elementToBeClickable(menuAbout)).click();
    }
    
    public boolean isOnPage(String expectedUrl) {
        String currentUrl = driver.getCurrentUrl().replaceAll("/$", "");
        expectedUrl = expectedUrl.replaceAll("/$", "");
        return currentUrl.equals(expectedUrl);
    }
    
    public boolean hasNoVisualErrors() {
        return driver.findElements(By.className("btn_visual_failure")).isEmpty() &&
               driver.findElements(By.className("visual_failure")).isEmpty();
    }
    
    protected void waitForElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    protected void waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public int getCartItemCount() {
        try {
            WebElement badge = waitForElement(this.cartBadge);
            return Integer.parseInt(badge.getText());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return 0; // Return 0 if badge is not present (cart is empty)
        }
    }
} 
