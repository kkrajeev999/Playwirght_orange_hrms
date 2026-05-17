package org.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlayWriteBasics {








    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
Browser browser = playwright.chromium().launch(
        new BrowserType().launchOptions().setHeadless(false)
        );

        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
        );

browser.newPage();

        Page page = browser.newPage();
        page.navigate("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
        String pagetitle = page.title();
        System.out.println("Page Title is: " + pagetitle);
        String pageurl = page.url();
        System.out.println("Page URL is: " + pageurl);
        browser.close();
        playwright.close();
    }



}
