package tests;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.setup;
import base.Screenshot;
import pages.Context_page;

public class Context_test extends setup{
	
	Context_page cpage;
	Screenshot ss;
	
	@BeforeMethod
	public void start() throws IOException 
	{
		basesetup();
		cpage=new Context_page(driver);
		ss=new Screenshot(driver);
		
		cpage.context();
	}
	@Test
	public void click() throws IOException, InterruptedException 
	{
		Thread.sleep(5000);
		cpage.click();
		driver.switchTo().alert().accept();
		ss.screenshot("contextclick");
	}
	@AfterMethod
	public void close() 
	{
		driver.close();
	}
}
