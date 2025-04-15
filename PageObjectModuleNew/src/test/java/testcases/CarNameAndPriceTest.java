package testcases;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import base.BasePage;
import base.BaseTest;
import pages.HomePage;
import pages.NewCarPage;
import utilities.DataUtil;

public class CarNameAndPriceTest extends BaseTest{

	
@Test(dataProviderClass = DataUtil.class, dataProvider = "dp1")
	
	public void carNameAndPrice(String browserName, String carBrand,String carTitle,String runMode) {	

		if(runMode.equals("N")) {
			
			throw new SkipException("Skipping the test as Run Mode is No");
		}
		
		setup(browserName);
		HomePage Hpage = new HomePage(driver);
		NewCarPage carPage = Hpage.findNewCar();
		
		if(carBrand.equals("Hyundai")) {
			
			carPage.goToHyundai();
		}else if (carBrand.equals("BMW")){
			carPage.goToBMW();
		}else if (carBrand.equals("Toyota")){
			carPage.goToToyota();
		}
		
		BasePage.carBase.getCarNameAndPrice();
		

		
		
	}
	
}
