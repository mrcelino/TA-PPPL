package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.registerPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class registerSteps {
    WebDriver driver;
    registerPage registerPage;

    @Given("User membuka halaman registrasi sistem Avesta")
    public void userBukaHalamanRegistrasi() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://127.0.0.1:8000/register");
        registerPage = new registerPage(driver);
    }

    @And("Mengisi semua field dengan data valid:")
    public void mengisiFieldDenganDataValid(io.cucumber.datatable.DataTable data) {
        Map<String, String> row = data.asMap(String.class, String.class);
        registerPage.fillRegistrationForm(
                row.get("Nama Depan"),
                row.get("Nama Belakang"),
                row.get("Email"),
                row.get("Nomor HP"),
                row.get("Sandi"),
                row.get("Konfirmasi Sandi")
        );
    }

    @When("Mengisi field dengan data:")
    public void mengisi_field_dengan_data(io.cucumber.datatable.DataTable data) {
        Map<String, String> row = data.asMap(String.class, String.class);
        registerPage.fillRegistrationForm(
                row.get("Nama Depan"),
                row.get("Nama Belakang"),
                row.get("Email"),
                row.get("Nomor HP"),
                row.get("Sandi"),
                row.get("Konfirmasi Sandi")
        );
    }

    @And("Mencentang checkbox persetujuan")
    public void mencentangCheckboxPersetujuan() {
        registerPage.getAgreementCheckbox().click();
    }

    @And("Mengklik tombol 'Daftar'")
    public void mengklikTombolDaftar() {
        registerPage.clickRegisterButton();
    }

    @Then("Sistem menyimpan data user dengan role Pembeli")
    public void sistemMenyimpanDataUser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://127.0.0.1:8000/login"));
        assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @And("Mengarahkan ke halaman login")
    public void mengarahkan_ke_halaman_login() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/login"));
        assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Then("Menampilkan pesan 'Registrasi berhasil'")
    public void menampilkanPesanRegistrasiBerhasil() {
        assertTrue(registerPage.getSuccessMessage().isDisplayed());
        assertEquals("Registrasi berhasil", registerPage.getSuccessMessage().getText());
    }

    @Then("Sistem menampilkan pesan error email tidak valid")
    public void sistemMenampilkanPesanErrorEmail() {
        WebElement emailField = registerPage.getEmailField();
        emailField.sendKeys(" ");
        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", emailField);
        System.out.println("Browser validation message: " + validationMessage);
        assertTrue(validationMessage.contains("@"));
    }

    @Then("Sistem menampilkan pesan error {string}")
    public void sistem_menampilkan_pesan_error(String expectedMessage) {
        WebElement emailField = registerPage.getEmailField();
        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", emailField);
        System.out.println("Browser validation message: " + validationMessage);
        assertTrue(validationMessage.toLowerCase().contains(expectedMessage.toLowerCase()));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
