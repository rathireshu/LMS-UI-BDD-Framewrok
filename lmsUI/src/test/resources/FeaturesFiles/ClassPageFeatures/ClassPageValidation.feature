@HomePage
Feature: Class Page Validation

  Background: 
    Given Admin logged on the home page

  Scenario: Validating the class manage page
    Given Admin is on the home page
    When Admin clicks the "Class" navigation bar in the header
    Then Admin should land on the "Manage Class" page

  @ClassPage
  Scenario: Validating the Title in the Manage class page
    Given Admin is on the home page after login
    When Admin clicks the "Class" navigation bar in the header
    Then Admin should see the "LMS-Learning Management System" Title

  @ClassPage
  Scenario: Validating the Header in the Manage class page
    Given Admin is on the home page after login
    When Admin clicks the "Class" navigation bar in the header
    Then Admin should see the "Mange Class" Header

  @ClassPage
  Scenario: Validating Search bar in class page
    Given Admin is on the home page after login
    When Admin clicks the "Class" navigation bar in the header
    Then Admin should see the Search Bar in Manage class page

  @ClassPage
  Scenario: Validating the data table headers in the class page
    Given Admin is on the home page after login
    When Admin clicks the "Class" navigation bar in the header
    Then Admin should see the datatable heading like Batchname,class topic,class description,status,class Date,staff name,Edit/Delete

  @ClassPage
  Scenario: Validating the text and pagination icon in the classpage
    Given Admin is on the home page after login
    When Admin clicks the "Class" navigation bar in the header
    Then Admin should see the Search Bar in Manage class page

  @ClassPage
  Scenario: Validate the sort icon of all the field in datatable
    Given Admin is on the home page after login
    When Admin clicks the "Class" navigation bar in the header
    Then Admin should see the Sort icon of all the field in the datatable

  @ClassPage
  Scenario: Validating the Delete button under the Manage class
    Given Admin is on the home page after login
    When Admin clicks the "Class" navigation bar in the header
    Then Admin should see the Delete button under the Manage class page header

  @ClassPage
  Scenario: Validate the total no of classes in the Manage class page
    Given Admin is on the home page after login
    When Admin clicks the "Class" navigation bar in the header
    Then Admin should see Total no of classes in below of the data table.
