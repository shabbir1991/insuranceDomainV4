package com.inetBanking.TestCases;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBanking.Utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readConfig = new ReadConfig();
	
	public String baseURL = readConfig.getApplicationURL();
	public String username = readConfig.getUsername();
	public String password = readConfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)  {
			
		// Logging purpose
		logger = Logger.getLogger("ebanking"); // name of the class or project
		PropertyConfigurator.configure("log4j.properties"); // define log4j.property file
		
		
		// based on the value which i provided from TestNG XML file it will launch appropriate browser.
		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
			driver = new ChromeDriver();
		} else if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readConfig.getFirefoxPath());
			driver = new FirefoxDriver();
		}else if (br.equals("ie")) {
			System.setProperty("webdriver.ie.driver", readConfig.getIEPath());
			driver = new InternetExplorerDriver();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseURL);
	}
	
	// after executing all test cases
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	// ScreenShot Method 
	public void captureScreen(WebDriver driver , String tname) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/ScreenShots/"+ tname +".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot Taken.");
		
	}
	
	
		// generate random Email 
		public String randomEmail() {
			String generatedString = RandomStringUtils.randomAlphabetic(5);
			return (generatedString);
		}
		// Generate random Number
		public String randomNumber() {
			String generatedString2 = RandomStringUtils.randomNumeric(4);
			return (generatedString2);
		}

}
