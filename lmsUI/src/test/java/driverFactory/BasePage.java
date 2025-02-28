package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BasePage {

	private static final String headlessChrome = "headlesschrome";
	private static final String headlessFirefox = "headlessfirefox";
	private static final String firefox = "firefox";
	
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	//return a Singleton WebDriver Instance
	
	public static WebDriver driverManager(String browser)	{
		if(driver.get()== null) // Ensure only one instance is created	
		{
		switch(browser.toLowerCase()) {
		
		case headlessChrome:
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1366,768"); 
			driver.set(new ChromeDriver(options));
			System.out.println("getting headless chrome driver");
        	break;		
		case firefox:
			driver.set(new FirefoxDriver());
			System.out.println("getting firefox driver ");
			break;
		case headlessFirefox:
			FirefoxOptions ffOptions = new FirefoxOptions();
			ffOptions.addArguments("headless");
			ffOptions.addArguments("window-size=1366,768"); 
			driver.set(new FirefoxDriver(ffOptions));
			System.out.println("getting headless firefox driver.");
			break;
			default:
				driver.set(new ChromeDriver());
		}
		driver.get().manage().window().maximize();
	}
		return  driver.get();
}		
	
	//creating public get driver method to enforce Singleton 
	
	public static synchronized WebDriver getDriver() {
		if(driver==null) {
		}
		WebDriver dri = driver.get();
		return dri;
	}
	
    public static void quitDriver() {
		if(driver.get()!=null) {
    	driver.get().quit();
    	driver.remove();//Clear thread-local storage
		}
	}
}
