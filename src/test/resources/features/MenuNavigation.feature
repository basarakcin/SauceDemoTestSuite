@regression
Feature: Menu Navigation
  As a user
  I want to use the menu options
  So that I can navigate through the application

  Background:
    Given I am on the login page

  @smoke
  Scenario Outline: Access all items from menu
    When I login with "<username>" and "<password>"
    And I click the menu button
    And I click on "All Items"
    Then I should see the inventory page

    Examples:
      | username                | password     |
      | standard_user           | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |
      | error_user              | secret_sauce |
      | visual_user             | secret_sauce |

  @smoke
  Scenario Outline: Logout through menu
    When I login with "<username>" and "<password>"
    And I click the menu button
    And I click on "Logout"
    Then I should be redirected to the "login" page

    Examples:
      | username                | password     |
      | standard_user           | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |
      | error_user              | secret_sauce |
      | visual_user             | secret_sauce |

  Scenario Outline: Reset app state
    When I login with "<username>" and "<password>"
    And I add all available items to my cart
    And I click the menu button
    And I click on "Reset App State"
    Then my cart should be empty

    Examples:
      | username                | password     |
      | standard_user           | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |
      | error_user              | secret_sauce |
      | visual_user             | secret_sauce |

  Scenario Outline: Visit About Page
    When I login with "<username>" and "<password>"
    And I click the menu button
    And I click on "About"
    Then I should be redirected to the "about" page

    Examples:
      | username                | password     |
      | standard_user           | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |
      | error_user              | secret_sauce |
      | visual_user             | secret_sauce |
