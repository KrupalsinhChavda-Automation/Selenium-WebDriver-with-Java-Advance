package com.kbc.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.kbc.base.TestBase;
import com.kbc.listeners.CustomListeners;
import com.kbc.utilities.TestUtil;

public class AddCustomerTest extends TestBase {

	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void addCustomerTest(Hashtable<String,String>data)
			throws InterruptedException {

		if(!data.get("Runmode").equals("Y")) {
			
			throw new SkipException("Skipping the test case!!");
		}
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("btn_AddCustomer"))));

		// Click on Add cutomer button
//		driver.findElement(By.cssSelector(OR.getProperty("btn_AddCustomer"))).click();
		click("btn_AddCustomer");

		// Set data from excel for First Name
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("txt_FirstName"))));
//		driver.findElement(By.xpath(OR.getProperty("txt_FirstName"))).sendKeys(firstName);
		setText("txt_FirstName", data.get("firstname"));

		// Set data from excel for Last Name
		setText("txt_LastName", data.get("lastname"));

		// Set data from excel for Post Code
		setText("txt_PostCode", data.get("postcode"));

		// Click on Add cutomer button
		click("btn_Addcustomer");

		// Verify Add cutomer with alert verification
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("alerttext")));

		String Act_alertText = driver.switchTo().alert().getText();
		if (Act_alertText.contains(Act_alertText)) {
			Assert.assertTrue(true);
			log.debug("Customer has been added and verified over the alert");
		} else {

			Assert.assertTrue(false);
			log.debug("Customer has not been added and verified over the alert");
		}
		driver.switchTo().alert().accept();
//		Thread.sleep(5000);
		log.debug("Customer has been added");
		
	}
	
@DataProvider	
public Object[][] getData(){
		
		String sheetName = "AddCustomerTest";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		
		Object[][] data = new Object[rows-1][cols];
		
		for(int rowNum = 2;rowNum <= rows; rowNum++) {
			for(int colNum = 0; colNum< cols; colNum++) {
				
				data[rowNum-2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
				
			}
			
		}
		return data;
	}
}
