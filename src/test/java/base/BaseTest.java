package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ExtentManager;

import java.awt.*;
import java.lang.reflect.Method;

public class  BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeMethod
    public void setup(Method method) {

        // Initialize Extent Report
        extent = ExtentManager.getInstance();

        // Create test entry in report
        test = extent.createTest(method.getName());

        // Launch Playwright
        playwright = Playwright.create();

        // Launch Browser
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false));

        // Get Screen Size for maximizing window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        // Create new page and maximize
        page = browser.newPage(new Browser.NewPageOptions().setViewportSize(width, height));

        // Navigate to Login Page
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");



        // Perform Login
        page.locator("[name='username']").fill("Admin");

        page.locator("[name='password']").fill("admin123");

        page.locator("button[type='submit']").click();

        // Wait for dashboard page
        page.waitForURL("**/dashboard/index");

        test.info("Login successful");
        System.out.println("Login successful");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        // Log Test Result
        if (result.getStatus() == ITestResult.FAILURE) {

            test.fail("Test Failed");

            test.fail(result.getThrowable());

        } else if (result.getStatus() == ITestResult.SUCCESS) {

            test.pass("Test Passed Successfully");

        } else if (result.getStatus() == ITestResult.SKIP) {

            test.skip("Test Skipped");
        }

        // Flush report
        extent.flush();

        // Close browser
        if (browser != null) {
            browser.close();
        }

        // Close Playwright
        if (playwright != null) {
            playwright.close();
        }
    }
}
