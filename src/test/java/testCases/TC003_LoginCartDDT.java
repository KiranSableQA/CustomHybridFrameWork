package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginCartPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginCartDDT  extends BaseClass 
{
	@Test (dataProvider = "LoginData", dataProviderClass=DataProviders.class,groups = "DataDriven" ) // getting data provider from different class
	public void  verify_loginDDT (String email,String pwd, String exp) 
	{
		logger.info("******  Start The TC003_LoginDDT ******");  
		
		try {
			HomePage  homepage = new HomePage(driver);  
			homepage.click_linkMyAccount();
			homepage.click_loginLink();

			LoginCartPage lcp = new LoginCartPage(driver); 
			lcp.Set_emailFiled(email);
			lcp.Set_passwordFiled(pwd);
			lcp.Set_loginbtn();

			MyAccountPage map = new MyAccountPage (driver);
			boolean targetpage= map.isMyPageExists();

			/*Data is valid  - login success - test pass  - logout
		  Data is valid  -- login failed - test fail

		  Data is invalid - login success - test fail  - logout
		  Data is invalid -- login failed - test pass
			 */
			if (exp.equalsIgnoreCase("Valid"))
			{
				if(targetpage==true) 
				{
					map.clicklogOut();
					Assert.assertTrue(true);	
				}
				else 
				{
					Assert.assertTrue(false);
				}
			}
			if (exp.equalsIgnoreCase("invalid"))
			{
				if(targetpage==true) 
				{
					map.clicklogOut();
					Assert.assertTrue(false);	
				}
				else 
				{
					Assert.assertTrue(true);
				}
			}
		}
		catch (Exception e)
		{
			Assert.fail();
		}

		logger.info("******  End The TC003_LoginDDT ******");
	}





}
