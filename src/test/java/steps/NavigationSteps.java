package steps;

// ✅ Import Hooks ➜ Buat akses driver & scenario global
import hooks.Hooks;

// ✅ Cucumber annotation ➜ Mapping step ke Gherkin
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// ✅ Log4j2 logger
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// ✅ Selenium API
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ConfigReader;

// ✅ Assertion TestNG
import org.testng.Assert;

// ✅ ScreenshotUtil biar ga duplicate code
import utils.ScreenshotUtil;

public class NavigationSteps {

    // ✅ Pakai driver global dari Hooks
    WebDriver driver = Hooks.driver;

    // ✅ Logger khusus NavigationSteps class
    private static final Logger logger = LogManager.getLogger(NavigationSteps.class);

    /**
     * ✅ Step: User buka homepage
     * - Buka URL
     * - Log info
     * - Screenshot step ➜ via ScreenshotUtil
     */
    @Given("User opens the application homepage")
    public void open_homepage() {
        String url = ConfigReader.getProperty("base.url");
        driver.get(url);
        logger.info("✅ Opened Homepage");
        ScreenshotUtil.attachStepScreenshot(driver, Hooks.scenario, "Opened_Homepage");
    }

    /**
     * ✅ Step: User klik tombol Login
     * - Temukan element
     * - Klik
     * - Log info
     * - Screenshot step ➜ via ScreenshotUtil
     */
    @When("User clicks the Login button")
    public void click_login() {
        WebElement loginBtn = driver.findElement(By.linkText("Login"));
        loginBtn.click();
        logger.info("✅ Clicked Login");
        ScreenshotUtil.attachStepScreenshot(driver, Hooks.scenario, "After_Click_Login");
    }

    /**
     * ✅ Step: Verifikasi URL berisi /login
     * - Ambil current URL
     * - Logging
     * - Assert
     */
    @Then("User should be redirected to login URL")
    public void verify_login_url() {
        String currentUrl = driver.getCurrentUrl();
        logger.info("🔗 Current URL: " + currentUrl);
        Assert.assertTrue(currentUrl.toLowerCase().contains("/login"), "URL harus mengandung /login");
    }

}
