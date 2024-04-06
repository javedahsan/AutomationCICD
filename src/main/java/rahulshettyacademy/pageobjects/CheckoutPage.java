package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	// constructor
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// create page factory
	// WebElement selectedCountry =
	// driver.findElement(By.cssSelector("[placeholder='Select Country']"));

	@FindBy(css = "a.btnn.action__submit.ng-star-inserted")
	WebElement submit;

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;

	// this can not write in page factory - because there is no webelement
	By results = By.cssSelector("ta-results");
	// actions based on above facotry locator

	public void selectCountry(String countryName) throws InterruptedException {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		
		Thread.sleep(1000);
		System.out.println("Country displayed: " + driver.findElement(By.xpath("//button[contains(@class, 'ta-item')][2]")).isDisplayed());
		driver.findElement(By.xpath("//button[contains(@class, 'ta-item')][2]")).click();
		
		//selectCountry.click();
	}
	
	// submit button
	
	public ConfirmationPage submitOrder() {
		Actions a = new Actions(driver);
		a.doubleClick(submit).build().perform();
		
		return new ConfirmationPage(driver);
	}
}
