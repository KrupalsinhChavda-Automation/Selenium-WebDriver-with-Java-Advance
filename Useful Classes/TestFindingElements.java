package testcases;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestFindingElements {

	public static void main(String[] args) {

		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		driver.get("http://gmail.com");
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// WebDriverWait wait = new WebDriverWait(driver, 30);

		Wait<WebDriver> wait = new FluentWait<WebDriver> (driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(2))
				.withMessage("User defined Timed out after 30 seconds")
				.ignoring(NoSuchElementException.class);

		/* String title = driver.getTitle(); */

		System.out.println(driver.getTitle().length());

		/*
		 * WebElement username =
		 * driver.findElement(By.xpath("//*[@id='identifierId']"));
		 * username.sendKeys("trainer@way2automation.com");
		 * 
		 * WebElement nextBtn =
		 * driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/content"));
		 * nextBtn.click();
		 */

		// driver.findElement(By.xpath("//*[@id='identifierId']")).sendKeys("trainer@way2automation.com");

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='identifierId']")))
				.sendKeys("trainer@way2automation.com");

		driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/content")).click();
		/*
		 * try { Thread.sleep(1000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		// Implicit Wait and ExplicitWait, FluentWait
		// driver.findElement(By.name("password")).sendKeys("sdfsdfsdf");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys("asdfsdfds");

		driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/content/span")).click();

		System.out.println(wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"password\"]/div[2]/div[2]/div")))
				.getText());

	}

}
