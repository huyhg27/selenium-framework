package com.techpanda.account;

import commons.BasePage;
import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;

import java.util.concurrent.TimeUnit;

public class Account_01_Register_p4 extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    LoginPageObject loginPage;

    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browserName) {

        //driver = getBrowserDriver(browserName);
        driver = getBrowserDriverID(browserName);
        homePage = new HomePageObject(driver);

    }

//    @BeforeMethod
//    public void beforeMethod() {
//        openPageUrl();
//        clickToElement(driver, "//*[@class='footer']//a[text()='My Account']");
//
//    }

    @Test
    public void TC_01_Login_With_Empty_Value() {
        homePage.openPageUrl(driver, "http://live.techpanda.org/");
        homePage.clickToElement(driver, "//*[@class='footer']//a[text()='My Account']");
        homePage.clickToMyAccountLink();

        loginPage = new LoginPageObject(driver);

        loginPage.inputToEmailAddressTextbox("");
        loginPage.inputToEmailAddressTextbox("");
        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getEmailAddressEmptyErrorMessage(), "This is a required field.");
        Assert.assertEquals(loginPage.getPasswordEmptyErrorMessage(), "This is a required field.");
    }


    @AfterClass
    public void afterClass() {
        driver.close();
    }

    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
