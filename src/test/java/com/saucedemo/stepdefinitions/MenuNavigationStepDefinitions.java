package com.saucedemo.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.saucedemo.config.WebDriverConfig;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class MenuNavigationStepDefinitions {
    
    private final WebDriver driver;
    private final InventoryPage inventoryPage;
    private final LoginPage loginPage;

    public MenuNavigationStepDefinitions() {
        this.driver = WebDriverConfig.getDriver();
        this.inventoryPage = new InventoryPage(driver);
        this.loginPage = new LoginPage(driver);
    }

    @And("I click the menu button")
    public void clickOnMenuButton() {
        inventoryPage.isOnInventoryPage();
        inventoryPage.clickMenuButton();
    }

    @And("I click on {string}")
    public void clickOnOption(String option) {
        inventoryPage.clickMenuOption(option);
    }
    
    //     I should be redirected to the login page
    @Then("I should be redirected to the {string} page")
    public void shouldBeRedirectedToPage(String page) {
        switch (page) {
            case "login":
                System.out.println("I should be redirected to the login page");
                assert loginPage.isOnLoginPage() : "I should be redirected to the login page";
                break;
            case "inventory":
                assert inventoryPage.isOnInventoryPage() : "I should be redirected to the inventory page";
                break;
            case "about":
                assert inventoryPage.isOnAboutPage() : "I should be redirected to the about page";
                break;
            default:
                throw new IllegalArgumentException("Invalid page: " + page);
        }
    }

    @Then("my cart should be empty")
    public void myCartShouldBeEmpty() {
        assert inventoryPage.getCartItemCount() == 0 : "My cart should be empty";
    }


}
