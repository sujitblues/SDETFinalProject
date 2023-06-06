package com.jiomart.pageobject;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.jiomart.testcases.BaseClass;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

public class HomePage extends BaseClass{
	//Create object of webdriver
	WebDriver ldriver;
	public HomePage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	/*
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}*/
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		@FindBy(xpath="//ul[@class='header-nav|1 custom-scrollbar]'//li[@class='headr-nav-|1-item'")WebElement menuItem;
		@FindBy(xpath="//a[@id='nav_link_4']")
		WebElement electronicsMenu;
		@FindBy(xpath="//a[@id='nav_link_757']") WebElement mobAndTablet;
		@FindBy(xpath="//a[@id='nav_level3_777']") WebElement smartPhone;
		@FindBy(xpath="//ul[@class='header-nav-l1 custom-scrollbar']//li[@class='header-nav-l1-item']//a[@class='header-nav-l1-item-link']")
		  List<WebElement> catgories;
		//WebElement catgories;
		@FindBy(xpath="//div[@class='header-main-logo jm-mr-m'][1]") WebElement logo;
		@FindBy(xpath="//input[@id='autocomplete-0-input']") WebElement searchTextBox;
		
			//Searching product
			public void enterSearchText() throws InterruptedException
			{
				Thread.sleep(5000);
				//searchTextBox.clear();
				searchTextBox.sendKeys("Samsung Galaxy 64 GB",Keys.ENTER);
			}
		//Capture logo image
		public void captureLogoImage() throws IOException
		{
			
			Screenshot logoImageScreenshot=new AShot().takeScreenshot(driver,logo);
			
			ImageIO.write(logoImageScreenshot.getImage(), "png", new File("D://selenium logo/logo.png"));
		//File f=new File("D://selenium logo/logo.png");
		}
		public List<String> readMenuItem()
		{
			
		        List<WebElement> values = driver.findElements(By.xpath("//ul[@class='header-nav-l1 custom-scrollbar']//li[@class='header-nav-l1-item']//a[@class='header-nav-l1-item-link']"));
		        List<String> listStr=new ArrayList<String>();
		        for (WebElement anchor : values) {
		        	listStr.add(anchor.getText().trim());
		        }
		        return listStr;
		}
		public void displayMenuItem()
		{
			List<String> anchorTagValues = readMenuItem();
			System.out.println(anchorTagValues);
			
		}
		public int countMenuItem()
		{
			List<String> anchorTagValues = readMenuItem();
			int menuCount=anchorTagValues.size();
			System.out.println("Number of Categories :"+anchorTagValues.size());
			return menuCount;
		}
		public void mouseHover() throws InterruptedException
		{
			
			Actions act=new Actions(driver);
			act.moveToElement(electronicsMenu).moveToElement(mobAndTablet).build().perform();
			Thread.sleep(5000);
		
		}
		public void clickOnMouseHoverItem() throws InterruptedException
		{
			mouseHover();
			Thread.sleep(5000);
			smartPhone.click();
			
		}
		public String getTitle()
		{
			return driver.getTitle();
		}
		
		

}
