package com.saucedemo.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.saucedemo.config.WebDriverConfig;
import com.saucedemo.pages.InventoryPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class ShoppingCartManagementStepDefinitions {
    private final WebDriver driver;
    private final InventoryPage inventoryPage;


    public ShoppingCartManagementStepDefinitions() {
        this.driver = WebDriverConfig.getDriver();
        this.inventoryPage = new InventoryPage(driver);
    }

    @When("I add all available items to my cart")
    public void i_add_all_available_items_to_my_cart() {
        inventoryPage.addAllItemsToCart();
    }

    @Then("the cart count should equal the total number of items")
    public void the_cart_count_should_equal_the_total_number_of_items() {
        int cartCount = inventoryPage.getCartItemCount();
        int totalItems = inventoryPage.getProductCount();
        assert cartCount == totalItems;
    }

    @And("I should see remove buttons for each item")
    public void i_should_see_remove_buttons_for_each_item() {
        int totalItems = inventoryPage.getProductCount();
        int removeButtonCount = inventoryPage.getRemoveButtonCount();
        assert removeButtonCount == totalItems : 
            String.format("Expected %d remove buttons but found %d", totalItems, removeButtonCount);
    }

    @When("I remove all items from my cart")
    public void i_remove_all_items_from_my_cart() {
        inventoryPage.removeAllItemsFromCart();
    }

    @Then("the cart count should be 0")
    public void the_cart_count_should_be_0() {
        int cartCount = inventoryPage.getCartItemCount();
        assert cartCount == 0;
    }
}
