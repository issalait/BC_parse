package appManager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Любовь on 01.12.2017.
 */
public class NavHelper {
    private ChromeDriver wd;
    public NavHelper(ChromeDriver wd) {
        this.wd = wd;
    }

    public void goToExchangerSite(String url) {
        wd.get(url);
    }

    public void scrollDown(final String downOn) {
        JavascriptExecutor jse = (JavascriptExecutor) wd;
        jse.executeScript("window.scrollBy(0," + downOn + ")", "");
    }
}
