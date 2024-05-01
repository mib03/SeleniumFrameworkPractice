package rahulshettyacademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {

	WebDriver driver;
	
	public ProductCatalog(WebDriver driver) {
//		First execute method
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement displayAnimation;
	
	
	
	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastContainer = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementAppearBy(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement produk = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		return produk;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement produk = getProductByName(productName);
		produk.findElement(addToCart).click();
		waitForElementAppearBy(toastContainer);
		waitForElementDisappear(displayAnimation);
	}

}
