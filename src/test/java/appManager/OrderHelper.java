package appManager;

import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Любовь on 01.12.2017.
 */
public class OrderHelper extends BaseHelper{

    public OrderHelper(ChromeDriver wd) {
        super(wd);
    }

    public void cancelOrder() {
        clickOnElByCss(".cancel_paybutton");
    }

    public void selectBcoin() {
        clickOnElByXpath("//ul[@id='calculation_param']/li[2]/div/div[1]/ul/li[12]/span");
    }

    public void selectQiwiRur() {
        clickOnElByCss("span.bank_name");
        clickOnElByXpath("//ul[@id='calculation_param']//span[.='Киви RUR']");
    }
}
