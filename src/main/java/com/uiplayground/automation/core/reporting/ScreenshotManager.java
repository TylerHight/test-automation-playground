package com.uiplayground.automation.core.reporting;

import com.uiplayground.automation.core.config.ConfigManager;
import com.uiplayground.automation.core.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Manages taking screenshots during test execution
 */
public class ScreenshotManager {
    
    private static final Logger logger = LogManager.getLogger(ScreenshotManager.class);
    
    private ScreenshotManager() {
        // Private constructor to prevent instantiation
    }
    
    /**
     * Take a screenshot and save it to the configured screenshots directory
     * @param testName Name of the test for the screenshot filename
     * @return Path to the saved screenshot or null if failed
     */
    public static String takeScreenshot(String testName) {
        WebDriver driver = DriverManager.getDriver();
        if (driver == null) {
            logger.error("Driver is null, cannot take screenshot");
            return null;
        }
        
        if (driver instanceof TakesScreenshot) {
            try {
                // Create timestamp for unique filename
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String fileName = testName + "_" + timestamp + ".png";
                
                // Get screenshot directory from config
                String screenshotDir = ConfigManager.getInstance().getScreenshotsPath();
                Path dirPath = Paths.get(screenshotDir);
                
                // Create directory if it doesn't exist
                if (!Files.exists(dirPath)) {
                    Files.createDirectories(dirPath);
                }
                
                // Take screenshot
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Path targetPath = Paths.get(screenshotDir, fileName);
                Files.copy(scrFile.toPath(), targetPath);
                
                logger.info("Screenshot saved to: " + targetPath);
                return targetPath.toString();
                
            } catch (IOException e) {
                logger.error("Failed to take screenshot", e);
            }
        } else {
            logger.warn("WebDriver doesn't support screenshots");
        }
        
        return null;
    }
}