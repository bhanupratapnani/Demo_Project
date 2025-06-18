package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.setup;

public class DynamicContent_page extends setup{
	
	public DynamicContent_page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[text()='Dynamic Content']") private WebElement dynamicbutton;
	public void dynamic() {dynamicbutton.click();}
	
	@FindBy(xpath="//h3[text()='Dynamic Content']") private WebElement title;
	public String title() {return title.getText();}
	
	@FindBy(xpath="//a[text()='click here']") private WebElement click;
	public boolean click() {return click.isDisplayed();}
	
	@FindBy(xpath="(//p)[1]") private WebElement p1;
	public String p1() {return p1.getText();}
	
	@FindBy(xpath="(//p)[2]") private WebElement p2;
	public String p2() {return p2.getText();}
	
	@FindBy(xpath="(//div[@class='large-10 columns'])[1]") private WebElement mp1;
	public boolean mp1() {return mp1.isDisplayed();}
	
	@FindBy(xpath="(//div[@class='large-10 columns'])[2]") private WebElement mp2;
	public boolean mp2() {return mp2.isDisplayed();}
	
	@FindBy(xpath="(//div[@class='large-10 columns'])[3]") private WebElement mp3;
	public boolean mp3() {return mp3.isDisplayed();}
}
