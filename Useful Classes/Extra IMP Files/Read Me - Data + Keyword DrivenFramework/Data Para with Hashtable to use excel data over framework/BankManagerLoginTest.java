package com.kbc.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.kbc.base.TestBase;

public class BankManagerLoginTest extends TestBase{
	
	@Test
	public void bankManagerLoginTest() throws InterruptedException, IOException   {

		System.setProperty("org.uncommons.reportng.escape-output", "false");
				
		log.debug("Lunching the login test");
		click("btn_Login");
		
		// Verify Login
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("btn_AddCustomer"))),"Login is sucsessfull");
		log.debug("Login Successfully!!");
	}

}
