Feature: Cart Operations
  As a user
  I want to manage items in my cart

  Background:
    Given I am on the login page

  Scenario Outline: Adding and removing all items from inventory page
    When I login as "<user_type>" user
    And I add all items to the cart
    Then I have 6 items in my cart
    When I remove all items from the cart
    Then my cart should be empty

    Examples:
      | user_type          |
      | standard           |
      | problem            |
      | performance_glitch |
      | error              |
      | visual             |

  Scenario Outline: Managing items from product details page
    When I login as "<user_type>" user
    Then I go to the product details page for "<product_id>"
    And I add the item to the cart
    Then I have 1 item in my cart
    When I remove the item
    Then my cart should be empty

    Examples:
      | user_type          | product_id |
      | standard           | 0          |
      | standard           | 1          |
      | problem            | 0          |
      | problem            | 1          |
      | performance_glitch | 0          |
      | performance_glitch | 1          |
      | error             | 0           |
      | error             | 1           |
      | visual            | 0           |
      | visual            | 1           |

  Scenario Outline: Managing items from cart page
    When I login as "<user_type>" user
    And I add all items to the cart
    And I navigate to the cart page
    When I remove all items from the cart
    Then my cart should be empty

    Examples:
      | user_type          |
      | standard           |
      | problem            |
      | performance_glitch |
      | error              |
      | visual             | 