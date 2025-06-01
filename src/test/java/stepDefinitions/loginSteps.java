package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.Duration;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.loginPage;

public class loginSteps {
    WebDriver driver;
    loginPage loginPage;


    @Given("User opens the Avesta system login page")
    public void user_opens_the_avesta_system_login_page() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.get("http://127.0.0.1:8000/login");
        this.loginPage = new loginPage(this.driver);
    }

    @When("User enters the email {string}")
    public void user_enters_the_email(String email) {
        this.loginPage.enterEmail(email);
    }

    @When("User enters the password {string}")
    public void user_enters_the_password(String password) {
        this.loginPage.enterPassword(password);
    }

    @When("User clicks the {string} button")
    public void user_clicks_the_button(String button) {
        this.loginPage.clickLoginButton();
    }

    @Then("User should be redirected to the Buyer dashboard successfully")
    public void user_is_redirected_to_the_buyer_dashboard() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));
        Assertions.assertTrue(driver.getCurrentUrl().contains("dashboard"));
        driver.quit();
    }

    @Then("The system displays an error message {string}")
    public void the_system_displays_an_error_message(String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10L));
        WebElement errorElement = (WebElement)wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".text-danger")));
        String actualMessage = errorElement.getText();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
