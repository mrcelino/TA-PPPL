package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class productPage {
    WebDriver driver;
    WebDriverWait wait;

    public productPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void waitForPage(String urlFragment) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains(urlFragment));
    }

    By cartCount = By.cssSelector("a[href='/checkout'] div.absolute.rounded-full.bg-white");
    By productCards = By.cssSelector(".bg-white.rounded-2xl");

    public void open() {
        driver.get("http://127.0.1:8000/cariayam");
    }

    public boolean isOnProductListingPage() {
        return driver.getCurrentUrl().contains("/cariayam");
    }


    public int getCartCount() {
        try {
            String countText = driver.findElement(cartCount).getText();
            return Integer.parseInt(countText);
        } catch (Exception e) {
            return 0;
        }
    }

    public void clickOrderButtonForStore(String storeName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productCards));
        List<WebElement> cards = driver.findElements(productCards);

        for (WebElement card : cards) {
            List<WebElement> storeLabels = card.findElements(By.cssSelector("a.text-sm"));
            for (WebElement storeLabel : storeLabels) {
                String labelText = storeLabel.getText().trim();
                if (labelText.equalsIgnoreCase(storeName)) {
                    System.out.println("Klik tombol 'Pesan' untuk: " + storeName);
                    WebElement orderButton = card.findElement(By.xpath(".//button[contains(normalize-space(.), 'Pesan')]"));
                    orderButton.click();
                    return;
                }
            }
        }
        throw new NoSuchElementException("Tidak ditemukan produk dari warung: " + storeName);
    }

    public void clickAddToCartOnModal() {
        By modalLocator = By.cssSelector(".modal.modal-open .modal-box");
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalLocator));

        WebElement addToCartButton = driver.findElement(By.cssSelector(".modal.modal-open .modal-box button.bg-pink"));
        if (addToCartButton.isDisplayed() && addToCartButton.isEnabled()) {
            addToCartButton.click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".modal.modal-open")));
        } else {
            System.out.println("Tombol tidak bisa diklik!");
        }
    }


    public boolean isProductDetailModalVisible() {
        try {
            By modalTitle = By.xpath("//div[contains(@class, 'modal-box')]//h3[contains(@class, 'text-lg') and contains(text(), 'Detail Produk')]");
            WebElement modalHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(modalTitle));
            return modalHeading.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isSwitchStoreModalVisible() {
        try {
            By modalTitle = By.xpath("//h3[contains(text(), 'Apakah Anda ingin beralih ke toko ini?')]");
            WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(modalTitle));
            return modal.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

}