package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.setup;
import base.takescreenshot;
import pages.DynamicControl_page;

public class DynamicControl_test extends setup{
	
	DynamicControl_page dypage;
	takescreenshot ss;
	
	@BeforeMethod
	public void start() throws IOException 
	{
		basesetup();
		dypage=new DynamicControl_page(driver);
		ss=new takescreenshot(driver);
		dypage.control();
	}
	@Test
	public void title() 
	{
		String exp="Dynamic Controls";
		String act=dypage.title();
		
		System.out.println(exp+"="+act);
		
		Assert.assertEquals(exp, act);
		Assert.assertTrue(true);
	}
	@Test
	public void p1() 
	{
		String exp="This example demonstrates";
		String act=dypage.p1();
		
		boolean v=act.startsWith(exp);
		
		System.out.println(exp+"="+act);
		Assert.assertTrue(v);	
	}
	@Test
	public void checkbox() throws IOException 
	{
		boolean v=dypage.vcheck();
		Assert.assertTrue(v);
		dypage.checkbox();
		
		ss.screenshot();
	}
	
	
	@AfterMethod
	public void close() {driver.close();}

}
