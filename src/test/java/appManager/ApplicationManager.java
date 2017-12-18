package appManager;


import objectModels.ProxyData;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import io.qameta.allure.Step;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;



/**
 * Created by Любовь on 01.12.2017.
 */
public class ApplicationManager {
    private BaseHelper baseHelper;
    private OrderHelper orderHelper;
    private NavHelper navHelper;
    ChromeOptions o;
    String targetName;
    String chromeProfile;
    ChromeDriver wd;
    String proxyAdress;
    String proxyPort;
    String proxyLogin;
    String proxyPass;
    ProxyData proxyData;
    Proxy proxy;


    int min;
    int max;
    int rnd;

    @BeforeMethod(description = "Configure proxy and browser before tests")
    public void init() throws IOException, InterruptedException {
       //waitBeforeStart();
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
        setProxy();
        System.out.println(proxyData.getProxyAdress()+":"+proxyData.getProxyPort());

        System.out.println("chromeProfile is : ");
        System.out.println(chromeProfile);
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--proxy-server=http://"+proxyData.getProxyAdress()+":"+proxyData.getProxyPort());
        options.addArguments("user-data-dir=" + chromeProfile);
        options.addArguments("--start-maximized");
        wd = new ChromeDriver(options);

        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        baseHelper = new BaseHelper(wd);
        orderHelper = new OrderHelper(wd);
        navHelper = new NavHelper(wd);
    }

    private void waitBeforeStart() throws InterruptedException {
        rnd = getRnd(1, 45);
        System.out.println("Sleep before start, minutes : "+String.valueOf(rnd));
        TimeUnit.MINUTES.sleep(rnd);
    }


    public static int getRnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    @Step
    public void setProxy() throws IOException {
        min = 1; // Минимальное число для диапазона
        max = 5; // Максимальное число для диапазона
        rnd = getRnd(min, max);

        System.out.println("Псевдослучайное целое число: " + rnd);

        BufferedReader reader = new BufferedReader(new FileReader(new File("C:/parser_settings/proxyData.txt")));
        String line = reader.readLine();
        for (int i = 0; i < rnd; i++) {
            String[] split = line.split(":");
            proxyAdress =split[0];
            proxyPort =split[1];

            line = reader.readLine();
        }
        proxyData = new ProxyData(proxyAdress, proxyPort);

    }


    public void waitPls(int timeout) throws InterruptedException {
        TimeUnit.SECONDS.sleep(timeout);
    }

    @Step
    public void saveData(String targetWallet, String url) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        targetName = "outputData_" + sdf.format(System.currentTimeMillis()) + ".csv";
        Writer writer = new FileWriter("C:/parser_out/" + targetName, true);
        writer.write(String.format("%s;%s;%s\n", targetWallet, java.util.Calendar.getInstance().getTime(),url));
        writer.close();

    }

    public ByteArrayInputStream takeScreenshot() {
        return new ByteArrayInputStream(((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES));
    }

    @AfterMethod(description = "close browser after")
    public void stop() {
        wd.quit();
    }


    public BaseHelper getBaseHelper() {
        return baseHelper;
    }

    public NavHelper getNavHelper() {
        return navHelper;
    }

    public OrderHelper getOrderHelper() {
        return orderHelper;
    }

    //метод для снятия скринов
    public byte[] takeScreen (){
        return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
    }
}
