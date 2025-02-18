Feature: Shopping Cart Management
  As a user
  I want to add and remove items from my cart
  So that I can manage my purchases 

  Background:
    Given I am on the login page

  Scenario Outline: Add and then remove all items from the cart
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
