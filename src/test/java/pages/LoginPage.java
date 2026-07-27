package pages;

import com.microsoft.playwright.Page;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import javax.swing.*;

public class LoginPage {

    protected Page page;
    SoftAssert softAssert = new SoftAssert();
    //Dashbaord text
    private String Dashboardtext = "xpath=//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']";

    //Admin click
    private String AdminClick = "xpath=//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a/span";

    private String Profileclick = "xpath=//p[@class=\"oxd-userdropdown-name\"]";

    private String Logoutclick = "xpath=(//a[@class=\"oxd-userdropdown-link\"])[4]";

    public LoginPage(Page page){
        this.page = page;

    }
    public  void dashBoardtext(){
        String Dtext =  page.textContent(Dashboardtext);
        System.out.println(Dtext);
        //Assert.assertEquals(Dtext, "DAshboard", "Not matched");

        softAssert.assertEquals(Dtext,"Dashboard", "Dashboard Not matched");
        softAssert.assertAll();
    }


    public void clickAdmin(){
        page.click(AdminClick);
    }

    public void clickProfile(){
        page.click(Profileclick);
    }

    public void clickLogout(){
        page.click(Logoutclick);
    }




    public void loginPage(){
        dashBoardtext();
        clickAdmin();
        clickProfile();
        clickLogout();

    }


}
