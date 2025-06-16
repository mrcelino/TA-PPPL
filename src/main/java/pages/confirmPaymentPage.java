package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class confirmPaymentPage {
    WebDriver driver;

    public confirmPaymentPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOnConfirmPaymentPage() {
        return driver.getCurrentUrl().contains("/payment");
    }

    public void clickAvestaPayButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By avestaPayButton = By.cssSelector("a.btn[href='/paymentconfirm']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(avestaPayButton));
        driver.findElement(avestaPayButton).click();
    }
}
