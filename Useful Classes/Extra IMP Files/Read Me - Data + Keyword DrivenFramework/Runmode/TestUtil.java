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

	public static void captureScreenshot() throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(
				System.getProperty("user.dir") + "\\target\\extent_reports\\screenshot\\" + screenshotName);

		dest.getParentFile().mkdirs();
		FileHandler.copy(screenshot, dest);
	}

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

		Object[][] data = new Object[rows - 1][cols];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			for (int colNum = 0; colNum < cols; colNum++) {
				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		return data;
	}

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
