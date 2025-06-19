package base;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot extends setup{

public Screenshot(WebDriver driver) {
	
	}

	public void screenshot(String name) throws IOException {
		
		TakesScreenshot tk=(TakesScreenshot) driver;
		File f=tk.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f, new File("./screenshots/"+name+".png"));
	}
	
	public void screenshot() throws IOException {
		
		String name=LocalTime.now().toString().substring(9, 15);
		
		TakesScreenshot tk=(TakesScreenshot)driver;
		File f=tk.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f, new File("./screenshots/"+name+".png"));
	}

}
