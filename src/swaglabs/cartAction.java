package swaglabs;

import org.testng.annotations.Test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class cartAction extends parameters {
	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void setUp() {

		driver.get(URL);
		WebElement UserInput = driver.findElement(By.id("user-name"));
		WebElement PasswordInput = driver.findElement(By.id("password"));
		UserInput.sendKeys(UserName);
		PasswordInput.sendKeys(password);
		driver.findElement(By.id("login-button")).click();
		driver.manage().window().maximize();
	}

	@Test
	public void CartTest() {
		List<WebElement> AddToCartButton = driver.findElements(By.className("btn"));
		List<WebElement> ItemsName = driver.findElements(By.className("inventory_item_name"));
		List<WebElement> ItemsPrice = driver.findElements(By.className("inventory_item_price"));

		for (int i = 0; i < AddToCartButton.size(); i++) {
			if (ItemsName.get(i).getText().contains("Bike Light") || ItemsName.get(i).getText().contains("Backpack")
					|| ItemsName.get(i).getText().contains("Sauce Labs Bolt")) {
				System.out.println(ItemsName.get(i).getText()+ " The item is not added");
				continue;
			} else {
				AddToCartButton.get(i).click();
				ItemsPrice.get(i).getText();
				System.out.println(ItemsPrice.get(i).getText()+ " The item is added");
				String FormattedPrice = ItemsPrice.get(i).getText().replace("$", " ");
				double PriceAsDouble = Double.parseDouble(FormattedPrice);
				double TaxValue = 0.10;
				double NewPrices = PriceAsDouble * TaxValue + PriceAsDouble;
				if ((int) NewPrices % 2 == 0) {
					System.out.println("the final price is  an even number and the value of this number " + NewPrices);
				} else {
					System.out.println("the final price is  an  odd number and the value of this price " + NewPrices);
				}
			}

		}
	}

	@AfterTest
	public void MyAfterTest() {
		driver.close();
	}

}