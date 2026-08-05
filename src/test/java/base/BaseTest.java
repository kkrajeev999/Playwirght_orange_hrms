package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ExtentManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Properties;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected ExtentReports extent;
    protected ExtentTest test;
    protected static Properties prop;

    static {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream("src/test/resources/config.properties");
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Configuration file not found or could not be read.");
        }
    }

    @BeforeMethod
    public void setup(Method method) {
        extent = ExtentManager.getInstance();
        test = extent.createTest(method.getName());
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)); // Always launch Chromium, not headless

        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1200, 800)); // Fixed viewport size
        page = context.newPage();

        context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));

        page.navigate(prop.getProperty("url"));
        // Perform Login - Reverted to original automatic login
        page.locator("[name='username']").fill(prop.getProperty("username"));
        page.locator("[name='password']").fill(prop.getProperty("password"));
        page.locator("button[type='submit']").click();
        page.waitForURL("**/dashboard/index"); // Wait for dashboard page
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        String traceFileName = "traces/" + result.getMethod().getMethodName() + "_trace.zip";
        context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get(traceFileName)));

        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail("Test Failed");
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed Successfully");
        } else {
            test.skip("Test Skipped");
        }

        extent.flush();

        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
