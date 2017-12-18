package listeners;

import appManager.ApplicationManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

import static io.qameta.allure.Allure.addByteAttachmentAsync;
import static io.qameta.allure.Allure.addStreamAttachmentAsync;
import static java.lang.ClassLoader.getSystemResourceAsStream;


/**
 * Created by Любовь on 18.12.2017.
 */
public class BaseListener implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(final IInvokedMethod method, final ITestResult testResult) {
        // not implemented
    }

    @Override
    public void afterInvocation(final IInvokedMethod method, final ITestResult testResult) {
        if (method.isTestMethod()) {
            addByteAttachmentAsync("Test log", "text/plain", "afterInvocationContent"::getBytes);

            if (!testResult.isSuccess()) {
                /*
                addStreamAttachmentAsync("Failure screenshot", "image/png", () ->
                        getSystemResourceAsStream("attachments/screenshot.png"));*/
                ApplicationManager app = (ApplicationManager) testResult.getTestContext().getAttribute("app");
                saveScreenshot(app.takeScreenshot());
            }
        }
    }
    @Attachment
    public void onTestFailure(ITestResult result) {
        ApplicationManager app = (ApplicationManager) result.getTestContext().getAttribute("app");
        saveScreenshot(app.takeScreenshot());

    }

    public void saveScreenshot(ByteArrayInputStream screenShot) {
        Allure.addAttachment("Screenshot", "image/png", screenShot, "png");
    }





}

