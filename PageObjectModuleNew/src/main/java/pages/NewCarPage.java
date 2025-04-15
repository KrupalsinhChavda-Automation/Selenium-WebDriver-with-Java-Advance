package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class NewCarPage extends BasePage {

	public NewCarPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//img[@title='Toyota Cars']")
	public WebElement toyota;

	@FindBy(xpath = "//img[@title='Hyundai Cars']")
	public WebElement hyundai;

	@FindBy(xpath = "//img[@title='BMW Cars']")
	public WebElement bmw;

	public void goToToyota() {
		toyota.click();

	}

	public HyundaiCarPage goToHyundai() {
		hyundai.click();
		return new HyundaiCarPage(driver);
	}

	public BMWCarPage goToBMW() {
		bmw.click();
		return new BMWCarPage(driver);
	}

}
