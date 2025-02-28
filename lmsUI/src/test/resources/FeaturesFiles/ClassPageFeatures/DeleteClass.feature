@DeleteClass
Feature: Sort Class Details

  Background: 
    Given Admin Is on the Manage Class Page after login

  Scenario: Validate row level delete icon
    Given Admin is on Manage Class Page
    When Admin clicks the delete icon
    Then Admin should see a alert open with heading "Confirm" along with  <YES> and <NO> button for deletion

  @DeleteClass
  Scenario: Click Yes on deletion window
    Given Admin is on Confirm Deletion alert
    When Admin clicks Yes option
    Then Admin gets a message "Successful Class Deleted" alert and do not see that Class in the data table

  @DeleteClass
  Scenario: Click No on deletion window
    Given Admin is on Confirm Deletion alert
    When Admin clicks No option
    Then Admin can see the deletion alert disappears without deleting

  @DeleteClass
  Scenario: Validate Close(X) icon on Confirm Deletion alert
    Given Admin is on Confirm Deletion alert
    When Admin clicks on close button
    Then Admin can see the deletion alert disappears without any changes

  @DeleteMultipleClass
  Scenario: Validate Common Delete button enabled after clicking on any checkbox
    Given Admin is in Manage Class Page
    When Admin clicks any checkbox in the data table
    Then Admin should see common delete option enabled under header Manage Class

  @DeleteMultipleClass
  Scenario: Validate multiple class deletion by selecting Single checkbox
    Given Admin is on Confirm Deletion alert
    When Admin clicks <YES> button on the alert
    Then Admin should land on Manage Class Page and can see the selected class is deleted from the data table

  @DeleteMultipleClass
  Scenario: Validate multiple class deletion by selecting Single check boxes
    Given Admin is on Confirm Deletion alert
    When Admin clicks <NO> button on the alert
    Then Admin should land on Manage Class Page and can see the selected class is not deleted from the data table

  @DeleteMultipleClass
  Scenario: Validate multiple class deletion by selecting Multiple check boxes
    Given Admin is on Confirm Deletion alert
    When Admin clicks <YES> button on the alert
    Then Admin should land on Manage Class Page and can see the selected class is not deleted from the data table

  @DeleteMultipleClass
  Scenario: Validate multiple class deletion by selecting Multiple check boxes
    Given Class is on Confirm Deletion alert
    When Admin clicks <NO> button on the alert
    Then Admin should land on Manage Class Page and can see the selected class is deleted from the data table
