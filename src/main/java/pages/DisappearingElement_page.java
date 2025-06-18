package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.setup;

public class DisappearingElement_page extends setup{
	
	public DisappearingElement_page(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[text()='Disappearing Elements']") private WebElement dEle;
	public void dele() {dEle.click();}
	
	@FindBy(xpath="//a[text()='Home']") private WebElement home;
	public void home() {home.click();}
	
	@FindBy(xpath="//a[text()='About']") private WebElement about;
	@FindBy(xpath="//h1[text()='Not Found']") private WebElement v1;
	public void about() {about.click();}
	public String v1() { return v1.getText();}
	
	@FindBy(xpath="//a[text()='Contact Us']") private WebElement contact;
	@FindBy(xpath="//h1[text()='Not Found']") private WebElement v2;
	public void contact() {contact.click();}
	public String v2() { return v2.getText();}
	
	@FindBy(xpath="//a[text()='Portfolio']") private WebElement portfolio;
	@FindBy(xpath="//h1[text()='Not Found']") private WebElement v3;
	public void portfolio() {portfolio.click();}
	public String v3() { return v3.getText();}
	
	@FindBy(xpath="//a[text()='Gallery']") private WebElement gallery;
	@FindBy(xpath="//h1[text()='Not Found']") private WebElement v4;
	public void gallery() {gallery.click();}
	public String v4() { return v4.getText();}
	
	
	
	
	

}
