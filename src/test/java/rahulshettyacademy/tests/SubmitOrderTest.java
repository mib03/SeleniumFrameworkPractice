package rahulshettyacademy.tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.PageObjects.CartPage;
import rahulshettyacademy.PageObjects.CheckoutPage;
import rahulshettyacademy.PageObjects.ConfirmPage;
import rahulshettyacademy.PageObjects.OrdersPage;
import rahulshettyacademy.PageObjects.ProductCatalog;
import rahulshettyacademy.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider="getData", groups="Purchase")
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalog.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.verifyProducts(input.get("product"));
		assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");

		ConfirmPage confirmPage = checkoutPage.submitOrder();
		String confirmMessage = confirmPage.getConfirmMessage();
		assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods = {"submitOrder"}, dataProvider="getData")
	public void orderHistory(HashMap<String, String> input) {
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
		OrdersPage ordersPage = productCatalog.goToOrdersPage();
		assertTrue(ordersPage.verifyProducts(input.get("product")));
	}
	
	
	
//	Extent Reports
	
	@DataProvider
	public Object[][] getData() throws IOException {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "sani@gmail.com");
//		map.put("password", "Test@Ihsan123");
//		map.put("product", "ZARA COAT 3");
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "test@ng.com");
//		map1.put("password", "Test#NG123");
//		map1.put("product", "ADIDAS ORIGINAL");
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
}
