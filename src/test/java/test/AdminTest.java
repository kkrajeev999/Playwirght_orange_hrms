package test;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.AdminPage;


public class AdminTest extends BaseTest {

    @Test (testName = "Admin Test", priority = 3)
    public void AdminTest() {

        //page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        AdminPage adminPage = new AdminPage(page);
        String pagetitle = page.title();
        System.out.println("Page Title is: " + pagetitle);
        String pageurl = page.url();
        System.out.println("Page URL is: " + pageurl);
        adminPage.adminClick2();
    }
}
