package rahulshettyacademy.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement inputCountry;
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement clickCountry;
	
	@FindBy(css=".action__submit")
	WebElement submitCountry;
	
	By resultCountry = By.cssSelector(".ta-results");

	public CheckoutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(inputCountry, countryName).build().perform();
		waitForElementAppearBy(resultCountry);
		clickCountry.click();
	}
	
	public ConfirmPage submitOrder() {
		submitCountry.click();
		return new ConfirmPage(driver);
	}
}
