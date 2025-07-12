package rough;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class prac2 {
	@Test
	public void login() throws EncryptedDocumentException, IOException, InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.get("https://practicetestautomation.com/practice-test-login/");
		driver.manage().window().maximize();

		FileInputStream fis = new FileInputStream("./files/users.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheetAt(0);

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {

			driver.findElement(By.xpath("//input[@id='username']"))
					.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			driver.findElement(By.xpath("//input[@id='password']"))
					.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[@id='submit']")).click();

			WebElement verify = driver.findElement(By.xpath("//div[@id='error']"));

			if (verify.isDisplayed()) {
				System.out.println(i + ":" + "Login failed."+"("+ sheet.getRow(i).getCell(0).getStringCellValue()+")");
			}

		}
		driver.close();
	}

}
