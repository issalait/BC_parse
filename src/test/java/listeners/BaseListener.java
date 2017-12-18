package listeners;

import appManager.ApplicationManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Driver;

import static io.qameta.allure.Allure.addByteAttachmentAsync;
import static io.qameta.allure.Allure.addStreamAttachmentAsync;
import static java.lang.ClassLoader.getSystemResourceAsStream;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Created by Любовь on 18.12.2017.
 */
public class BaseListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(final IInvokedMethod method, final ITestResult testResult) {
        // not implemented
    }

    @Override
    @Attachment
    public void afterInvocation(final IInvokedMethod method, final ITestResult testResult) {
        if (method.isTestMethod()) {
            addByteAttachmentAsync("Test log", "text/plain", "afterInvocationContent"::getBytes);

            if (!testResult.isSuccess()) {
                /*
                addStreamAttachmentAsync("Failure screenshot", "image/png", () ->
                        getSystemResourceAsStream("attachments/screenshot.png"));
                        */
                ApplicationManager app = (ApplicationManager) testResult.getTestContext().getAttribute("app");
                saveScreenshot(app.takeScreen());

            }
        }
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }



}

