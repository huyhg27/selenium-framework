package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class BasePage {

    private long longTimeout = 30;
    private long shortTimeout = 10;

    public static BasePage getBasePageInstance(){
        return new BasePage();
    }
    public void openPageUrl(WebDriver driver, String url){
        driver.get(url);
    }

    public String getPageUrl(WebDriver driver){
        return driver.getCurrentUrl();
    }
    public String getPageSource(WebDriver driver){
        return driver.getPageSource();
    }
    public String getPageTitle(WebDriver driver)
    {
        return driver.getTitle();
    }
    public void backToPage(WebDriver driver)

    {
        driver.navigate().back();
    }

    public void switchToWindowById(WebDriver driver, String expectedId)
    {
        Set<String> allTabsId = driver.getWindowHandles();

        for(String id : allTabsId){
            if(id.equals(expectedId))
            {
                driver.switchTo().window(id);
                break;
            }

        }
    }

    public void switchToWindowByTitle(WebDriver driver, String expectedTitle)
    {
        Set<String> allTabsId = driver.getWindowHandles();

        for(String id : allTabsId){
            driver.switchTo().window(id);
            if(driver.getTitle().equals(expectedTitle)){
                break;
            }
        }

    }

    public boolean closeAllWindowsWithoutParent(WebDriver driver, String parentId)
    {
        Set<String> allWindows = driver.getWindowHandles();

        for(String currentWindow : allWindows){
            if(!currentWindow.equals(parentId)){
                driver.switchTo().window(currentWindow);
                driver.close();
            }
        }

        driver.switchTo().window(parentId);
        if(driver.getWindowHandles().size() == 1)
            return true;
        else
            return false;

    }


    /* web element */

    public By byXpath(String locator){
        return By.xpath(locator);
    }

    public WebElement getWebElement(WebDriver driver, String locator){
        return driver.findElement(byXpath(locator));
    }
    public List<WebElement> getListElement(WebDriver driver, String locator){
        return driver.findElements(byXpath(locator));
    }

    public void clickToElement(WebDriver driver, String locator){
        getWebElement(driver,locator).click();
    }

    public void sendKeyToElement(WebDriver driver, String locator, String inputValue){
        WebElement ele = getWebElement(driver, locator);
        ele.clear();
        ele.sendKeys(inputValue);
    }

    public String getText(WebDriver driver, String locator){
        return getWebElement(driver, locator).getText();
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName){
        return getWebElement(driver,locator).getAttribute(attributeName);
    }

    public String getElementCssValue(WebDriver driver, String locator, String propertyName){
        return getWebElement(driver,locator).getCssValue(propertyName);
    }

    public int getListElementSize(WebDriver driver, String locator){
        return getListElement(driver, locator).size();
    }
    public void selectItemInDefaultDropdownlist(WebDriver driver, String locator, String itemText){
        Select select = new Select(getWebElement(driver,locator));
        select.selectByVisibleText(itemText);

    }

    public String getFirstSelectedItemText(WebDriver driver, String locator){
        Select select = new Select(getWebElement(driver,locator));
        return select.getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator){
        Select select = new Select(getWebElement(driver,locator));
        return select.isMultiple();
    }

    public void selectItemFromCustomDropdownlist(WebDriver driver, String parentXpath, String childXpath, String expectedItemText){
        getWebElement(driver,parentXpath).click();
        sleepInSecond(2);

        List<WebElement> childItems = new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(childXpath)));

        for(WebElement tempElement : childItems){
            if(tempElement.getText().trim().equals(expectedItemText)){
                ((JavascriptExecutor) driver).executeScript("argument[0].scrollIntoView(false);",tempElement);
                sleepInSecond(1);
                tempElement.click();
                sleepInSecond(1);
                break;
            }
        }

    }

    public void checkToCheckboxOrRadio(WebDriver driver, String locator){
        if(!getWebElement(driver,locator).isSelected()){
            clickToElement(driver,locator);
        }
    }

    public void uncheckTheCheckbox(WebDriver driver, String locator){
        if(getWebElement(driver,locator).isSelected()){
            clickToElement(driver,locator);
        }
    }

    public boolean isElementIsDisplayed(WebDriver driver, String locator){
        return getWebElement(driver,locator).isDisplayed();
    }
    public boolean isElementIsEnabled(WebDriver driver, String locator){
        return getWebElement(driver,locator).isEnabled();
    }
    public boolean isElementIsSelected(WebDriver driver, String locator){
        return getWebElement(driver,locator).isSelected();
    }

    public void switchToIframe(WebDriver driver, String locator){
        driver.switchTo().frame(getWebElement(driver,locator));
    }

    public void switchToDefaultContent(WebDriver driver){
        driver.switchTo().defaultContent();
    }

    /* action */

    public void hoverToElement(WebDriver driver, String locator){
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver,locator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator){
        Actions action = new Actions(driver);
        action.contextClick(getWebElement(driver,locator)).perform();
    }

    public void doubleClickToElement(WebDriver driver, String locator){
        Actions action = new Actions(driver);
        action.doubleClick(getWebElement(driver,locator)).perform();
    }

    public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator){
        Actions action = new Actions(driver);
        action.dragAndDrop(getWebElement(driver,sourceLocator), getWebElement(driver,targetLocator)).perform();
    }

    public void pressKey(WebDriver driver, String locator, Keys key){
        Actions action = new Actions(driver);
        action.sendKeys(getWebElement(driver, locator), key).perform();
        }


    /* wait */
    public void waitForElementVisible(WebDriver driver, String locator){
        new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator){
        new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator){
        new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
    }

    public void waitForAlertPresence(WebDriver driver){
        new WebDriverWait(driver, longTimeout).until(ExpectedConditions.alertIsPresent());
    }


    public void sleepInSecond(long second){
        try{
            Thread.sleep(second*1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
