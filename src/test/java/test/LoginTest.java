package test;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {


    @Test (testName = "Login Test", priority = 1)
    public void loginTest() {

        LoginPage loginPage = new LoginPage(page);

        String pagetitle = page.title();
       test.info("Page title: " + pagetitle);
        String pageurl = page.url();
        test.info("Page URL: " + pageurl);
        loginPage.loginPage();



    }
}
