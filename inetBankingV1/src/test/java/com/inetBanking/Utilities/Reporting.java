package com.inetBanking.Utilities;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

import org.testng.ITestResult;  // it's Interface
import org.testng.TestListenerAdapter;  // It's class 

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

//Listener Class used to generate Extent reports
public class Reporting extends TestListenerAdapter {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	// The below methods implemented in TestListenerAdapter Class but these are
	// defined in ITestResult Interface.

	public void onStart(ITestContext testContext) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String reportName = "Test-Report" + timeStamp + ".html";
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+reportName); // Specify Location
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		
		htmlReporter.config().setDocumentTitle("InetBanking Test Project"); // title of the report
		htmlReporter.config().setReportName("Functional Test Report"); // Name of the report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); // Location of the Chart
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("HostName", "LocalHost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("OS", "Windows10");
		extent.setSystemInfo("User", "Shabbir");
		extent.setSystemInfo("Browser Inf", "Chrome");
		
	}
	
	public void onTestSuccess(ITestResult tr) {
		
		logger = extent.createTest(tr.getName()); // Create a new entry in the report.
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); // Send the passed information
		
	}
	
	public void onTestFailure(ITestResult tr) {

		logger = extent.createTest(tr.getName()); // Create a new entry in the report.
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		
		String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		
		File f = new File(screenshotPath);
		
		if (f.exists()) {
			try {
				logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void onTestSkipped(ITestResult tr) {

		logger = extent.createTest(tr.getName()); // Create a new entry in the report.
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE)); // Send the passed information
		
	}
	
	public void onFinish(ITestContext testContext) {
		
		extent.flush();
	}
}
