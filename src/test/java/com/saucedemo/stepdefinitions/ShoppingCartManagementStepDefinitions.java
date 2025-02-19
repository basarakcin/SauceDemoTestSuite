package com.saucedemo.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.saucedemo.config.WebDriverConfig;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutStepOnePage;
import com.saucedemo.pages.InventoryItemPage;
import com.saucedemo.pages.InventoryPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ShoppingCartManagementStepDefinitions {
    private final WebDriver driver;
    private final InventoryPage inventoryPage;
    private final InventoryItemPage inventoryItemPage;
    private final CartPage cartPage;
    private final CheckoutStepOnePage checkoutStepOnePage;

    public ShoppingCartManagementStepDefinitions() {
        this.driver = WebDriverConfig.getDriver();
        this.inventoryPage = new InventoryPage(driver);
        this.inventoryItemPage = new InventoryItemPage(driver);
        this.cartPage = new CartPage(driver);
        this.checkoutStepOnePage = new CheckoutStepOnePage(driver);
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

    @When("I open {string}'s page")
    public void i_open_string_s_page(String productName) {
        inventoryPage.clickProductByName(productName);
    }

    @Then("I verify the add to cart button works as expected")
    public void i_verify_the_add_to_cart_button_works_as_expected() {
        assert inventoryItemPage.verifyAddToCartButton() : "The add to cart button does not work as expected";
    }

    @And("I verify the remove from cart button works as expected")
    public void i_verify_the_remove_from_cart_button_works_as_expected() {
        assert inventoryItemPage.verifyRemoveFromCartButton() : "The remove from cart button does not work as expected";
    }

    @And("I click the back to products button")
    public void i_click_the_back_to_products_button() {
        inventoryItemPage.clickBackToProductsButton();
    }

    @And("I go to the cart page")
    public void i_go_to_the_cart_page() {
        inventoryPage.clickCartIcon();
  
    }

    @Then("I should have the same number of items in my cart as the cart badge")
    public void i_should_have_the_same_number_of_items_in_my_cart_as_the_cart_badge() {
        int cartCount = inventoryPage.getCartItemCount();
        int cartBadgeCount = inventoryPage.getCartItemCount();
        assert cartCount == cartBadgeCount : 
            String.format("Expected %d items in cart but found %d", cartBadgeCount, cartCount);
    }
    

}
