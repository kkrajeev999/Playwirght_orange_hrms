package pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private Page page;
    private String usernameTextbox = "xpath=//input[@name='username']";
    private String passwordTextbox = "xpath=//input[@name='password']";
    private String loginButton = "xpath=//button[@type='submit']";
    private String AdminClick = "xpath=//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a/span";

    public LoginPage(Page page){
        this.page = page;

    }
    public void addUsername(String username){
        page.fill(usernameTextbox, username);

    }
    public void addPassword(String password){
        page.fill(passwordTextbox, password);
    }
    public void clickLogin(){
        page.click(loginButton);
    }

    public void clickAdmin(){
        page.click(AdminClick);
    }


    public void login(String username, String password){
        addUsername(username);
        addPassword(password);
        clickLogin();
        clickAdmin();


    }
}
