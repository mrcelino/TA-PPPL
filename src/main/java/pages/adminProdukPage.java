package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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
        String xpath = "//tr[td[contains(@class,'font-medium') and contains(text(),'" + productName + "')]]//button[span[text()='Hapus']]";
        WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        deleteBtn.click();
    }

    public void clickConfirmDeleteButton(String productName) {
        WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton));
        confirmBtn.click();

        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        shortWait.until(ExpectedConditions.invisibilityOf(confirmBtn));

        By productRowLocator = By.xpath("//tr[td[contains(@class, 'font-medium') and normalize-space(text())='" + productName + "']]");

        List<WebElement> productRows = driver.findElements(productRowLocator);
        if (!productRows.isEmpty()) {
            shortWait.until(ExpectedConditions.stalenessOf(productRows.get(0)));
        }
    }


    public boolean isProductVisibleByName(String productName) {
        try {
            By productNameLocator = By.xpath("//td[contains(@class, 'font-medium') and normalize-space(text())='" + productName + "']");
            WebElement product = wait.until(ExpectedConditions.presenceOfElementLocated(productNameLocator));
            return product.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }
}
