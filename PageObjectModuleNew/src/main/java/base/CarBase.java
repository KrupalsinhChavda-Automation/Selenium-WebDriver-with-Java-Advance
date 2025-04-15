package base;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CarBase {

	WebDriver driver;

	public CarBase(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h1[@data-skin='title']")
	public WebElement carTitle;

	public String getPageTitle() {

		System.out.println(carTitle.getText());
		return carTitle.getText();
	}

	
	@FindBy(xpath= "//div/div/div/a/h3")
	public List<WebElement> carNames;
	
	@FindBy(xpath= "//div/div/div/div/div/div[1]/span/span[1]")
	public List<WebElement> carPrices;
	
	public void getCarNameAndPrice() {
		
		for(int i = 0; i<carPrices.size(); i++) {
			
			System.out.println(carNames.get(i).getText() + "---- Car price is: "+ carPrices.get(i).getText());
		}
	}
	

	
}
