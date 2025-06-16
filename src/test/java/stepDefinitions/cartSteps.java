package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pages.loginPage;
import pages.productPage;

public class cartSteps {

    WebDriver driver = TestContext.getDriver();
    loginPage login = new loginPage(driver);
    productPage product = new productPage(driver);
    int initialCartCount = 0;

    private void loginUser() {
        login.goToLoginPage();
        login.loginAs("user@test.com", "12345678");
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

    @When("the user sets the quantity to {int}")
    public void the_user_sets_the_quantity_to(Integer quantity) {
        int currentQty = product.getCurrentQuantity();

        while (currentQty < quantity) {
            product.clickIncrementQuantity();

            if (product.isStockExceededErrorVisible()) {
                break; // stop klik jika error sudah muncul
            }

            currentQty = product.getCurrentQuantity();
        }
    }

    @Then("an error message should be shown indicating stock is insufficient")
    public void an_error_message_should_be_shown_indicating_stock_is_insufficient() {
        Assertions.assertTrue(product.isStockExceededErrorVisible(), "Pesan error stok melebihi seharusnya tampil");
    }
}
