Feature: Login

  Scenario: Valid Login
    Given user is on login page
#    When user enters valid credentials
#    Then user should login successfully

  Scenario Outline: Invalid login
    Given user is on login page and checks the title "<title>"
    Examples:
      | title |
      | asDFG |