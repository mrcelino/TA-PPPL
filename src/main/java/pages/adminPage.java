package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class adminPage {
    WebDriver driver;
    WebDriverWait wait;

    By produkButton = By.cssSelector("a[href='/admin/produk']");


    public adminPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitForAdminPage() {
        wait.until(ExpectedConditions.urlContains("/admin"));
    }

    public void clickProdukButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(produkButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}
