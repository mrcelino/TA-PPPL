package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class mitraPage {
    WebDriver driver;

    private By dashboardBtn = By.cssSelector("a[href='/admin']");

    public mitraPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickDashboard(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(dashboardBtn));
        driver.findElement(dashboardBtn).click();
    }
}
