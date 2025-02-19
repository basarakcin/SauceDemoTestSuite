Feature: Shopping Cart Management
  As a user
  I want to add and remove items from my cart
  So that I can manage my purchases 

  Background:
    Given I am on the login page

  Scenario Outline: Add and then remove all items from inventory page
    When I login with "<VALID_USERNAME>" and "<VALID_PASSWORD>"
    Then I should see the inventory page
    When I add all available items to my cart
    Then the cart count should equal the total number of items
    And I should see remove buttons for each item
    When I remove all items from my cart
    Then the cart count should be 0

    Examples:
      | VALID_USERNAME          | VALID_PASSWORD |
      | standard_user           | secret_sauce   |
      | problem_user            | secret_sauce   |
      | performance_glitch_user | secret_sauce   |
      | error_user              | secret_sauce   |
      | visual_user             | secret_sauce   |

  Scenario: Add and remove items from individual item pages
    When I login with "<VALID_USERNAME>" and "<VALID_PASSWORD>"
    Then I should see the inventory page
    When I open "<PRODUCT>"'s page
    Then I verify the add to cart button works as expected
    And I verify the remove from cart button works as expected
    And I click the back to products button
    Then I should see the inventory page
      

    Examples:
      | VALID_USERNAME | VALID_PASSWORD | PRODUCT |
      | standard_user  | secret_sauce   | Sauce Labs Backpack |
      | standard_user  | secret_sauce   | Sauce Labs Bike Light |
      | standard_user  | secret_sauce   | Sauce Labs Bolt T-Shirt |
      | standard_user  | secret_sauce   | Sauce Labs Fleece Jacket |
      | standard_user  | secret_sauce   | Sauce Labs Onesie |
      | standard_user  | secret_sauce   | Test.allTheThings() T-Shirt (Red) |
      | problem_user   | secret_sauce   | Sauce Labs Backpack |
      | problem_user   | secret_sauce   | Sauce Labs Bike Light |
      | problem_user   | secret_sauce   | Sauce Labs Bolt T-Shirt |
      | problem_user   | secret_sauce   | Sauce Labs Fleece Jacket |
      | problem_user   | secret_sauce   | Sauce Labs Onesie |
      | problem_user   | secret_sauce   | Test.allTheThings() T-Shirt (Red) |
      | performance_glitch_user   | secret_sauce   | Sauce Labs Backpack |
      | performance_glitch_user   | secret_sauce   | Sauce Labs Bike Light |
      | performance_glitch_user   | secret_sauce   | Sauce Labs Bolt T-Shirt |
      | performance_glitch_user   | secret_sauce   | Sauce Labs Fleece Jacket |
      | performance_glitch_user   | secret_sauce   | Sauce Labs Onesie |
      | performance_glitch_user   | secret_sauce   | Test.allTheThings() T-Shirt (Red) |
      | error_user   | secret_sauce   | Sauce Labs Backpack |
      | error_user   | secret_sauce   | Sauce Labs Bike Light |
      | error_user   | secret_sauce   | Sauce Labs Bolt T-Shirt |
      | error_user   | secret_sauce   | Sauce Labs Fleece Jacket |
      | error_user   | secret_sauce   | Sauce Labs Onesie |
      | error_user   | secret_sauce   | Test.allTheThings() T-Shirt (Red) |
      | visual_user   | secret_sauce   | Sauce Labs Backpack |
      | visual_user   | secret_sauce   | Sauce Labs Bike Light |
      | visual_user   | secret_sauce   | Sauce Labs Bolt T-Shirt |
      | visual_user   | secret_sauce   | Sauce Labs Fleece Jacket |
      | visual_user   | secret_sauce   | Sauce Labs Onesie |
      | visual_user   | secret_sauce   | Test.allTheThings() T-Shirt (Red) |

 Scenario Outline: Add and then remove all items from cart page
    When I login with "<VALID_USERNAME>" and "<VALID_PASSWORD>"
    Then I should see the inventory page
    When I add all available items to my cart
    And I go to the cart page
    Then I should have the same number of items in my cart as the cart badge
    When I remove all items from my cart
    Then the cart count should be 0

    Examples:
      | VALID_USERNAME          | VALID_PASSWORD |
      | standard_user           | secret_sauce   |
      | problem_user            | secret_sauce   |
      | performance_glitch_user | secret_sauce   |
      | error_user              | secret_sauce   |
      | visual_user             | secret_sauce   |

 Scenario Outline: Add and then remove all items from cart page
    When I login with "<VALID_USERNAME>" and "<VALID_PASSWORD>"
    Then I should see the inventory page
    When I add all available items to my cart
    And I go to the cart page
    And I press the Checkout button
    Then I should see the checkout step one page

        Examples:
      | VALID_USERNAME          | VALID_PASSWORD |
      | standard_user           | secret_sauce   |
      | problem_user            | secret_sauce   |
      | performance_glitch_user | secret_sauce   |
      | error_user              | secret_sauce   |
      | visual_user             | secret_sauce   |