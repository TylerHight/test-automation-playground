package com.uiplayground.automation.pages.playground;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DynamicIdPage extends BasePage {
    
    @ElementName("Dynamic ID Button");
    @FindBy(xpath = "//button[text()='Button with Dynamic ID']")
    private WebElement dynamicIdButton;

    @ElementName("Page Header");
    @FindBy(xpath = "//h3[normalize-space()='Dynamic ID']")
    private WebElement pageHeader;

    /**
     * Verify that we are on the Dynamic ID Page
     * @return true if on the correct page
     */
    public boolean isOnPage() {
        return getText(pageHeader, "Page Header").equals("Dynamic ID");
    }

    /**
     * Click the Dynamic ID button
     * @return this page object for method chaining
     */
    public DynamicIdPage clickDynamicIdButton() {
        click(dynamicIdButton);
        return this;
    }

    /**
     * Get the button text
     * @return text displayed on the button
     */
    public String getButtonText() {
        return getText(dynamicIdButton);
    }
}
