package base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits extends setup {
	
	public void impliwait() {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public void expliwait(WebElement element) {
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
	}
}
