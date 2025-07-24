package com.uiplayground.automation.core.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Manages ExtentReports for test reporting
 */
public class ReportManager {
    
    private static final Logger logger = LogManager.getLogger(ReportManager.class);
    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    
    private ReportManager() {
        // Private constructor to prevent instantiation
    }
    
    /**
     * Initialize the ExtentReports instance
     */
    public static void initReports() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/test-report.html");
            sparkReporter.config().setDocumentTitle("UI Playground Automation Report");
            sparkReporter.config().setReportName("Test Execution Report");
            
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            
            logger.info("ExtentReports initialized successfully");
        }
    }
    
    /**
     * Get the ExtentReports instance
     * @return ExtentReports instance
     */
    public static ExtentReports getExtentReports() {
        if (extent == null) {
            initReports();
        }
        return extent;
    }
    
    /**
     * Create a new test in the report
     * @param testName Name of the test
     */
    public static void createTest(String testName) {
        ExtentTest test = getExtentReports().createTest(testName);
        extentTest.set(test);
        logger.info("Created test in report: " + testName);
    }
    
    /**
     * Get the current test instance
     * @return ExtentTest instance for the current thread
     */
    public static ExtentTest getTest() {
        return extentTest.get();
    }
    
    /**
     * Remove the current test instance
     */
    public static void removeTest() {
        extentTest.remove();
    }
    
    /**
     * Flush reports to disk
     */
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
            logger.info("ExtentReports flushed successfully");
        }
    }
}
