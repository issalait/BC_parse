package bc_parse_tests.from_sites.tests.tests;

import appManager.ApplicationManager;

import io.qameta.allure.Attachment;
import listeners.BaseListener;
import listeners.TestListener;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.io.IOException;

/**
 * Created by Любовь on 01.12.2017.
 */

@Listeners(BaseListener.class)
public class TestBase {


    protected final ApplicationManager app = new ApplicationManager();

    @BeforeTest
    public void setUp(ITestContext context) throws Exception, InterruptedException {

        app.init();
        context.setAttribute("app", app);

    }

    @AfterTest
    public void tearDown() {
        app.stop();
    }

    public ApplicationManager getApp() {
        return app;
    }
}
