package rough;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class prac5 {
	
	String name="iphone16";
	String website="https://www.flipkart.com/";
	String searchtab="//input[@class='Pke_EE']";
	String producttitle=".KzDlHZ";
	String nextbutton="//span[text()='Next']/ancestor::a[1]";
	
	@Test
	public void topfive() {
	
	WebDriver driver = new EdgeDriver();
	driver.get(website);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	
	WebElement search = driver.findElement(By.xpath(searchtab));
	search.clear();
	search.sendKeys(name);
	search.sendKeys(Keys.ENTER);
	
	while (true) {

		List<WebElement> titles = driver.findElements(By.cssSelector(producttitle));

		for (WebElement title : titles) {
			System.out.println(title.getText());
		}

		try {
			List<WebElement> nextButtons = driver.findElements(By.xpath(nextbutton));
			if (!nextButtons.isEmpty() && nextButtons.get(0).isDisplayed()) {
				nextButtons.get(0).click();
				Thread.sleep(2000);
			} else {
				break;
			}
		} catch (Exception e) {
			break;
		}
	}
	driver.close();
	}
}
