package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.setup;
import base.takescreenshot;
import pages.DynamicContent_page;

public class DynamicContent_test extends setup{
	
	DynamicContent_page dcpage;
	takescreenshot ss;
	
	@BeforeMethod
	public void start() throws IOException 
	{
		basesetup();
		dcpage=new DynamicContent_page(driver);
		ss=new takescreenshot(driver);
		
		dcpage.dynamic();
	}

	@Test
	public void title() 
	{
		String exp="Dynamic Content";
		String act=dcpage.title();
		
		System.out.println(exp+"="+act);
		
		Assert.assertEquals(exp, act);
		Assert.assertTrue(true);
	}
	
	@Test
	public void clickhere() 
	{
		boolean v=dcpage.click();	
		
		Assert.assertTrue(v);
	}
	
	@Test
	public void p1() 
	{
		String exp="This example demonstrates";
		String act=dcpage.p1();
		
		boolean v=act.startsWith(exp);
		
		Assert.assertTrue(v);
	}
	@Test
	public void p2()
	{
		String exp="To make some of the";
		String act=dcpage.p2();
		
		boolean v=act.startsWith(exp);
		
		Assert.assertTrue(v);
	}
	@Test
	public void mp1()
	{
		boolean v=dcpage.mp1();
		
		Assert.assertTrue(v);
	}
	@Test
	public void mp2()
	{
		boolean v=dcpage.mp2();
		
		Assert.assertTrue(v);
	}
	@Test
	public void mp3()
	{
		boolean v=dcpage.mp3();
		
		Assert.assertTrue(v);
	}
	
	@AfterMethod
	public void close() {driver.close();}
}
