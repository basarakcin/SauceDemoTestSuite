@regression
Feature: Footer
  As a user
  I want to see and use the footer elements
  So that I can access social media links and copyright information

  Background:
    Given I am on the login page

  Scenario Outline: Verify social media links in footer
    When I login with "<VALID_USERNAME>" and "<VALID_PASSWORD>"
    Then I should see the inventory page
    And the footer should contain links to "Twitter", "Facebook" and "LinkedIn"
    And the social media links should point to the correct URLs
    And the copyright text should be correct

    Examples:
      | VALID_USERNAME          | VALID_PASSWORD |
      | standard_user           | secret_sauce   |
      | problem_user            | secret_sauce   |
      | performance_glitch_user | secret_sauce   |
      | error_user              | secret_sauce   |
      | visual_user             | secret_sauce   |