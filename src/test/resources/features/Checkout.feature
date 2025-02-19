Feature: Checkout

  Background:
    Given I am on the login page

 Scenario Outline: Go to checkout step one page
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