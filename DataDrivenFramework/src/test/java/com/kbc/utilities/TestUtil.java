package com.kbc.utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.DataProvider;

import com.kbc.base.TestBase;

public class TestUtil extends TestBase {

	public static String screenshotPath;
	public static String screenshotName;

	/* Defining the capture screenshot code to use it for failed Test cases to show the failure result */
	public static void captureScreenshot() throws IOException {

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(
				System.getProperty("user.dir") + "\\target\\extent_reports\\screenshot\\" + screenshotName);

		dest.getParentFile().mkdirs();
		FileHandler.copy(screenshot, dest);
	}
	
	
	/* Defining the data provider code at one place to use it all over the project */
	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {
		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		// Print debug information
		System.out.println("Sheet Name: " + sheetName);
		System.out.println("Rows: " + rows);
		System.out.println("Cols: " + cols);

		if (rows <= 1 || cols <= 0) {
			throw new IllegalArgumentException("Invalid row or column count in sheet: " + sheetName);
		}
		
		Hashtable<String,String> table = null;

		Object[][] data = new Object[rows - 1][1];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			
			table = new Hashtable<String,String>();
			
			for (int colNum = 0; colNum < cols; colNum++) {
				
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}
		}
		return data;
	}

	/* Code for the run Mode as we set in the excel which test case to run */
	public static boolean isTestRunnable(String testName, ExcelReader excel) {

		String sheetName = "test_suite";
		int rows = excel.getRowCount(sheetName);

		for (int rNum = 2; rNum <= rows; rNum++) {

			String testCase = excel.getCellData(sheetName, "TCID", rNum);
			if (testCase.equalsIgnoreCase(testName)) {
				String runmode = excel.getCellData(sheetName, "Runmode", rNum);
				if (runmode.equalsIgnoreCase("Y"))

					return true;
				else
					return false;

			}
		}
		
		return false;
	}

}
