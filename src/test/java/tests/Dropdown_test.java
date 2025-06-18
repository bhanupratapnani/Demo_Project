package tests;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.setup;
import pages.Dropdown_page;

public class Dropdown_test extends setup{

	Dropdown_page ddpage;
	
	@BeforeMethod
	public void dropdown() throws IOException 
	{
		basesetup();
		ddpage=new Dropdown_page(driver);
		ddpage.ddpage();
	}
	@Test
	public void drop() 
	{
		ddpage.drop();
	}
	@AfterMethod
	public void close() 
	{
		driver.close();
	}
}
