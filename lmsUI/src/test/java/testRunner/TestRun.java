package testRunner;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(	tags="@login",
					features="src/test/resources/FeaturesFiles/TC001_Login.feature",
					glue={"stepDefinitions","hooks"},
					monochrome=true,
					//dryRun=false,
					plugin= {"pretty","html:target/cucumber-Report.html",}
		
		)

public class TestRun extends AbstractTestNGCucumberTests{
	
	 @Override
	 @DataProvider(parallel=true) 
	  public Object[][] scenarios()
	  { 
		  return super.scenarios(); 
	  }
	 
}
