package com.uiplayground.automation.pages.base;

import com.uiplayground.automation.core.config.ConfigManager;
import com.uiplayground.automation.core.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Base class for all Page Objects
 * Provides common functionality for interacting with web elements
 */
public abstract class BasePage {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final Logger logger = LogManager.getLogger(this.getClass());
    
    /**
     * Constructor initializes WebDriver, WebDriverWait, and PageFactory
     */
    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, 
                Duration.ofSeconds(ConfigManager.getInstance().getExplicitWait()));
        PageFactory.initElements(driver, this);
        logger.debug("Initialized page: " + this.getClass().getSimpleName());
    }
    
    /**
     * Navigate to a URL
     * @param url URL to navigate to
     */
    protected void navigateTo(String url) {
        driver.get(url);
        logger.info("Navigated to URL: " + url);
    }
    
    /**
     * Click on an element with logging and highlighting
     * @param element Element to click
     * @param elementName Name of the element for logging
     */
    protected void click(WebElement element, String elementName) {
        try {
            waitForElementClickable(element);
            highlightElement(element);
            element.click();
            logger.info("Clicked on element: " + elementName);
        } catch (Exception e) {
            logger.error("Failed to click on element: " + elementName, e);
            throw e;
        }
    }
    
    /**
     * Enter text into an input field
     * @param element Element to enter text into
     * @param text Text to enter
     * @param elementName Name of the element for logging
     */
    protected void sendText(WebElement element, String text, String elementName) {
        try {
            waitForElementVisible(element);
            highlightElement(element);
            element.clear();
            element.sendKeys(text);
            logger.info("Entered text in " + elementName + ": " + text);
        } catch (Exception e) {
            logger.error("Failed to enter text in element: " + elementName, e);
            throw e;
        }
    }
    
    /**
     * Get text from an element
     * @param element Element to get text from
     * @param elementName Name of the element for logging
     * @return The text content of the element
     */
    protected String getText(WebElement element, String elementName) {
        try {
            waitForElementVisible(element);
            highlightElement(element);
            String text = element.getText();
            logger.info("Got text from " + elementName + ": " + text);
            return text;
        } catch (Exception e) {
            logger.error("Failed to get text from element: " + elementName, e);
            throw e;
        }
    }
    
    /**
     * Wait for an element to be visible
     * @param element Element to wait for
     */
    protected void waitForElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    /**
     * Wait for an element to be clickable
     * @param element Element to wait for
     */
    protected void waitForElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    /**
     * Highlight an element by changing its border style
     * @param element Element to highlight
     */
    protected void highlightElement(WebElement element) {
        if (driver instanceof JavascriptExecutor) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.border='2px solid red'", element);
        }
    }
    
    /**
     * Check if an element is displayed
     * @param element Element to check
     * @param elementName Name of the element for logging
     * @return true if the element is displayed, false otherwise
     */
    protected boolean isElementDisplayed(WebElement element, String elementName) {
        try {
            boolean isDisplayed = element.isDisplayed();
            logger.info(elementName + " is displayed: " + isDisplayed);
            return isDisplayed;
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            logger.info(elementName + " is not displayed");
            return false;
        }
    }
    
    /**
     * Execute JavaScript
     * @param script JavaScript to execute
     * @param args Arguments for the script
     * @return Result of the script execution
     */
    protected Object executeJavaScript(String script, Object... args) {
        if (driver instanceof JavascriptExecutor) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript(script, args);
        }
        return null;
    }
}