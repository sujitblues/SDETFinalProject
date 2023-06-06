package com.jiomart.testcases;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import com.jiomart.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;
public class BaseClass {
	ReadConfig readConfig =new ReadConfig();
	String url =readConfig.getBaseUrl();
	String browser=readConfig.getBrowser();
	public static WebDriver	driver;
	public static Logger Logger;
	
	//using browser value from config.properties file
	@BeforeClass
	public void setup()
	{
		System.out.println(browser);
		
		switch(browser.toLowerCase().trim())
		{
		case "chrome" :
			WebDriverManager.chromedriver().setup();
			ChromeOptions op=new ChromeOptions();
			op.addArguments("--remote-allow-origins=*");
			driver=new ChromeDriver(op);
			driver.manage().window().maximize();
			break;
		case "firefox" :
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			break;
		case "edge" :
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			driver.manage().window().maximize();
			break;
		default :
			driver=null;
			break;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		driver.get(url);
		//for logging
		Logger=LogManager.getLogger("SDET-Final");
	}
	public void caputreScreenShot(WebDriver driver,String testName) throws IOException
	{
		try {
		//Step1: Convert webDriver object to TakesCreenshot interface
		TakesScreenshot screenshot=((TakesScreenshot)driver);
		
		//Step2: call getScreenshotAs method to create image file
		File src=screenshot.getScreenshotAs(OutputType.FILE);
		File dest=new File(System.getProperty("user.dir")+"//Screenshots//"+testName+".png");
		
		//Step3: Copy image file to destination
		FileUtils.copyFile(src, dest);
		}
		catch (Exception e) {
	        System.out.println("Exception while taking screenshot " + e.getMessage());
	    }
	}

	
	@AfterClass
	public void tearDown()
	{
		driver.close();
		driver.quit();
	}


}
