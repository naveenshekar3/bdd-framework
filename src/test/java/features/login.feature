Feature: Login

  Scenario: Valid Login
    Given user is on login page
#    When user enters valid credentials
#    Then user should login successfully

  Scenario: Invalid login
    Given user is on login page and checks the title "<title>"

    Example:
      | title |
      | asDFG |