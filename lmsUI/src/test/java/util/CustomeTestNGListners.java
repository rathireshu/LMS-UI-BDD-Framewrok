package util;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomeTestNGListners implements ITestListener  {
//  it will not work with cucumber as its o ly work with @Test lifecycle and cucumber does not have @test

    @Override
    public void onTestStart(ITestResult result) {
        // Log when a test starts
        System.out.println("Test Started: " + result.getName());
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        // Log when a test passes
        System.out.println("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log when a test fails
        System.out.println("Test Failed: " + result.getName());
        // You can take a screenshot, generate logs, etc. here
    }
    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onFinish(ITestContext context) {}

}
