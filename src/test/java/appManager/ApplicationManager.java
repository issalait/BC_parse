package appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created by Любовь on 01.12.2017.
 */
public class ApplicationManager {
    ChromeDriver wd;
    ChromeOptions o;
    String targetName;
    String chromeProfile;

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void init() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("C:/parser_settings/chromeProfile.csv")));
            String line = reader.readLine();
            while (line != null) {
                String[] split = line.split(";");
                chromeProfile = split[0];
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        System.out.println("chromeProfile is : ");
        System.out.println(chromeProfile);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=" + chromeProfile);
        options.addArguments("--start-maximized");
        wd = new ChromeDriver(options);

        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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

    public void clickOnElByXpath(String xpathExpression) {
        wd.findElement(By.xpath(xpathExpression)).click();
    }

    public void waitPls(int timeout) throws InterruptedException {
        TimeUnit.SECONDS.sleep(timeout);
    }

    public void goToExchangerSite(String url) {
        wd.get(url);
    }

    public void cancelOrder() {
        wd.findElement(By.linkText("ОТМЕНИТЬ ЗАЯВКУ")).click();
    }

    public void clickOnElByID(String elementID) {
        wd.findElement(By.id(elementID)).click();
    }

    public void scrollDown(final String downOn) {
        JavascriptExecutor jse = (JavascriptExecutor) wd;
        jse.executeScript("window.scrollBy(0," + downOn + ")", "");
    }

    public void typeTextIntoElementByID(String elementID, String textToInput) {
        clickOnElByID(elementID);
        wd.findElement(By.id(elementID)).sendKeys(textToInput);
    }

    public void typeTextIntoElementByName(String elementName, String textToInput) {
        clickOnElementByName(elementName);
        wd.findElement(By.name(elementName)).sendKeys(textToInput);
    }

    public void clearElementByName(String elementName) {
        wd.findElement(By.name(elementName)).clear();
    }

    public void clickOnElementByName(String elementName) {
        wd.findElement(By.name(elementName)).click();
    }

    public void selectBcoin() {
        clickOnElByXpath("//ul[@id='calculation_param']/li[2]/div/div[1]/ul/li[12]/span");
    }

    public void selectQiwiRur() {
        clickOnElByCss("span.bank_name");
        clickOnElByXpath("//ul[@id='calculation_param']//span[.='Киви RUR']");
    }

    public void saveData(String targetWallet, String url) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        targetName = "outputData_" + sdf.format(System.currentTimeMillis()) + ".csv";
        Writer writer = new FileWriter("C:/parser_out/" + targetName, true);
        writer.write(String.format("%s;%s;%s\n", targetWallet, java.util.Calendar.getInstance().getTime(),url));
        writer.close();

    }

    public void stop() {
        wd.quit();
    }
}
