package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class loginPage {
    WebDriver driver;

    By emailInput = By.cssSelector("input[placeholder='Email']");
    By passwordInput = By.cssSelector("input[placeholder='Sandi']");
    By loginButton = By.xpath("//button[contains(text(), 'Login')]");

    public loginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void goToLoginPage() {
        driver.get("https://avesta.cloud/login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("html")));
        // Tunggu sampai input email muncul
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@type='email' and @placeholder='Email']")));
    }

    public void loginAs(String email, String password) {
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}
