package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import static org.junit.jupiter.api.Assertions.*;

public class deleteProductSteps {

    WebDriver driver;
    loginPage login;
    mitraPage mitra;
    adminPage admin;
    adminProdukPage produk;

    String targetProductName; // untuk menyimpan produk yang akan diuji

    @Given("The user is on the login page")
    public void the_user_is_on_the_login_page() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        login = new loginPage(driver);
        mitra = new mitraPage(driver);
        admin = new adminPage(driver);
        produk = new adminProdukPage(driver);
        login.goToLoginPage();
    }

    @When("The user enters a valid email and password")
    public void the_user_enters_a_valid_email_and_password() {
        login.enterEmail("mamat@gmail.com");
        login.enterPassword("12345678");
    }

    @And("The user presses the login button")
    public void the_user_presses_the_login_button() {
        login.clickLoginButton();
    }

    @And("The user goes to the mitra page")
    public void the_user_goes_to_the_mitra_page() {
        mitra.waitForMitraPage();
    }

    @And("The user presses the dashboard button")
    public void the_user_presses_the_dashboard_button() {
        mitra.clickDashboardButton();
    }

    @And("The user goes to the admin page and presses the button produk")
    public void the_user_goes_to_the_admin_page_and_presses_the_button_produk() {
        admin.waitForAdminPage();
        admin.clickProdukButton();
    }

    @And("The user selects the product named {string}")
    public void the_user_selects_the_product_named(String productName) {
        targetProductName = productName;
        produk.waitForProdukPage();
        assertTrue(produk.isProductVisibleByName(productName), "Produk tidak ditemukan sebelum dihapus");
    }

    @And("The user presses the delete button on the selected product")
    public void the_user_presses_the_delete_button_on_the_selected_product() {
        produk.clickDeleteButtonByProductName(targetProductName);
    }

    @And("The user presses the delete confirmation button")
    public void the_user_presses_the_delete_confirmation_button() {
        produk.clickConfirmDeleteButton();
    }

    @Then("The product was successfully deleted and the user remains on the page produk")
    public void the_product_was_successfully_deleted_and_the_user_remains_on_the_page_produk() {
        produk.waitForProdukPage();
        assertFalse(produk.isProductVisibleByName(targetProductName), "Produk masih terlihat, padahal seharusnya sudah terhapus");
    }

    @But("The user does not press the delete confirmation button")
    public void the_user_does_not_press_the_delete_confirmation_button() {
        // Kosong, tidak menekan tombol konfirmasi
    }

    @Then("The product is not deleted and remains in the product list")
    public void the_product_is_not_deleted_and_remains_in_the_product_list() {
        produk.waitForProdukPage();
        assertTrue(produk.isProductVisibleByName(targetProductName), "Produk tidak ditemukan, padahal seharusnya tidak dihapus");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
