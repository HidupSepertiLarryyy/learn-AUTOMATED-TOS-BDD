package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import drivers.DriverSingleton;

import java.io.File;
import java.io.IOException;

public class Hooks {
    public static WebDriver driver;
    public static Scenario scenario; // ✅ SIMPEN GLOBAL

    @Before
    public void setUp(Scenario sc) {
        driver = DriverSingleton.getDriver();
        scenario = sc; // ✅ SIMPEN Scenario
        System.out.println("🔧 Browser started.");
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
            System.out.println("✅ Browser closed.");
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
