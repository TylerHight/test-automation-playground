package com.uiplayground.automation.tests.base;

import com.uiplayground.automation.core.driver.DriverManager;
import com.uiplayground.automation.core.reporting.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

/**
 * Base class for all test classes
 * Handles setup and teardown operations
 */
@Listeners(TestListener.class)
public abstract class BaseTest {
    
    protected final Logger logger = LogManager.getLogger(this.getClass());
    
    /**
     * Setup method to run before each test method
     */
    @BeforeMethod
    public void setUp() {
        logger.info("Setting up test");
        DriverManager.initializeDriver();
    }
    
    /**
     * Teardown method to run after each test method
     */
    @AfterMethod
    public void tearDown() {
        logger.info("Tearing down test");
        DriverManager.quitDriver();
    }
}