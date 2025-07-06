package steps;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import org.testng.Assert;

import java.io.File;
import java.io.IOException;

public class NavigationSteps {

    WebDriver driver = Hooks.driver;

    @Given("User opens the application homepage")
    public void open_homepage() {
        driver.get("https://localhost:7279/");
        System.out.println("✅ Opened Homepage");
        takeStepScreenshot("Opened_Homepage");
    }

    @When("User clicks the Login button")
    public void click_login() {
        WebElement loginBtn = driver.findElement(By.linkText("Login"));
        loginBtn.click();
        System.out.println("✅ Clicked Login");
        takeStepScreenshot("After_Click_Login");
    }

    @Then("User should be redirected to login URL")
    public void verify_login_url() {
        String currentUrl = driver.getCurrentUrl();
        System.out.println("🔗 Current URL: " + currentUrl);
        Assert.assertTrue(currentUrl.toLowerCase().contains("/login"), "URL harus mengandung /login");
    }

    private void takeStepScreenshot(String name) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            String fileName = "screenshots/" + name + ".png";
            FileUtils.copyFile(src, new File(fileName));
            System.out.println("📸 Step Screenshot saved: " + fileName);

            byte[] bytes = ts.getScreenshotAs(OutputType.BYTES);
            Hooks.scenario.attach(bytes, "image/png", name);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
