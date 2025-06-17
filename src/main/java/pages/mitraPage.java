package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class mitraPage {
    WebDriver driver;
    WebDriverWait wait;

    By dashboardButton = By.xpath("//a[contains(text(),'Dashboard')]");

    public mitraPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitForMitraPage() {
        wait.until(ExpectedConditions.urlContains("https://avesta.cloud/mitra"));
    }

    public void clickDashboardButton() {
        wait.until(ExpectedConditions.elementToBeClickable(dashboardButton)).click();
    }
}
