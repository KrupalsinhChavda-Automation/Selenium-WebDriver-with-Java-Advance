package com.kbc.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.kbc.base.TestBase;
import com.kbc.utilities.TestUtil;


public class OpenAccountTest extends TestBase{
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void openAccountTest(Hashtable<String,String>data) throws InterruptedException {

		//Click on Open Account button
		click("btn_OpenAccount");
		
		//Select Customer :
		select("drp_Customer", data.get("customer"));
		
		//Select Currency
		select("drp_Currency", data.get("currency"));
		
		//Click on Process button
		click("btn_Process");
		
		//Accept Alert
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		
	}
}
