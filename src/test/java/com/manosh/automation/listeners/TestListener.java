package com.manosh.automation.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.manosh.automation.base.DriverFactory;
import com.manosh.automation.config.ConfigReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

/**
 * Builds an HTML report (target/reports/TestReport.html) and attaches
 * a screenshot to every failed test.
 */
public class TestListener implements ITestListener {

    private static final ExtentReports EXTENT = new ExtentReports();
    private static final ThreadLocal<ExtentTest> TEST = new ThreadLocal<>();

    static {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/reports/TestReport.html");
        spark.config().setDocumentTitle("Flight Booking Automation Report");
        spark.config().setReportName("BlazeDemo UI Regression");
        EXTENT.attachReporter(spark);
        EXTENT.setSystemInfo("Browser", ConfigReader.get("browser"));
        EXTENT.setSystemInfo("Base URL", ConfigReader.get("baseUrl"));
    }

    @Override
    public void onTestStart(ITestResult result) {
        String name = result.getMethod().getMethodName();
        if (result.getParameters().length > 0) {
            name += " " + Arrays.toString(result.getParameters());
        }
        TEST.set(EXTENT.createTest(name, result.getMethod().getDescription()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        TEST.get().pass("Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = TEST.get();
        test.fail(result.getThrowable());
        try {
            String base64 = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BASE64);
            test.fail("Screenshot at failure",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
        } catch (RuntimeException e) {
            test.warning("Could not capture screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        TEST.get().skip("Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        EXTENT.flush();
    }
}
