Feature: Checkout

  Background:
    Given I am on the login page

  Scenario Outline: Checkout process
    When I login with "<VALID_USERNAME>" and "<VALID_PASSWORD>"
    Then I should see the inventory page

    When I add all available items to my cart
    And I go to the cart page
    And I press the Checkout button
    Then I should see the checkout step one page

    When I fill the checkout info with "<FIRST_NAME>" and "<LAST_NAME>" and "<POSTAL_CODE>"
    And I press the Continue button
    Then I should see the checkout step two page
    And there should not be any visual failures

    Examples:
      | VALID_USERNAME          | VALID_PASSWORD | FIRST_NAME | LAST_NAME | POSTAL_CODE |
      | standard_user           | secret_sauce   | Basar      | Akcin     |       81925 |
      | standard_user           | secret_sauce   |            | Akcin     |       81925 |
      | standard_user           | secret_sauce   | Basar      | Akcin     |             |
      | problem_user            | secret_sauce   | Basar      | Akcin     |       81925 |
      | problem_user            | secret_sauce   |            | Akcin     |       81925 |   
      | problem_user            | secret_sauce   | Basar      | Akcin     |             |
      | performance_glitch_user | secret_sauce   | Basar      | Akcin     |       81925 |
      | performance_glitch_user | secret_sauce   |            | Akcin     |       81925 |
      | performance_glitch_user | secret_sauce   | Basar      | Akcin     |             |
      | error_user              | secret_sauce   | Basar      | Akcin     |       81925 |
      | error_user              | secret_sauce   |            | Akcin     |       81925 |
      | error_user              | secret_sauce   | Basar      | Akcin     |             |
      | visual_user             | secret_sauce   | Basar      | Akcin     |       81925 |
      | visual_user             | secret_sauce   |            | Akcin     |       81925 |
      | visual_user             | secret_sauce   | Basar      | Akcin     |             |

  Scenario Outline: Checkout process cancel at step one
    When I login with "<VALID_USERNAME>" and "<VALID_PASSWORD>"
    Then I should see the inventory page

    When I add all available items to my cart
    And I go to the cart page
    And I press the Checkout button
    Then I should see the checkout step one page
    And there should not be any visual failures

    When I press the Cancel button
    Then I should see the cart page

    Examples:
      | VALID_USERNAME          | VALID_PASSWORD |
      | standard_user           | secret_sauce   |
      | problem_user            | secret_sauce   |
      | performance_glitch_user | secret_sauce   |
      | error_user              | secret_sauce   |
       | visual_user             | secret_sauce   |

    Scenario Outline: Checkout process cancel at step two
    When I login with "<VALID_USERNAME>" and "<VALID_PASSWORD>"
    Then I should see the inventory page

    When I add all available items to my cart
    And I go to the cart page
    And I press the Checkout button
    Then I should see the checkout step one page

    When I fill the checkout info with "<FIRST_NAME>" and "<LAST_NAME>" and "<POSTAL_CODE>"
    And I press the Continue button
    Then I should see the checkout step two page

    When I press the Cancel button
    Then I should see the inventory page

    Examples:
      | VALID_USERNAME          | VALID_PASSWORD | FIRST_NAME | LAST_NAME | POSTAL_CODE |
      | standard_user           | secret_sauce   | Basar      | Akcin     |       81925 |
      | problem_user            | secret_sauce   | Basar      | Akcin     |       81925 |
      | performance_glitch_user | secret_sauce   | Basar      | Akcin     |       81925 |
      | error_user              | secret_sauce   | Basar      | Akcin     |       81925 |
      | visual_user             | secret_sauce   | Basar      | Akcin     |       81925 |
  
  Scenario Outline: Finish the checkout process
    When I login with "<VALID_USERNAME>" and "<VALID_PASSWORD>"
    Then I should see the inventory page

    When I add all available items to my cart
    And I go to the cart page
    And I press the Checkout button
    Then I should see the checkout step one page

    When I fill the checkout info with "<FIRST_NAME>" and "<LAST_NAME>" and "<POSTAL_CODE>"
    And I press the Continue button
    Then I should see the checkout step two page
    And I should see have the same number of items in my cart as the cart badge
    And the total price of the items should be displayed correctly

    When I press the Finish button
    Then I should see the checkout complete page
    And there should not be any visual failures

    Examples:
      | VALID_USERNAME          | VALID_PASSWORD | FIRST_NAME | LAST_NAME | POSTAL_CODE |
      | standard_user           | secret_sauce   | Basar      | Akcin     |       81925 |
      | problem_user            | secret_sauce   | Basar      | Akcin     |       81925 |
      | performance_glitch_user | secret_sauce   | Basar      | Akcin     |       81925 |
      | error_user              | secret_sauce   | Basar      | Akcin     |       81925 |
      | visual_user             | secret_sauce   | Basar      | Akcin     |       81925 |