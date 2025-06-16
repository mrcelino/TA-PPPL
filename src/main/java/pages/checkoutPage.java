package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class checkoutPage {
    WebDriver driver;

    public checkoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("http://127.0.1:8000/checkout");
    }

    public void increaseQuantityUntilOverSaldo() {
        int currentTotal = getTotalHarga();

        // Tunggu dulu tombolnya muncul
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By plusButton = By.xpath("//div[text()='+'][contains(@class, 'cursor-pointer') and contains(@class, 'rounded-full')]");

        wait.until(ExpectedConditions.elementToBeClickable(plusButton));

        while (currentTotal <= 100000) {
            driver.findElement(plusButton).click();
            currentTotal = getTotalHarga();
        }
    }


    private int getTotalHarga() {
        WebElement hargaElement = driver.findElement(
                By.xpath("//span[normalize-space(text())='Total Belanja']/following-sibling::span")
        );
        String totalText = hargaElement.getText().replaceAll("[^0-9]", "");
        return Integer.parseInt(totalText);
    }

    public void clickConfirmButton() {
        driver.findElement(By.xpath("//button[normalize-space(text())='Konfirmasi']")).click();
    }
}
