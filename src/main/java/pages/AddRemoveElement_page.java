package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.setup;

public class AddRemoveElement_page extends setup {

	public AddRemoveElement_page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	

	//Entering into the add/remove Element page.
	@FindBy(xpath="//a[text()='Add/Remove Elements']") private WebElement addremovebutton;
	public void addremove() {addremovebutton.click();}
	
	//clicking on add element button
	@FindBy(xpath="//button[text()='Add Element']") private WebElement add;
	public void add() {add.click();}
	
	
	@FindBy(xpath="//button[@class='added-manually']") private WebElement remove;
	public void remove() {remove.click();}
	public String checkremove() {return remove.getText();}
}
