package steps;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ScreenshotUtil;
import org.testng.Assert;

import java.time.Duration;

public class LoginSteps {

    WebDriver driver = Hooks.driver;
    private static final Logger logger = LogManager.getLogger(LoginSteps.class);

    // ✅ Taruh di SINI! Bukan di tengah step
    LoginPage loginPage = new LoginPage(driver);

    @Given("User opens the application homepage")
    public void open_homepage() {
        String url = ConfigReader.getProperty("base.url");
        driver.get(url);
        logger.info("✅ Opened Homepage");
        ScreenshotUtil.attachStepScreenshot(driver, Hooks.scenario, "Opened_Homepage");
    }

    @When("User clicks the Login button")
    public void click_login() {
        WebElement loginBtn = driver.findElement(By.linkText("Login"));
        loginBtn.click();
        logger.info("✅ Clicked Login");
        ScreenshotUtil.attachStepScreenshot(driver, Hooks.scenario, "After_Click_Login");
    }

    @Then("User should be redirected to login URL")
    public void verify_login_url() {
        String currentUrl = driver.getCurrentUrl();
        logger.info("🔗 Current URL: " + currentUrl);
        Assert.assertTrue(currentUrl.toLowerCase().contains("/login"), "URL harus mengandung /login");
    }

    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        loginPage.openLoginPage();
    }

    @When("User enters valid username and password")
    public void user_enters_valid_username_and_password() {
        loginPage.enterUsername("a@a.com");
        ScreenshotUtil.attachStepScreenshot(driver, Hooks.scenario, "After_username");
        loginPage.enterPassword("123");
        ScreenshotUtil.attachStepScreenshot(driver, Hooks.scenario, "After_password");
    }

    @When("User clicks the login button")
    public void user_clicks_the_login_button() {
        loginPage.clickLogin();
        //ScreenshotUtil.attachStepScreenshot(driver, Hooks.scenario, "After_click_loginBtn");
    }

    @Then("User should see the admin page")
    public void user_should_see_the_admin_page() {
        loginPage.verifyAdminPageVisible();
    }
}
