package tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.setup;
import base.Screenshot;
import pages.DisappearingElement_page;

public class DisappearingElement_test extends setup{
	
	DisappearingElement_page depage;
	Screenshot ss;
	
	@BeforeMethod
	public void disappearingelement() throws IOException 
	{
		basesetup();
		depage=new DisappearingElement_page(driver);
		ss=new Screenshot(driver);
		depage.dele();
	}
	@Test
	public void home() 
	{	
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Home']")).isDisplayed());
		depage.home();	
	}
	
	@Test
	public void about() 
	{
		depage.about();
		
		String exp="Not Found";
		String act= depage.v1();
		
		System.out.println(exp+" = "+act);
		
		Assert.assertEquals(act, exp);
		Assert.assertTrue(true);	
	}
	
	@Test
	public void contact() 
	{
		depage.contact();
		
		String exp="Not Found";
		String act= depage.v2();
		
		System.out.println(exp+" = "+act);
		
		Assert.assertEquals(act, exp);
		Assert.assertTrue(true);	
	}
	
	@Test
	public void portfolio() 
	{
		depage.portfolio();
		
		String exp="Not Found";
		String act= depage.v3();
		
		System.out.println(exp+" = "+act);
		
		Assert.assertEquals(act, exp);
		Assert.assertTrue(true);	
	}
	@Test
	public void gallery() 
	{
		try
		{
			driver.findElement(By.xpath("//a[text()='Gallery']"));
   
			depage.gallery();
			
			String exp="Not Found";
			String act= depage.v4();
			
			System.out.println(exp+" = "+act);
			
			Assert.assertEquals(act, exp);
			Assert.assertTrue(true);		
		}
		catch(NoSuchElementException e)
		{
			System.out.println("Not Found");
		}
	}
	@AfterMethod
	public void close() {driver.close();}

}
