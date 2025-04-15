package com.kbc.listeners;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.kbc.base.TestBase;
import com.kbc.utilities.ExtentManager;
import com.kbc.utilities.TestUtil;

public class CustomListeners extends TestBase implements ITestListener, ISuiteListener {

	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
	static String messageBody;
	private static ExtentReports extent = ExtentManager
			.createInstance(System.getProperty("user.dir") + "\\target\\extent_reports\\" + fileName);
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		ExtentTest test = extent
				.createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName());
		testReport.set(test);

	}

	public void onTestSuccess(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);

		System.out.println("Passed Test - " + result.getName());

	}

	public void onTestFailure(ITestResult result) {

		// Maven reports
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("Click to see ScreenShot");
		String ScreenPath = System.getProperty("user.dir") + "\\target\\extent_reports\\screenshot\\" + TestUtil.screenshotName;
		Reporter.log("<a target = \"blank\" href= " + ScreenPath + " >Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target = \"blank\" href=" + ScreenPath + "><img src=" + ScreenPath
				+ " height=200, weigth=200></img></a>");
		System.out.println("Capturing Screenshot for the Failed test -- " + result.getName());
		
		
		// Extent report setup
		String ScreenPathextent = System.getProperty("user.dir")+ "\\target\\extent_reports\\screenshot\\"+ TestUtil.screenshotName;
		System.out.println(ScreenPath);
		String excepionMessage=Arrays.toString(result.getThrowable().getStackTrace());
		testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
				+ "</font>" + "</b >" + "</summary>" +excepionMessage.replaceAll(",", "<br>")+"</details>"+" \n");
		
		try {

			TestUtil.captureScreenshot();
			testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(ScreenPathextent)
							.build());
		} catch (IOException e) {

		}
		
		String failureLogg="TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);


	}

	public void onTestSkipped(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"Test Case:- "+ methodName+ " Skipped"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		if (extent != null) {

			extent.flush();
		}


	}

}
