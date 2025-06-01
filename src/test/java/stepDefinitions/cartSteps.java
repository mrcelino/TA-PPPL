package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.loginPage;
import io.cucumber.java.After;
import pages.productPage;

public class cartSteps {

    WebDriver driver;
    loginPage login;
    productPage product;
    int initialCartCount = 0;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        login = new loginPage(driver);
        product = new productPage(driver);
    }

    private void loginUser() {
        login.goToLoginPage();
        login.loginAs("tesuser@example.com", "driveby123");
    }

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        loginUser();
        product.waitForPage("/dashboard");
        product.open();
        product.waitForPage("/cariayam");
    }

    @Given("the user is on the product listing page")
    public void the_user_is_on_the_product_listing_page() {
        product.open();
        Assertions.assertTrue(product.isOnProductListingPage(), "User seharusnya ada di halaman 'Cari Ayam'");
    }

    @When("the user clicks pesan on a product from {string}")
    public void the_user_clicks_button_on_a_product_from_store(String storeName) {
        product.clickOrderButtonForStore(storeName);
    }

    @And("the modal detail product should be shown")
    public void the_modal_detail_product_should_be_shown() {
        Assertions.assertTrue(product.isProductDetailModalVisible(), "Modal detail produk seharusnya muncul");
    }

    @And("the user clicks Tambahkan ke keranjang")
    public void the_user_clicks_tambahkan_ke_keranjang() {
        product.clickAddToCartOnModal();
    }

    @Then("the product should be added to the cart successfully")
    public void the_product_should_be_added_to_the_cart_successfully() {
        Assertions.assertTrue(product.getCartCount() > 0, "Cart count should be greater than 0");
        driver.quit();
    }

    @Given("the user adds a product from {string} to the cart")
    public void the_user_adds_a_product_from_store_to_the_cart(String storeName) {
        product.clickOrderButtonForStore(storeName);
        Assertions.assertTrue(product.isProductDetailModalVisible(), "Modal detail produk seharusnya muncul");
        product.clickAddToCartOnModal();
        initialCartCount = product.getCartCount();
    }

    @When("the user attempts to add a product from {string} to the cart")
    public void the_user_attempts_to_add_a_product_from_another_store(String storeName) {
        product.clickOrderButtonForStore(storeName);
    }

    @Then("an modal message should be shown")
    public void an_modal_message_should_be_shown() {
        Assertions.assertTrue(product.isSwitchStoreModalVisible(), "Modal konfirmasi ganti toko seharusnya muncul");
    }

    @Then("the cart count should not increase")
    public void the_cart_count_should_not_increase() {
        Assertions.assertEquals(initialCartCount, product.getCartCount(), "Jumlah item di keranjang seharusnya tidak bertambah");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
