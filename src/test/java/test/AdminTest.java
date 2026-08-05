package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminPage;


public class AdminTest extends BaseTest {

    @Test (testName = "Admin Test", priority = 3)
    public void AdminTest() throws InterruptedException {

        //page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        AdminPage adminPage = new AdminPage(page);
        String pagetitle = page.title();
        System.out.println("Page Title is: " + pagetitle);
        String pageurl = page.url();
        System.out.println("Page URL is: " + pageurl);

        adminPage.adminClick2();
        System.out.println("Admin clicked");
        adminPage.SystemUser();
        System.out.println("System user field filled");
        Thread.sleep(5000);
        adminPage.UserRoledropdown();
        System.out.println("User role dropdown clicked");
        adminPage.Employeenamefield();
        System.out.println("Employee name field filled");
        /*adminPage.Statusdropdown();
        System.out.println("Status dropdown selected");*/

    }
}
