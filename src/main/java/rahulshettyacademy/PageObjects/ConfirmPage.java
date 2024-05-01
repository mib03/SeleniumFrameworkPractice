package rahulshettyacademy.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ConfirmPage extends AbstractComponent {

	WebDriver driver;
	
	@FindBy(css=".hero-primary")
	WebElement confirmMessage;
	
	public ConfirmPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getConfirmMessage() {
		return confirmMessage.getText();
	}

}
