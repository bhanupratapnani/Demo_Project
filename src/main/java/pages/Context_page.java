package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.setup;

public class Context_page extends setup{
	
	public Context_page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[text()='Context Menu']") private WebElement context;
	public void context() {context.click();}
	
	@FindBy(xpath="//div[@id='hot-spot']") private WebElement click;
	public void click() 
	{
		Actions action=new Actions(driver);
		action.contextClick(click).build().perform();
		
	}
}
