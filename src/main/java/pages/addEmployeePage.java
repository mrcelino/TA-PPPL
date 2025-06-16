package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class addEmployeePage {
    WebDriver driver;

    private By firstName = By.name("nama_depan");
    private By lastName = By.name("nama_belakang");
    private By phone = By.name("no_telepon");
    private By email = By.name("email");
    private By photo = By.cssSelector("#fotoInput");
    private By createBtn = By.cssSelector("button[type='submit']");

    public addEmployeePage(WebDriver driver) {
        this.driver = driver;
    }

    private void waitForElement(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void enterFirstName(String fname) {
        waitForElement(firstName);
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(fname);
    }

    public void enterLastName(String lname) {
        waitForElement(lastName);
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(lname);
    }

    public void enterPhone(String phoneNumber) {
        waitForElement(phone);
        driver.findElement(phone).clear();
        driver.findElement(phone).sendKeys(phoneNumber);
    }

    public void enterEmail(String emailId) {
        waitForElement(email);
        driver.findElement(email).clear();
        driver.findElement(email).sendKeys(emailId);
    }

    public void uploadPhoto(String photoPath) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement photoInput = driver.findElement(photo);
        // Jadikan visible dahulu
        js.executeScript("arguments[0].style.display='block'", photoInput);
        photoInput.sendKeys(photoPath);
    }

    public void clickCreate(){
        waitForElement(createBtn);
        driver.findElement(createBtn).click();
    }
}
