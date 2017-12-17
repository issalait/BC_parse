package appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.qameta.allure.Step;

/**
 * Created by Любовь on 01.12.2017.
 */
public class BaseHelper {
    private ChromeDriver wd;

    public BaseHelper(ChromeDriver wd) {
        this.wd = wd;
    }

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
    @Step
    public void clickOnElByCss(String cssSelector) {
        wd.findElement(By.cssSelector(cssSelector)).click();
    }

    @Step
    public String getTextByXpath(String xpathExpression) {
        return wd.findElement(By.xpath(xpathExpression)).getText();
    }

    @Step
    public String getTextByID(String elementID) {
        return wd.findElement(By.id(elementID)).getText();
    }

    @Step
    public String getTextByName(String elementName) {
        return wd.findElement(By.id(elementName)).getText();
    }

    @Step
    public void clickOnElByXpath(String xpathExpression) {
        wd.findElement(By.xpath(xpathExpression)).click();
    }

    @Step
    public void clickOnElByID(String elementID) {
        wd.findElement(By.id(elementID)).click();
    }

    @Step
    public void clickOnElByLinkTxt(String linkText) {
        wd.findElement(By.id(linkText)).click();
    }

    @Step
    public void typeTextIntoElementByID(String elementID, String textToInput) {
        clickOnElByID(elementID);
        wd.findElement(By.id(elementID)).sendKeys(textToInput);
    }

    @Step
    public void clearAndTypeTextIntoElementByID(String elementID, String textToInput) {
        clickOnElByID(elementID);
        clearElementByID(elementID);
        wd.findElement(By.id(elementID)).sendKeys(textToInput);
    }

    @Step
    public void typeTextIntoElementByName(String elementName, String textToInput) {
        clickOnElementByName(elementName);
        wd.findElement(By.name(elementName)).sendKeys(textToInput);
    }

    @Step
    public void typeTextIntoElementByXpath(String elementXpath, String textToInput) {
        clickOnElementByXpath(elementXpath);
        wd.findElement(By.xpath(elementXpath)).sendKeys(textToInput);
    }

    @Step
    public void clearElementByName(String elementName) {
        wd.findElement(By.name(elementName)).clear();
    }

    @Step
    public void clearElementByID(String elementID) {
        wd.findElement(By.id(elementID)).clear();
    }

    @Step
    public void clickOnElementByName(String elementName) {
        wd.findElement(By.name(elementName)).click();
    }

    @Step
    public void clickOnElementByXpath(String elementXpath) {
        wd.findElement(By.xpath(elementXpath)).click();
    }
}
