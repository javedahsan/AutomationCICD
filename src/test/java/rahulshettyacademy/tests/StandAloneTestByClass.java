package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.FluentWait;
//import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.manager.SeleniumManager;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTestByClass {

	public static void main(String[] args) throws InterruptedException {
		// standalone Test

		String username = "javedmohamd@gamil.com";
		String passwd = "Mar@#456";
		String productName = "ZARA COAT 3";

//     Use webdrive download from internet. instead of using local .
		WebDriverManager.chromedriver().setup();
		// chromedriver
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Actions a = new Actions(driver);
		driver.get("https://rahulshettyacademy.com/client/");

		
		
		// getTitle
		driver.getTitle();

		// delete Cookies
		driver.manage().deleteAllCookies();
		// login

		// username
		driver.findElement(By.id("userEmail")).sendKeys(username);
		driver.findElement(By.id("userPassword")).sendKeys(passwd);
		driver.findElement(By.id("login")).click();

		// wait for all the products are loaded
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		// user last of type if there is more than one button object
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		// Explicitly wait toast

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		// wait until toast master is invisible - ng-animating

		// Below line has performance issue - use driver full path
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		// below is not working
		// wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		// click cart
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		// validate itemlist in the cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow")));

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		// match any product name having product name - return true
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

		Assert.assertTrue(match);
		System.out.println(match);
		Thread.sleep(2000);
		// checkout button
		// driver.findElement(By.cssSelector(".totalRow button")).click();
		WebElement checkOut = driver.findElement(By.cssSelector(".subtotal ul li button"));
		// a.clickAndHold(checkOut).build().perform();
		a.doubleClick(checkOut).build().perform();

		// use action for Select Country
		WebElement selectedCountry = driver.findElement(By.cssSelector("[placeholder='Select Country']"));

		a.sendKeys(selectedCountry, "india").build().perform();

		// wait until countries list is displayed
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		// pick 2nd country
		driver.findElement(By.xpath("//button[contains(@class, 'ta-item')][2]")).click();

		Thread.sleep(1000);
		// click place order button
		WebElement placeOrd = driver.findElement(By.cssSelector("a.btnn.action__submit.ng-star-inserted"));
		a.doubleClick(placeOrd).build().perform();

		// validating Thank you message
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
	}
}
