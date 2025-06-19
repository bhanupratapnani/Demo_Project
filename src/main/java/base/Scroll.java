package base;

import org.openqa.selenium.JavascriptExecutor;

public class Scroll extends setup{
	
	public void scrollvertical(int y) {
		
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("Scroll(0,"+y+");");
	}
	
	public void scrollhorizantal(int x) {
		
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("Scroll("+x+"0);");
	}

}
