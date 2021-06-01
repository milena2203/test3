package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage {
	public static final String URL = "https://www.saucedemo.com/inventory.html";
	public static final String dropdown_xpath ="//*[@id=\"header_container\"]/div[2]/div[2]/span/select";
	public static final String cheapest_xpath ="//*[@id=\"item_2_img_link\"]/img";
	public static final String cheapestURL ="https://www.saucedemo.com/inventory-item.html?id=2";
	
	
	
	
	public static void sortDropDown (WebDriver driver ) {
		//driver.findElement(By.xpath(dropdown_xpath)).click(); 
		Select select = new Select(driver.findElement(By.xpath(InventoryPage.dropdown_xpath)));
		select.selectByIndex(2);
	}

	
	
	public static void cheapestItem (WebDriver driver) {
		driver.findElement(By.xpath(cheapest_xpath)).click();
	}
}
