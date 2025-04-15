package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;

import extentlisteners.ExtentListeners;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.DbManager;
import utilities.ExcelReader;
import utilities.MonitoringMail;

public class BaseTest {

	/*
	 * WebDriver - done TestNG - done Database - done Mail - done Extent Reports -
	 * done Log4j - done Excel - done Properties - done Screenshots - done Keywords
	 * - done
	 * 
	 */

	public static WebDriver driver;
	private static Properties or = new Properties();
	private static Properties config = new Properties();
	private static FileInputStream fis;
	private static Logger log = Logger.getLogger(BaseTest.class);
	public static ExcelReader excel = new ExcelReader(".//src//test//resources//excel//testdata.xlsx");
	public static MonitoringMail mail = new MonitoringMail();
	public static WebDriverWait wait;
	public static WebElement dropdown;
	/* Reusable Keywords */
	public static void click(String locatorKey) {
		try {
			if (locatorKey.endsWith("_XPATH")) {
				driver.findElement(By.xpath(or.getProperty(locatorKey))).click();
			} else if (locatorKey.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(or.getProperty(locatorKey))).click();
			} else if (locatorKey.endsWith("_ID")) {
				driver.findElement(By.id(or.getProperty(locatorKey))).click();
			}

			log.info("Clicking on an Element : " + locatorKey);
			ExtentListeners.test.log(Status.INFO, "Clicking on an Element : " + locatorKey);
		} catch (Throwable t) {

			log.error("Error while Clicking on an Element : " + locatorKey + " error message : " + t.getMessage());
			ExtentListeners.test.log(Status.FAIL,
					"Error while Clicking on an Element : " + locatorKey + " error message : " + t.getMessage());
			Assert.fail(t.getMessage());

		}

	}

	public static boolean isElementPresent(String locatorKey) {

		try {
			if (locatorKey.endsWith("_XPATH")) {
				driver.findElement(By.xpath(or.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(or.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_ID")) {
				driver.findElement(By.id(or.getProperty(locatorKey)));
			}
		} catch (Throwable t) {

			log.info("Element not found : " + locatorKey);
			ExtentListeners.test.log(Status.INFO, "Element not found : " + locatorKey);
			return false;

		}

		log.info("Finding an Element : " + locatorKey);
		ExtentListeners.test.log(Status.INFO, "Finding an Element : " + locatorKey);
		return true;
	}

	public static void type(String locatorKey, String value) {
		try {
			if (locatorKey.endsWith("_XPATH")) {
				driver.findElement(By.xpath(or.getProperty(locatorKey))).sendKeys(value);
			} else if (locatorKey.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(or.getProperty(locatorKey))).sendKeys(value);
			} else if (locatorKey.endsWith("_ID")) {
				driver.findElement(By.id(or.getProperty(locatorKey))).sendKeys(value);
			}
			log.info("typing in an Element : " + locatorKey + " entered the value as : " + value);
			ExtentListeners.test.log(Status.INFO,
					"typing in an Element : " + locatorKey + " entered the value as : " + value);
		} catch (Throwable t) {

			log.error("Error while typing in an Element : " + locatorKey + " error message : " + t.getMessage());
			ExtentListeners.test.log(Status.FAIL,
					"Error while typing in an Element : " + locatorKey + " error message : " + t.getMessage());
			Assert.fail(t.getMessage());

		}

	}
	
	
	
	public static void select(String locatorKey, String value) {
		try {
			
			
			if (locatorKey.endsWith("_XPATH")) {
				dropdown = driver.findElement(By.xpath(or.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_CSS")) {
				dropdown = driver.findElement(By.cssSelector(or.getProperty(locatorKey)));
			} else if (locatorKey.endsWith("_ID")) {
				dropdown = driver.findElement(By.id(or.getProperty(locatorKey)));
			}
			
			Select select = new Select(dropdown);
			select.selectByVisibleText(value);
			log.info("Selecting an Element : " + locatorKey + " selected the value as : " + value);
			ExtentListeners.test.log(Status.INFO,
					"Selecting an Element : " + locatorKey + " selected the value as : " + value);
		} catch (Throwable t) {

			log.error("Error while selecting an Element : " + locatorKey + " error message : " + t.getMessage());
			ExtentListeners.test.log(Status.FAIL,
					"Error while selecting an Element : " + locatorKey + " error message : " + t.getMessage());
			Assert.fail(t.getMessage());

		}

	}

	/* Initialization */
	@BeforeSuite
	public void setUp() {
		// loading the log file
		PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");

		// loading the OR and Config properties
		try {
			fis = new FileInputStream("./src/test/resources/properties/config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			config.load(fis);
			log.info("Config properties loaded !!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			fis = new FileInputStream("./src/test/resources/properties/OR.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			or.load(fis);
			log.info("OR properties loaded !!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (config.getProperty("browser").equals("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.info("Launching Chrome !!!");

		} else if (config.getProperty("browser").equals("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			log.info("Launching Firefox !!!");

		}

		driver.get(config.getProperty("testsiteurl"));
		log.info("Navigated to : " + config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit.wait"))));
		wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(config.getProperty("explicit.wait"))));
		try {
			DbManager.setMysqlDbConnection();
			log.info("DB Connection established !!!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void tearDown() {

		driver.quit();
		log.info("Test Execution completed !!!");
	}

}
