Feature: Open Homepage and Click Login

  Scenario: Access homepage and click Login
    Given User opens the application homepage
    When User clicks the Login button
    Then User should be redirected to login URL
