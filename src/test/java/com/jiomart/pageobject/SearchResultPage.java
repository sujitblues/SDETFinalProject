package com.jiomart.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jiomart.testcases.BaseClass;

public class SearchResultPage extends BaseClass{
	//Create object of webdriver
	
			WebDriver ldriver;
			public SearchResultPage(WebDriver rdriver)
			{
				ldriver=rdriver;
				PageFactory.initElements(rdriver, this);
			}
			
/*
	public SearchResultPage()
	{
		
		PageFactory.initElements(driver, this);
	}
	*/
	@FindBy(xpath="//div[@id='avg_selling_price_filter']//div[@role='slider'][2]") WebElement max_Slider;
	@FindBy(xpath="//ol[@class='ais-InfiniteHits-list jm-row jm-mb-massive']//li[1]//a") WebElement searchResultProduct;
	//@FindBy(css="#.plp-card-details-name") WebElement searchResultProduct;
	
	
	public void priceSliderAdjust() throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", max_Slider);
		 Thread.sleep(10000);
		Actions moveSlider = new Actions(driver);
		 moveSlider.dragAndDropBy(max_Slider, -200, 0).build().perform();
	}
	public double getCurrentPriceRange() throws InterruptedException
	{
		priceSliderAdjust();
		String selectedpriceRange=max_Slider.getAttribute("aria-valuenow");
		double priceRange=Double.parseDouble(selectedpriceRange);
		return priceRange;
	}
	public String getTextSearchProduct() throws InterruptedException
	{
		Thread.sleep(3000);
		String searchproducttext=searchResultProduct.getText();
		return searchproducttext;
	}
	public void clickOnProduct() throws InterruptedException
	{
		Thread.sleep(3000);
		searchResultProduct.click();
	}
	
}
