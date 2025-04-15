package com.kbc.utilities;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

import com.kbc.base.TestBase;

public class TestUtil extends TestBase{
	
	
	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshot() throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"\\target\\extent_reports\\screenshot\\"+ screenshotName);

		dest.getParentFile().mkdirs();
		FileHandler.copy(screenshot, dest);
		

		
	}

	

}
