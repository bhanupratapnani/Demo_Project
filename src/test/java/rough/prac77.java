package rough;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class prac77 {

	String name = "iphone 16 128gb";

	@Test
	public void compare() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// Flipkart search
		WebElement search = driver.findElement(By.name("q"));
		search.sendKeys(name);
		search.sendKeys(Keys.ENTER);

		String flipkartprice = driver.findElement(By.xpath("(//div[contains(@class,'Nx9bqj')])[1]")).getText()
				.replaceAll("[^0-9]", "");
		System.out.println("Flipkart price: " + flipkartprice);

		// Open Amazon in new tab
		((JavascriptExecutor) driver).executeScript("window.open();");
		List<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));

		driver.get("https://www.amazon.in/");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(name + Keys.ENTER);
		Thread.sleep(3000);

		String amazonprice = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText()
				.replaceAll("[^0-9]", "");
		System.out.println("Amazon price: " + amazonprice);

		// Open Croma in new tab
		((JavascriptExecutor) driver).executeScript("window.open();");
		tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(2));

		driver.get("https://www.croma.com/");
		driver.findElement(By.id("searchV2")).sendKeys(name + Keys.ENTER);

		String cromaprice = driver.findElement(By.xpath("(//span[@class='amount plp-srp-new-amount'])[1]")).getText()
				.replaceAll("[^0-9]", "");
		System.out.println("Croma price: " + cromaprice);

		// Compare prices
		int flipkart = Integer.parseInt(flipkartprice);
		int amazon = Integer.parseInt(amazonprice);
		int croma = Integer.parseInt(cromaprice);
		

		if (flipkart <= amazon && flipkart <= croma) {
			System.out.println("Flipkart has the lowest price.");
		} else if (amazon <= flipkart && amazon <= croma) {
			System.out.println("Amazon has the lowest price.");
		} else {
			System.out.println("Croma has the lowest price.");
		}

		driver.quit();
	}
}
