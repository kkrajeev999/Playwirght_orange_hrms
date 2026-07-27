package pages;

import com.microsoft.playwright.Page;
import org.testng.Assert;

public class AdminPage{
    protected Page page;

    //username
    private String SystemUsers = "xpath=(//input[@class='oxd-input oxd-input--active'])[2]";

    //UserRole
    //private String SystemUserRole = "xpath=(//div[@class=\"oxd-select-text oxd-select-text--active\"])[1]";
    private String SystemUserRole = "xpath=(//div[@class='oxd-select-text-input'])[1]";


    private String Employeenamefield = "xpath=//input[@placeholder=\"Type for hints...\"]";

    private String Statusdropdown = "xpath=(//div[@class=\"oxd-select-text-input\"])[2]";

    private String Userrioledrpdn = "xpath=//div[@class='oxd-select-text-input' and normalize-space()='Admin']";

    private String Statusdrpdn = "xpath=//div[@class='oxd-select-text-input' and normalize-space()='Enabled']";

    public AdminPage(Page page) {
        this.page = page;
    }


    public void adminClick2(){
        String adminClick2 = "xpath=//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a/span";
        //page.click(adminClick2);
        page.locator(adminClick2).click();

        String text1 = page.textContent(adminClick2);
        String  expectedText = "Admin";
        Assert.assertEquals(text1, expectedText, "Not matched");

    }

    public void SystemUser(){
        page.fill(SystemUsers, "Rajeev");

    }
    public void UserRoledropdown() {
        page.locator(SystemUserRole).click();
        //page.locator(Userrioledrpdn).click();
        //page.click(SystemUserRole);
        //page.click(Userrioledrpdn);
    }


    public  void Employeenamefield(){
        page.fill(Employeenamefield, "Rajeev");

    }

    public void Statusdropdown(){
        //page.click(Statusdropdown);
        page.locator(Statusdropdown).click();
        //page.locator(Statusdrpdn).click();
        //page.click(Statusdrpdn);

    }

/*    public void adminPage(){
        adminClick2();
        SystemUser();
        UserRoledropdown();
        Employeenamefield();
        Statusdropdown();
    }*/
}
