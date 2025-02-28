@AEditClass
Feature: Edit Class

  Background: 
    Given Admin Is on the Manage class page after login

  Scenario: Validate row level edit icon
    Given Admin is on the Manage class page
    When Admin clicks on the edit icon
    Then A new pop up with class details appears

  @AEditClass
  Scenario: Check disabled  batch name
    Given Admin is on the Manage class page
    When Admin clicks on the edit icon
    Then Admin should see batch name field is disabled

  @AEditClass
  Scenario: Check disabled class topic
    Given Admin is on the Manage class page
    When Admin clicks on the edit icon
    Then Admin should see class topic field is disabled

  @AEditClass
  Scenario: Check if the fields are updated with valid data
    Given Admin is on the Edit Class Popup window
    When Update the fields with valid data and click save
    Then Admin gets message "class details updated Successfully " and see the updated values in data table

  @AEditClass
  Scenario: Check if the fields are updated with invalid values
    Given Admin is on the Edit Class Popup window
    When Update the fields with invalid values and click save
    Then Admin should get Error message

  @AEditClass
  Scenario: Check if the mandatory fields are updated with valid data
    Given Admin is on the Edit Class Popup window
    When Update the mandatory fields with valid values and click save
    Then Admin gets message "Class details updated Successfully " and see the updated values in data table

  @AEditClass
  Scenario: Check if the optional fields are updated with valid data
    Given Admin is on the Edit Class Popup window
    When Update the optional fields with valid values and click save
    Then Admin gets message "Class details updated Successfully " and see the updated values in data table

  @AEditClass
  Scenario: Validate invalid values in the text fields
    Given Admin is on the Edit Class Popup window
    When Admin enters only numbers or special char in the text fields
    Then Admin should get Error message

  @AEditClass
  Scenario: Validate Cancel button on Edit popup
    Given Admin is on the Edit Class Popup window
    When Admin clicks Cancel button on edit popup
    Then Admin can see the class details popup disappears and can see nothing changed for particular Class
