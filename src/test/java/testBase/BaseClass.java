package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;

public class BaseClass    
{
    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups = {"Sanity", "Regression", "Master", "DataDriven"})
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException 
    {
        // Load config
        FileReader file = new FileReader("./src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());

        // =======================================================================
        // 1️ LOCAL EXECUTION (Chrome / Edge / Firefox)
        // =======================================================================

/*
        System.out.println("Running on LOCAL Machine...");
        switch(br.toLowerCase()) {
            case "chrome": driver = new ChromeDriver(); break;
            case "edge": driver = new EdgeDriver(); break;
            case "firefox": driver = new FirefoxDriver(); break;
            default: throw new RuntimeException("Invalid Browser: " + br);
        }
*/

        // =======================================================================
        // 2️ SELENIUM GRID EXECUTION (without Docker)
        // =======================================================================

/*
        System.out.println("Running using SELENIUM GRID...");
        DesiredCapabilities cap = new DesiredCapabilities();

        // OS
        if (os.equalsIgnoreCase("windows")) cap.setPlatform(Platform.WIN11);
        else if (os.equalsIgnoreCase("mac")) cap.setPlatform(Platform.MAC);

        // Browser
        cap.setBrowserName(br.toLowerCase());

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
*/

        // =======================================================================
        // 3️ DOCKER SELENIUM GRID EXECUTION (hub + chrome/firefox containers)
        // =======================================================================

/*
        System.out.println("Running on DOCKER SELENIUM GRID...");
        DesiredCapabilities capDocker = new DesiredCapabilities();
        capDocker.setBrowserName(br.toLowerCase());   // chrome or firefox

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capDocker);

*/
        // =======================================================================
        
//		For selenium Grid Setup
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();

			//			os
			if(os.equalsIgnoreCase("windows")) 
			{  capabilities.setPlatform(Platform.WIN11); } 
			else if (os.equalsIgnoreCase("mac")) 
			{  capabilities.setPlatform(Platform.MAC);  } 
			else  
			{ System.out.println(" no matching os"); }

			//			browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("no matching browser");
			}

			driver = new RemoteWebDriver (new URL("http://localhost:4444/wd/hub"), capabilities);
		}

		//		For paralle testing/ crossbrowser testing	(For selenium Grid Setup) 
		if (p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{ 
			case "chrome" : driver = new ChromeDriver();break;  
			case "edge" : driver = new EdgeDriver();break;
			case "firefox" : driver = new FirefoxDriver();break; 
			default: System.out.println("no matching browser");  
			return;
			}

		}
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(p.getProperty("appURL2"));
    }

    @AfterClass(groups = {"Sanity", "Regression", "Master", "DataDriven"})
    public void tearDown() {
        driver.quit();
    }

    // -------------------------------------------------------------------------
    // RANDOM DATA GENERATION HELPERS
    // -------------------------------------------------------------------------

    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphanumeric() {
        return RandomStringUtils.randomAlphabetic(5) + "@#$" +
               RandomStringUtils.randomNumeric(5);
    }

    // -------------------------------------------------------------------------
    // SCREENSHOT (Allure Compatible)
    // -------------------------------------------------------------------------
    public static String captureScreenshot(String testName) {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date()); 
        String screenshotPath = System.getProperty("user.dir") +
                "/screenshots/" + testName + "_" + timeStamp + ".png";

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            File trg = new File(screenshotPath);
            FileUtils.copyFile(src, trg);

            // For Allure Report
            Allure.addAttachment("Screenshot - " + testName,
                    new ByteArrayInputStream(ts.getScreenshotAs(OutputType.BYTES)));

        } catch (Exception e) {
            System.out.println("Screenshot error: " + e.getMessage());
        }

        return screenshotPath;
    }

}
