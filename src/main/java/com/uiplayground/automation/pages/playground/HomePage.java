package com.uiplayground.automation.pages.playground;

/**  
 * Page object for UI Testing Playground homepage
*/
public class HomePage extends BasePage {
    
    @FindBy(css = ".container h1")
    private WebElement pageTitle;

    // A list of links for the available test scenarios
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
        return getText(pageTitle, "Page Title");
    }

    /**
     * Click on a specific test scenario link by name
     * @param linkText The text of the link to click
     * @return HomePage instance for now (will be updated to return specific page objects)
     */
    public HomePage clickTestLink(String linkText) {
        for (WebElement link : testLinks) {
            if (link.getText().trim().equals(linkText)) {
                click(link, "Test Link: " + linkText);
                return this;
            }
        }
        logger.error("Test link not found: " + linkText);
        throw new NoSuchElementException("Test link not found: " + linkText);
    }

    /**
     * Get the number of test scenarios available on the homepage
     * @return Number of test scenario links
     */
    public int getTestLinkCount() {
        return testLinks.size();
    }

     
}
