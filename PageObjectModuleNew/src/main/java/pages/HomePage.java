package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class HomePage extends BasePage {



	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//*[text()='NEW CARS']")
	public WebElement newCars;

	@FindBy(xpath = "//div[contains(text(),'Find New Cars')]")
	public WebElement findNewCars;
	
	@FindBy(xpath = "//div[contains(text(),'Find New Cars')]")
	public WebElement searchNewCars;

	public NewCarPage findNewCar() {

		new Actions(driver).moveToElement(newCars).perform();
		findNewCars.click();
		
		return new NewCarPage(driver);
	}

	public void searchNewCar(String carName) {

		searchNewCars.sendKeys(carName);

	}

}
