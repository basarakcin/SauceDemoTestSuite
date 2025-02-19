@regression
Feature: Sort Items
  As a user
  I want to sort items in the inventory
  So that I can view them in my preferred order

  Background:
    Given I am on the login page

  Scenario Outline: Sort items by a specific option
    When I login with "<VALID_USERNAME>" and "<VALID_PASSWORD>"
    Then I should see the inventory page
    And the items should be able to be sorted correctly

    Examples:
      | VALID_USERNAME          | VALID_PASSWORD |
      | standard_user           | secret_sauce   |
      | problem_user            | secret_sauce   |
      | performance_glitch_user | secret_sauce   |
      | error_user              | secret_sauce   |    
      | visual_user             | secret_sauce   |
