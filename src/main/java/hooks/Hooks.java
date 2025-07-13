package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import factory.DriverFactory;
import utils.ScreenshotUtil;

public class Hooks {

    public static WebDriver driver;
    public static Scenario scenario;
    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario sc) {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        scenario = sc;
        logger.info("🔧 Browser started.");
    }

    @After
    public void tearDown(Scenario sc) {
        if (sc.isFailed()) {
            ScreenshotUtil.attachScreenshot(driver, sc, "FAILED");
        } else {
            ScreenshotUtil.attachScreenshot(driver, sc, "PASSED");
        }

        DriverFactory.quitDriver();
        logger.info("✅ Browser closed.");
    }
}
