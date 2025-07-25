package com.uiplayground.automation.pages.playground;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.uiplayground.automation.core.annotations.ElementName;
import com.uiplayground.automation.core.config.ConfigManager;
import com.uiplayground.automation.pages.base.BasePage;

import java.util.List;
import org.openqa.selenium.NoSuchElementException;
/**  
 * Page object for UI Testing Playground homepage
*/
public class HomePage extends BasePage {
    
    @ElementName("Page Title")
    @FindBy(css = ".container h1")
    private WebElement pageTitle;

    @ElementName("Test Scenario Links")
    @FindBy(css = ".container .row a.btn")
    private List<WebElement> testLinks;

    /**
     * Navigate to the homepage
     * @return HomePage instance
     */
    public HomePage open() {
        navigateTo(ConfigManager.getInstance().getBaseUrl());
        return this;
    }

    /**
     * Get the title text from the homepage
     * @return Title text
     */
    public String getPageTitleText() {
        return getText(pageTitle);
    }

    /**
     * Click on a specific test scenario link by name
     * @param linkText The text of the link to click
     * @return HomePage instance
     */
    public HomePage clickTestLink(String linkText) {
        for (WebElement link : testLinks) {
            if (link.getText().trim().equals(linkText)) {
                click(link);
                return this;
            }
        }
        logger.error("Test link not found: " + linkText);
        throw new NoSuchElementException("Test link not found: " + linkText);
    }

    /**
     * Get the number of test scenarios available
     * @return Number of test scenario links
     */
    public int getTestLinkCount() {
        return testLinks.size();
    }
}
