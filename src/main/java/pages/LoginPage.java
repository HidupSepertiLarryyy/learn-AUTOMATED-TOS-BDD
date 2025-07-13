package pages;

// 👉 Page Object Model (POM) untuk HomePage.

import factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;

    // 👉 Tombol login di homepage
    @FindBy(css = "a.getstarted.scrollto")
    WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickLoginButton() {
        loginBtn.click();
    }

    public void openLoginPage() {
        driver.get("https://localhost:7279/Authentication/Login");
    }

    public void verifyLoginFormVisible() {
        assert driver.findElement(By.id("formLogin")).isDisplayed();
    }

    public void enterUsername(String username) {
        driver.findElement(By.id("Email")).clear();
        driver.findElement(By.id("Email")).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("Password")).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(By.id("btn_login")).click();
    }

    public void verifyAdminPageVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dashboardMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Dashboard']")));
        assert dashboardMenu.isDisplayed() : "Dashboard menu not visible, user might not be logged in as Admin.";
    }

}
