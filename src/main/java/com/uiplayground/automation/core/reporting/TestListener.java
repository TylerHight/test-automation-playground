package com.uiplayground.automation.core.reporting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestNG listener to handle test lifecycle events for reporting
 */
public class TestListener implements ITestListener {
    
    private static final Logger logger = LogManager.getLogger(TestListener.class);
    
    @Override
    public void onStart(ITestContext context) {
        logger.info("Starting test suite: " + context.getName());
        ReportManager.initReports();
    }
    
    @Override
    public void onFinish(ITestContext context) {
        logger.info("Finishing test suite: " + context.getName());
        ReportManager.flushReports();
    }
    
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.info("Starting test: " + testName);
        ReportManager.createTest(testName);
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.info("Test passed: " + testName);
        ReportManager.getTest().pass("Test passed");
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.error("Test failed: " + testName, result.getThrowable());
        
        // Take screenshot on failure
        String screenshotPath = ScreenshotManager.takeScreenshot(testName);
        
        // Add failure details to report
        if (screenshotPath != null) {
            ReportManager.getTest().fail("Test failed: " + result.getThrowable().getMessage())
                .addScreenCaptureFromPath(screenshotPath);
        } else {
            ReportManager.getTest().fail("Test failed: " + result.getThrowable().getMessage());
        }
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.info("Test skipped: " + testName);
        ReportManager.getTest().skip("Test skipped: " + result.getThrowable().getMessage());
    }
}
