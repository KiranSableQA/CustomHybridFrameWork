package pageObjects;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage 
{
	
	public HomePage(WebDriver driver) 
	{
		super(driver);  
	}

	@FindBy (xpath = "//span[normalize-space()='My Account']") WebElement linkMyAccount;
	@FindBy (xpath = "//a[normalize-space()='Register']") WebElement linkRegister;
	@FindBy (xpath = "//a[text()='Login']") WebElement loginLink;
	
	
	public void click_linkMyAccount()
	{
		linkMyAccount.click();


	}
	public void click_linkRegister()
	{
		linkRegister.click();
	}
	public void click_loginLink()
	{
		loginLink.click();
	}
}
