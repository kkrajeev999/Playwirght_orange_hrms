package test;

import base.BaseTest;
import base.BaseTestNoLogin;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomepageTest extends BaseTestNoLogin {

    @Test (testName = "Home Page Test", priority = 1)
    public void homePageTest(){
        page.navigate(prop.getProperty("url"));
        HomePage homePage = new HomePage(page);
        homePage.CredentialsText();
        System.out.println("Home Page Test completed successfully.");
        homePage.LoginHeadingText();
        System.out.println("Login Heading Text test completed successfully.");





    }
}
