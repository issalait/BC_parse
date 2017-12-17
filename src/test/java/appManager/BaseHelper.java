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
    @Step("clicks on el by css {cssSelector}")
    public void clickOnElByCss(String cssSelector) {
        wd.findElement(By.cssSelector(cssSelector)).click();
    }

    @Step("get text by xpath {xpathExpression}")
    public String getTextByXpath(String xpathExpression) {
        return wd.findElement(By.xpath(xpathExpression)).getText();
    }

    @Step("get text by id {elementID}")
    public String getTextByID(String elementID) {
        return wd.findElement(By.id(elementID)).getText();
    }

    @Step
    public String getTextByName(String elementName) {
        return wd.findElement(By.id(elementName)).getText();
    }

    @Step("clicks on el by Xpath {xpathExpression}")
    public void clickOnElByXpath(String xpathExpression) {
        wd.findElement(By.xpath(xpathExpression)).click();
    }

    @Step("clicks on el by ID {elementID}")
    public void clickOnElByID(String elementID) {
        wd.findElement(By.id(elementID)).click();
    }

    @Step
    public void clickOnElByLinkTxt(String linkText) {
        wd.findElement(By.id(linkText)).click();
    }

    @Step("type text by id {elementID}/{textToInput}")
    public void typeTextIntoElementByID(String elementID, String textToInput) {
        clickOnElByID(elementID);
        wd.findElement(By.id(elementID)).sendKeys(textToInput);
    }

    @Step("clearAndTypeTextIntoElementByID text by id {elementID}/{textToInput}")
    public void clearAndTypeTextIntoElementByID(String elementID, String textToInput) {
        clickOnElByID(elementID);
        clearElementByID(elementID);
        wd.findElement(By.id(elementID)).sendKeys(textToInput);
    }

    @Step("type text by Name {elementName}/{textToInput}")
    public void typeTextIntoElementByName(String elementName, String textToInput) {
        clickOnElementByName(elementName);
        wd.findElement(By.name(elementName)).sendKeys(textToInput);
    }

    @Step("type text by Xpath {elementXpath}/{textToInput}")
    public void typeTextIntoElementByXpath(String elementXpath, String textToInput) {
        clickOnElementByXpath(elementXpath);
        wd.findElement(By.xpath(elementXpath)).sendKeys(textToInput);
    }

    @Step("clear el by Name {elementName}")
    public void clearElementByName(String elementName) {
        wd.findElement(By.name(elementName)).clear();
    }

    @Step("clear el by ID {elementID}")
    public void clearElementByID(String elementID) {
        wd.findElement(By.id(elementID)).clear();
    }

    @Step("clicks on el by Name {elementName}")
    public void clickOnElementByName(String elementName) {
        wd.findElement(By.name(elementName)).click();
    }

    @Step("clicks on el by Xpath {elementXpath}")
    public void clickOnElementByXpath(String elementXpath) {
        wd.findElement(By.xpath(elementXpath)).click();
    }
}
