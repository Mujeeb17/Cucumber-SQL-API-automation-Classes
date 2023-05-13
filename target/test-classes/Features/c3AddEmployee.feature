Feature: Employee

  Background:
    When user enters valid email and valid password
    And click on login button
    When user clicks on PIM option
    And user clicks on add employee option
    And user enters firstname and middlename and lastname
  @testcase2 @smoke
  Scenario: adding a new employee
    #Given open the browser and launch HRMS application
#    When user enters valid email and valid password
#    And click on login button
#    When user clicks on PIM option
#    And user clicks on add employee option
    And user enters firstname and middlename and lastname
    #And user clicks on save button
    #And close the browser

  @database
  Scenario: adding the employee from front end and verifying from back end
#    When user enters valid email and valid password
#    And click on login button
#    When user clicks on PIM option
#    And user clicks on add employee option
    And user enters "nesha" and "sania" and "standart"
    And user captures the employee id
    And user clicks on save button
    And query the information in back end
    Then verify the results from front end and backend
