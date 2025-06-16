package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class dashboardPage {
    WebDriver driver;

    private By karyawanBtn = By.cssSelector("a[href='/admin/karyawan']");

    public dashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickKaryawan(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(karyawanBtn));
        driver.findElement(karyawanBtn).click();
    }
}
