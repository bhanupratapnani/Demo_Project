package tests;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.setup;
import base.Screenshot;
import pages.BasicAuth_page;

public class BasicAuth_test extends setup {
	
	BasicAuth_page bapage;
	Screenshot ss;
	
	@BeforeMethod
	public void start() throws IOException 
	{
		basesetup();
		bapage =new BasicAuth_page(driver);
		ss=new Screenshot(driver);
		bapage.auth();
	}
	@Test
	public void login() throws IOException 
	{
        // Use username and password in the URL
        String username = "admin";
        String password = "admin";
        
        String url = "https://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth";

        // Navigate to the URL
        driver.get(url);
        
        ss.screenshot("auth");
    }
	
	@AfterMethod
	public void close() 
	{
		driver.quit();
	}

}
