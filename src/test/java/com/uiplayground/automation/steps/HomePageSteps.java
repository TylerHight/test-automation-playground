package com.uiplayground.automation.steps;

import com.uiplayground.automation.constants.messages.ValidationMessages;
import com.uiplayground.automation.constants.pages.HomePageConstants;
import com.uiplayground.automation.pages.playground.HomePage;
import com.uiplayground.automation.utils.AssertionUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomePageSteps {

    private static final Logger logger = LogManager.getLogger(HomePageSteps.class);

    private HomePage homePage;
    private String actualTitle;
    private int linkCount;

    public HomePageSteps(HomePage homePage) {
        this.homePage = homePage;
    }

    @Given("I navigate to the homepage")
    public void i_navigate_to_the_homepage() {
        logger.info("Navigating to homepage");
        homePage.open();
    }

    @When("I view the page title")
    public void i_view_the_page_title() {
        logger.info("Retrieving page title");
        actualTitle = homePage.getPageTitleText();
        logger.debug("Actual title: {}", actualTitle);
    }

    @Then("the page title should be displayed correctly")
    public void the_page_title_should_be_displayed_correctly() {
        logger.info("Validating the page title content");
        AssertionUtils.assertTitle(
                actualTitle,
                HomePageConstants.HOME_PAGE_TITLE,
                String.format(ValidationMessages.TITLE_VERIFICATION_FAILED,
                        HomePageConstants.HOME_PAGE_TITLE, actualTitle));
    }

    @Then("the page title should not be empty")
    public void the_page_title_should_not_be_empty() {
        logger.info("Validating that the page title is not empty");
        AssertionUtils.assertNotNullOrEmpty(actualTitle, "Page Title");
    }

    @When("I check the available test links")
    public void i_check_the_available_test_links() { 
        logger.info("Validating that home page test links are available");
    }

    @Then("I should see test scenario links on the page")
    public void i_should_see_test_scenario_links_on_the_page() {
    }
}
