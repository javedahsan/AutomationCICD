package rahulshettyacademy.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	// constructor

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// locator to find list of products in cartpage
	//@FindBy(css = ".totalRow button")
	@FindBy(css = ".subtotal ul li button")
	WebElement checkOutEle;

	@FindBy(css = ".cartSection h3")
	List<WebElement> productTitles;

//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow")));
//
//	List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

	// Action method - verify product displayed product list
	public Boolean verifyProductDisplay(String productName) {
		
		Boolean match = productTitles.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;

	}

	public CheckoutPage goToCheckOut() {
		Actions a = new Actions(driver);
		a.doubleClick(checkOutEle).build().perform();
		
		return new CheckoutPage(driver);
	}

}
