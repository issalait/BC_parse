package appManager;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

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
        baseHelper = new BaseHelper(wd);
        orderHelper = new OrderHelper(wd);
        navHelper = new NavHelper(wd);
    }

    public void waitPls(int timeout) throws InterruptedException {
        TimeUnit.SECONDS.sleep(timeout);
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

    public BaseHelper getBaseHelper() {
        return baseHelper;
    }

    public NavHelper getNavHelper() {
        return navHelper;
    }

    public OrderHelper getOrderHelper() {
        return orderHelper;
    }
}
