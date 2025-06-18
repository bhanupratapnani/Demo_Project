package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.setup;

public class AB_testing_page extends setup{
	
	public AB_testing_page(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	
	//clicking on A/B Testing 
	@FindBy(xpath="//a[text()='A/B Testing']") private WebElement abtesting;
	
	public void abtesting() {abtesting.click();}
	
	//verifying HEADER
	@FindBy(xpath="//h3[text()='A/B Test Control']") private WebElement header;
	
	public String header() {return header.getText();}
	
	//verify Paragraph
	@FindBy(xpath="//h3[text()='A/B Test Control']/following::p") private WebElement paragraph;
	
	public String paragraph() {return paragraph.getText();}
	
	//verify FOOTER
	@FindBy(xpath="//div[@style='text-align: center;']") private WebElement footer;
	
	public String footer() {return footer.getText();}
	
	//verify link display
	@FindBy(xpath="") private WebElement link;
	
	public boolean link() {return link.isDisplayed();}
}
