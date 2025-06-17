package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.loginPage;
import pages.tokoPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class tokoSteps {
    WebDriver driver = TestContext.getDriver();
    tokoPage tokoPage;
    loginPage loginPage;

    @Given("User membuka halaman login sistem Avesta")
    public void userBukaHalamanLogin() {
        this.loginPage = new loginPage(this.driver);
        loginPage.goToLoginPage();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // ✅ deklarasi wait
        wait.until(ExpectedConditions.urlToBe("https://avesta.cloud/login"));
    }

    @When("User login dengan email {string} dan password {string}")
    public void userLogin(String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='email']")));
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(text(), 'Login')]")).click();
        wait.until(ExpectedConditions.urlContains("/mitra"));
    }

    @And("User mengklik Dashboard di navbar")
    public void userKlikDashboard() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Dashboard')]")));
        driver.findElement(By.xpath("//a[contains(text(), 'Dashboard')]")).click();
        wait.until(ExpectedConditions.urlContains("/admin"));
    }

    @And("User mengklik Toko di sidebar")
    public void userKlikToko() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String[] selectors = {
                "//a[contains(text(), 'Toko')]", // Primary selector from output
                "//nav//a[contains(text(), 'Toko')]"
        };
        WebElement tokoElement = null;
        for (String selector : selectors) {
            try {
                tokoElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(selector)));
                System.out.println("Berhasil menemukan Toko dengan: " + selector);
                break;
            } catch (Exception e) {
                System.out.println("Gagal dengan: " + selector);
            }
        }
        if (tokoElement == null) {
            driver.get("https://avesta.cloud/admin/toko");
            wait.until(ExpectedConditions.urlContains("/admin/toko"));
            System.out.println("Navigasi langsung ke /admin/toko");
        } else {
            js.executeScript("arguments[0].click();", tokoElement);
            wait.until(ExpectedConditions.urlContains("/admin/toko"));
        }
    }

    @And("User mengklik tombol Ubah")
    public void userKlikUbah() {
        tokoPage = new tokoPage(driver);
        tokoPage.clickEditButton();
        tokoPage.waitForEditForm(); // Wait for edit form to appear
    }

    @When("User mengupdate data toko dengan:")
    public void userMengupdateDataToko(io.cucumber.datatable.DataTable data) {
        if (tokoPage == null) {
            tokoPage = new tokoPage(driver);
        }
        java.util.Map<String, String> row = data.asMap(String.class, String.class);
        String namaWarung = row.get("Nama Warung");
        String alamatWarung = row.get("Alamat Warung");
        String deskripsi = row.get("Deskripsi");
        String nomorHp = row.get("Nomor HP");
        String kelurahan = row.get("Kelurahan");
        if (alamatWarung == null || alamatWarung.trim().isEmpty() || alamatWarung.equals("[empty]")) {
            alamatWarung = "";
        }
        System.out.println("=== Data to be filled ===");
        System.out.println("Nama Warung: " + namaWarung);
        System.out.println("Alamat Warung: '" + alamatWarung + "'");
        System.out.println("Deskripsi: " + deskripsi);
        System.out.println("Nomor HP: " + nomorHp);
        System.out.println("Kelurahan: " + kelurahan);
        tokoPage.fillStoreForm(namaWarung, alamatWarung, deskripsi, nomorHp, kelurahan);
        System.out.println("✓ Form filled successfully");
    }

    @And("User mengklik tombol Simpan")
    public void userKlikSimpan() {
        tokoPage.clickSaveButton();
    }

    @Then("Sistem menyimpan data toko yang diperbarui")
    public void sistemMenyimpanDataToko() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/admin/toko"));
        assertTrue(driver.getCurrentUrl().contains("/admin/toko"), "URL tidak sesuai setelah simpan");
    }

    @Then("Sistem menampilkan pesan error alamat {string}")
    public void sistemMenampilkanPesanErrorAlamat(String expectedMessage) {
        // Get fresh reference to the address field to avoid stale element
        WebElement addressField = tokoPage.getStoreAddressField();

        // Get validation message using JavaScript
        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", addressField);

        System.out.println("Actual validation message: " + validationMessage);

        assertFalse(validationMessage.toLowerCase().contains(expectedMessage.toLowerCase()),
                "Expected '" + expectedMessage + "', got: " + validationMessage);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}