package rough;


import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class prac66 {

	private WebDriver driver;
	private Workbook workbook;
	private Sheet sheet;
	private int rowCount;
	private String name = "iphone 16";

	@Parameters("browser")
	@BeforeMethod
	public void setup(String browser) {
		switch (browser.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			
			case "edge":
				driver = new EdgeDriver();
				break;
			default:
				throw new IllegalArgumentException("Invalid browser: " + browser);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Products");

		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Product No.");
		header.createCell(1).setCellValue("Product Name");

		rowCount = 1;
	}

	@Test
	public void scrapeFlipkart() throws InterruptedException, IOException {
		driver.get("https://www.flipkart.com/");

		WebElement search = driver.findElement(By.xpath("//input[@class='Pke_EE']"));
		search.sendKeys(name);
		search.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		while (true) {
			List<WebElement> titles = driver.findElements(By.cssSelector(".KzDlHZ"));
			for (WebElement title : titles) {
				String productName = title.getText().trim();
				if (!productName.isEmpty()) {
					Row row = sheet.createRow(rowCount++);
					row.createCell(0).setCellValue(rowCount - 1);
					row.createCell(1).setCellValue(productName);
				}
			}

			try {
				List<WebElement> nextButtons = driver.findElements(By.xpath("//span[text()='Next']/ancestor::a[1]"));
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
	}

	@AfterMethod
	public void tearDown() throws IOException {
		String fileName = "./Flipkart_" + name + "_" + getBrowserName() + ".xlsx";
		FileOutputStream fileOut = new FileOutputStream(fileName);
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();

		System.out.println("Excel saved: " + fileName);

		if (driver != null) {
			driver.quit();
		}
	}

	private String getBrowserName() {
		if (driver instanceof ChromeDriver) return "Chrome";
		if (driver instanceof FirefoxDriver) return "Firefox";
		if (driver instanceof EdgeDriver) return "Edge";
		return "UnknownBrowser";
	}
}
