package com.jiomart.pageobject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jiomart.testcases.BaseClass;

public class ProductDetailPage extends BaseClass{
	//Create object of webdriver
	
			WebDriver ldriver;
			public ProductDetailPage(WebDriver rdriver)
			{
				ldriver=rdriver;
				PageFactory.initElements(rdriver, this);
			}
			
			
	/*
	public ProductDetailPage()
	{
		
		PageFactory.initElements(driver, this);
	}
	*/
	@FindBy(xpath="//span[@class='jm-heading-xs jm-ml-xxs']") WebElement productPrice;
	@FindBy(xpath="//input[@id='prod_rel_pincode']") WebElement pincode;
	@FindBy(xpath="//div[@class='product-delivery-pincode-cta']//button") WebElement pinCodeApply;
	@FindBy(xpath="//ul[@class='product-key-features-list']//li")
	List<WebElement> keyFeature;
	
	@FindBy(xpath="//button[@class='jm-btn primary medium center full-width jm-fw-bold addtocartbtn']")WebElement addToCart;
	@FindBy(xpath="//div[@class='field-success']") WebElement checkStimationSuccessMsg;
	@FindBy(xpath="//div[@class='jm-badge jm-fc-primary-grey-80 jm-mt-xxs' ]//img") WebElement incart;
	@FindBy(xpath="//div[@class='jm-toast-content-message") WebElement successFullText;
	/*
	@DataProvider(name = "pinCodes")
    public Object[][] getPinCodes() {
        return new Object[][] {
            { "123456" },
            { "789012" },
            { "345678" }
        };
    }
	@Test(dataProvider = "pinCodes")
	public void enterPinCode(String pinCode)
	{
		
		pincode.clear();
		pincode.sendKeys(pinCode);
	}
	*/
	public void enterPinCode()
	{
		
		pincode.clear();
		pincode.sendKeys("110044");
	}
	public void clickOnApplyButton()
	{
		pinCodeApply.click();
	}

	public void writeKeyFeatureInExcel() throws IOException
		{
		Workbook workbook = new XSSFWorkbook("/SDET-Final/src/test/resources/WebPageData.xlsx");
		Sheet sheet = workbook.createSheet("Web Page Data");

		int rownum = 0;
		for (WebElement element : keyFeature) {
		    String text = element.getText();
		    Row row = sheet.createRow(rownum++);
		    Cell cell = row.createCell(0);
		    cell.setCellValue(text);
		}
		FileOutputStream outputStream = new FileOutputStream("WebPageData.xlsx");
		workbook.write(outputStream);
		workbook.close();

	}
	public void readKeyFeature() throws IOException
	{
		FileInputStream inputStream = new FileInputStream(new File("WebPageData.xlsx"));
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheet("Web Page Data");
		int numRows = sheet.getPhysicalNumberOfRows();
		for (int i = 0; i < numRows; i++) {
		    Row row = sheet.getRow(i);
		    Cell cell = row.getCell(0);
		    String actualText = cell.getStringCellValue();
		    //return actualText;
		}
	}
	/*
	public void readAndWriteKeyFeatureToExcel()
	{
		// create a new Excel workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        
        // create a new sheet in the workbook
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        
        // assuming you have a WebDriver instance named "driver"
        driver.get("https://www.example.com");
        
        // assuming you have a WebElement instance named "element"
        //String textContent = keyFeature.ge
        List<String> keyfeature=new ArrayList<String>();
        for(String i:keyfeature)
        	
        
        // create a new row in the sheet
        XSSFRow row = sheet.createRow(0);
        
        // create a new cell in the row and set its value
        XSSFCell cell = row.createCell(0);
        cell.setCellValue(textContent);
        
        // write the workbook to a file
        FileOutputStream outputStream = new FileOutputStream("output.xlsx");
        workbook.write(outputStream);
        outputStream.close();
    }
*/
	
	public String getStimationSuccessText()
	{
		String successMsg=checkStimationSuccessMsg.getText();
		return successMsg;
	}
	public double getProductPrice()
	{
		
		String prodtext=productPrice.getText();
		String text=prodtext.replaceAll("[₹,]", "");
		//prodtext.trim();
		System.out.println(text);
		double productprice=Double.parseDouble(text);
		return productprice;
	}
	public void clickOnAddToCart()
	{
		addToCart.click();
	}
public String getAddtocartMsg()
{
	String incartmsg=incart.getText();
	System.out.println(incartmsg);
	return incartmsg;
}
public String getSuccessfullText()
{
	String successtext=successFullText.getText();
	return successtext;
}

}
