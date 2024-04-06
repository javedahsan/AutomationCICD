package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends rahulshettyacademy.AbstractComponents.AbstractComponent {

	WebDriver driver;
	// constructor
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	

	// WebElement userEmail = driver.findElement(By.id("userEmail"));
	//page factory - it will behave same like above line at run time
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	// invalid login error message
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	// Action method
	
	public ProductCatalogue loginApplication(String email, String password) {
		
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		
		// below method is used for overloading objects
		// to load the next page from here
		
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;

	}
	
	public String getErrorMessage() {
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public String goTo(){
		driver.get("https://rahulshettyacademy.com/client/");
		return driver.getTitle();
	}
	
	
	
}
