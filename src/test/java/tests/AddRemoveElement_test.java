package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.setup;
import base.takescreenshot;
import pages.AddRemoveElement_page;

public class AddRemoveElement_test extends setup{
	
	AddRemoveElement_page arpage;
	takescreenshot ss;
	
	@BeforeMethod
	public void start() throws IOException 
	{
		basesetup();
		arpage=new AddRemoveElement_page(driver);
		ss=new takescreenshot(driver);
		arpage.addremove();
	}
	@Test
	public void add()
	{
		arpage.add();
		String exp="Delete";
		String act=arpage.checkremove();
		
		System.out.println(exp);
		System.out.println(act);
		
		Assert.assertEquals(act, exp);
		Assert.assertTrue(true);	
	}
	
	@Test
	public void remove() throws InterruptedException
	{
		arpage.add();
		Thread.sleep(2000);
		arpage.remove();
		
		String exp="Delete";
		String act=arpage.checkremove();
		
		System.out.println(exp);
		System.out.println(act);
		
		Assert.assertEquals(exp, act);
		Assert.assertTrue(false, exp);
	}
	@AfterMethod
	public void close() {driver.close();}

}
