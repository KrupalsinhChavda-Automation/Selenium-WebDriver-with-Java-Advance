package testcases;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.DataUtil;

public class OpenAccountTest extends BaseTest {

	
	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp1")
	public void openAccount(String customer, String currency) {
		
		click("openaccount_XPATH");
		select("customer_XPATH",customer);
		select("currency_XPATH",currency);
		click("process_XPATH");
		
		Alert alert = driver.switchTo().alert();
		Assert.assertTrue(alert.getText().contains("Account created successfully"));
		
		alert.accept();
		
		
		
	}
	
}
