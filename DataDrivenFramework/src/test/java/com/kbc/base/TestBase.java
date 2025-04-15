package com.kbc.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.kbc.listeners.CustomListeners;
import com.kbc.utilities.ExcelReader;
import com.kbc.utilities.TestUtil;

public class TestBase {

	/*
	 * Need to Initialize WebDriver Properties Logs - log4j jar, .log,
	 * log4j2.properties, Logger Excel - excelReader, data provider Report NG DB
	 * Mail , Extent Reports Jenkins
	 * 
	 */

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = LogManager.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public static String browser;
	
	

	@BeforeSuite
	public void setup() {

		// Properties file setup to call and use in runtime in the test case
		if (driver == null) {
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// For Jenkins logic
			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

				browser = System.getenv("browser");
			} else {
				browser = config.getProperty("browser");
			}

			config.setProperty("browser", browser);

			// Browser selection
			if (config.getProperty("browser").equals("chrome")) {

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");

				driver = new ChromeDriver(options);

				log.debug("Chrome lounched");
			} else if (config.getProperty("browser").equals("firefox")) {

				driver = new FirefoxDriver();
			}

			driver.get(config.getProperty("testurl"));
			log.debug("Navigated to: " + config.getProperty("testurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts()
					.implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("expict_wait"))));
			wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(config.getProperty("expict_wait"))));
		}

	}
	

	@AfterSuite
	public void teardown() {

		if (driver != null) {
			driver.quit();
		}
		log.debug("Test Execution completed");

	}
	
	//Common Keywords
	/* Reusable Keywords Click() */
	public static void click(String locatorKey) {
		try {
			driver.findElement(By.xpath(OR.getProperty(locatorKey))).click();
			log.info("Clicking on an Element : " + locatorKey);
			CustomListeners.test.log(Status.INFO, "Clicking on an Element : " + locatorKey);
		} catch (Throwable t) {

			log.error("Error while Clicking on an Element : " + locatorKey + " error message : " + t.getMessage());
			CustomListeners.test.log(Status.FAIL,
					"Error while Clicking on an Element : " + locatorKey + " error message : " + t.getMessage());
			Assert.fail(t.getMessage());

		}

	}

	/* Reusable Keywords setText() */
	public void setText(String locator, String settext) {
		try {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(settext);
			log.info("Typing in : " + locator + " entered value as " + settext);
			CustomListeners.testReport.get().log(Status.INFO,
					"Typing in : " + locator + " entered value as " + settext);
		} catch (Throwable t) {

			log.error("Error while typing on an Element: " + settext + " error message : " + t.getMessage());
			CustomListeners.test.log(Status.FAIL,
					"Error while typing on an Element: " + settext + " error message : " + t.getMessage());
		}
	}

	/* Reusable Keywords selectByVisibleText() */
	public WebElement dropdown;

	public void select(String locator, String value) {

		try {
		dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));

		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		log.info("Selecting the dropdown value from : " + locator + " entered value as " + value);
		CustomListeners.testReport.get().log(Status.INFO,
				"Selecting the dropdown value from : " + locator + " entered value as " + value);
		}catch(Throwable t) {
			
			log.error("Error selecting the dropdown value from an Element: " + value + " error message : " + t.getMessage());
			CustomListeners.test.log(Status.FAIL,
					"Error selecting the dropdown value from an Element: " + value + " error message : " + t.getMessage());
		}
	}

	public boolean isElementPresent(By by) {

		try {
			driver.findElement(by);
			log.info("Element is present : " + by );
			CustomListeners.testReport.get().log(Status.INFO,
					"Element is present : " + by );
			return true;

		} catch (NoSuchElementException e) {

			log.error("Element is not present : " + by + " error message : " + e.getMessage());
			CustomListeners.test.log(Status.FAIL,
					"Element is not present : " + by + " error message : " + e.getMessage());
			return false;
		}
	}

	public static void verifyEquals(String expected, String actual) throws IOException {

		try {

			Assert.assertEquals(actual, expected);

		} catch (Throwable t) {

			TestUtil.captureScreenshot();
			// ReportNG
			String ScreenPath = System.getProperty("user.dir") + "\\target\\extent_reports\\screenshot\\"
					+ TestUtil.screenshotName;
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + ScreenPath + "><img src=" + ScreenPath
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extent Reports

			CustomListeners.testReport.get().log(Status.FAIL,
					" Verification failed with exception : " + t.getMessage());
			CustomListeners.testReport.get().fail(
					"<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(ScreenPath).build());
		}

	}
}
