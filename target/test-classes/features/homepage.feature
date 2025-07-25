Feature: Homepage functionality
    As a user
    I want to access the homepage 
    So that I can view the page title and navigate to test scenarios

  @smoke @homepage
  Scenario: Verify homepage loads with correct title
    Given I navigate to the homepage
    When I view the page title
    Then the page title should be displayed correctly
    And the page title should not be empty

  # @regression @homepage
  # Scenario: Verify test scenario links are available
  #   Given I navigate to the homepage
  #   When I check the available test links
  #   Then I should see test scenario links on the page
