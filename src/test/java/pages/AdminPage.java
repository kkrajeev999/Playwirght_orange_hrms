package pages;

import com.microsoft.playwright.Page;

public class AdminPage {
    protected Page page;
    private String AdminClick2 = "xpath=//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a/span";

    public AdminPage(Page page) {
        this.page = page;


    }

    public void adminClick2(){
        page.click(AdminClick2);
    }

    public void adminPage(){
        adminClick2();
    }

}
