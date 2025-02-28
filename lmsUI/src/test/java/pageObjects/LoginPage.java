package pageObjects;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.HomePage;
import util.ElementUtil;
import driverFactory.BasePage;
import util.ConfigReader;

public class LoginPage extends BasePage

{
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "username")
	public WebElement loginusername;

	@FindBy(id = "password")
	public WebElement loginpwd;

	@FindBy(xpath = "//div[contains(@class,'mat-form-field-infix')]/input")
	public List<WebElement> inputBoxes;

	@FindBy(xpath = "(//label[@id='mat-form-field-label-1']//span)[1]")
	public WebElement userInput;
	@FindBy(xpath = "(//label[@id='mat-form-field-label-1']//span)[2]")
	public WebElement userAsterisk;

	@FindBy(xpath = "(//label[@id='mat-form-field-label-3']//span)[1]")
	public WebElement pwdInput;
	@FindBy(xpath = "(//label[@id='mat-form-field-label-3']//span)[2]")
	public WebElement pwdInputAsterisk;

	@FindBy(xpath = "//mat-select[@role='combobox']")
	public WebElement loginrole;

	@FindBy(xpath = "//mat-option/span[text()=' Admin ']")
	public WebElement loginroleAdminvalue;

	@FindBy(xpath = "//mat-option//span")
	public List<WebElement> selectingRole;

	@FindBy(xpath = "//mat-select[@placeholder='Select the role']")
	public WebElement selectTheRolePlaceholder;

	@FindBy(xpath = "//mat-card-content/form")
	public WebElement inputFiledsform;

	@FindBy(xpath = "//mat-error[text()=' Please enter your password ']")
	public WebElement missingPwdErrMsg;

	@FindBy(xpath = "//mat-error[contains(text(), 'Please enter your user name')]")
	public WebElement missingUserErrMsg;

	@FindBy(id = "login")
	public WebElement loginBtn;

	@FindBy(id = "logout")
	public WebElement homelogout;

	public String readConfigUrl() {
		return ConfigReader.getPropObject().getProperty("appUrl");
	}

	public void navigateToAppUrl() throws InterruptedException {

		String appUrl = readConfigUrl();
		driver.get(appUrl);
		ElementUtil.implicitPageWait(driver);
		System.out.println("Application URL in POM: " + appUrl);

	}
	public void navigateToInvalidUrl() throws InterruptedException {
		String appUrl = readConfigUrl();
		System.out.println("Navigated to the invalid URL." + appUrl);
		driver.get(appUrl);
		ElementUtil.implicitPageWait(driver);
	}
	public String getPageSource() {
		return driver.getPageSource();
	}
	public String getPageUrl() {
		return driver.getCurrentUrl();
	}
	public String getPageTitle() {
		return driver.getTitle();
	}
	public String signInMsg() {
		String pageText = driver.findElement(By.tagName("body")).getText();
		System.out.println("pageText: " + pageText);
		return pageText;
	}
	public int getInputBoxCount() {

		return inputBoxes.size();
	}
	public String getUserInputBoxText() {

		return userInput.getText();
	}
	public boolean isUserAsteriskDisplyed() {

		return userAsterisk.isDisplayed();
	}
	public String getPwdInputBoxText() {

		return pwdInput.getText();
	}

	public boolean isPwdAsteriskDisplyed() {

		return pwdInputAsterisk.isDisplayed();
	}

	public boolean isRolePlaceholderDisplyed() {

		return selectTheRolePlaceholder.isDisplayed();
	}

	public boolean isRoleDropDownDisplyed() {

		return loginrole.isDisplayed();
	}

	public boolean isLoginBtnDisplyed() {

		return loginBtn.isDisplayed();
	}

	public String getUserInputTextcolor() {

		return userInput.getCssValue("color");
	}

	public String getPwdInputTextcolor() {

		return pwdInput.getCssValue("color");
	}

	public int getResponseCode() {

		String appUrl = readConfigUrl();
		URL url = null;
		int responseCode = -1;
		HttpURLConnection connection = null;

		try {
			url = new URL(appUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			responseCode = connection.getResponseCode();
			return responseCode;

		} catch (Exception e) {
			System.err.println("Error checking application URL: " + e.getMessage());
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return responseCode;
	}

	public boolean isSpellingErrorExistOnLogin() {

		// Get all visible text on the page
		String pageText = driver.findElement(By.tagName("body")).getText();
		System.out.println("pageText: " + pageText);
		// List of expected words that should be correctly spelled
		List<String> words = Arrays.asList("Please login to LMS application", "User", "Password", "Select the role",
				"Login");
		// Check if expected words are present
		boolean incorrectSpellingFound = false;

		for (String word : words) {

			if (!pageText.contains(word)) {

				System.out.println("Spelling error found: " + word);
				incorrectSpellingFound = true;
			}
		}
		return incorrectSpellingFound;
	}

	public void sendUserName(String uname) {
		ElementUtil.sendKeysIntoInput(driver, loginusername, uname, 20L);
	}

	public void sendPwdName(String password) {
		ElementUtil.sendKeysIntoInput(driver, loginpwd, password, 20L);
	}

	public void sendRoleAsAdmin() throws InterruptedException {

		ElementUtil.waitForElementClickablity(driver, loginrole, 20L).click();
		ElementUtil.waitForElementClickablity(driver, loginroleAdminvalue, 20L).click();

	}

	public boolean verifyRoleOptions(String roleOptions) {

		ElementUtil.waitForElementClickablity(driver, loginrole, 20L).click();

		boolean isRoleOptionExist = false;
		for (WebElement role : selectingRole)// Admin,Staff,Student
		{
			System.out.println(role.getText());
			if (roleOptions.contains(role.getText())) {
				isRoleOptionExist = true;
			}
		}
		return isRoleOptionExist;
	}

	public void sendRole(String role) throws InterruptedException {
		ElementUtil.waitForElementClickablity(driver, loginrole, 20L).click();

		for (WebElement e : selectingRole)// Admin,Staff,Student
		{
			System.out.println("role :" + role);

			if (e.getText().contains(role)) {
				e.click();
			}
		}
	}

	public int getLeftRightMarginOfInputField() {

		// Get the CSS values for margin-left and margin-right
		String marginLeft = inputFiledsform.getCssValue("margin-left");
		String marginRight = inputFiledsform.getCssValue("margin-right");

		// Assert that both margins are approximately equal (with a small tolerance)

		int leftMargin = Integer.parseInt(marginLeft.replace("px", ""));
		int rightMargin = Integer.parseInt(marginRight.replace("px", ""));

		int marginDiff = Math.abs(leftMargin - rightMargin);
		System.out.println("margin Diff is" + marginDiff);
		return marginDiff;
	}

	public void clickLogin() {

		ElementUtil.waitForElementClickablity(driver, loginBtn, 20L).click();
	}
	
	public void loginBtnKeyboardClick() {

		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB).sendKeys(Keys.ENTER).build().perform();
	}
	public void loginBtnMouseClick() {

		Actions action = new Actions(driver);
		action.moveToElement(loginBtn).click().build().perform();
	}
	
	
	

	public void clickLogout() {
		ElementUtil.waitForElementClickablity(driver, homelogout, 10L).click();
	}
	public void isInvalidCredentialErrorMsgDisplyed() {
		// this is bug no error msg displayed on app

	}
	public boolean isNullUserNameErrorMsgDisplyed() {
		boolean isNullUserNameErrorMsg = false;

		try {
			WebElement errorMsg = ElementUtil.waitForElementVisibility(driver, missingUserErrMsg, 60L);
			// Ensure the element is found and displayed
			if (errorMsg.isDisplayed()) {
				System.out.println("Error message is displayed: " + errorMsg.getText());
				isNullUserNameErrorMsg = true;
			}

		} catch (Exception e) {
			System.out.println("Exception in isNullUserNameErrorMsgDisplyed: " + e.getMessage());
		}

		return isNullUserNameErrorMsg;
	}

	public boolean isNullPwdErrorMsg() {

		boolean isNullPwdErrorMsg = false;

		try {
			WebElement errorMsg = ElementUtil.waitForElementVisibility(driver, missingPwdErrMsg, 60L);
			// Ensure the element is found and displayed
			if (errorMsg.isDisplayed()) {
				System.out.println("Error message is displayed: " + errorMsg.getText());
				isNullPwdErrorMsg = true;
			}

		} catch (Exception e) {
			System.out.println("Exception in isNullUserNameErrorMsgDisplyed: " + e.getMessage());
		}

		return isNullPwdErrorMsg;
	}

	public HomePage getHomePageObject() {

//when ever user redirected to new page that method shld return new page object.	   
		HomePage homePage = new HomePage(driver);// this driver has a reference home page driver
		return homePage;
	}

}
