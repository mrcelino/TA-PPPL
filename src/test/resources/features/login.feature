Feature: User Login

  Scenario: Successful login as a Buyer
    Given User opens the Avesta system login page
    When User enters the email "user@test.com"
    And User enters the password "12345678"
    And User clicks the 'Login' button
    Then User should be redirected to the Buyer dashboard successfully

  Scenario: Failed login with invalid credentials
    Given User opens the Avesta system login page
    When User enters the email "email_salah@domain.com"
    And User enters the password "password_salah"
    And User clicks the 'Login' button
    Then The system displays an error message 'Email atau password salah'