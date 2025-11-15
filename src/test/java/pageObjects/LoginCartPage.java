package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginCartPage extends BasePage {

	public LoginCartPage(WebDriver driver)
	{
		super(driver); 
	}
	@FindBy(xpath = "//input[@id='input-email']")WebElement emailFiled;
	@FindBy(xpath = "//input[@id='input-password']")WebElement passwordFiled;
	@FindBy(xpath = "//input[@value='Login']")WebElement loginbtn; 
	

	
	public void Set_emailFiled (String email) 
	{
		emailFiled.sendKeys(email);
	}
	
	public void Set_passwordFiled (String pwd) 
	{
		passwordFiled.sendKeys(pwd);
	}
	
	public void Set_loginbtn () 
	{
		loginbtn.click();
	}
}
