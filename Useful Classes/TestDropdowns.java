package testcases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestDropdowns {

	public static void main(String[] args) {


		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.wikipedia.org/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		/*
		 * English - US
		 * English - UK
		 * 
		 */

		
		//driver.findElement(By.id("searchLanguage")).sendKeys("Eesti");
		WebElement dropdown = driver.findElement(By.id("searchLanguage"));
		Select select = new Select(dropdown);
		
		//select.selectByVisibleText("Eesti");
		select.selectByVisibleText("English");
		
	
		
		List<WebElement> values = dropdown.findElements(By.tagName("option"));
		
		//0 - size-1(61-1=60)
		System.out.println("Total values are : "+values.size());
		
		System.out.println(values.get(7).getText());
		
		
		for(int i=0; i<values.size(); i++) {
			
			System.out.println(values.get(i).getAttribute("lang"));
			
		}
		
		
		WebElement block = driver.findElement(By.xpath("//*[@id='www-wikipedia-org']/div[11]/div[3]"));
		
		
		
		List<WebElement> links = block.findElements(By.tagName("a"));
		
		System.out.println("----Printing links----");
		
		System.out.println("Total links are: "+links.size());
		
		
		for(WebElement link: links) {
			
			System.out.println(link.getText()+" -- URL IS ---"+link.getAttribute("href"));
		}
		
		/*
		WebElement secondBlock = driver.findElement(By.id("blockid"));
		
		secondBlock.findElements(By.id("abc")).get(2).click();*/
		
		
	}

}
