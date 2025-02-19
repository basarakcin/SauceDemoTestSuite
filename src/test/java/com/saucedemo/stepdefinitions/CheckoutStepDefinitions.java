package com.saucedemo.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.saucedemo.config.WebDriverConfig;
import com.saucedemo.constants.ErrorMessages;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutCompletePage;
import com.saucedemo.pages.CheckoutStepOnePage;
import com.saucedemo.pages.CheckoutStepTwoPage;
import com.saucedemo.pages.InventoryItemPage;
import com.saucedemo.pages.InventoryPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckoutStepDefinitions {

    private final CheckoutStepOnePage checkoutStepOnePage;
    private final WebDriver driver;
    private final InventoryPage inventoryPage;
    private final InventoryItemPage inventoryItemPage;
    private final CartPage cartPage;
    private final CheckoutStepTwoPage checkoutStepTwoPage;
    private final CheckoutCompletePage checkoutCompletePage;

    public CheckoutStepDefinitions() {
        this.driver = WebDriverConfig.getDriver();
        this.inventoryPage = new InventoryPage(driver);
        this.inventoryItemPage = new InventoryItemPage(driver);
        this.cartPage = new CartPage(driver);
        this.checkoutStepOnePage = new CheckoutStepOnePage(driver);
        this.checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        this.checkoutCompletePage = new CheckoutCompletePage(driver);
    }

    @And("I press the Checkout button")
    public void i_press_the_checkout_button() {
        cartPage.clickCheckout();
    }

    @Then("I should see the checkout step one page")
    public void i_should_see_the_checkout_step_one_page() {
        assert checkoutStepOnePage.isOnCheckoutStepOne() : "I should see the checkout step one page";
    }

    @When("I fill the checkout info with {string} and {string} and {string}")
    public void i_fill_the_checkout_info_with_and_and(String firstName, String lastName, String postalCode) {
        checkoutStepOnePage.fillCheckoutInfo(firstName, lastName, postalCode);  
    }

    @And("I press the Continue button")
    public void i_press_the_continue_button() {
        checkoutStepOnePage.clickContinue();
    }

    @And("I press the Cancel button")
    public void i_press_the_cancel_button() {
        checkoutStepOnePage.clickCancel();
    }

    @Then("I should see the checkout step two page")
    public void i_should_see_the_checkout_step_two_page() {
        if (checkoutStepOnePage.isOnCheckoutStepOne()) {
            if (checkoutStepOnePage.isErrorMessageDisplayed()) {
                String actualError = checkoutStepOnePage.getErrorMessage();
                String expectedFirstNameError = ErrorMessages.CHECKOUT_STEP_ONE_FIRST_NAME_ERROR;
                String expectedPostalCodeError = ErrorMessages.CHECKOUT_STEP_ONE_POSTAL_CODE_ERROR;
                
                if (actualError.equals(expectedFirstNameError) || actualError.equals(expectedPostalCodeError)) {
                    throw new AssertionError("Validation failed: " + actualError);
                } else {
                    throw new AssertionError("Unexpected error message: " + actualError);
                }
            }
        }
        
        assert checkoutStepTwoPage.isOnCheckoutStepTwo() : "Failed to navigate to checkout step two page";
    }
    
    @Then("I should see the cart page")
    public void i_should_see_the_cart_page() {
        assert cartPage.isOnCartPage() : "I should see the cart page";
    }
    
    @And("I should see have the same number of items in my cart as the cart badge")
    public void i_should_see_have_the_same_number_of_items_in_my_cart_as_the_cart_badge() {
        assert checkoutStepTwoPage.getNumberOfItemsInCart() == checkoutStepTwoPage.getCartItemCount() : "I should see have the same number of items in my cart as the cart badge";
    }

    @And("the total price of the items should be displayed correctly")
    public void the_total_price_of_the_items_should_be_displayed_correctly() {
        assert checkoutStepTwoPage.verifyTotalCalculation() : "The total price of the items should be displayed correctly";
    }

    @And("I press the Finish button")
    public void i_press_the_finish_button() {
        checkoutStepTwoPage.clickFinish();
    }

    @Then("I should see the checkout complete page")
    public void i_should_see_the_checkout_complete_page() {
        assert checkoutCompletePage.isOnCheckoutComplete() : "I should see the checkout complete page";
    }

    
}
