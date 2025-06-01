package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public void goToLoginPage() {
        driver.get("http://127.0.1:8000/login");
    }

    public void loginAs(String email, String password) {
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}
