package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
//	String productName = "ZARA COAT 3";
	@Test(dataProvider = "getData", groups={"Purchase"}, retryAnalyzer=Retry.class)
//	public void submitOrder(String username, String passwd, String productName) throws IOException, InterruptedException {
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
//		String username = "javedmohamd@gamil.com";
//		String passwd = "Mar@#456";
		
		//LandingPage landingPage = launchApplication();
//		ProductCatalogue productCatalogue = landingPage.loginApplication(username, passwd);
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("userName"), input.get("passwd"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		Thread.sleep(2000);
		// you can parent class via child class
		// productCatalogue.goToCartPage();
		CartPage cartPage = productCatalogue.goToCartPage();
		Thread.sleep(1000);
		// CartPage
		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		System.out.println(match);
		Assert.assertTrue(match);
		Thread.sleep(2000);
		CheckoutPage checkoutPage = cartPage.goToCheckOut();
		Thread.sleep(2000);
		checkoutPage.selectCountry("india");
		Thread.sleep(3000);
		// confirmation page
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		Thread.sleep(2000);
		String confirmMessage = confirmationPage.getConfirmationMessage();
				
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
		
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistory() throws IOException, InterruptedException {
		// shows the "ZARA COAT 3" in the order history;
		String username = "javedmohamd@gamil.com";
		String passwd = "Mar@#456";
		String productName = "ZARA COAT 3";
		Thread.sleep(1000);
		ProductCatalogue productCatalogue = landingPage.loginApplication(username, passwd);
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
//		// let us user HasMap instead of sending data in Array
////		HashMap<Object, Object> map = new HashMap<Object, Object>();
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("username","javedmohamd@gamil.com");
//		map.put("passwd","Mar@#456");
//		map.put("productName","ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("username","javedmohamd@gamil.com");
//		map1.put("passwd","Mar@#456");
//		map1.put("productName","ADIDAS ORIGINAL");
//		
		
		
//		return new Object[][] {{"javedmohamd@gamil.com","Mar@#456","ZARA COAT 3"},{"javedmohamd@gamil.com","Mar@#456","ADIDAS ORIGINAL"}};
	
		// use FileUtils json method
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		System.out.println(data.get(0) + " " + data.get(1)  );
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
		}
	
}