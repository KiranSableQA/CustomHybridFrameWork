package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.utility.RandomString;
import pageObjects.AccountRegistration;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_Account_Registration_Test extends BaseClass   
{
	@Test (groups = {"Regression","Master"})
	public void verify_account_registration() throws InterruptedException 
	{
		HomePage hp=new HomePage(driver); 
		hp.click_linkMyAccount(); 
		hp.click_linkRegister(); 

		AccountRegistration ar = new AccountRegistration (driver);   
		/*
		ar.enterFirstName("Max");
		ar.enterLastName("Hub");
		ar.enterEmail("symudo0@mailinator.com");
		ar.enterTelephone("9876543210");
		Thread.sleep(1000);
		ar.enterPassword("Kush#1111");
		Thread.sleep(1000);
		ar.enterConfirmPassword("Kush#1111");
		ar.selectNewsletterSubscribe();
		Thread.sleep(1000);
		ar.click_privacy_Policy_Btn();
		Thread.sleep(1000);
		ar.clickContinueButton();
		String confmsg = ar.getConfirmationMessage();
		Thread.sleep(1000);
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		System.out.println("get output msg : - "+" " +confmsg);
		 */

		ar.enterFirstName(randomString().toUpperCase());
		ar.enterLastName(randomString().toUpperCase());
		ar.enterEmail(randomString()+ "@mailinator.com");
		ar.enterTelephone(randomNumber());
		String password = randomAlphanumeric();
		ar.enterPassword(password);
		ar.enterConfirmPassword(password);
		ar.selectNewsletterSubscribe();
		Thread.sleep(1000);
		ar.click_privacy_Policy_Btn();
		Thread.sleep(1000);
		ar.clickContinueButton();

		String confmsg = ar.getConfirmationMessage();
		Thread.sleep(1000);
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		System.out.println("get output msg : - "+" " +confmsg); 

	}


	
}
