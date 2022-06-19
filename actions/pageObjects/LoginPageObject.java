package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
    WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToLoginButton(){
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }

    public void inputToEmailAddressTextbox(String s) {
        waitForElementVisible(driver,LoginPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX,s);
    }

    public void inputToPasswordTextbox(String s) {
        waitForElementVisible(driver,LoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX,s);
    }

    public String getEmailAddressEmptyErrorMessage(){
        waitForElementVisible(driver, LoginPageUI.EMAIL_EMPTY_ERROR_MESSAGE);
        return getText(driver, LoginPageUI.EMAIL_EMPTY_ERROR_MESSAGE);

    }

    public String getPasswordEmptyErrorMessage(){
        waitForElementVisible(driver, LoginPageUI.PASSWORD_EMPTY_ERROR_MESSAGE);
        return getText(driver, LoginPageUI.PASSWORD_EMPTY_ERROR_MESSAGE);

    }

}
