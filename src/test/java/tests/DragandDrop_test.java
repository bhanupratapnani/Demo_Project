package tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.setup;
import base.takescreenshot;
import pages.DragandDrop_page;

public class DragandDrop_test extends setup{
	
	DragandDrop_page ddpage;
	takescreenshot ss;
	
	@BeforeMethod
	public void dragdrop() throws IOException 
	{
		basesetup();
		ddpage=new DragandDrop_page(driver);
		ss=new takescreenshot(driver);
		ddpage.dragdrop();
	}
	@Test
	public void drag() throws IOException 
	{
		WebElement drag=driver.findElement(By.xpath("//div[@id='column-a']"));
		WebElement drop=driver.findElement(By.xpath("//div[@id='column-b']"));
		
		Actions action=new Actions(driver);
		
		action.dragAndDrop(drag, drop).build().perform();	
		ss.screenshot();
		action.dragAndDrop(drop, drag).build().perform();
		ss.screenshot();
	}
	@AfterMethod
	public void close() 
	{
		driver.close();
	}

}
