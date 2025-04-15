package testcases;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class TestSorting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		   WebDriver driver=new ChromeDriver();

	       driver.get("https://www.bu.edu/tech/services/cccs/websites/www/wordpress/how-to/sortable-searchable-tables/");
	       
//	       driver.findElement(By.xpath("//*[@id=\"post-76995\"]/table/thead/tr/th[1]")).click();
	       
	       List<WebElement> listofAthelet = driver.findElements(By.xpath("//*[@id=\"post-76995\"]/table/tbody/tr/td[1]"));
	       
	       List <String> gotList = listofAthelet.stream().map(s->s.getText()).collect(Collectors.toList());
	       System.out.println(gotList);
	       List <String> sorted = (List<String>) listofAthelet.stream().map(s->s.getText()).sorted().collect(Collectors.toList());
	       System.out.println(sorted);
	       Assert.assertEquals(gotList, sorted);
	       
		
		

	}

}
