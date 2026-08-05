package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.testng.Assert;

public class AdminPage{
    protected Page page;
    private String AdminClick2 = "xpath=//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a/span";

    //username
    private String SystemUsers = "xpath=(//input[@class='oxd-input oxd-input--active'])[2]";

    //UserRole
    private String SystemUserRole = "xpath=(//div[@class=\"oxd-select-text oxd-select-text--active\"])[1]";

    private String Employeenamefield = "xpath=//input[@placeholder=\"Type for hints...\"]";

    private String Statusdropdown = "xpath=(//div[@class=\"oxd-select-text-input\"])[2]";




    public AdminPage(Page page) {
        this.page = page;
    }


    public void adminClick2(){
        page.click(AdminClick2);
        String text1 = page.textContent(AdminClick2);
        String  expectedText = "Admin";
        Assert.assertEquals(text1, expectedText, "Not matched");

    }

    public void SystemUser(){
        page.fill(SystemUsers, "Rajeev");

    }
    public void UserRoledropdown() {
        page.click(SystemUserRole);
    }


    public  void Employeenamefield(){
        page.fill(Employeenamefield, "Rajeev kumar");

    }

    public void Statusdropdown(){
        Locator status = page.locator(Statusdropdown);
        status.click();
        status.selectOption("Enabled");


    }
}
