package com.saucedemo.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.saucedemo.config.WebDriverConfig;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.runners.PerformanceTestRunner;

import io.cucumber.java.en.And;

public class SortItemsStepDefinitions {

    private final WebDriver driver;
    private final InventoryPage inventoryPage;
    private final String[] sortOptions = {"Name (A to Z)", "Name (Z to A)", "Price (low to high)", "Price (high to low)"};

    public SortItemsStepDefinitions() {
        this.driver = WebDriverConfig.getDriver();
        this.inventoryPage = new InventoryPage(driver);
    }

    @And("the items should be able to be sorted correctly")
    public void the_items_should_be_able_to_be_sorted_correctly() {
        StringBuilder errorMessage = new StringBuilder();
        boolean allSortsPassed = true;
        

        for (String option : sortOptions) {
            PerformanceTestRunner.resetTimer();
            inventoryPage.sortProducts(option);
            boolean isSorted = inventoryPage.isSorted(option);
            if (!isSorted) {
                allSortsPassed = false;
                errorMessage.append(String.format("\nSort by '%s' failed.", option));
            }
        }

        if (!allSortsPassed) {
            throw new AssertionError("Sorting validation failed:" + errorMessage.toString());
        }
    }

}