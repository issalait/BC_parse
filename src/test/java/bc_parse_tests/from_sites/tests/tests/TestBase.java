package bc_parse_tests.from_sites.tests.tests;

import appManager.ApplicationManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

/**
 * Created by Любовь on 01.12.2017.
 */
public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();

    @BeforeMethod
    public void setUp() throws IOException {

        app.init();

    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }

    public ApplicationManager getApp() {
        return app;
    }
}
