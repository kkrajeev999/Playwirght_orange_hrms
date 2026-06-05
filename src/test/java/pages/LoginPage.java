package pages;

import com.microsoft.playwright.Page;

import javax.swing.*;

public class LoginPage {

    protected Page page;
    /*private String usernameTextbox = "xpath=//input[@name='username']";
    private String passwordTextbox = "xpath=//input[@name='password']";
    private String loginButton = "xpath=//button[@type='submit']";*/
    private String AdminClick = "xpath=//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a/span";

    //username
    private String SystemUsers = "xpath=(//input[@class='oxd-input oxd-input--active'])[2]";

    //UserRole
    private String SystemUserRole = "xpath=(//div[@class=\"oxd-select-text oxd-select-text--active\"])[1]";

    public LoginPage(Page page){
        this.page = page;

    }
  /*  public void addUsername(String systemUsers){


    }*/
   /* public void addPassword(String password){
        page.fill(passwordTextbox, password);
    }*/
   /* public void clickLogin(){
        page.click(loginButton);
    }*/
    public void clickAdmin(){
        page.click(AdminClick);
    }
    public void SystemUser(){
        page.fill(SystemUsers, "Rajeev");

    }

    public void UserRoledropdown(){
        page.click(SystemUserRole);

    }
   /* public void selectUserRole() {
        page.selectOption(SystemUserRole, "Admin");


    }*/
    public void loginPage(){
        /*addUsername(username);
        addPassword(password);
        clickLogin();*/
        clickAdmin();
        SystemUser();
        UserRoledropdown();




    }
}
