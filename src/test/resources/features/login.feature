@regression
Feature: Login Functionality

  Background:
    Given I am on the login page

  @smoke
  Scenario: Login with valid credentials
    When I login with "<VALID_USERNAME>" and "<VALID_PASSWORD>"
    Then I should see the inventory page
    And the average response time should be less than 1 second
    And there should not be any visual failures

    Examples:
      | VALID_USERNAME          | VALID_PASSWORD |
      | standard_user           | secret_sauce   |
      | problem_user            | secret_sauce   |
      | performance_glitch_user | secret_sauce   |
      | error_user              | secret_sauce   |
      | visual_user             | secret_sauce   |

  @smoke
  Scenario: Login as locked out user
    When I login with "<LOCKED_OUT_USER>" and "<VALID_PASSWORD>"
    Then I should see an error message indicating locked out user

    Examples:
      | LOCKED_OUT_USER | VALID_PASSWORD |
      | locked_out_user | secret_sauce   |

  @smoke
  Scenario: Login with invalid credentials
    When I login with "<INVALID_USERNAME>" and "<INVALID_PASSWORD>"
    Then I should see an error message indicating invalid credentials

    Examples:
      | INVALID_USERNAME | INVALID_PASSWORD |
      | standard_user    | basar_akcin      |
      | basar_akcin      | secret_sauce     |

  @smoke
  Scenario: Login with empty username
    When I login with "<EMPTY_USERNAME>" and "<INVALID_PASSWORD>"
    Then I should see an error message indicating required field

    Examples:
      | EMPTY_USERNAME | INVALID_PASSWORD |
      |                | basar_akcin      |
