package rahulshettyacademy.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.io.IOException;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.PageObjects.CartPage;
import rahulshettyacademy.PageObjects.ProductCatalog;
import rahulshettyacademy.TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = rahulshettyacademy.TestComponents.Retry.class)
	public void LoginErrorValidation() {
		landingPage.loginApplication("sani@gmail.com", "Test@Ihsan");
		assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";

		ProductCatalog productCatalog = landingPage.loginApplication("sani@gmail.com", "Test@Ihsan123");
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.verifyProducts("ZARA COAT 33");
		assertFalse(match);
	}

}
