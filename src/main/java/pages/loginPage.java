package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class loginPage {
    WebDriver driver;

    By emailInput = By.cssSelector("input[placeholder='Email']");
    By passwordInput = By.cssSelector("input[placeholder='Sandi']");
    By loginButton = By.xpath("//button[contains(text(), 'Login')]");
    By errorMessage = By.cssSelector(".text-danger");

    public loginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        WebElement emailField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailInput)
        );
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        WebElement passwordField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordInput)
        );
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(loginButton)
        );
        button.click();
    }

    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage)
        );
        return error.getText();
    }

    public void goToLoginPage() {
        driver.get("https://avesta.cloud/login");
    }

    public void loginAs(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }
}
