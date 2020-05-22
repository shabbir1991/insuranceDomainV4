package com.inetBanking.TestCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.Utilities.XLUtils;
import com.inetBanking.pageObjects.LoginPage;

public class TC_LoginDDT_002 extends BaseClass {
	
	@Test(dataProvider="LoginData")
	public void loginDDT(String usr , String pswd) throws InterruptedException {
		
		Thread.sleep(2000);
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(usr);
		logger.info("User name provided");
		lp.setPassword(pswd);
		logger.info("Password provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		if (isAlertPresent()== true) {
			driver.switchTo().alert().accept();// close Alert.
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login Failed.");
		} else {
			Assert.assertTrue(true);
			logger.info("Login Passed");
			lp.clickLogOut();
			Thread.sleep(3000);
			driver.switchTo().alert().accept(); // close the logout Alert.
			driver.switchTo().defaultContent();
		}
	}
	
	
	public boolean isAlertPresent() {  // user defined method to check alert is present or not
		
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			
			return false;
		}
	}
	
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException {
		
		String path = System.getProperty("user.dir")+"/src/test/java/com/inetBanking/TestData/TDD.xlsx";
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		
		String logindata[][] = new String[rownum][colcount];
		
		for (int i = 1; i <=rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				
				logindata[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j); // 1,0	
			}	
		}
		return logindata;
	}
}
