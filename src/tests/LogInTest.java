package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import objects.HomePage;
import objects.InventoryPage;

public class LogInTest {

	private static WebDriver driver;

	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32_91\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	@Test(priority = 1)
	public void invalidCredTest() {
		File f = new File("Data.xlsx");
		try {
			InputStream in = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(in);
			Sheet sheet = wb.getSheetAt(0);

			Row row = sheet.getRow(1);

			Cell c0 = row.getCell(0);
			Cell c1 = row.getCell(1);

			String username = c0.toString(); // username : locked_out_user
			String password = c1.toString();// password : secret_sauce

			driver.navigate().to(HomePage.URL);
			driver.manage().window().maximize();

			HomePage.inputUserName(driver, username);
			HomePage.inputPassword(driver, password);
			HomePage.clickLogin(driver);
			
			try {	Thread.sleep(3000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			String currentUrl = driver.getCurrentUrl();
			String expected = "https://www.saucedemo.com/inventory.html"; // inventory stranica nakon uspesnog log in-a
			Assert.assertNotEquals(currentUrl, expected);

			//wb.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Test(priority = 2)
	public void validCredTest() {
		File f = new File("Data.xlsx");
		try {
			InputStream in = new FileInputStream(f);
			XSSFWorkbook wb = new XSSFWorkbook(in);
			Sheet sheet = wb.getSheetAt(0);

			Row row = sheet.getRow(0);

			Cell c0 = row.getCell(0);
			Cell c1 = row.getCell(1);

			String username = c0.toString();// username : standard_user
			String password = c1.toString();// password : secret_sauce

			driver.navigate().to(HomePage.URL);
			driver.manage().window().maximize();

			HomePage.inputUserName(driver, username);
			HomePage.inputPassword(driver, password);
			HomePage.clickLogin(driver);
			
			try {	Thread.sleep(3000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			String currentUrl = driver.getCurrentUrl();
			String expected = "https://www.saucedemo.com/inventory.html";// inventory stranica nakon uspesnog log in-a

			Assert.assertEquals(currentUrl, expected);

			wb.close();
			driver.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	
}
