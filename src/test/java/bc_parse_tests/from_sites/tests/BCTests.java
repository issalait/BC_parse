package bc_parse_tests.from_sites.tests;

/**
 * Created by Любовь on 28.11.2017.
 */

import objectModels.BCData;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BCTests extends TestBase {
    String qiwiWallet;
    String userEmail;
    String bcWallet;
    String targetWallet;
    String url;
    int min;
    int max;
    int rnd;
    BCData bcData;

    public static int getRnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public void setTestData() throws IOException {
        min = 1; // Минимальное число для диапазона
        max = 20; // Максимальное число для диапазона
        rnd = getRnd(min, max);

        System.out.println("Псевдослучайное целое число: " + rnd);

        BufferedReader reader = new BufferedReader(new FileReader(new File("C:/parser_settings/inputData.csv")));
        String line = reader.readLine();
        for (int i = 0; i < rnd; i++) {
            String[] split = line.split(";");
            qiwiWallet = split[0];
            userEmail = split[1];
            bcWallet = split[2];
            line = reader.readLine();
        }
        bcData = new BCData(qiwiWallet,userEmail,bcWallet);

    }

    @Test
    public void bc_test2() throws InterruptedException, IOException {
        url="https://betatransfer.net/exchange_QIWIRUB_BTC/";
        goToExchangerSite(url);
        setTestData();

        typeTextIntoElementByID("account1", bcData.getQiwiWallet());
        clearElementByName ("sum1");
        typeTextIntoElementByName("sum1", "10000");
        scrollDown("500");
        typeTextIntoElementByID("cf6", bcData.getEmail());
        typeTextIntoElementByID ("account2",bcData.getBCWallet());
        clickOnElByCss("input.xchange_submit");
        waitPls(10);
        System.out.println("Прошли первую страницу: ");
        clickOnElByXpath("//*[@id='exchange_status_html']/form/div[2]/div/div[4]/div");
        waitPls(5);
        scrollDown("250");
        clickOnElByID("check_rule_step_input");
        waitPls(5);
        System.out.println("Прошли вторую страницу: ");
        targetWallet = getTextByXpath("//*[@id='exchange_status_html']/div[2]/div/div[2]/div/div[1]/ol/li[2]/span/strong");
        System.out.println("targetWallet: "+targetWallet);
        cancelOrder();
        saveData(targetWallet, url);
    }

    @Test
    public void bc_test1() throws IOException, InterruptedException {
        url="https://fastchange.cc/";
        goToExchangerSite(url);
        selectQiwiRur();
        selectBcoin();
        setTestData();
        typeTextIntoElementByID("wallet_one", bcData.getQiwiWallet());
        clearElementByName ("give_money");
        typeTextIntoElementByName("give_money", "1000");
        typeTextIntoElementByID("e_mail", bcData.getEmail());
        typeTextIntoElementByID ("wallet_two",bcData.getBCWallet());
        waitPls(60);
        clickOnElementByName("captcha");
        clickOnElByXpath("//ul[@id='application_param']/li[9]/input");
        waitPls(10);
        targetWallet = wd.findElement(By.id("finish_kard")).getText();
        saveData(targetWallet, url);

    }


}
