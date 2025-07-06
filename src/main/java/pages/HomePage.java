package pages;

// 👉 Page Object Model (POM) untuk HomePage.

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;

    // 👉 Tombol login di homepage
    @FindBy(css = "a.getstarted.scrollto")
    WebElement loginBtn;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickLoginButton() {
        loginBtn.click();
    }
}
