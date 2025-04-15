package testcases;


import org.testng.annotations.Test;

import base.BaseTest;
import utilities.DataUtil;

public class UserRegTest extends BaseTest {
	
	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp1")
	public void registerUser(String firstName, String lastName) {
		
		System.out.println(firstName+"---"+lastName);
	}

	
	
	
}
