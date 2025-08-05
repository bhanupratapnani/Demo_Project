package rough;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;

public class prac6 {

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriver driver = new EdgeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		try {
			driver.findElement(By.xpath("//button[contains(text(),'✕')]")).click();
		} catch (Exception e) {
			// Ignore if popup not found
		}
		String name="iphone 16";
		WebElement search = driver.findElement(By.xpath("//input[@class='Pke_EE']"));
		search.sendKeys(name);
		search.sendKeys(Keys.ENTER);

		Thread.sleep(2000);

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Products");

		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Product No.");
		header.createCell(1).setCellValue("Product Name");

		int rowCount = 1;

		while (true) {

			// Collect product titles
			List<WebElement> titles = driver.findElements(By.cssSelector(".KzDlHZ"));

			for (WebElement title : titles) {
				String productName = title.getText();
				if (!productName.trim().isEmpty()) {
					Row row = sheet.createRow(rowCount++);
					row.createCell(0).setCellValue(rowCount - 1);
					row.createCell(1).setCellValue(productName);
				}
			}

			// Try to find and click Next button
			try {
				List<WebElement> nextButtons = driver.findElements(By.xpath("//span[text()='Next']/ancestor::a[1]"));
				if (!nextButtons.isEmpty() && nextButtons.get(0).isDisplayed()) {
					nextButtons.get(0).click();
					Thread.sleep(2000);
				} else {
					break; // No next button -> exit loop
				}
			} catch (Exception e) {
				break; // Element not found or not clickable -> exit loop
			}
		}

		// Write to Excel
		String filePath = "./Flipkart_"+name+".xlsx";
		FileOutputStream fileOut = new FileOutputStream(filePath);
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();

		System.out.println("Excel file saved at: " + filePath);

		driver.quit();
	}
}
