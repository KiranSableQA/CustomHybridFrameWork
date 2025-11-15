package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{
	public LoginPage(WebDriver driver) 
	{
		super(driver);   
	}
	

	@FindBy (xpath = "//input[@placeholder='name@company.com']") WebElement email_Field;
	@FindBy (xpath = "//input[@placeholder='Enter your password']") WebElement password_Field;
	@FindBy (xpath = "//button[normalize-space()='Sign In']") WebElement login_click;
	@FindBy (xpath = "//div[normalize-space()='Green Jeeva LLC']") WebElement MsgConfirmation;
	
	
	public void set_email_Field(String email)
	{
		email_Field.sendKeys(email);
	}
	public void set_password_Field(String password)
	{
		password_Field.sendKeys(password);
	}
	public void click_login_click() 
	{
		login_click.click();
	}
	
	public String getConfirmationMessage() 
	{
		try 
		{
			return (MsgConfirmation.getText());
		} catch (Exception e)
		{
			return(e.getMessage());
		}
	
	}
}
