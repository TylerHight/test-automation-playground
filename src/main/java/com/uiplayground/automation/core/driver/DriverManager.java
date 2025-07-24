package com.uiplayground.automation.core.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Manages WebDriver instances for the framework
 * Uses ThreadLocal to support parallel test execution
 */
public class DriverManager {
    
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    
    private DriverManager() {
        // Private constructor to prevent instantiation
    }
    
    /**
     * Get the WebDriver instance for the current thread
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            initializeDriver();
        }
        return driver.get();
    }
    
    /**
     * Initialize a new WebDriver instance based on browser configuration
     */
    public static void initializeDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        logger.info("Initializing WebDriver for browser: " + browser);
        
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
        }
        
        driver.get().manage().window().maximize();
        logger.info("WebDriver initialized successfully");
    }
    
    /**
     * Quit the WebDriver instance and remove it from ThreadLocal
     */
    public static void quitDriver() {
        logger.info("Quitting WebDriver");
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
            logger.info("WebDriver quit successfully");
        }
    }
}
