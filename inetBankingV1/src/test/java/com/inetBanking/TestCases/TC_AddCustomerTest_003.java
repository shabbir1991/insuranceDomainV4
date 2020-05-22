package com.inetBanking.TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.AddCustomerPage;
import com.inetBanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass {
	
	@Test
	public void addNewCustomer() throws InterruptedException, IOException {
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("UserName is Provided");
		lp.setPassword(password);
		logger.info("Password is Provided");
		lp.clickSubmit();
		
	//	Thread.sleep(3000);
		
		AddCustomerPage addCust = new AddCustomerPage(driver);
		addCust.clickAddNewCustomer();
		
		logger.info("Providing Customer Details ....!!!");
		
		addCust.custName("satish");
		addCust.custGender("male");
		addCust.custDob("05", "20", "1986");
		Thread.sleep(3000);
		addCust.custAddress("India");
		addCust.custCity("Guntur");
		addCust.custState("AP");
		addCust.custPinNumber("500435");
		addCust.custMobileNumber("9028976540");
		
	 	String email = randomEmail()+"@gmail.com";
		addCust.custEmail(email);
		
		addCust.custPassword("abcdef");
		addCust.custSubmit();
		
		Thread.sleep(3000);	
		
		logger.info("Validation is started ");
		
		// Validation
		boolean result = driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if (result==true) {
			Assert.assertTrue(true);
			logger.info("Test Case is passed");
		} else {
			logger.info("Test Case is Failed");
			captureScreen(driver, "AddCustomer");
			Assert.assertTrue(false);
		}
	}	
}
