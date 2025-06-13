package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class registerPage {
    WebDriver driver;

    // Constructor untuk inisialisasi driver
    public registerPage(WebDriver driver) {
        this.driver = driver;
    }

    // Element untuk Nama Depan
    public WebElement getFirstNameField() {
        return driver.findElement(By.xpath("//input[@placeholder='Nama Depan']"));
    }

    // Element untuk Nama Belakang
    public WebElement getLastNameField() {
        return driver.findElement(By.xpath("//input[@placeholder='Nama Belakang']"));
    }

    // Element untuk Email
    public WebElement getEmailField() {
        return driver.findElement(By.xpath("//input[@placeholder='Email']"));
    }

    // Element untuk Nomor HP
    public WebElement getPhoneNumberField() {
        return driver.findElement(By.xpath("//input[@placeholder='Nomor HP']"));
    }

    // Element untuk Sandi
    public WebElement getPasswordField() {
        return driver.findElement(By.xpath("//input[@placeholder='Sandi']"));
    }

    // Element untuk Konfirmasi Sandi
    public WebElement getConfirmPasswordField() {
        return driver.findElement(By.xpath("//input[@placeholder='Konfirmasi Kata Sandi']"));
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
