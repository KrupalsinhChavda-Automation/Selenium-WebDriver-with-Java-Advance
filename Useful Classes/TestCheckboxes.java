package testcases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCheckboxes {
	
public static WebDriver driver;
	
	public static boolean isElementPresent(By by) {
		/*try {
		driver.findElement(By.xpath(locator));
		return true;
		}catch(Throwable t) {
			
			return false;
		}*/
		
		int size = driver.findElements(by).size();
		if(size==0) {
			
			return false;
		}else {
			
			return true;
		}
	}

	public static void main(String[] args) {



		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://www.tizag.com/htmlT/htmlcheckboxes.php");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	/*	driver.findElement(By.xpath("//div[4]/input[1]")).click();
		
		driver.findElement(By.xpath("//div[4]/input[2]")).click();
		
		driver.findElement(By.xpath("//div[4]/input[3]")).click();
		
		driver.findElement(By.xpath("//div[4]/input[4]")).click();
*/		
		
		/*
		for(int i=1; i<=4; i++) {
		
			driver.findElement(By.xpath("//div[4]/input["+i+"]")).click();
			
			
		}*/
		
		/*int i=1;
		int count=0;
		
		while(isElementPresent(By.xpath("//div[4]/input["+i+"]"))) {
			
			driver.findElement(By.xpath("//div[4]/input["+i+"]")).click();
			i++;
			count++;
		}

		System.out.println("Total checkboxes are : "+count);*/
		
		WebElement block = driver.findElement(By.xpath("/html/body/table[3]/tbody/tr[1]/td[2]/table/tbody/tr/td/div[4]"));
		
		List<WebElement> checkboxes = block.findElements(By.name("sports"));
	
		System.out.println("Total checkboxes are : "+checkboxes.size());
		
		for(WebElement checkbox: checkboxes) {
			
			checkbox.click();
			
		}
		
		
	
	}

}
