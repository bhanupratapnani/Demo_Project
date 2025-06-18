package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import base.setup;

public class Dropdown_page extends setup{
	
	public Dropdown_page(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//a[text()='Dropdown']") private WebElement dropbox;
	public void ddpage() 
	{
		dropbox.click();
	}
	
	@FindBy(xpath="//select[@id='dropdown']") private WebElement drop;
	public void drop() 
	{
		drop.click();
		Select s=new Select(drop);
		s.selectByIndex(1);
		
	}
	
}
