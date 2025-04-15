package testcases;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.DataUtil;

public class AddCustomerTest extends BaseTest {

	
	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp1")
	public void addCustomer(String firstName, String lastName, String postCode) {
		
		click("addCustomerBtn_XPATH");
		type("firstname_XPATH",firstName);
		type("lastname_XPATH",lastName);
		type("postcode_XPATH",postCode);
		click("addcust_XPATH");
		
		Alert alert = driver.switchTo().alert();
		Assert.assertTrue(alert.getText().contains("Customer added successfully"));
		
		alert.accept();
		
		
		
	}
	
}
