package com.inetBanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {
	
	WebDriver ldriver;
	
	public AddCustomerPage(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	/*@FindBy(how = How.XPATH , using = "/html/body/div[3]/div/ul/li[2]/a")
	WebElement lnkAddNewCustomer;*/
	
	
	@FindBy(how = How.LINK_TEXT , using ="New Customer")
	WebElement lnkAddNewCustomer;
	
	
	@FindBy(how = How.NAME , using = "name")
	WebElement txtCustomerName;
	
	@FindBy(how = How.NAME , using = "rad1")
	WebElement rdGender;
	
	
	@FindBy(how = How.ID_OR_NAME , using = "dob")
	WebElement txtDob;
	
	@FindBy(how = How.NAME , using = "addr")
	WebElement txtAddress;
	
	@FindBy(how = How.NAME , using = "city")
	WebElement txtCity;
	
	@FindBy(how = How.NAME , using = "state")
	WebElement txtState;
	
	@FindBy(how = How.NAME , using = "pinno")
	WebElement txtPinNo;
	
	@FindBy(how = How.NAME , using = "telephoneno")
	WebElement txtMobileNumber;
	
	@FindBy(how = How.NAME , using = "emailid")
	WebElement txtEmail;
	
	@FindBy(how = How.NAME , using = "password")
	WebElement txtPassword;
	
	
	@FindBy(how = How.NAME , using = "sub")
	WebElement btnSubmit;
	
	
	public void clickAddNewCustomer() {
		lnkAddNewCustomer.click();
	}
	
	public void custName(String cname) {
		txtCustomerName.sendKeys(cname);	
	}
	
	public void custGender(String cgender) {
		rdGender.click();	
	}
	
	public void custDob(String mm , String dd, String yy) {
		txtDob.sendKeys(mm);
		txtDob.sendKeys(dd);
		txtDob.sendKeys(yy);
	}
	
	public void custAddress(String caddress) {
		txtAddress.sendKeys(caddress);	
	}
	
	public void custCity(String ccity ) {
		txtCity.sendKeys(ccity);	
	}
	
	public void custState(String cstate) {
		txtState.sendKeys(cstate);	
	}
	
	public void custPinNumber(String cpinno) {
		txtPinNo.sendKeys(String.valueOf(cpinno));	
	}
	
	public void custMobileNumber(String cmobileno) {
		txtMobileNumber.sendKeys(cmobileno);	
	}
	
	public void custEmail(String cemail) {
		txtEmail.sendKeys(cemail);	
	}
	
	public void custPassword(String cpassword) {
		txtPassword.sendKeys(cpassword);	
	}
	
	public void custSubmit() {
		btnSubmit.click();	
	}
	
	
	

}
