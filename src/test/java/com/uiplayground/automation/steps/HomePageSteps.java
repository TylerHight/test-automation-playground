package com.uiplayground.automation.steps;

import com.uiplayground.automation.pages.playground.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.testng.Assert;

public class HomePageSteps {

    private HomePage homePage;
    private String actualTitle;
    private int linkCount;

    @Given("I navigate to the homepage")
    public void i_navigate_to_the_homepage() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("I check the available test links")
    public void i_check_the_available_test_links() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("I should see test scenario links on the page")
    public void i_should_see_test_scenario_links_on_the_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
