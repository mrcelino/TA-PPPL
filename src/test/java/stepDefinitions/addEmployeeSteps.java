package stepDefinitions;

import io.cucumber.java.en.*;
import io.cucumber.java.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class addEmployeeSteps {

    WebDriver driver = TestContext.getDriver();
    loginPage login = new loginPage(driver);
    mitraPage mitraPage = new mitraPage(driver);
    dashboardPage dashboardPage = new dashboardPage(driver);
    employeePage employeePage = new employeePage(driver);
    addEmployeePage addEmployeePage = new addEmployeePage(driver);


    @Given("the owner is logged in")
    public void theOwnerIsLoggedIn(){
        WebDriver driver = TestContext.getDriver();
        login.goToLoginPage();
        login.loginAs("marcelino@mail.ugm.ac.id", "12345678");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe("https://avesta.cloud/mitra"));
        assertEquals("https://avesta.cloud/mitra", driver.getCurrentUrl(), "Login Gagal!");
    }

    @And("the owner is on Mitra page {string}")
    public void theOwnerIsOnMitraPage(String url){
        assertEquals(url, driver.getCurrentUrl(), "Bukan di halaman Mitra.");
    }

    @When("the owner clicks the Dashboard button")
    public void theOwnerClicksTheDashboardBtn(){
        mitraPage.clickDashboardButton();
    }

    @And("the owner is redirected to Dashboard page {string}")
    public void theOwnerIsOnDashboardPage(String url){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(url));
        assertEquals(url, driver.getCurrentUrl(), "Bukan di Dashboard.");
    }

    @And("the owner clicks the Karyawan button")
    public void theOwnerClicksTheKaryawanBtn(){
        dashboardPage.clickKaryawan();
    }

    @And("the owner is redirected to Karyawan page {string}")
    public void theOwnerIsOnEmployeePage(String url){
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.urlToBe(url));
        assertEquals(url, driver.getCurrentUrl(), "Bukan di Karyawan.");
    }

    @And("the owner clicks the Tambah Karyawan button")
    public void theOwnerClicksTambahBtn(){
        employeePage.clickTambahKaryawan();
    }

    @And("the owner is redirected to Tambah Karyawan page {string}")
    public void theOwnerIsOnAddEmployeePage(String url){
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.urlToBe(url));

        assertEquals(url, driver.getCurrentUrl(), "Bukan di Tambah Karyawan.");

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
    }

    @And("the owner enters first name {string} and last name {string} and phone {string} and email {string}")
    public void theOwnerEntersEmployeeData(String fname, String lname, String phone, String email){
        addEmployeePage.enterFirstName(fname);
        addEmployeePage.enterLastName(lname);
        addEmployeePage.enterPhone(phone);
        addEmployeePage.enterEmail(email);
    }

    @And("the owner uploads a photo {string}")
    public void theOwnerUploadsAPhoto(String photoPath){
        addEmployeePage.uploadPhoto(photoPath);
    }

    @And("the owner clicks the Buat button")
    public void theOwnerClicksBuatBtn(){
        addEmployeePage.clickCreate();
    }

    @Then("a new employee should be successfully added")
    public void newEmployeeAdded(){
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains("/admin/karyawan"));
        assertTrue(driver.getCurrentUrl().contains("/admin/karyawan"),
                "Employee tidak ditambah.");
    }

    @Then("the registration should show a message {string}")
    public void registrationShouldShowMessage(String message){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.text-center.text-red-600.mt-4"))
        );

        assertEquals(message, error.getText().trim(), "Pesan error tidak sesuai.");
    }
}
