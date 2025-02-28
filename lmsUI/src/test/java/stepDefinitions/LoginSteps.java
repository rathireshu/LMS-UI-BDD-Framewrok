package stepDefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import util.ExcelReaderListMap;
import util.LoggerLoad;
import pageObjects.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import util.PicoDInjection;

public class LoginSteps{
	
	   PicoDInjection picoObject;
	    
	   public LoginSteps(PicoDInjection picoObject){
	    	this.picoObject=picoObject;	 
	   }
	@Given("Admin is on login Page")
	public void admin_is_on_login_page() throws InterruptedException {
		System.out.println("creating login page object");
		picoObject.loginPage= new LoginPage();		
		
	}
	@Given("The browser is open")
	public void the_browser_is_open() {
		System.out.println("browser is opened");
	}
	@When("Admin gives the correct LMS portal URL")
	public void admin_gives_the_correct_lms_portal_url() throws InterruptedException {
		picoObject.loginPage.navigateToAppUrl();
	}
	@Then("Admin should land on the login page")
	public void admin_should_land_on_the_login_page() {
		
		String loginUrl = picoObject.loginPage.getPageUrl();
		String loginTitle = picoObject.loginPage.getPageTitle();
		
		System.out.println("page Url is in setpdef:-"+picoObject.loginPage.getPageUrl());
		System.out.println("Page title is :-"+picoObject.loginPage.getPageTitle());
		
		Assert.assertTrue(loginUrl.contains("login"));
		Assert.assertEquals(loginTitle, "LMS");
	}
	
	@When("Admin enter valid username as {string} ,password as {string} and role as {string}")
	public void admin_enter_valid_username_as_password_as_and_role_as(String username, String pwd, String role) throws InterruptedException 
	{
			
		picoObject.loginPage.sendUserName(username);
		picoObject.loginPage.sendPwdName(pwd);;
		picoObject.loginPage.sendRoleAsAdmin();
	}

	@When("clicks login button")
	public void clicks_login_button() throws InterruptedException {
		
		picoObject.loginPage.clickLogin();
		Thread.sleep(3000);
	}
	
	@Then("Admin should land on home page")
	public void admin_should_land_on_home_page() throws InterruptedException {
	   
		picoObject.homePage = picoObject.loginPage.getHomePageObject();	
	    String pageTitle =	picoObject.homePage.getHomePageTitle();
		Assert.assertEquals(pageTitle, "LMS");
		Assert.assertTrue(picoObject.homePage.isHomePageToolBarPresent());;
	}
	
	@When("Admin gives the invalid LMS portal URL")
	public void admin_gives_the_invalid_lms_portal_url() throws InterruptedException {
		
	   try {		
		     picoObject.loginPage.navigateToInvalidUrl();
	     }catch(Exception e){
	       System.out.println("Expected error encountered: " + e.getMessage());
	       Assert.assertTrue(e.getMessage().contains("net::ERR_NAME_NOT_RESOLVED"), "Unexpected error message.");
		}
	}
	@Then("Admin should receive application error")
	public void admin_should_receive_application_error() {
		String loginPageError = picoObject.loginPage.getPageSource();
		Assert.assertTrue(loginPageError.contains("This site can’t be reached"));
	}

	@Then("the link is broken if HTTP response >= {int}.")
	public void the_link_is_broken_if_http_response(Integer expectedRespCode) {
		int loginActualRespCode = picoObject.loginPage.getResponseCode();
		
		boolean isBroken = loginActualRespCode >= expectedRespCode; 
		//if isBroken=true ,the test fails with an assertion error.500>400
		//if isBroken=false ,the test passes normally. 200>400
        Assert.assertFalse(isBroken,"Test failed!Response Code is >=400: " + loginActualRespCode);
	}
	
	@Then("Admin should see correct spellings in all fields")
	public void admin_should_see_correct_spellings_in_all_fields() {
	   
		boolean spellingErrorFound= picoObject.loginPage.isSpellingErrorExistOnLogin();
		Assert.assertFalse(spellingErrorFound, "spelling error found");
	}

	@Then("Admin should see app name as {string}")
	public void admin_should_see_lms_learning_management_system(String expectedName) {
	}

	@Then("Admin should see company name below the app name")
	public void admin_should_see_company_name_below_the_app_name() {
	    
	}

	@Then("Admin should see {string}")
	public void admin_should_see(String expectedMsg) {
		
		Assert.assertTrue(picoObject.loginPage.signInMsg().contains(expectedMsg));
	}

	@Then("Admin should see two text field")
	public void admin_should_see_two_text_field() {
	  
		Assert.assertTrue(picoObject.loginPage.getInputBoxCount()==2);
	}

	@Then("Admin should see {string} in the first text field")
	public void admin_should_in_the_first_text_field(String expected) {
		Assert.assertTrue(picoObject.loginPage.getUserInputBoxText().equalsIgnoreCase(expected));
	}

	@Then("Admin should {string} in the second text field")
	public void admin_should_in_the_second_text_field(String expected) {
		Assert.assertTrue(picoObject.loginPage.getPwdInputBoxText().equalsIgnoreCase(expected));
	}
	
	@Then("Admin should see asterisk mark symbol next to text for mandatory fields")
	public void admin_should_see_asterisk_mark_symbol_next_to_text_for_mandatory_fields() {
		Assert.assertTrue(picoObject.loginPage.isUserAsteriskDisplyed());
	}

	@Then("Admin should see asterisk mark symbol next to password text")
	public void admin_should_see_asterisk_mark_symbol_next_to_password_text() {
		Assert.assertTrue(picoObject.loginPage.isPwdAsteriskDisplyed());
	}
	
	@Then("Admin should see one dropdown")
	public void admin_should_see_one_dropdown() {
		Assert.assertTrue(picoObject.loginPage.isRoleDropDownDisplyed());
	}
	@Then("Admin should see {string} placeholder in dropdown")
	public void admin_should_see_placeholder_in_dropdown(String string) {
		Assert.assertTrue(picoObject.loginPage.isRolePlaceholderDisplyed());
	}

	@Then("Admin should see {string} options in dropdown")
	public void admin_should_see_options_in_dropdown(String roleOptions) {
		boolean roleOptionsFound= picoObject.loginPage.verifyRoleOptions(roleOptions);
		Assert.assertTrue(roleOptionsFound, "role options not found");
	}

	@Then("Admin should see input field on the centre of the page")
	public void admin_should_see_input_field_on_the_centre_of_the_page() {
		int margin= picoObject.loginPage.getLeftRightMarginOfInputField();
		 int tolerance = 5; // tolerance in pixels
		 //If the margins are equal or close, 
		 //it implies that the input field is centered horizontally on the page.
		Assert.assertTrue(margin<tolerance);
	}

	@Then("Admin should see login button")
	public void admin_should_see_login_button() {
		Assert.assertTrue(picoObject.loginPage.isLoginBtnDisplyed());
	}

	@Then("Admin should see user in gray color")
	public void admin_should_see_user_in_gray_color() {
		String textColor= picoObject.loginPage.getUserInputTextcolor();
		System.out.println("textColor is "+textColor);//rgba(0, 0, 0, 0.54)
		// Assert the color is rgba(0, 0, 0, 0.54)for grey
        String expectedGrayColor = "rgba(0, 0, 0, 0.54)";
		Assert.assertEquals(textColor, expectedGrayColor);
	}

	@Then("Admin should see password in gray color")
	public void admin_should_see_password_in_gray_color() {
		String textColor= picoObject.loginPage.getPwdInputTextcolor();
		System.out.println("textColor is "+textColor);//rgba(0, 0, 0, 0.54)
		// Assert the color is rgba(0, 0, 0, 0.54)for grey
        String expectedGrayColor = "rgba(0, 0, 0, 0.54)";
		Assert.assertEquals(textColor, expectedGrayColor); 
	}
	



	
	//Datadriven from Excel
		@When("Admin enter valid credentials from {string} and {int}")
		public void admin_enter_valid_credentials_from_and(String SheetName, Integer rowNumber) throws InvalidFormatException, IOException, InterruptedException {
			ExcelReaderListMap reader = new ExcelReaderListMap();
			LoggerLoad.info("User enters login credentials");
			List<Map<String, String>> testData = reader.getData(PicoDInjection.eXCEL, "Login");
			
			String User_name = testData.get(rowNumber).get("user"); // Column heading
			String Pass_word = testData.get(rowNumber).get("password"); // Column heading
			String Select_role= testData.get(rowNumber).get("role");//Column Heading
			
			picoObject.loginPage.sendUserName(User_name); 
			picoObject.loginPage.sendPwdName(Pass_word);
			picoObject.loginPage.sendRole(Select_role);
			
			LoggerLoad.info("Login credentials entered");

		}
	
	@When("Admin enter inValid credentials from {string} and {int}")
	public void admin_enter_inValid_credentials_from_and(String SheetName, Integer rowNumber) throws InvalidFormatException, IOException, InterruptedException {
		ExcelReaderListMap reader = new ExcelReaderListMap();
		LoggerLoad.info("User enters inValid login credentials");
		
		List<Map<String, String>> testData = reader.getData(PicoDInjection.eXCEL, "Login");
		
		String User_name = testData.get(rowNumber).get("user"); // Column heading
		String Pass_word = testData.get(rowNumber).get("password"); // Column heading
		String Select_role= testData.get(rowNumber).get("role");//Column Heading
		
		picoObject.loginPage.sendUserName(User_name); 
		picoObject.loginPage.sendPwdName(Pass_word);
		picoObject.loginPage.sendRole(Select_role);
		
		LoggerLoad.info("invalid credentials entered");
		
	}

	@Then("Error message {string}")
	public void error_message(String errorMsg) {
	    
		String error = "Invalid username and password Please try again";
		Assert.assertTrue(errorMsg.equalsIgnoreCase(error));
		
	}
	@When("Admin enter value only in password from {string} and {int}")
	public void admin_enter_value_only_in_password_from_and(String SheetName, Integer rowNumber) throws InvalidFormatException, IOException, InterruptedException {
		
		ExcelReaderListMap reader = new ExcelReaderListMap();
		LoggerLoad.info("User enters only password");
		System.out.println("User enters only password");
		List<Map<String, String>> testData = reader.getData(PicoDInjection.eXCEL, "Login");
		
		String User_name = testData.get(rowNumber).get("user"); // Column heading
		String Pass_word = testData.get(rowNumber).get("password"); // Column heading
		String Select_role= testData.get(rowNumber).get("role");//Column Heading
		
		picoObject.loginPage.sendUserName(User_name); 
		picoObject.loginPage.sendPwdName(Pass_word);
		picoObject.loginPage.sendRole(Select_role);
		Thread.sleep(3000);
	}

	@When("Admin enter value only in user name from {string} and {int}")
	public void admin_enter_value_only_in_user_name_from_and(String SheetName, Integer rowNumber) throws InvalidFormatException, IOException, InterruptedException {
		
		ExcelReaderListMap reader = new ExcelReaderListMap();
		LoggerLoad.info("User enters login credentials");
		List<Map<String, String>> testData = reader.getData(PicoDInjection.eXCEL, "Login");
		
		String User_name = testData.get(rowNumber).get("user"); // Column heading
		String Pass_word = testData.get(rowNumber).get("password"); // Column heading
		String Select_role= testData.get(rowNumber).get("role");//Column Heading
		
		picoObject.loginPage.sendUserName(User_name); 
		picoObject.loginPage.sendPwdName(Pass_word);
		picoObject.loginPage.sendRole(Select_role);
	}
	
	@Then("Username Error message {string}")
	public void error_message_please_enter_your_user_name(String string) {
		System.out.println("Error message displayed: " + picoObject.loginPage.isNullUserNameErrorMsgDisplyed());
		System.out.println("username error msg");
		Assert.assertTrue(picoObject.loginPage.isNullUserNameErrorMsgDisplyed());
	}

	@Then("Password Error message {string}")
	public void error_message_please_enter_your_password(String string) {
		
		System.out.println("Error message displayed: " + picoObject.loginPage.isNullUserNameErrorMsgDisplyed());
		System.out.println("username error msg");
		Assert.assertTrue(picoObject.loginPage.isNullPwdErrorMsg());
	}

	
	@When("clicks login button through keyboard")
	public void clicks_login_button_through_keyboard() {
	   
		System.out.println("click login btn via keyboard");
		picoObject.loginPage.loginBtnKeyboardClick();
		
		
	}

	@When("clicks login button through mouse")
	public void clicks_login_button_through_mouse() {
	    
		System.out.println("click login btn via mouse");
		picoObject.loginPage.loginBtnMouseClick();
	}




}
