package com.jiomart.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;



public class BrowserUtil {

	 WebDriver driver;

	
	/**
	 * This method is initialize the driver on the basis of given browser.
	 * Possible browsers are chrome, firefox, safari, edge
	 * @param browserName
	 * 
	 */
	public void initDriver(String browserName) {

		System.out.println("launching the browser: " + browserName);
		
		if(browserName == null) {
			System.out.println("browser can not be null");
			//throw new MySeleniumException("BROWSERNULLEXCEPTION");
		}
		
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(co);
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "safari":
			driver = new SafariDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;

		default:
			System.out.println("plz pass the right browser name...." + browserName);
			//throw new MySeleniumException("WRONGBROWSEREXCEPTION");
		}

	}
	
	/**
	 * This method is used to launch the application url. Please pass the url with http/https.
	 * @param url
	 */
	public void launchURL(String url) {
		
		if(url == null) {
			System.out.println("URL can not be null");
			//throw new MySeleniumException("URLNULLXCEPTION");
		}
		
		if(url.contains("http")) { 
			driver.get(url);
		}
		else {
			System.out.println("http(s) is missing");
		}
		
		
	}
	
	
	//
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String getPageURL() {
		return driver.getCurrentUrl();
	}
	
	public void quitBrowser() {
		driver.quit();
	}
	
	public void closeBrowser() {
		driver.close();
	}
	
	

}