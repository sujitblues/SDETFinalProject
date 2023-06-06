package com.jiomart.testcases;

import java.io.IOException;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jiomart.pageobject.HomePage;
import com.jiomart.pageobject.ProductDetailPage;
import com.jiomart.pageobject.SearchResultPage;

public class TC_ProductDetailPage extends BaseClass {
	/*
	 * HomePage homepage=new HomePage(driver); ProductDetailPage proddetailpage=new
	 * ProductDetailPage(driver); SearchResultPage searchresult=new
	 * SearchResultPage(driver);
	 */

	@Test
	public void VerifyProductPrice() throws InterruptedException {
		HomePage homepage = new HomePage(driver);
		ProductDetailPage proddetailpage = new ProductDetailPage(driver);
		SearchResultPage searchresult = new SearchResultPage(driver);

		homepage.enterSearchText();
		searchresult.clickOnProduct();
		double AcutalProductPrice = proddetailpage.getProductPrice();
		System.out.println("Actual price " + AcutalProductPrice);
		if (AcutalProductPrice >= 0) {
			Logger.info("Valid product price so test case Pass");
			Assert.assertTrue(true);
		} else {
			Logger.info("Invalid product price Test case Fail");
			Assert.assertFalse(false);

		}

	}

	@Test
	public void VerifyAddToCart() throws InterruptedException, IOException {
		HomePage homepage = new HomePage(driver);
		ProductDetailPage proddetailpage = new ProductDetailPage(driver);
		SearchResultPage searchresult = new SearchResultPage(driver);
		String ExpectedIncartMsg = "in cart";
		homepage.enterSearchText();
		searchresult.clickOnProduct();
		proddetailpage.enterPinCode();
		proddetailpage.clickOnApplyButton();
		proddetailpage.clickOnAddToCart();
		String ActualIncartMsg = proddetailpage.getAddtocartMsg();
		System.out.println("Actual message:"+ActualIncartMsg);
		if (ActualIncartMsg.equals(ExpectedIncartMsg)) {
			Logger.info("Item added successfully :Pass");
			Assert.assertTrue(true);
		} else {
			Logger.info("Item added successfully :Fail");
			Assert.assertTrue(false);
			//caputreScreenShot(driver,"VerifyAddToCart");
		}
	}
	@Test
	public void VerifyAddToCartSuccessfullText() throws InterruptedException
	{
		String ExpectedText="Added to cart";
		HomePage homepage = new HomePage(driver);
		ProductDetailPage proddetailpage = new ProductDetailPage(driver);
		SearchResultPage searchresult = new SearchResultPage(driver);
		homepage.enterSearchText();
		searchresult.clickOnProduct();
		proddetailpage.enterPinCode();
		proddetailpage.clickOnApplyButton();
		proddetailpage.clickOnAddToCart();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		String AcutalText=proddetailpage.getSuccessfullText();
		Assert.assertEquals(AcutalText, ExpectedText,"Checking item added to cart");
		
	}

	@Test
	public void VerifyStimationMessage() throws InterruptedException, IOException {
		HomePage homepage = new HomePage(driver);
		ProductDetailPage proddetailpage = new ProductDetailPage(driver);
		SearchResultPage searchresult = new SearchResultPage(driver);
		homepage.enterSearchText();
		searchresult.clickOnProduct();
		proddetailpage.enterPinCode();
		proddetailpage.clickOnApplyButton();
		String deliverytext = proddetailpage.getStimationSuccessText();
		if (deliverytext.contains("Delivery by")) {
			Logger.info("Delivery available on entered pin code:");
			Assert.assertTrue(true);
		} else {
			Logger.info("Delivery not available on entered pin code");
			Assert.assertTrue(false);
			//caputreScreenShot(driver,"VerifyStimationMessage");
		}
	}
	/*
	 * public void keyFeature() { HomePage homepage=new HomePage();
	 * ProductDetailPage proddetailpage=new ProductDetailPage(); SearchResultPage
	 * searchresult=new SearchResultPage(); String expectedText = "Expected Text";
	 * homepage.mouseHover(); homepage.clickOnMouseHoverItem();
	 * searchresult.clickOnProduct(); proddetailpage.readKeyFeature(); //
	 * Assert.assertEquals(actualText, expectedText); } }
	 */
}
