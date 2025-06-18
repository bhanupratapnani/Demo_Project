package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.setup;
import base.takescreenshot;
import pages.AB_testing_page;

public class AB_testing_test extends setup{
	
	AB_testing_page abpage;
	takescreenshot ss;
	
	@BeforeMethod
	public void ABtesting() throws IOException {
		
		basesetup();
		abpage=new AB_testing_page(driver);
		ss=new takescreenshot(driver);
		
		abpage.abtesting();
	}

	//TC001-verifying header text presence.
	@Test
	public void header() throws InterruptedException, IOException
	{
		Thread.sleep(3000);
		String exp="A/B Test Control";
		String act=abpage.header();
		
		Assert.assertEquals(act, exp);	
		Assert.assertTrue(true, act);
        
        ss.screenshot();
	}
	
	//TC002-verifying paragraph start
	@Test
	public void paragraphstart() throws InterruptedException, IOException 
	{
		//paragraph starts with;
		Thread.sleep(3000);
		String exp="Also known as split testing";
		String act=abpage.paragraph();
		
		System.out.println("exp:"+exp);
		System.out.println("act:"+act);
		
		boolean v=act.startsWith(exp);
		Assert.assertTrue(v);
		
		ss.screenshot();
	}
	//TC003-verifying paragraph end
	@Test
	public void paragraphends() throws InterruptedException 
	{	
		//paragraph ends with;
		Thread.sleep(5000);
		String exp="(e.g. a user action such as a click-through).";
		String act=abpage.paragraph();
		
		System.out.println("exp:"+exp);
		System.out.println("act:"+act);
		
		boolean v=act.endsWith(exp);
		Assert.assertTrue(v);
	}
	//TC004-verifying footer
	@Test
	public void footer() throws InterruptedException, IOException 
	{
		//footer content
		Thread.sleep(500);
		String exp="Powered by Elemental Selenium";
		String act=abpage.footer();
		
		System.out.println("exp:"+exp);
		System.out.println("act:"+act);
		
		Assert.assertEquals(act, exp);
		Assert.assertTrue(true, act);
		
		ss.screenshot("test1");
		ss.screenshot();
		
	}
	
	@AfterMethod
	public void close() {driver.close();}
}
