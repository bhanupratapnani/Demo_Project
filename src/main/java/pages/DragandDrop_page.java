package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.setup;

public class DragandDrop_page extends setup{
	
	public DragandDrop_page(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[text()='Drag and Drop']") private WebElement dragdrop;
	public void dragdrop() {dragdrop.click();}

}
