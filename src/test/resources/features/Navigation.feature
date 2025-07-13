Feature: Open Homepage and Click Login

  Scenario: Access homepage and click Login
    Given User opens the application homepage
    When User clicks the Login button
    Then User should be redirected to login URL

  Scenario: User can login with valid credentials
    Given User is on the login page
    When User enters valid username and password
    And User clicks the login button
    Then User should see the admin page