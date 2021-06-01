package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import objects.HomePage;
import objects.InventoryPage;

public class SortItemsTest {

	private static WebDriver driver;

	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32_91\\chromedriver.exe");
		driver = new ChromeDriver();

	}
	
	@Test
	public void sortTest() {
		driver.navigate().to(HomePage.URL);
		driver.manage().window().maximize();

		HomePage.inputUserName(driver, "standard_user");
		HomePage.inputPassword(driver, "secret_sauce");
		HomePage.clickLogin(driver);
		InventoryPage.sortDropDown(driver);
		
		try {	Thread.sleep(3000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		InventoryPage.cheapestItem(driver);
		
		

		String currentUrl = driver.getCurrentUrl();
		String expected = InventoryPage.cheapestURL.toString();

		Assert.assertEquals(currentUrl, expected);

		driver.close();
	}
	
	
	
}
