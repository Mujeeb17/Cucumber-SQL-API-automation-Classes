Feature: Login Functionalities

  Scenario: Valid Admin Login
    #Given open the browser and launch HRMS application
    When user enters valid email and valid password
    And click on login button
    Then user is logged in successfully
    #And close the browser

  @testcase1
  Scenario: Valid Admin Login
    #Given open the browser and launch HRMS application
    When user enters valid "admin" and valid "Hum@nhrm123"
    And click on login button
    Then user is logged in successfully
    #And close the browser