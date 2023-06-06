package com.jiomart.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jiomart.pageobject.HomePage;
import com.jiomart.pageobject.SearchResultPage;

public class TC_SearchResultPage extends BaseClass{
	
	
	/*
	SearchResultPage searchresultpage=new SearchResultPage();
	HomePage homepage =new HomePage();
	*/
	@Test
	public void VerifyPriceRange() throws InterruptedException, IOException
	{
		SearchResultPage searchresultpage=new SearchResultPage(driver);
		HomePage homepage =new HomePage(driver);
		double expectedPriceRange=60000.0;
		homepage.enterSearchText();
		Thread.sleep(5000);
		searchresultpage.priceSliderAdjust();
		double ActualPriceRange=searchresultpage.getCurrentPriceRange();
		if(ActualPriceRange==expectedPriceRange)
		{
			Logger.info("Price Range Matched : Pass");
			Assert.assertTrue(true);
		}
		else
		{
			Logger.info("Price Range not Matched : Fail");
			Assert.assertTrue(false);
			//caputreScreenShot(driver,"VerifyPriceRange");
			
		}
	}
	@Test
	public void VerifySmartPhone() throws InterruptedException, IOException
	{
		SearchResultPage searchresultpage=new SearchResultPage(driver);
		HomePage homepage =new HomePage(driver);
		String searchkey="Samsung Galaxy  64 GB";
		homepage.enterSearchText();
		Thread.sleep(5000);
		String searchproducttext=searchresultpage.getTextSearchProduct();
		String producttitle=searchproducttext.replace("A04e","");
		System.out.println("Searched product titile "+searchproducttext);
		System.out.println("Searched product titile "+producttitle);
		if(producttitle.contains(searchkey))
		{
			Logger.info("Search Product test case Pass");
			Assert.assertTrue(true);
		}
		else
		{
			Logger.info("Search Product test case Fail");
			homepage.caputreScreenShot(driver, searchproducttext);
			Assert.assertTrue(false);
			
		}
	}

}
