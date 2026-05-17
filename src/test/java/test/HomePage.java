package test;

import base.BaseTest;
import org.testng.annotations.Test;

public class HomePage extends BaseTest {

    @Test (testName = "Home Page Test", priority = 1)
    public void homePageTest(){

        //URL of the application
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        String pagetitle = page.title();
        // Print the page title
        System.out.println("Page Title is: " + pagetitle);
        String pageurl = page.url();
       // To print the page URL
        System.out.println("Page URL is: " + pageurl);


    }
}
