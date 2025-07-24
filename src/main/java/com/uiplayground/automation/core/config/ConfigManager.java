package com.uiplayground.automation.core.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Manages configuration properties for the framework
 * Follows Singleton pattern to ensure only one instance exists
 */
public class ConfigManager {
    
    private static final Logger logger = LogManager.getLogger(ConfigManager.class);
    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE = "src/main/resources/config/config.properties";
    private static ConfigManager instance;
    
    private ConfigManager() {
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);
            logger.info("Configuration loaded successfully from: " + CONFIG_FILE);
        } catch (IOException e) {
            logger.error("Error loading configuration file: " + CONFIG_FILE, e);
        }
    }
    
    /**
     * Get the singleton instance of ConfigManager
     * @return ConfigManager instance
     */
    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }
    
    /**
     * Get a property value by key
     * @param key Property key
     * @return Property value
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    /**
     * Get the base URL of the application
     * @return Base URL
     */
    public String getBaseUrl() {
        return getProperty("baseUrl");
    }
    
    /**
     * Get the implicit wait time in seconds
     * @return Implicit wait time
     */
    public int getImplicitWait() {
        return Integer.parseInt(getProperty("implicitWait"));
    }
    
    /**
     * Get the explicit wait time in seconds
     * @return Explicit wait time
     */
    public int getExplicitWait() {
        return Integer.parseInt(getProperty("explicitWait"));
    }
    
    /**
     * Get the path for saving screenshots
     * @return Screenshots path
     */
    public String getScreenshotsPath() {
        return getProperty("screenshotsPath");
    }
}
