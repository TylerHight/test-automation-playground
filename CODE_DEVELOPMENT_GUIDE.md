# CODE_DEVELOPMENT_GUIDE.md

## Project Overview
This is a Selenium WebDriver test automation framework using Cucumber BDD, TestNG, and Page Object Model for testing the UI Testing Playground website.

## Core Project Structure
```
src/
├── main/java/com/uiplayground/automation/
│   ├── constants/          # Page constants, selectors, messages
│   ├── core/               # Framework foundation
│   ├── models/             # Data models
│   ├── services/           # Service layer for API interactions
│   └── pages/              # Page Objects
└── test/
    ├── java/com/uiplayground/automation/
    │   ├── runners/        # TestNG Cucumber runners
    │   │   ├── ui/         # UI-specific runners
    │   │   └── api/        # API-specific runners
    │   ├── steps/          # BDD step definitions
    │   │   ├── ui/         # UI step definitions
    │   │   └── api/        # API step definitions 
    │   ├── utils/          # Test utilities
    │   └── hooks/          # Test lifecycle hooks
    └── resources/
        ├── features/       # Cucumber feature files
        │   ├── ui/         # UI test scenarios
        │   └── api/        # API test scenarios
        ├── extent.properties # ExtentReports config
        ├── extent-config.xml # ExtentReports customization
        ├── log4j2.xml      # Logging config
        └── testng.xml      # TestNG config
```

## Key Components Reference

### Constants
- **HomePageConstants**: Page-specific constants
- **HomePageSelectors**: CSS/XPath selectors for HomePage
- **UrlConstants**: Application URLs
- **ErrorMessages**: Standard error messages
- **ValidationMessages**: Validation message templates
- **FrameworkConstants**: Global framework settings

### Core
- **ConfigManager**: Singleton for configuration properties
- **DriverManager**: WebDriver lifecycle management
- **ReportManager**: Test reporting and logs with ExtentReports
- **ScreenshotManager**: Screenshot capture and management
- **ElementName**: Custom annotation for improved element identification

### Page Objects
- **BasePage**: Parent class with common actions
- **HomePage**: UI Testing Playground homepage interactions
- **DynamicIdPage**: Example page implementation

### API Components
- **Models**: Data transfer objects for API requests/responses
- **Services**: API client implementations
- **API Steps**: Cucumber step definitions for API testing

### Configuration
- **config.properties**: Framework settings (baseUrl, browser, timeouts)
- **log4j2.xml**: Logging configuration
- **testng.xml**: Test execution configuration
- **extent.properties**: ExtentReports configuration
- **extent-config.xml**: ExtentReports customization

## Code Standards & Patterns

### Page Object Pattern
```java
public class HomePage extends BasePage {
    @ElementName("Page Title")
    private By pageTitle = By.xpath(HomePageSelectors.PAGE_TITLE);
    
    public HomePage() {
        super();
    }
    
    public String getPageTitleText() {
        logger.info("Retrieving page title");
        return getText(pageTitle);
    }
    
    public HomePage open() {
        navigateTo(UrlConstants.HOME_PAGE_URL);
        return this;
    }
}
```

### Step Definitions Pattern
```java
public class HomePageSteps {
    private HomePage homePage;
    private String actualTitle;
    
    // Constructor injection using PicoContainer
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
                          HomePageConstants.HOME_PAGE_TITLE, actualTitle)
        );
    }
}
```

### API Testing Pattern
```java
public class UserAPISteps {
    private Response response;
    private RequestSpecification request;
    private final UserService userService;
    
    public UserAPISteps(UserService userService) {
        this.userService = userService;
    }
    
    @Given("I have access to the user API endpoint")
    public void i_have_access_to_user_api_endpoint() {
        request = given()
            .baseUri(ConfigManager.getInstance().getApiBaseUrl())
            .header("Content-Type", "application/json");
    }
    
    @When("I request user information for user {string}")
    public void i_request_user_information(String userId) {
        response = request
            .when()
            .get("/users/" + userId);
        ReportManager.logInfo("Sent GET request for user: " + userId);
    }
}
```

### Test Runner Configuration
```java
@CucumberOptions(
    features = "src/test/resources/features/ui",
    glue = {
        "com.uiplayground.automation.steps.ui",
        "com.uiplayground.automation.hooks"
    },
    plugin = {
        "pretty",
        "html:target/cucumber-reports/html",
        "json:target/cucumber-reports/json/cucumber.json",
        "junit:target/cucumber-reports/junit/cucumber.xml",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    },
    tags = "@smoke or @homepage"
)
public class HomePageTestRunner extends AbstractTestNGCucumberTests {
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
    And the page title should not be empty
```

### Hook Pattern
```java
public class TestSetupHooks {
    @Before(order = 1)
    public void setUp(Scenario scenario) {
        logger.info("Setting up test scenario - initializing WebDriver");
        DriverManager.initializeDriver();
    }
    
    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotPath = ScreenshotManager.captureScreenshot("failure_" + 
                                   scenario.getName().replaceAll("\\\\s+", "_"));
            ReportManager.getTest().fail("Test Failed", 
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
        
        logger.info("Tearing down test scenario - quitting WebDriver");
        DriverManager.quitDriver();
    }
}
```

## Common Utils & Methods

### Element Interactions
- **click(By locator)**: Click element with logging and reporting
- **sendKeys(By locator, String text)**: Type text with logging
- **getText(By locator)**: Get element text with validation
- **isElementDisplayed(By locator)**: Check visibility with wait
- **waitForElementVisible(By locator)**: Explicit wait for element
- **scrollToElement(By locator)**: Scroll element into view

### Assertion Methods
- **assertEquals(actual, expected, message)**: Compare with detailed logging
- **assertNotNullOrEmpty(text, elementName)**: Validate non-empty strings
- **assertTrue(condition, message)**: Validate condition with detailed message
- **assertElementPresent(element, message)**: Verify element exists

### Configuration Access
- **ConfigManager.getInstance().getProperty("key")**: Get config value
- **ConfigManager.getInstance().getBrowser()**: Get configured browser
- **ConfigManager.getInstance().getBaseUrl()**: Get base URL
- **DriverManager.getDriver()**: Access WebDriver instance

### Reporting Methods
- **ReportManager.logInfo(message)**: Log informational message to report
- **ReportManager.logPass(message)**: Log passed step to report
- **ReportManager.logFail(message)**: Log failed step to report
- **ReportManager.getTest()**: Get current test for custom logging

## Logging Standards
- Use proper log levels: INFO for actions, DEBUG for details, WARN for warnings, ERROR for failures
- Include descriptive messages with page/element context
- Log before and after critical actions
- Use formatted log messages for consistency

## Dependencies
- Selenium WebDriver 4.15.0
- Cucumber 7.14.0
- TestNG 7.8.0
- Log4j2 2.21.1
- ExtentReports 5.1.1
- WebDriverManager 5.6.2
- Rest Assured 5.3.2
- Cucumber PicoContainer 7.14.0

## Creating New Tests

### UI Tests
1. Add selectors to appropriate Selectors class
2. Add constants to Constants class if needed
3. Create/update Page Object with element interactions
4. Write feature file with Gherkin scenarios in the features/ui directory
5. Implement step definitions in the steps/ui package
6. Add to appropriate UI test runner with relevant tags

### API Tests
1. Create/update model classes for request/response
2. Create/update service classes for API interactions
3. Write feature file with API scenarios in the features/api directory
4. Implement API step definitions in the steps/api package
5. Add to appropriate API test runner with relevant tags

## Running Tests
```bash
# Run all tests
mvn test

# Run specific test runner
mvn test -Dtest=HomePageTestRunner

# Run tests in headless mode
mvn test -Dheadless=true

# Clean and run tests
mvn clean test
```

## Reporting
- ExtentReports HTML: test-output/SparkReport/Spark.html
- ExtentReports PDF: test-output/PdfReport/ExtentPdf.pdf
- Cucumber HTML reports: target/cucumber-reports/html
- TestNG reports: target/surefire-reports/

## Best Practices
- Follow the single responsibility principle for page objects and steps
- Use dependency injection to manage object creation and sharing
- Keep feature files focused on business scenarios, not technical details
- Use tags consistently to organize and categorize tests
- Structure directories logically by feature area or test type
- Keep the framework code separate from the application-specific tests
