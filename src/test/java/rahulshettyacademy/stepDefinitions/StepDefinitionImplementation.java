package rahulshettyacademy.stepDefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.PageObjects.CartPage;
import rahulshettyacademy.PageObjects.CheckoutPage;
import rahulshettyacademy.PageObjects.ConfirmPage;
import rahulshettyacademy.PageObjects.LandingPage;
import rahulshettyacademy.PageObjects.ProductCatalog;
import rahulshettyacademy.TestComponents.BaseTest;

public class StepDefinitionImplementation extends BaseTest {
	
	LandingPage landingPage;
	ProductCatalog productCatalog;
	ConfirmPage confirmPage;
	
	@Given("I landed on Ecommerce Page")
	public void landed_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_username_and_password(String username, String password) {
		productCatalog = landingPage.loginApplication(username, password);
	}
	
	@When("^I add the product (.+) to the Cart$")
	public void dd_product_to_Cart(String productName) throws InterruptedException {
		productCatalog.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
		CartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.verifyProducts(productName);
		assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		confirmPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_ConfirmationPage(String string) {
		String confirmMessage = confirmPage.getConfirmMessage();
		assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_displayed_LoginPage(String string) {
		assertEquals(string, landingPage.getErrorMessage());
		driver.close();
	}

}
