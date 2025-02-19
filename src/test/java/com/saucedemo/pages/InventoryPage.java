package com.saucedemo.pages;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.saucedemo.constants.Products;
import com.saucedemo.constants.URLs;

public class InventoryPage extends BasePage {

    private final By productSort = By.cssSelector("[data-test='product-sort-container']");
    private final By inventoryItems = By.cssSelector("[data-test='inventory-item']");
    private final By backToProductsButton = By.cssSelector("[data-test='back-to-products']");
    private final By productName = By.cssSelector("[data-test='inventory-item-name']");
    private final By productPrice = By.cssSelector("[data-test='inventory-item-price']");
    private final By productDescription = By.cssSelector("[data-test='inventory-item-desc']");

    protected static final By addToCartButtons = By.cssSelector("[data-test^='add-to-cart']");
    protected static final By removeButtons = By.cssSelector("[data-test^='remove']");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void sortProducts(String option) {
        try {
            WebElement sortDropdown = waitForElement(productSort);
            Select select = new Select(sortDropdown);
            String value;
            switch (option) {
                case "Name (A to Z)":
                    value = "az";
                    break;
                case "Name (Z to A)":
                    value = "za";
                    break;
                case "Price (low to high)":
                    value = "lohi";
                    break;
                case "Price (high to low)":
                    value = "hilo";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid sort option: " + option);
            }

            waitForElementToBeClickable(productSort);
            select.selectByValue(value);

            try {
                driver.switchTo().alert().accept();
                throw new RuntimeException("Sorting is broken for this user");
            } catch (org.openqa.selenium.NoAlertPresentException e) {
                waitForElementToBeVisible(productName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to sort products: " + e.getMessage());
        }
    }

    public boolean isSorted(String option) {
        waitForElementToBeClickable(productSort);

        List<WebElement> products = driver.findElements(inventoryItems);
        List<String> names = new ArrayList<>();
        List<BigDecimal> prices = new ArrayList<>();

        for (WebElement product : products) {
            try {
                WebElement nameElement = product.findElement(productName);
                WebElement priceElement = product.findElement(productPrice);

                wait.until(ExpectedConditions.visibilityOf(nameElement));
                wait.until(ExpectedConditions.visibilityOf(priceElement));

                String name = nameElement.getText().trim();
                String priceText = priceElement.getText().replace("$", "").trim();

                names.add(name);
                prices.add(new BigDecimal(priceText));

            } catch (Exception e) {
                System.out.println("Error processing item: " + e.getMessage());
                continue;
            }
        }

        if (names.isEmpty() || prices.isEmpty()) {
            return false;
        }

        boolean result = false;
        switch (option) {
            case "Name (A to Z)":
                result = isSortedAlphabetically(names, true);
                break;
            case "Name (Z to A)":
                result = isSortedAlphabetically(names, false);
                break;
            case "Price (low to high)":
                result = isSortedNumerically(prices, true);
                break;
            case "Price (high to low)":
                result = isSortedNumerically(prices, false);
                break;
        }

        return result;
    }

    private boolean isSortedAlphabetically(List<String> items, boolean ascending) {
        for (int i = 0; i < items.size() - 1; i++) {
            String current = items.get(i);
            String next = items.get(i + 1);
            int comparison = current.compareToIgnoreCase(next);

            if (ascending && comparison > 0) {
                return false;
            }
            if (!ascending && comparison < 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isSortedNumerically(List<BigDecimal> prices, boolean ascending) {
        for (int i = 0; i < prices.size() - 1; i++) {
            BigDecimal current = prices.get(i);
            BigDecimal next = prices.get(i + 1);
            int comparison = current.compareTo(next);

            if (ascending && comparison > 0) {
                return false;
            }
            if (!ascending && comparison < 0) {
                return false;
            }
        }
        return true;
    }

    public int getProductCount() {
        return driver.findElements(inventoryItems).size();
    }

    public void clickProductByName(String name) {
        List<WebElement> products = driver.findElements(productName);
        for (WebElement product : products) {
            if (product.getText().equals(name)) {
                product.click();
                return;
            }
        }
    }

    public void clickBackToProductsButton() {
        waitForElement(backToProductsButton).click();
    }

    public boolean isOnInventoryPage() {
        return isOnPage(URLs.INVENTORY_URL);
    }

    public boolean areAllProductDetailsCorrect() {
        List<WebElement> products = driver.findElements(inventoryItems);

        if (products.size() != Products.ALL_PRODUCTS.length) {
            return false;
        }

        for (WebElement product : products) {
            String name = product.findElement(productName).getText();
            Products.Product expectedProduct = findExpectedProduct(name);
            if (expectedProduct == null || !isProductDetailsCorrect(product, expectedProduct)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkName(WebElement product, Products.Product expectedProduct) {
        String actualName = product.findElement(productName).getText();
        return actualName.equals(expectedProduct.getName());
    }

    private boolean checkDescription(WebElement product, Products.Product expectedProduct) {
        String actualDesc = product.findElement(productDescription).getText();
        return actualDesc.equals(expectedProduct.getDescription());
    }

    private boolean checkPrice(WebElement product, Products.Product expectedProduct) {
        String actualPrice = product.findElement(productPrice).getText();
        return actualPrice.equals(expectedProduct.getPrice());
    }

    private boolean checkImage(WebElement product, Products.Product expectedProduct) {
        String actualName = product.findElement(productName).getText();
        String dataTestAttr = "inventory-item-" + actualName.toLowerCase().replace(" ", "-") + "-img";
        WebElement image = product.findElement(By.cssSelector("img[data-test='" + dataTestAttr + "']"));
        return image.isDisplayed() && image.getDomAttribute("src").equals(expectedProduct.getImageUrl());
    }

    private boolean isProductDetailsCorrect(WebElement product, Products.Product expectedProduct) {
        return checkName(product, expectedProduct)
                && checkDescription(product, expectedProduct)
                && checkPrice(product, expectedProduct)
                && checkImage(product, expectedProduct);
    }

    private Products.Product findExpectedProduct(String name) {
        for (Products.Product product : Products.ALL_PRODUCTS) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    public void addAllItemsToCart() {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        for (WebElement button : buttons) {
            waitForElementToBeClickable(button);
            button.click();
        }
    }

    public void removeAllItemsFromCart() {
        List<WebElement> removeFromCartButtons = driver.findElements(removeButtons);
        for (WebElement button : removeFromCartButtons) {
            button.click();
        }
    }

    public void goToProductDetailsPage(int productId) {
        List<WebElement> products = driver.findElements(inventoryItems);

        for (WebElement product : products) {
            WebElement titleLink = product.findElement(productName);
            if (titleLink != null) {
                titleLink.click();
                waitForElement(backToProductsButton);
                return;
            }
        }

        throw new NoSuchElementException("Could not find product with ID: " + productId);
    }

    public int getRemoveButtonCount() {
        return driver.findElements(removeButtons).size();
    }

    public boolean verifyAddToCartButton() {
        addAllItemsToCart();
        int cartCount = getCartItemCount();
        return cartCount == 1;
    }

    public boolean verifyRemoveFromCartButton() {
        removeAllItemsFromCart();
        int cartCount = getCartItemCount();
        return cartCount == 0;
    }

}
