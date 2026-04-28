Feature: Login functionality

  @loginpositive
  Scenario: Successful login with valid credentials
    Given User opens ilcarro Homepage
    And User clicks on login link
    And User enters email "test222@gmail.com" and password "Test12345!"
    And User clicks on Yalla button
    Then User verifies success message "You are logged in success" is displayed

  @wrongPassword
  Scenario Outline: Login with correct email and wrong password
    Given User opens ilcarro Homepage
    And User clicks on login link
    And User enters email "<email>" and password "<password>"
    And User clicks on Yalla button
    Then User verifies error message is displayed
    Examples:
      | email             | password   |
      | test222@gmail.com | Test12345  |
      | test222@gmail.com | test12345! |
      | test222@gmail.com | TEST12345! |
      | test222@gmail.com | ЕУЫЕ12345! |