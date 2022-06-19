package com.techpanda.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import commons.BasePage;
import java.util.concurrent.TimeUnit;

public class Account_01_Register {
    WebDriver driver;
    BasePage basePage;
    String projectPath = System.getProperty("user.dir");
    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        basePage = BasePage.getBasePageInstance();
        //driver.get("http://live.techpanda.org/");
    }

    @BeforeMethod
    public void beforeMethod(){
        basePage.openPageUrl(driver,"http://live.techpanda.org/");
        basePage.clickToElement(driver,"//*[@class='footer']//a[text()='My Account']");

    }

    @Test
    public void TC_01_Login_With_Empty_Value(){
        basePage.sendKeyToElement(driver,"//*[@id='email']","");

        basePage.sendKeyToElement(driver,"//*[@id='pass']","");

        basePage.clickToElement(driver,"//*[@id='send2']");
        sleep(1000);
        Assert.assertEquals(basePage.getText(driver,"//*[@id='advice-required-entry-email']"),"This is a required field.");
        Assert.assertEquals(basePage.getText(driver,"//*[@id='advice-required-entry-pass']"),"This is a required field.");

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
