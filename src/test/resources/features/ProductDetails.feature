Feature: Product Details
  As a user
  I want to view product details correctly

  Background:
    Given I am on the login page


  Scenario Outline: Verify all product details
    When I login with "<VALID_USERNAME>" and "<VALID_PASSWORD>"
    Then I should see the inventory page
    And there should not be any visual failures
    And all product details should be displayed correctly

    Examples:
      | VALID_USERNAME          | VALID_PASSWORD |
      | standard_user           | secret_sauce   |
      | problem_user            | secret_sauce   |
      | performance_glitch_user | secret_sauce   |
      | error_user              | secret_sauce   |
      | visual_user             | secret_sauce   |