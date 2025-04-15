package com.kbc.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.kbc.listeners.CustomListeners;
import com.kbc.utilities.ExcelReader;
import com.kbc.utilities.ExtentManager;

public class TestBase {

	/*
	 * Need to Initialize WebDriver Properties Logs - log4j jar, .log,
	 * log4j2.properties, Logger Excel - excelReader, data provider Report NG
	 * ExtentRports DB Mail , Extent Reports Jenkins
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

			if (config.getProperty("browser").equals("chrome")) {
				driver = new ChromeDriver();
				log.debug("Chrome lounched");
			} else if (config.getProperty("browser") == "firefox") {

				driver = new FirefoxDriver();
			}

			driver.get(config.getProperty("testurl"));
			log.debug("Navigated to: " + config.getProperty("testurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implict.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(config.getProperty("expict_wait"))));
		}

	}

	public void click(String locator) {

		driver.findElement(By.xpath(OR.getProperty(locator))).click();
		CustomListeners.testReport.get().log(Status.INFO, "Clicking on : " + locator);

	}

	public void setText(String locator, String settext) {

		driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(settext);
		CustomListeners.testReport.get().log(Status.INFO, "Typing in : " + locator + " entered value as " + settext);
	}

	public boolean isElementPresent(By by) {

		try {
			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {

			return false;
		}
	}

	@AfterSuite
	public void teardown() {

		if (driver != null) {
			driver.quit();
		}
		log.debug("Test Execution completed");

	}
}
