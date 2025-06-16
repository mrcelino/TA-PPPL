package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import pages.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class paymentSteps {
    WebDriver driver = TestContext.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    checkoutPage checkout = new checkoutPage(driver);
    confirmPaymentPage confirm = new confirmPaymentPage(driver);
    paymentPage payment = new paymentPage(driver);

    @When("User membuka halaman checkout")
    public void userMembukaHalamanCheckout() {
        checkout.open();
        wait.until(driver -> driver.getCurrentUrl().contains("/checkout"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space(text())='Konfirmasi']")));
    }

    @And("User menambah kuantitas produk hingga total melebihi saldo")
    public void userMenambahKuantitasProduk() {
        checkout.increaseQuantityUntilOverSaldo();
    }

    @And("User mengklik tombol Konfirmasi checkout")
    public void userKlikKonfirmasiCheckout() {
        checkout.clickConfirmButton();
    }

    @Then("User dialihkan ke halaman \"Konfirmasi Pembayaran\"")
    public void userDialihkanKeHalamanKonfirmasiPembayaran() {
        wait.until(ExpectedConditions.urlContains("/payment")); // tambahkan ini
        assertTrue(confirm.isOnConfirmPaymentPage());
    }

    @And("User mengklik tombol \"Bayar dengan AvestaPay\"")
    public void userKlikBayarDenganAvestaPay() {
        confirm.clickAvestaPayButton();
    }

    @Then("User diarahkan ke halaman \"Pembayaran\"")
    public void userDialihkanKeHalamanPembayaran() {
        wait.until(ExpectedConditions.urlContains("/paymentconfirm")); // tambahkan ini
        assertTrue(payment.isOnPaymentPage());
    }

    @And("User memasukkan password pembayaran {string}")
    public void userMemasukkanPassword(String password) {
        payment.inputPassword(password);
    }

    @And("User mengklik tombol Konfirmasi pembayaran")
    public void userKlikKonfirmasiPembayaran() {
        payment.clickConfirm();
    }

    @Then("Muncul pesan error \"Saldo tidak mencukupi\"")
    public void munculPesanErrorSaldoTidakCukup() {
        assertTrue(payment.getErrorMessage().contains("Saldo Tidak Mencukupi"), "Pesan error 'Saldo Tidak Mencukupi' seharusnya muncul di modal");
    }

    @Then("Muncul pesan berhasil \"Pembayaran berhasil\"")
    public void munculPesanPembayaranBerhasil() {
        assertTrue(payment.isTransactionSuccessful(), "Modal 'Pembayaran Terkonfirmasi' seharusnya muncul");
    }
}