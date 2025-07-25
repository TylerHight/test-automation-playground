package com.uiplayground.automation.pages.playground;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.uiplayground.automation.core.annotations.ElementName;
import com.uiplayground.automation.core.config.ConfigManager;
import com.uiplayground.automation.constants.pages.HomePageConstants;
import com.uiplayground.automation.constants.selectors.HomePageSelectors;
import com.uiplayground.automation.constants.messages.ErrorMessages;
import com.uiplayground.automation.pages.base.BasePage;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;

public class HomePage extends BasePage {

    @ElementName("Page Title")
    @FindBy(css = HomePageSelectors.PAGE_TITLE)
    private WebElement pageTitle;

    @ElementName("Test Scenario Links")
    @FindBy(css = HomePageSelectors.TEST_LINKS)
    private List<WebElement> testLinks;

    public HomePage open() {
        navigateTo(ConfigManager.getInstance().getBaseUrl());
        return this;
    }

    public String getPageTitleText() {
        return getText(pageTitle).replaceAll("\\s+", " ").trim();
    }

    public HomePage clickTestLink(String linkText) {
        for (WebElement link : testLinks) {
            if (link.getText().trim().equals(linkText)) {
                click(link);
                return this;
            }
        }
        logger.error("Test link not found: " + linkText);
        throw new NoSuchElementException(
                String.format(ErrorMessages.TEST_LINK_NOT_FOUND, linkText));
    }

    public int getTestLinkCount() {
        return testLinks.size();
    }

    public boolean hasExpectedLinkCount() {
        return getTestLinkCount() == HomePageConstants.EXPECTED_LINK_COUNT;
    }
}
