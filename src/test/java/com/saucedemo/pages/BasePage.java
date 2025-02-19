package com.saucedemo.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.saucedemo.constants.URLs;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    // Header elements
    protected final By menuButton = By.id("react-burger-menu-btn");
    protected final By cartIcon = By.cssSelector("[data-test='shopping-cart-link']");
    protected final By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");

    
    // Menu elements
    protected final By menuAllItems = By.cssSelector("[data-test='inventory-sidebar-link']");
    protected final By menuAbout = By.cssSelector("[data-test='about-sidebar-link']");
    protected final By menuLogout = By.cssSelector("[data-test='logout-sidebar-link']");
    protected final By menuResetAppState = By.cssSelector("[data-test='reset-sidebar-link']");

    // Footer elements
    protected final By twitterLink = By.cssSelector("[data-test='social-twitter']");
    protected final By facebookLink = By.cssSelector("[data-test='social-facebook']");
    protected final By linkedinLink = By.cssSelector("[data-test='social-linkedin']");
    protected final By footerCopy = By.cssSelector("[data-test='footer-copy']");


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    
    public void clickMenuButton() {
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
        
    }

    public void clickMenuOption(String option) {
        switch (option) {
            case "All Items":
                waitForElementToBeClickableAndClick(menuAllItems);
                break;
            case "About":
                waitForElementToBeClickableAndClick(menuAbout);
                break;
            case "Logout":
                waitForElementToBeClickableAndClick(menuLogout);
                break;
            case "Reset App State":
                waitForElementToBeClickableAndClick(menuResetAppState);
                break;
            default:
                throw new IllegalArgumentException("Invalid menu option: " + option);
        }
    }

    public void clickCartIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
        isOnPage(URLs.CART_URL);
    }
    
    
    public boolean isOnPage(String expectedUrl) {
        String currentUrl = driver.getCurrentUrl().replaceAll("/$", "");
        expectedUrl = expectedUrl.replaceAll("/$", "");
        return currentUrl.equals(expectedUrl);
    }

    public boolean isOnAboutPage() {
        return isOnPage(URLs.ABOUT_URL);
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

    protected void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    protected void waitForElementToBeClickableAndClick(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public int getCartItemCount() {
        if (driver.findElements(cartBadge).isEmpty()) {
            return 0; 
        }
        
        WebElement badge = driver.findElement(cartBadge);
        return Integer.parseInt(badge.getText());
    }
} 
