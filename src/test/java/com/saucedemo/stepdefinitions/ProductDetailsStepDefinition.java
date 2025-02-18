package com.saucedemo.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.saucedemo.config.WebDriverConfig;
import com.saucedemo.pages.InventoryPage;

import io.cucumber.java.en.And;    

public class ProductDetailsStepDefinition {

    private final WebDriver driver;
    private final InventoryPage inventoryPage;

    public ProductDetailsStepDefinition() {
        this.driver = WebDriverConfig.getDriver();
        this.inventoryPage = new InventoryPage(driver);
    }

    @And ("all product details should be displayed correctly")
    public void all_product_details_should_be_displayed_correctly() {
        assert inventoryPage.areAllProductDetailsCorrect() : "Product details do not match the expected values";
    }

}
