package rahulshettyacademy.tests;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("userEmail")).sendKeys("sani@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@Ihsan123");
		driver.findElement(By.id("login")).click();

		List<WebElement> products = driver.findElements(By.cssSelector(".card-body"));
		WebElement produk = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		produk.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder=\"Select Country\"]")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		assertTrue(confirmMessage.equalsIgnoreCase("THANK YOU FOR THE ORDER"));
		
		driver.close();
		
//		driver.findElement(By.cssSelector(".login-wrapper-footer-text")).click();
//		
//		String firstName = "Sani",
//				lastName = "Sani",
//				userEmail = "sani@gmail.com",
//				userMobile = "1231231231",
//				userPassword = "Test@Ihsan123";
//		
//		driver.findElement(By.id("firstName")).sendKeys(firstName);
//		driver.findElement(By.id("lastName")).sendKeys(lastName);
//		driver.findElement(By.id("userEmail")).sendKeys(userEmail);
//		driver.findElement(By.id("userMobile")).sendKeys(userMobile);
//		driver.findElement(By.cssSelector("[formcontrolname=\"occupation\"]")).click();
//		driver.findElement(By.cssSelector("option[value*=\"Student\"]")).click();
//		driver.findElement(By.cssSelector("input[value=\"Male\"]")).click();
//		driver.findElement(By.id("userPassword")).sendKeys(userPassword);
//		driver.findElement(By.id("confirmPassword")).sendKeys(userPassword);
//		driver.findElement(By.cssSelector("input[type=\"checkbox\"]:last-child")).click();
//		driver.findElement(By.id("login")).click();
//		
//		driver.findElement(By.className("headcolor")).getText();
//		driver.findElement(By.cssSelector("button.btn")).click();

	}

}
