package rahulshettyacademy.PageObjects;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;

	public CartPage(WebDriver driver) {
//		First execute method
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean verifyProducts(String productName) {
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkoutButton.click();
		return new CheckoutPage(driver);
	}
	
}
