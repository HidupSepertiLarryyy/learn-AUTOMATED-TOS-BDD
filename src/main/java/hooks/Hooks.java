package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import drivers.DriverSingleton;

import java.io.File;
import java.io.IOException;

public class Hooks {
    public static WebDriver driver;
    public static Scenario scenario;

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario sc) {
        driver = DriverSingleton.getDriver();
        scenario = sc;
        logger.info("🔧 Browser started.");
    }

    @After
    public void tearDown(Scenario sc) {
        if (sc.isFailed()) {
            attachScreenshot(sc, "FAILED");
        } else {
            attachScreenshot(sc, "PASSED");
        }

        if (driver != null) {
            driver.quit();
            logger.info("✅ Browser closed.");
        }
    }

    private void attachScreenshot(Scenario sc, String status) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] bytes = ts.getScreenshotAs(OutputType.BYTES);

            File src = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File("screenshots/"
                    + sc.getName().replaceAll(" ", "_")
                    + "_" + status + ".png"));

            sc.attach(bytes, "image/png", "Screenshot_" + status);
            logger.info("📸 Screenshot attached: " + status);

        } catch (IOException e) {
            logger.error("❌ Error when taking screenshot", e);
        }
    }
}
