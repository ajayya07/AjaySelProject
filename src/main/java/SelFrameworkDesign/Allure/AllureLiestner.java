package SelFrameworkDesign.Allure;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import SelFrameworkDesign.Methods.BaseTest;
import io.qameta.allure.Attachment;

public class AllureLiestner implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	// Text attachments for Allure
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	// Text attachments for Allure
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	// HTML attachments for Allure
	@Attachment(value = "{0}", type = "text/html")
	public static String attachHtml(String html) {
		return html;
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		System.out.println("Test Started " + iTestContext.getName());
		iTestContext.setAttribute("WebDriver", BaseTest.getDriver());
		saveTextLog(iTestContext.getName() + " Test Started ");
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		System.out.println("Test Finished " + iTestContext.getName());
		saveTextLog(iTestContext.getName() + " Test Finished ");
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		System.out.println("Test Started " + getTestMethodName(iTestResult) + " : Start");
		saveTextLog(getTestMethodName(iTestResult) + "Started ");	
		saveTextLog(getTestMethodName(iTestResult) + "Started ");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println( getTestMethodName(iTestResult) + " Passed");
		saveTextLog(getTestMethodName(iTestResult) + "Passed ");
		Object testClass = iTestResult.getInstance();
		WebDriver driver = BaseTest.getDriver();
		// Allure ScreenShotRobot and SaveTestLog
		if (driver instanceof WebDriver) {
			System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
			saveScreenshotPNG(driver);
		}
		
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println(getTestMethodName(iTestResult) + " Failed");
		Object testClass = iTestResult.getInstance();
		WebDriver driver = BaseTest.getDriver();
		// Allure ScreenShotRobot and SaveTestLog
		if (driver instanceof WebDriver) {
			System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
			saveScreenshotPNG(driver);
		}
		// Save a log on allure.
		saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");		
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println(getTestMethodName(iTestResult) + " Skipped");
		saveTextLog(getTestMethodName(iTestResult) + "Skipped ");
		System.out.println(getTestMethodName(iTestResult) + " skipped");
		Object testClass = iTestResult.getInstance();
		WebDriver driver = BaseTest.getDriver();
		// Allure ScreenShotRobot and SaveTestLog
		if (driver instanceof WebDriver) {
			System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
			saveScreenshotPNG(driver);
		}
		// Save a log on allure.
		saveTextLog(getTestMethodName(iTestResult) + " skipped and screenshot taken!");		
	
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
		saveTextLog(getTestMethodName(iTestResult) + "Test failed but it is in defined success ratio  ");
	}

}
	

