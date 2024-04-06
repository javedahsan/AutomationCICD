package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;
	// constructor

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// locator to find list of products in cartpage
	// @FindBy(css = ".totalRow button")
//	@FindBy(css = ".subtotal ul li button")
//	WebElement checkOutEle;

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productNames;

	// Action method - verify Order history
	public Boolean verifyOrderDisplay(String productName) {
		Boolean match = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;

	}

}
