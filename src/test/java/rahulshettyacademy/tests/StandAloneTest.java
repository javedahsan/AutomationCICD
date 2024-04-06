package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// standalone Test

		String username = "javedmohamd@gamil.com";
		String passwd = "Mar@#456";

		System.setProperty("webdriverchrome.driver",
				"C:\\Users\\user\\DevTools\\WbDrivers\\chromedriver-win64\\chromedriver.exe");
		// chromedriver
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
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

		// Wait
		Thread.sleep(3000);

		// findelements
///		ArrayList<String> ItemText = new ArrayList<String>();

		List<WebElement> products = driver.findElements(By.cssSelector("div[class='row'] div[class='card-body']"));
		List<WebElement> rows = driver.findElements(By.xpath("//h5/b"));

		// rows.stream().map(s->s.getText()).collect(Collectors.toList());
		List<String> ordItemText = rows.stream().map(s -> s.getText()).collect(Collectors.toList());
		System.out.println(ordItemText);

		List<WebElement> cartItems = products.stream()
				.map(s -> s.findElement(By.cssSelector("button[class='btn w-10 rounded']")))
				.collect(Collectors.toList());

		for (int i = 0; i < cartItems.size(); i++) {
			cartItems.get(i).click();
			Thread.sleep(2000);
		}

		// find shopping cart
		List<WebElement> btns = driver.findElements(By.xpath("//ul/li/button"));
//		Thread.sleep(2000);
		btns.get(2).click();

		Thread.sleep(2000);

//	 validate item text in shopping Cart list
		WebElement shoppingCartList = driver.findElement(By.cssSelector("div.cart"));
		List<WebElement> selectedItems = shoppingCartList.findElements(By.cssSelector("h3"));
		List<String> Items = selectedItems.stream().map(s -> s.getText()).collect(Collectors.toList());
		Thread.sleep(2000);
		System.out.println(Items);

		Assert.assertTrue(ordItemText.equals(Items));

		// buy items selected items

		List<WebElement> BuyingItems = shoppingCartList.findElements(By.cssSelector("button.btn.btn-primary"));
		List<WebElement> buyingList = BuyingItems.stream().filter(s -> s.getText().contains("Buy"))
				.collect(Collectors.toList());

		ArrayList<String> orderText = new ArrayList<String>();
		
		
		for (int k = 0; k < buyingList.size(); k++) {

			buyingList.get(k).click();
			Thread.sleep(1000);
			
			WebElement selectedCountry = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
			selectedCountry.sendKeys("india");
			
			List <WebElement> selectCountries = driver.findElements(By.cssSelector("span[class='ng-star-inserted']"));
			
			// search index one by one and until you find india
			for (WebElement selectCountry : selectCountries)
			{
//				WebElement x = selectCountry.findElement(By.cssSelector("i.fa.fa-search"));
					
				System.out.println(selectCountry.getText());
				
				if (selectCountry.getText().equalsIgnoreCase("india"))
				{
					selectedCountry.clear();
					selectedCountry.sendKeys(selectCountry.getText());
					selectedCountry.isSelected();
					
					break;
				}
			}
			
			Thread.sleep(2000);
//			driver.findElement(By.cssSelector("a.btnn.action__submit.ng-star-inserted")).click();
			
			WebElement placeOrd = driver.findElement(By.cssSelector("a.btnn.action__submit.ng-star-inserted"));
			Actions actions = new Actions(driver);
//			actions.moveToElement(placeOrd).clickAndHold().build().perform();
			actions.clickAndHold(placeOrd).build().perform();
			Thread.sleep(1000);
			
			
			// capture order#
			Thread.sleep(1000); 
			String orderNum = driver.findElement(By.cssSelector("label.ng-star-inserted")).getText();
			orderText.add(orderNum);
			System.out.println(orderText);
			driver.navigate().back();
			driver.navigate().back();

		}

}}
