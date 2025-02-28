package hooks;
import org.openqa.selenium.WebDriver;
import driverFactory.BasePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.cucumber.java.Scenario;

public class applicationHooks extends BasePage {

	
	@Before
	public void setUp() {
	//fetches the current WebDriver instance from ThreadLocal<WebDriver> 
		System.out.println("Before Hook - Driver setup");
	    BasePage.getDriver();//initialize the driver very first time
  }
	@After
	public void tearDownNScreenShot(Scenario scenario) {	
		System.out.println("After Hook - Driver quitting");
	
	// Always fetch the latest driver before taking a screenshot
    WebDriver currentDriver = BasePage.getDriver();
    
	if(scenario.isFailed()) {
		
		try {
            if (currentDriver != null) {
                byte[] screenshot = ((TakesScreenshot) currentDriver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failed Scenario Screenshot");
                System.out.println("Screenshot attached for failed scenario: " + scenario.getName());
            } else {
                System.out.println("Driver is null before screenshot capture.");
            }
        } catch (Exception e) {
            System.err.println("Error capturing screenshot: " + e.getMessage());
        }
    }
	BasePage.quitDriver();
	 System.out.println("Driver is null in After Hook");
  }
}
