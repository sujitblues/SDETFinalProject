package com.jiomart.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.jiomart.testcases.BaseClass;

public class ExtentListenerClass extends BaseClass implements ITestListener {
	ExtentSparkReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;
	
	
	public void configureRport()
	{
		String timestamp=new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
		String reportName="MyStoreTestReport"+timestamp+".hmtl";
		System.out.println(System.getProperty("user.dir")+"//Reports//"+reportName);
		htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"//Reports//"+reportName);
		
		//htmlReporter =new ExtentSparkReporter("ExtentListenerReportDemo.html");
		reports=new ExtentReports();
		reports.attachReporter(htmlReporter);
		
		//add  System information/enviornment info to reports
		reports.setSystemInfo("Machine", "testpc1");
		reports.setSystemInfo("OS", "Window 11");
		reports.setSystemInfo("browser", "chrome");
		reports.setSystemInfo("user name", "sujit");
		
		//Configuration to change look and feel of report
		htmlReporter.config().setDocumentTitle("Extent Listener Report Demo");
		htmlReporter.config().setReportName("SDET-Final Assesment ");
		htmlReporter.config().setTheme(Theme.DARK);
		
		
	}
	//onStart method is called when any test start
	public void onStart(ITestContext Result)
	{
		configureRport();
		System.out.println("On start invoked...");
		
	}
	//onFinish method is called after all tests are executed
	public void onFinish(ITestContext Result)
	{
		System.out.println("On finish invoked..");
		reports.flush(); //it is mandtory to call flush method to ensure information is written to the started report
	}
	//When Test cas get failed,this method is called
	public void onTestFailure(ITestResult Result)
	{
		
		System.out.println("Name of failed method"+Result.getName());
		test.log(Status.FAIL, "Test Case :"+ Result.getMethod().getMethodName()+"is Failed");
		test=reports.createTest(Result.getName());//create entry in html report
		test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed test case is: "+ Result.getName(),ExtentColor.RED));
		//add Screenshot for failed test:
	TakesScreenshot screenshot=((TakesScreenshot)driver);
		File src=screenshot.getScreenshotAs(OutputType.FILE);
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		Date date=new Date();
		String actualDate=format.format(date);
		
		String ScreenShotPath=System.getProperty("user.dir")+"\\Screenshots\\"+ actualDate+".png";
		File dest=new File(ScreenShotPath);
		
			try {
				FileUtils.copyFile(src, dest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		test.addScreenCaptureFromPath(ScreenShotPath,"Test case failure screenshot");
		
		/*
		if(screenShotFile.exists())
		{
			//test.addScreenCaptureFromPath(ScreenShotPath)
			//test.fail("Captured Screenshot is below :"+test.addScreenCaptureFromPath(ScreenShotPath));
		}
		*/
	}
	//When Test case get Skipped,this method is called
	public void onTestSkipped(ITestContext Result)
	{
		System.out.println("Name of failed method"+Result.getName());
		test=reports.createTest(Result.getName());//create entry in html report
		test.log(Status.SKIP, MarkupHelper.createLabel("Name of the failed test case is:"+ Result.getName(),ExtentColor.YELLOW));
	}
	//When test ccase get started,this method is called.
	public void onTestStart(ITestResult Result)
	{ 
		test=reports.createTest(Result.getMethod().getMethodName());
		
		System.out.println("On Test start method ...");
	}
	
	//When Test case get passed,this method is called
	public void onTestSuccess(ITestContext Result)
	{
		System.out.println("Name of failed method"+Result.getName());
		test=reports.createTest(Result.getName());//create entry in html report
		test.log(Status.PASS, MarkupHelper.createLabel("Name of the failed test case is:"+ Result.getName(),ExtentColor.GREEN));
	}
	
	public void onTestFailedButWithingSuccessPercentage()
	{
		
	}


}
