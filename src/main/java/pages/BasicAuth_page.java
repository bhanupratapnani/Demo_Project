package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.setup;

public class BasicAuth_page extends setup{
	
	public BasicAuth_page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Entering into auth page.
	@FindBy(xpath="//a[text()='Basic Auth']") private WebElement auth;
	public void auth() {auth.click();}
	
	
}

