Feature: API workflow for HRMS

  #because every scenario needs us to generate a token
  Background:
    Given a JWT is generated

  @api
  Scenario: create an employee using API call
    Given a request is prepared to create an employee
    When a POST call is made to create a employee
    Then the status code for creating an employee is 201
