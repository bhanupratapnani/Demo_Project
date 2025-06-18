package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.setup;

public class DynamicControl_page extends setup{
	
	public DynamicControl_page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//a[text()='Dynamic Controls']") private WebElement control;
	public void control() {control.click();}
	
	@FindBy(xpath="//h4[text()='Dynamic Controls']") private WebElement title;
	public String title() {return title.getText();}
	
	@FindBy(xpath="//p") private WebElement p1;
	public String p1() {return p1.getText();}
	
	@FindBy(xpath="//div[@id='checkbox']/input") private WebElement checkbox;
	public boolean vcheck() {return checkbox.isDisplayed();}
	public void checkbox() {checkbox.click();}
	
	@FindBy(xpath="//button[@type='button']") private WebElement remove;
	public void remove() {remove.click();}
	public boolean vremove() {return remove.isDisplayed();}
	public String cremove() {return remove.getText();}
	
	@FindBy(xpath="//input[@type='text']") private WebElement box;
	public boolean vbox() {return box.isEnabled();}
	public void box() {box.sendKeys("Operation Sindoor");}
	
	@FindBy(xpath="//input[@type='text']/following-sibling::button") private WebElement enable;
	public void enable() {enable.click();}
	public boolean venable() {return enable.isDisplayed();}
	public String cenable() {return enable.getText();}
}
