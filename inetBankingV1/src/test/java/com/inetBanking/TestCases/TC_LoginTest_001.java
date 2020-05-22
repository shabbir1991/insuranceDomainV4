package com.inetBanking.TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	
	@Test
	public void loginTest() throws InterruptedException, IOException {
		
		logger.info("URL is opened.");
		
		// Creating object for LoginPage
		LoginPage  lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Enter UserName.");
		lp.setPassword(password);
		logger.info("Enter Password.");
		lp.clickSubmit();
		
		// Validation
		Thread.sleep(2000);
		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("Login Test Passed.");
		} else {
			captureScreen(driver, "LoginTest_001");
			Assert.assertTrue(false);
			logger.info("Login Test Failed.");
		}
		
	}
}
