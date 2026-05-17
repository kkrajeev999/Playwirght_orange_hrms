package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ExtentManager;

import java.lang.reflect.Method;

public class BaseTest {

        protected Playwright playwright;
        protected  Browser browser;
        protected Page page;
        protected ExtentReports extent;
        protected ExtentTest test;


        @BeforeMethod
        public void setup(Method method){
            // Initialize ExtentReports
            extent = ExtentManager.getInstance();
            // Create a test in the report for the current test method
               test = extent.createTest(method.getName());
               playwright = Playwright.create();
               browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
               page = browser.newPage();

        }

    @AfterMethod
    public void tearDown(ITestResult result){
            // Log the test result in ExtentReports
            if (result.getStatus() == ITestResult.FAILURE) {
                test.fail("Test Failed: " + result.getThrowable());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                test.pass("Test Succeeded: " + result.getThrowable());
            } else if (result.getStatus() == ITestResult.SKIP) {
                test.skip("Test skipped: " + result.getThrowable());
            }
            extent.flush(); // Write the report to the file

        if (browser != null)browser.close();
        if (playwright!= null) playwright.close();
    }


}
