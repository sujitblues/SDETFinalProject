package com.jiomart.testcases;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jiomart.pageobject.HomePage;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;



public class TC_HomePageTest extends BaseClass{
	

	@Test
	public void VerifyTitle()
	{
		String ExpetedTitle="JioMart: India's online shopping destination";
		HomePage homePageObj=new HomePage(driver);
		String ActualTile=homePageObj.getTitle();
		System.out.println(ActualTile);
		
	if(ActualTile.equals(ExpetedTitle))
	{
		Assert.assertTrue(true);
		Logger.info("Title Matched : Pass");
	}
	else
	{
		Assert.assertTrue(false);
		Logger.info("Title Not Matched : Fail");
	}
	}
	@Test
	public void verifyMouseHoverOnElectronicMenu() throws InterruptedException
	{
		HomePage homePageObj=new HomePage(driver);
		homePageObj.mouseHover();
		Thread.sleep(3000);
		
	}
	@Test
	public void VerifyCaptureLogo() throws IOException
	{
		HomePage homePageObj=new HomePage(driver);
		homePageObj.captureLogoImage();
		File f=new File("D://selenium logo/logo.png");
		if(f.exists())
		{
			System.out.println("Image file Captured");
			Logger.info("Image Captured", browser);
			
		}
		else
		{
			System.out.println("Image File not Captured");
			Logger.info("Image Captured", browser);
			caputreScreenShot(driver,"captureed Logo");
		}
		
		}
	@Test 
	public void compairImage() throws IOException
	{
		BufferedImage expectedImage=ImageIO.read(new File("D://selenium logo/logo.png"));
		WebElement LogoImageElement=driver.findElement(By.xpath("//div[@class='header-main-logo jm-mr-m'][1]"));
		Screenshot logoImageScreenshot=new AShot().takeScreenshot(driver,LogoImageElement);
		BufferedImage actualImage=logoImageScreenshot.getImage();
		ImageDiffer imgDiff=new ImageDiffer();
		ImageDiff diff=imgDiff.makeDiff(expectedImage, actualImage);
		 
		if(diff.hasDiff()==true)
		{
			System.out.println("Images are Not same");
            Logger.info("Image are not same");
		}
		else
		{
			System.out.println("Images are same");
			Logger.info("Image are same");
			//caputreScreenShot(driver,"compairImage");
			
		}
	}
	@Test
	public void VerifyCatgoriesItem() throws IOException
	{
		HomePage homePageObj=new HomePage(driver);
		ArrayList<String> expectedCategoriesItems= new ArrayList<String>();
		expectedCategoriesItems.add("Groceries");
		expectedCategoriesItems.add("Premium Fruits");
		expectedCategoriesItems.add("Home & Kitchen");
		expectedCategoriesItems.add("Fashion");
		expectedCategoriesItems.add("Electronics");
		expectedCategoriesItems.add("Beauty");
		expectedCategoriesItems.add("Jewellery");
		expectedCategoriesItems.add("Home Improvement");
		expectedCategoriesItems.add("Sports, Toys & Luggage");
		List<String>ActualCategoriesItems=new ArrayList<String>();
		ActualCategoriesItems=homePageObj.readMenuItem();
		System.out.println(ActualCategoriesItems);		
	Assert.assertEquals(ActualCategoriesItems, expectedCategoriesItems);
	Logger.info("Categoires item campaired");
	//caputreScreenShot(driver,"VerifyCatgoriesItem");
	}
	@Test
	public void VerifyMenuCount() throws IOException
	{
		HomePage homePageObj=new HomePage(driver);
		int expectedMenuCount=9;
		int AcutalmenuCount=homePageObj.countMenuItem();
		Assert.assertEquals(AcutalmenuCount, expectedMenuCount);
		Logger.info("Categries count");
		//caputreScreenShot(driver,"VerifyMenuCount");
	}
}
