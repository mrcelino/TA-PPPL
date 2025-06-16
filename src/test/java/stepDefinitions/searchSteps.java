package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.homePage;
import pages.loginPage;
import io.cucumber.java.After;

import java.util.List;

public class searchSteps {

    WebDriver driver;
    loginPage login;
    homePage home;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        login = new loginPage(driver);
        home = new homePage(driver);

    }

    private void loginUser() {
        login.goToLoginPage();
        login.loginAs("user@test.com", "12345678");
    }

    @Given("the user is logged in for search")
    public void the_user_is_logged_in_for_search() {
        loginUser();
    }

    @Given("the user is on the homepage")
    public void the_user_is_on_the_homepage() {
        //driver.get("https://avesta.cloud/dashboard");
    }

    @When("the user enters {string} in the search bar")
    public void the_user_enters_in_the_search_bar(String keyword) {
        home.searchProduct(keyword);
    }

    @And("the user clicks the Search button")
    public void the_user_clicks_the_search_button() {
        // Karena searchProduct already performs clicking,
        // this step can be a no-op or just left empty.
    }

    @Then("the system should display a list of chicken products")
    public void the_system_should_display_a_list_of_chicken_products() {
        Assertions.assertTrue(home.hasResults(), "Products should be displayed.");
    }

    @And("the search results should be relevant to the keyword")
    public void the_search_results_should_be_relevant_to_the_keyword() {
        List<String> productNames = home.getProductNames();

        for (String name : productNames) {
            Assertions.assertTrue(
                    name.toLowerCase().contains("sayap"),
                    "Product " + name + " is not relevant to search."
            );
        }
    }


    @Then("the search should show a message {string}")
    public void tthe_search_should_show_a_message(String message) {
        Assertions.assertTrue(home.isNoResultMessageDisplayed(), "Product not found");
    }

//    @After
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}

