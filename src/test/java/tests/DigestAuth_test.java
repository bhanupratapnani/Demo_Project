package tests;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.setup;
import base.takescreenshot;
import pages.DigestAuth_page;

public class DigestAuth_test extends setup{
	
	DigestAuth_page dpage;
	takescreenshot ss; 
	
	@BeforeMethod
	public void start() throws IOException 
	{
		basesetup();
		dpage=new DigestAuth_page(driver);
		ss=new takescreenshot(driver);
		
		dpage.auth();
	}
	@Test
	public void Dauth() 
	{
		String username="admin";
		String password="admin";
		
		String url="https://"+username+":"+password+"@the-internet.herokuapp.com/digest_auth";
		
		driver.get(url);	
	}
	@AfterMethod
	public void close() {driver.close();}

}
