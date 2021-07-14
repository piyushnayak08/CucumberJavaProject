package StepDefinitions;

import PageObjects.LandingPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

public class AddProductsSteps {
    WebDriver driver;
    LandingPage landingPage;

    @Given("I add four different products to my wishlist")
    public void iAddFourDifferentProductsToMyWishlist() throws InterruptedException {
        landingPage = new LandingPage(driver);
        landingPage.accessPage();
        landingPage.addToWishlist();
    }

    @When("I view my wishlist table")
    public void iViewMyWishlistTable() throws InterruptedException {
        landingPage.viewWishlist();
    }

    @Then("I find total four selected items in my wishlist")
    public void iFindTotalFourSelectedItemsInMyWishlist() {
        landingPage.validateSelectedItemsWishlist();
    }

    @When("I search for lowest price product")
    public void iSearchForLowestPriceProduct() throws InterruptedException {
        landingPage.findMinimumPrice();
    }

    @And("I am able to add the lowest price item to my cart")
    public void iAmAbleToAddTheLowestPriceItemToMyCart() {
    }

    @Then("I am able to verify the item in my cart")
    public void iAmAbleToVerifyTheItemInMyCart() {
    }
}
