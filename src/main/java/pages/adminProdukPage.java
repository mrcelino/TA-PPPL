package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class adminProdukPage {
    WebDriver driver;
    WebDriverWait wait;

    By confirmDeleteButton = By.xpath("//button[text()='Konfirmasi']");

    public adminProdukPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void waitForProdukPage() {
        wait.until(ExpectedConditions.urlContains("/admin/produk"));
    }

    public void clickDeleteButtonByProductName(String productName) {
        String xpath = "//tr[td[contains(@class,'font-medium') and text()='" + productName + "']]//button[span[text()='Hapus']]";
        WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        deleteBtn.click();
    }

    public void clickConfirmDeleteButton() {
        WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton));
        confirmBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(confirmBtn)); // tunggu tombol hilang
    }

    public boolean isProductVisibleByName(String productName) {
        try {
            By productNameLocator = By.xpath("//td[contains(@class, 'font-medium') and text()='" + productName + "']");
            wait.until(ExpectedConditions.presenceOfElementLocated(productNameLocator));
            return true; // produk masih ada
        } catch (TimeoutException e) {
            return false; // produk tidak ditemukan
        }
    }
}
