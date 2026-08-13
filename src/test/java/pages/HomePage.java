package pages;

import com.microsoft.playwright.Page;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class HomePage {

    SoftAssert softAssert = new SoftAssert();

    Page page;
    private String usernamePasswordText = "div.orangehrm-demo-credentials > P";

    private String LoginTextHeading = "div.orangehrm-login-slot >h5";

    //private String  = "div.orangehrm-demo-credentials > P";


    public HomePage(Page page){
        this.page = page;
    }

    public void LoginHeadingText(){
        String LoginHeadingTesing = page.locator(LoginTextHeading).textContent();
        System.out.println("Login Heading Text is: " + LoginHeadingTesing);
        Assert.assertEquals(LoginHeadingTesing, "Login", "Login not Matched");
    }


    public void CredentialsText(){
      String userNametext = page.locator(usernamePasswordText).nth(0).textContent();
      String passwordtext = page.locator(usernamePasswordText).nth(1).textContent();
      System.out.println("Username text is: " + userNametext);
      System.out.println("Password text is: " + passwordtext);
        Assert.assertEquals(userNametext, "Username : Admin", "Username does not match");
        Assert.assertEquals(passwordtext, "Password : admin123", "Password does not match");
    }



}
