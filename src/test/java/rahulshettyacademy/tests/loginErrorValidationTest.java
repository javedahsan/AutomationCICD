package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class loginErrorValidationTest extends BaseTest {
    // insert Retry if we want to run test retry
	@Test (groups = {"errorHandling"}, retryAnalyzer=Retry.class)
	public void loginValidation() throws IOException, InterruptedException {

		String username = "javedm@gamil.com";
		String passwd = "Mar";
		
		LandingPage landingPage = launchApplication();
		landingPage.loginApplication(username, passwd);
		System.out.println(landingPage.getErrorMessage());
//		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

		
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {

		String username = "javedmohamd@gamil.com";
		String passwd = "Mar@#456";
		String productName = "ZARA COAT 3";

		LandingPage landingPage = launchApplication();
		ProductCatalogue productCatalogue = landingPage.loginApplication(username, passwd);

		List<WebElement> products = productCatalogue.getProductList();
		
		productCatalogue.addProductToCart(productName);
		Thread.sleep(2000);
		// you can parent class via child class
		// productCatalogue.goToCartPage();
		CartPage cartPage = productCatalogue.goToCartPage();
		Thread.sleep(1000);
		// CartPage
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		System.out.println(match);
		Assert.assertFalse(match);
		

		
		
	}
}