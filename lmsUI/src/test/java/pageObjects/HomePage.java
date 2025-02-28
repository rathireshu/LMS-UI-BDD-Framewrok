package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import driverFactory.BasePage;
import util.ElementUtil;

public class HomePage extends BasePage{

	private WebDriver driver;

		public HomePage() {
			this.driver = BasePage.getDriver();
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(xpath = "//mat-toolbar/span[text()=' LMS - Learning Management System ']") 
		public WebElement hometoolbartitle;
		
		
		public String getHomePageTitle() {
			return driver.getTitle();
		}
		
		public boolean isHomePageToolBarPresent() {
			
			ElementUtil.implicitPageWait(driver);
			System.out.println("home page URL in POM: " + driver.getCurrentUrl());
			return ElementUtil.waitForElementVisibility(driver, hometoolbartitle, 30L).isDisplayed();
			
	   }
}
