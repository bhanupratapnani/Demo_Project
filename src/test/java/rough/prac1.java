package rough;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class prac1 {

	private WebDriver driver;

	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) {
		switch (browser.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			default:
				throw new IllegalArgumentException("Invalid browser: " + browser);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void agoda() throws InterruptedException {
		driver.get("https://www.agoda.com/en-in/?cid=1922885");

		driver.findElement(By.xpath("//h6[text()='Flights']")).click();

		driver.findElement(By.xpath("//button[@aria-label='Close']")).click();

		driver.findElement(By.id("flight-origin-search-input")).sendKeys("Hyderabad");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//ul[@role='listbox']/li[1]")).click();

		driver.findElement(By.id("flight-destination-search-input")).sendKeys("visakapatnam");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//ul[@role='listbox']/li[1]")).click();

		driver.findElement(By.xpath("(//div[@class='SearchBoxTextDescription__title'])[1]")).click();
		driver.findElement(By.xpath("//span[@data-selenium-date='2025-07-26']")).click();

		WebElement adult = driver.findElement(By.xpath("(//*[@class='SvgIconstyled__SvgIconStyled-sc-1i6f60b-0 dfiCdD'])[1]"));
		adult.click();
		adult.click();

		driver.findElement(By.xpath("//div[text()='Business']")).click();

		driver.findElement(By.xpath("//span[text()='SEARCH FLIGHTS']")).click();
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
