package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class paymentPage {
    WebDriver driver;
    WebDriverWait wait;

    public paymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // ✅ tambahkan ini
    }

    public boolean isOnPaymentPage() {
        return driver.getCurrentUrl().contains("/paymentconfirm");
    }

    public void inputPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By passwordInput = By.cssSelector("input[type='password']");
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickConfirm() {
        driver.findElement(By.xpath("//button[normalize-space()='Konfirmasi']")).click();
    }

    public String getErrorMessage() {
        try {
            By errorModalTitle = By.xpath("//div[contains(@class,'modal-open')]//h3[contains(text(),'Saldo Tidak Mencukupi')]");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement modalTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(errorModalTitle));
            return modalTitle.getText();
        } catch (TimeoutException e) {
            return "";
        }
    }


    public boolean isTransactionSuccessful() {
        try {
            By modalTitle = By.xpath("//h3[contains(normalize-space(string()), 'Pembayaran Terkonfirmasi')]");
            WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(modalTitle));
            return modal.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
