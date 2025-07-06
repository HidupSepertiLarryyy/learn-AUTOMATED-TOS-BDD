package utils;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * ✅ Util untuk ambil & simpan screenshot.
 * Dipakai di Hooks & Steps ➜ DRY (Don't Repeat Yourself)
 */
public class ScreenshotUtil {

    private static final Logger logger = LogManager.getLogger(ScreenshotUtil.class);

    /**
     * Ambil screenshot & attach ke report + save ke folder /screenshots
     * @param driver  Driver aktif
     * @param sc      Scenario cucumber
     * @param status  Label status (PASSED / FAILED / StepName)
     */
    public static void attachScreenshot(WebDriver driver, Scenario sc, String status) {
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

    /**
     * Dipanggil Steps ➜ Simpan screenshot step by name.
     */
    public static void attachStepScreenshot(WebDriver driver, Scenario sc, String stepName) {
        attachScreenshot(driver, sc, stepName);
    }
}
