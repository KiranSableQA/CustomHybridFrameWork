package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BasePage;
import pageObjects.HomePage;
import pageObjects.LoginCartPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginCart extends BaseClass  
{
	@Test (groups = {"Sanity", "Master"})
	public void verify_login()
	{
		logger.info(" ***** Start TC002_LoginCart ***** "); 

		try {
			HomePage  homepage = new HomePage(driver);  
			homepage.click_linkMyAccount(); 
			homepage.click_loginLink();


			LoginCartPage lcp = new LoginCartPage(driver);
			lcp.Set_emailFiled(p.getProperty("email2"));

			Thread.sleep(4000);
			lcp.Set_passwordFiled(p.getProperty("password2"));
//			lcp.Set_passwordFiled(p.getProperty("password3")); 

			lcp.Set_loginbtn(); 


			MyAccountPage map = new MyAccountPage (driver); 
			boolean targetpage= map.isMyPageExists();
			Assert.assertEquals(targetpage, true, "My Account");    
		} 
		catch(Exception e) 
		{ 
			Assert.fail();
		}
		logger.info(" ***** Finish TC002_LoginCart ***** ");
	}

}

