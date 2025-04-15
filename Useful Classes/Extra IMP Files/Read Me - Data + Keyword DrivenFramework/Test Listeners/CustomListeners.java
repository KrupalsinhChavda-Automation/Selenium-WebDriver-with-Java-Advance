package com.kbc.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class CustomListeners implements ITestListener {

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult result) {

		System.out.println("Passed Test - " + result.getName());

	}

	public void onTestFailure(ITestResult result) {

		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log("Login Successfully!!");
		Reporter.log("<a target = \"blank\" href=\"C:\\Users\\krupa\\OneDrive\\Desktop\\Screenshot (83).png\">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target = \"blank\" href=\"C:\\Users\\krupa\\OneDrive\\Desktop\\Screenshot (83).png\"><img src=\"C:\\Users\\krupa\\OneDrive\\Desktop\\Screenshot (83).png\" height=200, weigth=200></img></a>");
		System.out.println("Capturing Screenshot for the Failed test -- " + result.getName());

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
