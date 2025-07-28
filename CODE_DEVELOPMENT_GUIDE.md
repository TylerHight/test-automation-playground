# CODE_DEVELOPMENT_GUIDE.md

## Project Overview
This is a Selenium WebDriver test automation framework using Cucumber BDD, TestNG, and Page Object Model for testing the UI Testing Playground website.

## Core Project Structure
```
src/
├── main/java/com/uiplayground/automation/
│   ├── constants/          # Page constants, selectors, messages
│   ├── core/               # Framework foundation
│   └── pages/              # Page Objects
└── test/
    ├── java/com/uiplayground/automation/
    │   ├── runners/        # TestNG Cucumber runners
    │   ├── steps/          # BDD step definitions
    │   └── hooks/          # Test lifecycle hooks
    └── resources/
        ├── features/       # Cucumber feature files
        ├── log4j2.xml      # Logging config
        └── testng.xml      # TestNG config
```

## Key Components Reference

### Constants
- **HomePageConstants**: Page-specific constants
- **HomePageSelectors**: CSS/XPath selectors for HomePage
- **UrlConstants**: Application URLs
- **ErrorMessages**: Standard error messages
- **FrameworkConstants**: Global framework settings

### Core
- **ConfigManager**: Singleton for configuration properties
- **DriverManager**: WebDriver lifecycle management
- **ReportManager**: Test reporting and logs
- **ElementName**: Custom annotation for improved element identification

### Page Objects
- **BasePage**: Parent class with common actions
- **HomePage**: UI Testing Playground homepage interactions
- **DynamicIdPage**: Example page implementation

### Configuration
- **config.properties**: Framework settings (baseUrl, browser, timeouts)
- **log4j2.xml**: Logging configuration
- **testng.xml**: Test execution configuration

## Code Standards & Patterns

### Page Object Pattern
```java
public class HomePage extends BasePage {
    @ElementName("Page Title")
    private By pageTitle = By.xpath(HomePageSelectors.PAGE_TITLE);
    
    public HomePage() {
        super();
    }
    
    public String getPageTitle() {
        logger.info("Retrieving page title");
        return getText(pageTitle);
    }
}
```

### Step Definitions Pattern
```java
public class HomePageSteps {
    private HomePage homePage = new HomePage();
    
    @Given("I navigate to the homepage")
    public void navigateToHomepage() {
        homePage.navigateToHomePage();
    }
    
    @When("I view the page title")
    public void viewPageTitle() {
        homePage.getPageTitle();
    }
    
    @Then("the page title should be displayed correctly")
    public void verifyPageTitle() {
        AssertionUtils.assertEquals(
            homePage.getPageTitle(), 
            HomePageConstants.EXPECTED_TITLE,
            ErrorMessages.TITLE_MISMATCH
        );
    }
}
```

### Test Runner Configuration
```java
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.uiplayground.automation.steps", "com.uiplayground.automation.hooks"},
    plugin = {"pretty", "html:target/cucumber-reports/html"},
    tags = "@smoke or @homepage"
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
```

### Feature File Pattern
```gherkin
Feature: Homepage functionality
  
  @smoke @homepage
  Scenario: Verify homepage loads with correct title
    Given I navigate to the homepage
    When I view the page title
    Then the page title should be displayed correctly
```

### Hook Pattern
```java
public class TestSetupHooks {
    @Before(order = 1)
    public void setUp() {
        DriverManager.initializeDriver();
    }
    
    @After(order = 1)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
```

## Common Utils & Methods

### Element Interactions
- **click(By locator)**: Click element with logging
- **sendKeys(By locator, String text)**: Type text with logging
- **getText(By locator)**: Get element text with validation
- **isElementDisplayed(By locator)**: Check visibility with wait
- **waitForElementVisible(By locator)**: Explicit wait for element

### Assertion Methods
- **assertEquals(actual, expected, message)**: Compare with detailed logging
- **assertNotEmpty(text, message)**: Validate non-empty strings
- **assertElementPresent(element, message)**: Verify element exists

### Configuration Access
- **ConfigManager.getProperty("key")**: Get config value
- **DriverManager.getDriver()**: Access WebDriver instance

## Logging Standards
- Use proper log levels: INFO for actions, WARN for warnings, ERROR for failures
- Include descriptive messages with page/element context
- Log before and after critical actions

## Dependencies
- Selenium WebDriver 4.15.0
- Cucumber 7.14.0
- TestNG 7.8.0
- Log4j2 2.21.1
- ExtentReports 5.1.1
- WebDriverManager 5.6.2

## Creating New Tests
1. Add selectors to appropriate Selectors class
2. Add constants to Constants class if needed
3. Create/update Page Object with element interactions
4. Write feature file with Gherkin scenarios
5. Implement step definitions
6. Add to test runner with appropriate tags