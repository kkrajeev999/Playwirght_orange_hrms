package test;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.PIMPage;


public class PIMTest extends BaseTest {

    @Test (testName = "Admin Test", priority = 3)
    public void AdminTest() throws InterruptedException {

        //page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        PIMPage pimPage = new PIMPage(page);
        String pagetitle = page.title();
        System.out.println("Page Title is: " + pagetitle);
        String pageurl = page.url();
        System.out.println("Page URL is: " + pageurl);

        pimPage.setClickPIM();
        pimPage.EmpName();
        pimPage.SuperVisor();

    }
}
