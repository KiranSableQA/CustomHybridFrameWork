package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistration extends BasePage
{

	

	public AccountRegistration(WebDriver driver) {
		super(driver);
	}

	@FindBy (xpath = "//input[@id='input-firstname']") WebElement first_Name;
	@FindBy (xpath = "//input[@id='input-lastname']") WebElement last_Name;
	@FindBy (xpath = "//input[@id='input-email']") WebElement  e_Mail ;
	@FindBy (xpath = "//input[@id='input-telephone']") WebElement telephone_No;
	@FindBy (xpath = "//input[@id='input-password']") WebElement password;
	@FindBy (xpath = "//input[@id='input-confirm']") WebElement con_Password;
	@FindBy (xpath = "//input[@value='0']") WebElement newsletter_subscribe;
	@FindBy (xpath = "//input[@value='Continue']") WebElement continue_btn ;
	@FindBy (xpath = "//input[@name='agree']") WebElement privacy_Policy_Btn ;
	@FindBy (xpath = "//h1[normalize-space()='Your Account Has Been Created!']") WebElement MsgConfirmation;
	
	
	// Methods to interact with elements:

	public void enterFirstName(String fname)
	{
	    first_Name.sendKeys(fname);
	}

	public void enterLastName(String lname)
	{
	    last_Name.sendKeys(lname);
	}

	public void enterEmail(String email) 
	{
	    e_Mail.sendKeys(email);
	}

	public void enterTelephone(String phone) 
	{
	    telephone_No.sendKeys(phone);
	}

	public void enterPassword(String pwd) 
	{
	    password.sendKeys(pwd);
	}

	public void enterConfirmPassword(String pwd)
	{
	    con_Password.sendKeys(pwd);
	}

	public void selectNewsletterSubscribe() 
	{
	    if(!newsletter_subscribe.isSelected()) 
	    {
	        newsletter_subscribe.click();
	    }
	}
	
	public void click_privacy_Policy_Btn() 
	{
		privacy_Policy_Btn.click();
	}
	
	public void clickContinueButton() 
	{
	    continue_btn.click();
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
