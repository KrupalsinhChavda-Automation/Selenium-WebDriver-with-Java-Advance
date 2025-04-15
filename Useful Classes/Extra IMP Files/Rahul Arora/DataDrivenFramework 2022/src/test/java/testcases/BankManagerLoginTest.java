package testcases;

import org.testng.annotations.Test;

import base.BaseTest;

public class BankManagerLoginTest extends BaseTest{
	
	@Test
	public void loginAsBankManager() {
		
		click("bmlBtn_XPATH");
		isElementPresent("addCustomerBtn_XPATH");
		
	}

}
