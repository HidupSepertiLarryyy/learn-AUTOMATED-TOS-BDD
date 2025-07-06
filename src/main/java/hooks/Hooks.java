package hooks;

// ✅ Import hook annotation Cucumber
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

// ✅ Apache Commons IO buat copy file screenshot
import org.apache.commons.io.FileUtils;

// ✅ Logger pakai Log4j2
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// ✅ Selenium API buat screenshot
import org.openqa.selenium.WebDriver;

// ✅ Panggil driver singleton bikin 1 browser instance
import drivers.DriverSingleton;

// ✅ Panggil util baru ScreenshotUtil
import utils.ScreenshotUtil;

public class Hooks {

    // ✅ Global webdriver yang bisa dipanggil step manapun
    public static WebDriver driver;

    // ✅ Simpen skenario aktif (buat attach screenshot per step)
    public static Scenario scenario;

    // ✅ Inisialisasi logger Log4j2, target class = Hooks
    private static final Logger logger = LogManager.getLogger(Hooks.class);

    /**
     * ✅ Method ini jalan sebelum scenario mulai.
     * - Dapat Scenario dari Cucumber
     * - Ambil driver dari singleton (Chrome, Edge, dll)
     * - Log info.
     */
    @Before
    public void setUp(Scenario sc) {
        driver = DriverSingleton.getDriver();
        scenario = sc;
        logger.info("🔧 Browser started.");
    }

    /**
     * ✅ Method ini jalan sesudah scenario selesai.
     * - Kalau fail ➜ attach screenshot "FAILED"
     * - Kalau pass ➜ attach screenshot "PASSED"
     * - Selalu quit driver di akhir.
     */
    @After
    public void tearDown(Scenario sc) {
        if (sc.isFailed()) {
            ScreenshotUtil.attachScreenshot(driver, sc, "FAILED");
        } else {
            ScreenshotUtil.attachScreenshot(driver, sc, "PASSED");
        }

        if (driver != null) {
            driver.quit();
            logger.info("✅ Browser closed.");
        }
    }
}
