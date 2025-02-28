package util;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	
	//common page load wait
	  public static void implicitPageWait(WebDriver driver) {	

	  driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50L));//15sec
  }
	
	//common explicit wait for Visibility 
	public static WebElement waitForElementVisibility(WebDriver driver, WebElement element, long durationInSeconds) {
		
		WebElement webElement = null;
		try{
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(durationInSeconds));
			webElement=wait.until(ExpectedConditions.visibilityOf(element));
			}catch(Exception e) {
				System.out.println("elemnet not visiable "+e.getMessage());
			 }		
			return webElement;
		}
	//common explicit wait for Clickablity
	public static WebElement waitForElementClickablity(WebDriver driver, WebElement element, long durationInSeconds) {
		
		WebElement webElement = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(durationInSeconds));
			webElement=wait.until(ExpectedConditions.elementToBeClickable(element));
			}catch(Exception e) {
				System.out.println("elemnet is not clickable "+e.getMessage());
			}		
			return webElement;
		}
	
	
	public static void sendKeysIntoInput(WebDriver driver, WebElement element, String textToBeTyped, long durationInSeconds) {
		WebElement webElement = null;
		try {
	    webElement = waitForElementClickablity(driver, element, durationInSeconds);
		webElement.clear();
		webElement.click();
		webElement.sendKeys(textToBeTyped);
		}catch(Exception e) {
			System.out.println("error inside sendkeys method:"+e.getMessage());
		}	
	}
	
	
	
	
	
	
}
