package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class setup {
		
		public static File file;
		public static FileInputStream fi;
		public static Properties pro;
		public static String browser;
		public static String url;
		public static WebDriver driver;
		
	public void basesetup() throws IOException {
		
		file=new File("./files/config.properties");
		fi=new FileInputStream(file);
		pro=new Properties();
		pro.load(fi);
		
		browser=pro.getProperty("browser");
		url=pro.getProperty("url");
		
		if(browser.equals("chrome")) {driver=new ChromeDriver();}
		else if(browser.equals("edge")) {driver=new EdgeDriver();}
		else if(browser.equals("safari")) {driver=new SafariDriver();}
		else if(browser.equals("firefox")) {driver=new FirefoxDriver();}
		else {System.out.println("browser not found!");}
		
		driver.get(url);
		driver.manage().window().maximize();
		
	}
		
		
}

