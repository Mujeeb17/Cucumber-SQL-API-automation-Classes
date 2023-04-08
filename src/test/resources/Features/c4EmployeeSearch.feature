Feature: Employee Search

  Background:
    When user enters valid email and valid password
    And click on login button
    When user clicks on PIM option

  @empSearchId
  Scenario: Searching for an employee by id
    #Given open the browser and launch HRMS application
#    When user enters valid email and valid password
#    And click on login button
#    When user clicks on PIM option
    When user enters valid employee id
    And clicks on search button
    And user sees employee info displayed
    #And close the browser

    @empSearchJobTitle
  Scenario: Search Employee by Job Title
    #Given open the browser and launch HRMS application
#    When user enters valid email and valid password
#    And click on login button
#    When user clicks on PIM option
    When user select Job Title
    And clicks on search button
    And user sees employee info displayed
    #And close the browser