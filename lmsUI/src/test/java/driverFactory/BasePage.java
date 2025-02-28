package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import util.ConfigReader;

public class BasePage {

	private static final String headlessChrome = "headlesschrome";
	private static final String headlessFirefox = "headlessfirefox";
	private static final String firefox = "firefox";
	
	// Use ThreadLocal to store WebDriver for each thread(test)
	protected static ThreadLocal<WebDriver> thdriver = new ThreadLocal<>();
	
	private static WebDriver driverManager(String browser)	{
		
		WebDriver driver = null;  // Local WebDriver instance
		
		switch(browser.toLowerCase()) {
		
		case headlessChrome:
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1366,768"); 
			driver= new ChromeDriver(options);
			System.out.println("getting headless chrome driver");
        	break;		
		case firefox:
			driver = new FirefoxDriver();
			System.out.println("getting firefox driver ");
			break;
		case headlessFirefox:
			FirefoxOptions ffOptions = new FirefoxOptions();
			ffOptions.addArguments("headless");
			ffOptions.addArguments("window-size=1366,768"); 
			driver = new FirefoxDriver(ffOptions);
			System.out.println("getting headless firefox driver.");
			break;
			default:
				System.out.println("Getting default Chrome driver");
				driver = new ChromeDriver();
		}
		
		  thdriver.set(driver); // Store driver in ThreadLocal instance
		  return thdriver.get();
   }		
	// Get WebDriver instance (Singleton pattern with ThreadLocal)
	
	public static synchronized WebDriver getDriver() {
		System.out.println("inside basepage getDriver()");
		if(thdriver.get()==null) {
			// Initialize the WebDriver only when its null
			thdriver.set(driverManager(ConfigReader.getPropObject().getProperty("browser")));
		  }
		return thdriver.get(); //otherwise return existing driver refrence
	 }
	// Quit and remove WebDriver instance
    public static void quitDriver() {
		if(thdriver.get()!=null) {
			thdriver.get().quit();
			thdriver.remove();//Clear thread-local storage
		}
	}
}
