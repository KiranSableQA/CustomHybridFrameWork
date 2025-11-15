package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC002_Login extends BaseClass   
{
	@Test
	public void verify_login() throws InterruptedException 
	{
		
		logger.info("***** Starting TC002_Login ******"); 
		
		try 
		{
		LoginPage lg=new LoginPage(driver); 
		lg.set_email_Field("kiran.sable@greenjeeva.com");   
		lg.set_password_Field("Test#111"); 
		lg.click_login_click();
		
		logger.info("***** Login Successfully ******");
		
		logger.info("***** Validating Expected result ******"); 
		String confmsg = lg.getConfirmationMessage();
		Thread.sleep(1000);
		
		if(confmsg.equals("Green Jeeva LLC")) 
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed...."); 
			logger.debug("Debug logs....");
			Assert.assertTrue(false);
		}
		
//		Assert.assertEquals(confmsg, "Green Jeeva LLC3");
		
		System.out.println("get expected Result : - "+" " +confmsg);
		} 
		catch (Exception e) 
		{
		Assert.fail();
		}
		
		logger.info("***** Finish TC002 ******");
	}

}
