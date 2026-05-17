package test;

import base.BaseTest;
import org.testng.annotations.Test;

public class Login extends BaseTest {


    @Test
    public void loginTest() {
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        page.locator("xpath=//input[@name='username']").fill("Admin");
        page.locator("xpath=//input[@name='password']").fill("admin123");
        page.locator("xpath=//button[@type='submit']").click();
        System.out.println("Login successful");
    }
}
