Feature: Login  Page Verification 

Background:
        Given Admin is on login Page
  
   Scenario: Verify Admin is able to land on login page
  		Given The browser is open
  		When Admin gives the correct LMS portal URL
  		Then Admin should land on the login page
 
  	Scenario: Verify Admin is not able to land on home page with invalid URL
  		Given The browser is open
  		When Admin gives the invalid LMS portal URL
  		Then Admin should receive application error
    	
  	Scenario: Verify for broken link
  		Given The browser is open
  		When Admin gives the correct LMS portal URL
  		Then the link is broken if HTTP response >= 400.

  	Scenario: Verify the text spelling in the page 
  		Given The browser is open
  		When Admin gives the correct LMS portal URL
  		Then Admin should see correct spellings in all fields 
 @skip	
   Scenario: Verify application name 
  		Given The browser is open
  		When Admin gives the correct LMS portal URL
  		Then Admin should see app name as "LMS - Learning Management System"
  @skip			
  	Scenario: Verify company name 
  		Given The browser is open
  		When Admin gives the correct LMS portal URL
  		Then Admin should see company name below the app name
 
 	Scenario: Validate sign in content 
  		Given The browser is open
  		When Admin gives the correct LMS portal URL
  		Then Admin should see "Please login to LMS application"

 	Scenario: Verify text field is present 
  		Given The browser is open
  		When Admin gives the correct LMS portal URL
  		Then Admin should see two text field

 	Scenario: Verify text on the first field
  		Given The browser is open
  		When Admin gives the correct LMS portal URL
  		Then Admin should see "User" in the first text field
 		
 Scenario: Verify text on the second field
  		Given The browser is open
  		When Admin gives the correct LMS portal URL
  		Then Admin should "Password" in the second text field
  	
  Scenario: Verify asterisk next to USER text 
  		Given The browser is open
  		When Admin gives the correct LMS portal URL
  		Then Admin should see asterisk mark symbol next to text for mandatory fields
 	
 Scenario: Verify asterisk mark symbol next to password text
  		Given The browser is open
  		When Admin gives the correct LMS portal URL
  		Then Admin should see asterisk mark symbol next to password text
  		
 Scenario: Verify dropdown is present 
  		Given The browser is open
  		When Admin gives the correct LMS portal URL
  		Then Admin should see one dropdown
	
 Scenario: Verify placeholder in dropdown to select role
  		Given The browser is open
  		When Admin gives the correct LMS portal URL
  		Then Admin should see "select the role " placeholder in dropdown
	
 Scenario: Verify dropdown option to select role
  		Given The browser is open
  		When Admin gives the correct LMS portal URL
  		Then Admin should see "Admin , staff, student" options in dropdown
 @smoke
 Scenario: Verify the alignment input field for the login
  		Given The browser is open
  		When Admin gives the correct LMS portal URL
  		Then Admin should see input field on the centre of the page
  
 Scenario: verify Login button is present
  		Given The browser is open
  		When Admin gives the correct LMS portal URL
  		Then Admin should see login button 
 
 Scenario: Verify input descriptive text in user field
  		Given The browser is open
  		When Admin gives the correct LMS portal URL
  		Then Admin should see user in gray color

 Scenario: Verify input descriptive text in password field
  		Given The browser is open
  		When Admin gives the correct LMS portal URL
  		Then Admin should see password in gray color


 Scenario: Validate login with valid data in all field
  		Given Admin gives the correct LMS portal URL
  		When Admin enter valid username as "sdetnumpyninja@gmail.com" ,password as "Feb@2025" and role as "Admin" 
  		And clicks login button 
  		Then Admin should land on home page  

 Scenario Outline: Validate login with invalid data
  		Given Admin gives the correct LMS portal URL
  		When Admin enter inValid credentials from "<SheetName>" and <rowNumber>
  		And clicks login button  
  		Then Error message "Invalid username and password Please try again"
   
    Examples: 
      | SheetName   | rowNumber |
      | Login       |   1       |
   
  		
  Scenario Outline: Validate login credentials with null user name
  		Given Admin gives the correct LMS portal URL
  		When Admin enter value only in password from "<SheetName>" and <rowNumber>
  		And clicks login button  
  		Then Username Error message " Please enter your user name "
  Examples: 
      | SheetName   | rowNumber |
      | Login       |   2       |
      
	
 Scenario Outline: Validate login credentials with null password
  		Given Admin gives the correct LMS portal URL
  		When Admin enter value only in user name from "<SheetName>" and <rowNumber>
  		And clicks login button  
  		Then Password Error message " Please enter your password "
  		
  Examples: 
      | SheetName   | rowNumber |
      | Login       |   3       |
 
Scenario Outline: verify login button action through keyboard
  		Given Admin gives the correct LMS portal URL
  		When Admin enter valid credentials from "<SheetName>" and <rowNumber>
  		And clicks login button through keyboard  
  		Then Admin should land on home page
  		
  Examples: 
      | SheetName   | rowNumber |
      | Login       |   0       |

@Validlogin
Scenario: verify login button action through mouse
  		Given Admin gives the correct LMS portal URL
  		When Admin enter valid credentials from "<SheetName>" and <rowNumber>
  		And clicks login button through mouse 
  		Then Admin should land on home page
  		
  Examples: 
      | SheetName   | rowNumber |
      | Login       |   0       |
  		
  		
 #Data driven from Excel worksheet
 @validlogin		
 Scenario Outline: Validate login with valid data in all field 
    Given Admin gives the correct LMS portal URL
    When Admin enter valid credentials from "<SheetName>" and <rowNumber>
    And clicks login button 
    Then Admin should land on home page

    Examples: 
      | SheetName   | rowNumber |
      | Login       |   0       |




   		  		
  		
  		
  		
  
	
  		
  		