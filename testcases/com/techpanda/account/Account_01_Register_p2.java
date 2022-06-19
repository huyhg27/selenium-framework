package com.techpanda.account;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Account_01_Register_p2 extends BasePage{
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @BeforeMethod
    public void beforeMethod(){
        openPageUrl(driver,"http://live.techpanda.org/");
        clickToElement(driver,"//*[@class='footer']//a[text()='My Account']");

    }

    @Test
    public void TC_01_Login_With_Empty_Value(){
        sendKeyToElement(driver,"//*[@id='email']","");

        sendKeyToElement(driver,"//*[@id='pass']","");

        clickToElement(driver,"//*[@id='send2']");
        sleep(1000);
        Assert.assertEquals(getText(driver,"//*[@id='advice-required-entry-email']"),"This is a required field.");
        Assert.assertEquals(getText(driver,"//*[@id='advice-required-entry-pass']"),"This is a required field.");

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
