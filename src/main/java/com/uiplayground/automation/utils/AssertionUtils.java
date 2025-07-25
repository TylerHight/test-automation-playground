package com.uiplayground.automation.utils;

import org.testng.Assert;

import com.uiplayground.automation.core.config.ConfigManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public final class AssertionUtils {
    
    private static final Logger logger = LogManager.getLogger(AssertionUtils.class);
    
    private AssertionUtils() {
        // Utility class
    }
    
    public static void assertTitle(String actual, String expected, String message) {
        logger.info("Asserting title - Expected: '{}', Actual: '{}'", expected, actual);
        Assert.assertEquals(actual, expected, message);
    }
    
    public static void assertNotNullOrEmpty(String actual, String fieldName) {
        Assert.assertNotNull(actual, fieldName + " should not be null");
        Assert.assertFalse(actual.trim().isEmpty(), fieldName + " should not be empty");
        logger.info("Validated {} is not null or empty", fieldName);
    }
    
    public static void assertContains(String actual, String expected, String message) {
        Assert.assertTrue(actual.contains(expected), 
            String.format("%s. Expected '%s' to contain '%s'", message, actual, expected));
    }
}
