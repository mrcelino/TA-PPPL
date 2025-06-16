package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class employeePage {
    WebDriver driver;

    private By tambahBtn = By.cssSelector("a[href='/admin/tambah-karyawan']");

    public employeePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickTambahKaryawan(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

        // Tunggu sampai link "Tambah Karyawan" dapat diklik
        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/admin/tambah-karyawan']"))
        );

        button.click();
    }
}
