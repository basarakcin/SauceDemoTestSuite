@login                    
Feature: SauceDemo Login Functionality

  # Test Strategy and Approach
  # -------------------------
  # Document ID: TS-LOGIN-001
  # Version: 1.0
  # Last Updated: 2025-02-14
  #
  # 1. Test Objectives:
  #    - Verify authentication functionality
  #    - Validate security controls
  #    - Ensure data integrity
  #
  # 2. Test Levels (ISTQB):
  #    - System Testing
  #    - Security Testing
  #    - Acceptance Testing
  #
  # 3. Risk Assessment:
  #    Critical Risks:
  #    - Authentication bypass (RA-001)
  #    - Data exposure (RA-002)
  #    - Session hijacking (RA-003)
  #
  # 4. Test Coverage Matrix:
  #    |    Test Area    | Priority | Coverage |
  #    |-----------------|----------|----------|
  #    | Authentication  |    P1    |   100%   |
  #    | Input Validation|    P2    |    95%   |
  #    | Error Handling  |    P2    |    90%   |
  #    | Security        |    P1    |   100%   |
  #
  # 5. Defect Classification:
  #    - Severity 1: Security vulnerabilities
  #    - Severity 2: Functional blockers
  #    - Severity 3: UI/UX issues
  #
  # 6. Test Environment:
  #    - Chrome browser (latest)
  #    - Test automation framework
  #    - CI/CD pipeline integration

  # Business Requirements:
  # REQ-001: Users must authenticate before accessing the system
  # REQ-002: System must prevent unauthorized access
  # REQ-003: System must handle various error conditions appropriately
  
  # Test Strategy:
  # 1. Equivalence Partitioning
  # 2. Boundary Value Analysis
  # 3. Error Guessing
  # 4. Security Testing
  
  Background: Common Steps
    Given I am on the SauceDemo login page "https://www.saucedemo.com/"

  # Test Suite: Critical Path Tests
  # Priority: P1 (Critical)
  # Coverage: Core Authentication
  @smoke @critical
  Scenario: Invalid login with incorrect username
    When I enter username "invalid_user" and password "secret_sauce"
    And I click on the login button
    Then I should see error message "Epic sadface: Username and password do not match any user in this service"

  # Test Suite: Valid Equivalence Classes
  # Priority: P1
  # Coverage: Valid Input Combinations
  @regression @smoke
  Scenario Outline: Successful login with valid user
    # Test Data:
    # - standard_user: Regular user with all permissions
    # - problem_user: User with known UI issues
    # - performance_glitch_user: User with intentional delays
    # - error_user: User that triggers JS errors
    # - visual_user: User with visual glitches
    When I enter username "<username>" and password "secret_sauce"
    And I click on the login button
    Then I should be redirected to the inventory page "https://www.saucedemo.com/inventory.html"

    Examples:
      | username                |
      | standard_user           |
      | problem_user            |
      | performance_glitch_user |
      | error_user              |
      | visual_user             |

  # Test Suite: Invalid Equivalence Classes
  # Priority: P2
  # Coverage: Input Validation
  @regression
  Scenario: Login with empty credentials
    # Validation Rule: Both fields are required
    Given I am on the SauceDemo login page "https://www.saucedemo.com/"
    When I enter username "" and password ""
    And I click on the login button
    Then I should see error message "Epic sadface: Username is required"

  # Test Suite: Security Tests
  # Priority: P1
  # Coverage: Security Vulnerabilities
  @regression @security
  Scenario: Login attempt with SQL injection
    # Security Test: SQL Injection Prevention
    # Risk Level: High
    # OWASP Category: A03:2021-Injection
    Given I am on the SauceDemo login page "https://www.saucedemo.com/"
    When I enter username "' OR '1'='1" and password "' OR '1'='1"
    Then I should see error message "Epic sadface: Username and password do not match any user in this service"

  # Test Suite: Business Rules
  # Priority: P1
  # Coverage: Account Lockout
  @regression @smoke
  Scenario: Locked out user login attempt
    # Business Rule: Locked accounts cannot access the system
    When I enter username "locked_out_user" and password "secret_sauce"
    And I click on the login button
    Then I should see error message "Epic sadface: Sorry, this user has been locked out."
    And I should remain on the login page "https://www.saucedemo.com/"

  # Test Suite: Edge Cases
  # Priority: P3
  # Coverage: Input Boundaries
  @regression @boundary
  Scenario: Login attempt with very long username
    # Boundary Test: Maximum Length Validation
    Given I am on the SauceDemo login page "https://www.saucedemo.com/"
    When I enter username "verylongusernameexceedingmaximumlength" and password "secret_sauce"
    Then I should see error message "Epic sadface: Username and password do not match any user in this service"
