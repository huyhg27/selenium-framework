package com.techpanda.account;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;

import java.util.concurrent.TimeUnit;

public class Account_01_Register_p3 extends BasePage{
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    HomePageObject homePage;
    LoginPageObject loginPage;
    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        homePage = new HomePageObject(driver);
    }

    @BeforeMethod
    public void beforeMethod(){
        openPageUrl(driver,"http://live.techpanda.org/");
        clickToElement(driver,"//*[@class='footer']//a[text()='My Account']");

    }

    @Test
    public void TC_01_Login_With_Empty_Value(){
        homePage.clickToMyAccountLink();
        loginPage = new LoginPageObject(driver);

        loginPage.inputToEmailAddressTextbox("");
        loginPage.inputToEmailAddressTextbox("");
        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getEmailAddressEmptyErrorMessage(),"This is a required field.");
        Assert.assertEquals(loginPage.getPasswordEmptyErrorMessage(),"This is a required field.");
    }


    @AfterClass
    public void afterClass(){
        driver.close();
        driver.quit();
    }
    public void sleep(long time){
        try{
            Thread.sleep(time);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
