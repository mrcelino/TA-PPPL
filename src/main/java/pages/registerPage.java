package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class registerPage {
    WebDriver driver;
    private WebDriverWait wait;

    // Constructor untuk inisialisasi driver
    public registerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Pindahkan ke sini
    }

    public void goToRegisterPage() {
        driver.get("https://avesta.cloud/register");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("html")));
    }

    // Element untuk Nama Depan
    public WebElement getFirstNameField() {
        By locator = By.xpath("//input[@placeholder='Nama Depan']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    // Element untuk Nama Belakang
    public WebElement getLastNameField() {
        By locator = By.xpath("//input[@placeholder='Nama Belakang']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    // Element untuk Email
    public WebElement getEmailField() {
        By locator = By.xpath("//input[@placeholder='Email']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    // Element untuk Nomor HP
    public WebElement getPhoneNumberField() {
        By locator = By.xpath("//input[@placeholder='Nomor HP']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    // Element untuk Sandi
    public WebElement getPasswordField() {
        By locator = By.xpath("//input[@placeholder='Sandi']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    // Element untuk Konfirmasi Sandi
    public WebElement getConfirmPasswordField() {
        By locator = By.xpath("//input[@placeholder='Konfirmasi Kata Sandi']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }


    public WebElement getAgreementCheckbox() {
        return driver.findElement(By.xpath("//input[@type='checkbox']"));
    }


    // Element untuk tombol Daftar
    public WebElement getRegisterButton() {
        return driver.findElement(By.xpath("//button[text()='Buat Akun']"));
    }

    // Element untuk pesan sukses registrasi
    public WebElement getSuccessMessage() {
        return driver.findElement(By.xpath("//div[contains(text(),'Registrasi berhasil')]"));
    }

    // Element untuk pesan error email tidak valid
    public WebElement getErrorMessage() {
        return driver.findElement(By.xpath("//div[contains(text(),'Email tidak valid')]"));
    }

    // Method untuk mengisi semua field
    public void fillRegistrationForm(String firstName, String lastName, String email, String phone, String password, String confirmPassword) {
        getFirstNameField().sendKeys(firstName);
        getLastNameField().sendKeys(lastName);
        getEmailField().sendKeys(email);
        getPhoneNumberField().sendKeys(phone);
        getPasswordField().sendKeys(password);
        getConfirmPasswordField().sendKeys(confirmPassword);
    }

    // Method untuk klik tombol Daftar
    public void clickRegisterButton() {
        getRegisterButton().click();
    }
}
