package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;



public class SubmitOrderTestBackup extends BaseTest {
	
	@Test
	public void submitOrder() throws IOException, InterruptedException  {
	
//	public static void main(String[] args) throws IOException {
		// standalone Test

		String username = "javedmohamd@gamil.com";
		String passwd = "Mar@#456";
		String productName = "ZARA COAT 3";

////     Use webdrive download from internet. instead of using local .
//		System.setProperty("webdriverchrome.driver",
//				"C:\\Users\\user\\DevTools\\WbDrivers\\chromedriver-win64\\chromedriver.exe");
////		WebDriverManager.chromedriver().setup();
//		// chromedriver
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//
//		// delete Cookies
//		driver.manage().deleteAllCookies();
//		// Time out
//		
//
//		
////		driver.get("https://rahulshettyacademy.com/client/");
//
//		// call landing page
//		LandingPage landingPage = new LandingPage(driver);
//
//		System.out.println(landingPage.goTo());
		LandingPage landingPage = launchApplication();
		ProductCatalogue productCatalogue = landingPage.loginApplication(username, passwd);
//
//		// call catelog page - wait for all the products are loaded
//		// comment out below line to implement object overloading from landing page
//        // ProductCatalogue productCatalogue = new ProductCatalogue(driver);
//		// see above productCatalogue from landing page
		
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		 Thread.sleep(2000);
		// you can parent class via child class
		//productCatalogue.goToCartPage();
		 CartPage cartPage = productCatalogue.goToCartPage();
		 Thread.sleep(1000);
		//Cartpage 
		// we are sure next page is cartpage - then we encapsulate -
		// CartPage cartPage = new CartPage(driver); within productCatalogue.goToCartPage();
		//CartPage cartPage = new CartPage(driver);
		//return the object above line
		Boolean match = cartPage.verifyProductDisplay(productName);
		System.out.println(match);
		Assert.assertTrue(match);
		Thread.sleep(2000);
		CheckoutPage checkoutPage = cartPage.goToCheckOut();
		Thread.sleep(2000);
		checkoutPage.selectCountry("india");
//		Thread.sleep(2000);
		// confirmation page
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		Thread.sleep(2000);
		String confirmMessage = confirmationPage.getConfirmationMessage();
		
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
  }
}