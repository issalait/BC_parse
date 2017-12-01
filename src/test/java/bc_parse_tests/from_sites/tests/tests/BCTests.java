package bc_parse_tests.from_sites.tests.tests;

/**
 * Created by Любовь on 28.11.2017.
 */

import objectModels.BCData;
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
        app.getNavHelper().goToExchangerSite(url);
        setTestData();

        app.getBaseHelper().typeTextIntoElementByID("account1", bcData.getQiwiWallet());
        app.getBaseHelper().clearElementByName("sum1");
        app.getBaseHelper().typeTextIntoElementByName("sum1", "10000");
        app.getNavHelper().scrollDown("500");
        app.getBaseHelper().typeTextIntoElementByID("cf6", bcData.getEmail());
        app.getBaseHelper().typeTextIntoElementByID("account2",bcData.getBCWallet());
        app.getBaseHelper().clickOnElByCss("input.xchange_submit");
        app.waitPls(10);
        System.out.println("Прошли первую страницу: ");
        app.getBaseHelper().clickOnElByXpath("//*[@id='exchange_status_html']/form/div[2]/div/div[4]/div");
        app.waitPls(5);
        app.getNavHelper().scrollDown("250");
        app.getBaseHelper().clickOnElByID("check_rule_step_input");
        app.waitPls(5);
        System.out.println("Прошли вторую страницу: ");
        targetWallet = app.getBaseHelper().getTextByXpath("//*[@id='exchange_status_html']/div[2]/div/div[2]/div/div[1]/ol/li[2]/span/strong");
        System.out.println("targetWallet: "+targetWallet);
        app.getOrderHelper().cancelOrder();
        app.saveData(targetWallet, url);
    }

    @Test
    public void bc_test1() throws IOException, InterruptedException {
        url="https://fastchange.cc/";
        app.getNavHelper().goToExchangerSite(url);
        app.getOrderHelper().selectQiwiRur();
        app.getOrderHelper().selectBcoin();
        setTestData();
        app.getBaseHelper().typeTextIntoElementByID("wallet_one", bcData.getQiwiWallet());
        app.getBaseHelper().clearElementByName("give_money");
        app.getBaseHelper().typeTextIntoElementByName("give_money", "1000");
        app.getBaseHelper().typeTextIntoElementByID("e_mail", bcData.getEmail());
        app.getBaseHelper().typeTextIntoElementByID("wallet_two",bcData.getBCWallet());
        app.waitPls(60);
        app.getBaseHelper().clickOnElByXpath("//ul[@id='application_param']/li[9]/input");
        app.waitPls(3);
        targetWallet = app.getBaseHelper().getTextByID("finish_kard");
        app.saveData(targetWallet, url);

    }


}
