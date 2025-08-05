package rough;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class lam {
	public static void main(String[] args) throws AWTException {

		WebDriver driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/");
		driver.manage().window().maximize();

		WebElement exit = driver.findElement(By.xpath("//a[text()='Exit Intent']"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", exit);

		exit.click();

		Robot robot = new Robot();
		robot.mouseMove(100, 100);
		

		driver.findElement(By.xpath("//p[text()='Close']")).click();

	}
}
