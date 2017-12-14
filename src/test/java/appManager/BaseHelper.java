package appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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

    public void clickOnElByCss(String cssSelector) {
        wd.findElement(By.cssSelector(cssSelector)).click();
    }

    public String getTextByXpath(String xpathExpression) {
        return wd.findElement(By.xpath(xpathExpression)).getText();
    }

    public String getTextByID(String elementID) {
        return wd.findElement(By.id(elementID)).getText();
    }

    public String getTextByName(String elementName) {
        return wd.findElement(By.id(elementName)).getText();
    }

    public void clickOnElByXpath(String xpathExpression) {
        wd.findElement(By.xpath(xpathExpression)).click();
    }

    public void clickOnElByID(String elementID) {
        wd.findElement(By.id(elementID)).click();
    }

    public void clickOnElByLinkTxt(String linkText) {
        wd.findElement(By.id(linkText)).click();
    }

    public void typeTextIntoElementByID(String elementID, String textToInput) {
        clickOnElByID(elementID);
        wd.findElement(By.id(elementID)).sendKeys(textToInput);
    }

    public void typeTextIntoElementByName(String elementName, String textToInput) {
        clickOnElementByName(elementName);
        wd.findElement(By.name(elementName)).sendKeys(textToInput);
    }

    public void typeTextIntoElementByXpath(String elementXpath, String textToInput) {
        clickOnElementByXpath(elementXpath);
        wd.findElement(By.xpath(elementXpath)).sendKeys(textToInput);
    }

    public void clearElementByName(String elementName) {
        wd.findElement(By.name(elementName)).clear();
    }

    public void clickOnElementByName(String elementName) {
        wd.findElement(By.name(elementName)).click();
    }

    public void clickOnElementByXpath(String elementXpath) {
        wd.findElement(By.xpath(elementXpath)).click();
    }
}
