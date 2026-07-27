package pages;

import com.microsoft.playwright.Page;
import org.testng.Assert;

public class PIMPage {
    protected Page page;

    //username
    private String ClickPIM = "xpath=(//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"])[2]";

    //UserRole
    //private String SystemUserRole = "xpath=(//div[@class=\"oxd-select-text oxd-select-text--active\"])[1]";
    private String Empname = "xpath=(//input[@placeholder=\"Type for hints...\"])[1]";


    private String SupervisorName = "xpath=(//input[@placeholder=\"Type for hints...\"])[2]";

    public PIMPage(Page page) {
        this.page = page;
    }


    public void setClickPIM(){
        //String clickPIM = "xpath=//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a/span";
        //page.click(adminClick2);
        page.locator(ClickPIM).click();

        /*String text1 = page.textContent(ClickPIM);
        String  expectedText = "PIM";
        Assert.assertEquals(text1, expectedText, "Not matched");
*/
    }

    public void EmpName(){
        page.fill(Empname, "Ashok");

    }
    public void SuperVisor() {
        page.fill(SupervisorName, "Linda Anderson");
    }




/*    public void adminPage(){
        adminClick2();
        SystemUser();
        UserRoledropdown();
        Employeenamefield();
        Statusdropdown();
    }*/
}
