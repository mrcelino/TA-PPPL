package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class homePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Lebih spesifik: cari input q di form action /cariayam
    private By searchInput = By.name("q");
    private By searchButton = By.xpath("//button[normalize-space()='Cari']");
    private By resultItems = By.cssSelector(".bg-white.rounded-2xl.shadow-md.border.p-3");
    private By noResultText = By.xpath("//p[contains(text(), 'Tidak ada produk ditemukan')]");

    public homePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private void waitForPageToLoad() {
        // Tunggu hingga document.readyState == complete
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(webDriver ->
                        ((JavascriptExecutor) webDriver)
                                .executeScript("return document.readyState")
                                .equals("complete")
                );
    }

    public void searchProduct(String keyword) {
        // 1) Tunggu page load
        waitForPageToLoad();

        // 2) Tunggu sampai searchInput terlihat
        WebElement inputElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchInput)
        );
        inputElement.clear();
        inputElement.sendKeys(keyword);

        // 3) Klik tombol Cari
        WebElement buttonElement = wait.until(
                ExpectedConditions.elementToBeClickable(searchButton)
        );
        buttonElement.click();

        // 4) Tunggu page load untuk hasil
        waitForPageToLoad();
    }

    public List<String> getProductNames() {
        List<WebElement> items = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(resultItems)
        );
        return items.stream()
                .map(WebElement::getText)
                .toList();
    }

    public boolean hasResults() {
        List<WebElement> items = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(resultItems)
        );
        System.out.println("Jumlah hasil pencarian: " + items.size());
        return !items.isEmpty();
    }

    public boolean isNoResultMessageDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(noResultText)
            ).isDisplayed();
        } catch (Exception e) {
            System.out.println("Tidak ada produk ditemukan untuk " + e.getMessage());
            return false;
        }
    }
}
