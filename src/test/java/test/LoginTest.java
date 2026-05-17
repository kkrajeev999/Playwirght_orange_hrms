package test;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {


    @Test (testName = "Login Test", priority = 2)
    public void loginTest() {

        LoginPage loginPage = new LoginPage(page);
        //HomePage homePage = new HomePage();

        test.info("Navigating to the login page");
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage.login("Admin", "admin123");
        test.info("Performing login action");
        loginPage.clickLogin();
        test.info("Login action completed");
        test.info("Login successful");
        String pagetitle = page.title();
        test.info("Page title: " + pagetitle);
        String pageurl = page.url();
        test.info("Page URL: " + pageurl);
        loginPage.clickAdmin();


    }
}
