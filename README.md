# QA Automation Playground

A test automation framework built with Selenium WebDriver, Cucumber BDD, and TestNG for testing the UI Testing Playground website. This project demonstrates modern test automation practices with Page Object Model, behavior-driven development, and maintainable architecture.

## Technology Stack
- **Java 11** - Core programming language
- **Selenium WebDriver 4.15.0** - Browser automation
- **Cucumber 7.14.0** - BDD test framework with Gherkin syntax
- **TestNG 7.8.0** - Test execution and management
- **Maven 3.x** - Build and dependency management
- **Log4j2** - Comprehensive logging framework

## Project Structure
```
src/
├── main/java/com/uiplayground/automation/
│   ├── constants/
│   │   ├── pages/                         # Page-specific constants
│   │   │   └── HomePageConstants.java
│   │   ├── selectors/                     # CSS/XPath selectors
│   │   │   └── HomePageSelectors.java
│   │   └── messages/                      # Error and validation messages
│   │       ├── ErrorMessages.java
│   │       └── ValidationMessages.java
│   ├── core/
│   │   ├── annotations/ElementName.java   # Custom element naming
│   │   ├── config/ConfigManager.java      # Configuration management
│   │   ├── driver/DriverManager.java      # WebDriver lifecycle
│   │   └── reporting/
│   │       ├── ReportManager.java         # Test reporting
│   │       └── ScreenshotManager.java     # Screenshot utilities
│   └── pages/
│       ├── base/BasePage.java             # Base page functionality
│       └── playground/                    # Application-specific pages
│           ├── HomePage.java
│           └── DynamicIdPage.java
├── main/resources/
│   └── config/config.properties           # Framework configuration
└── test/
    ├── java/com/uiplayground/automation/
    │   ├── runners/                       # TestNG Cucumber runners
    │   ├── steps/                         # BDD step definitions
    │   │   └── HomePageSteps.java
    │   ├── utils/                         # Test utilities
    │   │   └── AssertionUtils.java
    │   └── hooks/                         # Cucumber lifecycle hooks
    └── resources/
        ├── features/                      # Cucumber feature files
        │   └── homepage.feature
        ├── log4j2.xml                     # Logging configuration
        └── testng.xml                     # TestNG suite configuration
```

## Currently Implemented Features ✅

### Core Framework
- **BDD test authoring** with Gherkin syntax and Cucumber integration
- **Page Object Model** implementation with method chaining
- **Automatic WebDriver management** with proper lifecycle handling
- **Constants organization** with hierarchical structure for maintainability
- **Custom assertions** with detailed logging and validation messages
- **Text normalization** for robust text comparison with whitespace handling
- **Screenshot capture** on test failures
- **Comprehensive logging** with Log4j2 configuration
- **Element name annotations** for enhanced debugging

### Test Implementation
- **Homepage title validation** - Verify correct title display
- **Homepage title empty validation** - Ensure title is not null or empty
- **Dynamic ID page navigation** - Basic page interaction examples
- **Reusable assertion utilities** - Common validation methods

### Architecture Patterns
- **Page Object Model** - Clean separation of page-specific functionality
- **Singleton Pattern** - ConfigManager for centralized configuration
- **Constants Pattern** - Organized constants for maintainability
- **Method chaining** - Fluent interface for readable test code

## Usage

### Running Tests
```bash
# Run all tests
mvn test

# Run specific test runner
mvn test -Dtest=HomePageTestRunner

# Clean and run tests
mvn clean test

# Run with quiet output (less verbose)
mvn test -q
```

### Configuration
```properties
# config/config.properties (Currently Implemented)
baseUrl=http://www.uitestingplayground.com
browser=chrome
explicitWait=10
implicitWait=5
screenshotsPath=target/screenshots
```

### Sample Test Execution Output
```bash
[INFO] Running com.uiplayground.automation.runners.HomePageTestRunner
[INFO] Navigating to homepage
[INFO] Retrieving page title
[INFO] Validating the page title content
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
```

## Test Coverage (Current)
- ✅ **Homepage title verification** - Validates correct page title display
- ✅ **Homepage title non-empty check** - Ensures title exists and is not empty
- 🔄 **Test scenario links** - Counting and interaction (partially implemented)
- ✅ **Dynamic ID page** - Basic page object example

## Reporting (Current Implementation)
- **Maven Surefire Reports** - Available in `target/surefire-reports/`
- **Console Logging** - Detailed step-by-step execution logs
- **Screenshots** - Captured on test failures in `target/screenshots/`
- **Test Results** - Summary statistics and pass/fail information

## Architecture Highlights

### Design Patterns Implemented
- **Page Object Model** - Encapsulates page-specific functionality
- **Singleton Pattern** - ConfigManager for configuration management
- **Constants Organization** - Hierarchical structure for maintainability
- **Custom Assertions** - Reusable validation utilities

### Key Components
- **BasePage** - Common page functionality and WebDriver interactions
- **ElementName Annotation** - Enhanced element identification for logging
- **AssertionUtils** - Centralized validation methods with detailed messages
- **Text Normalization** - Robust string comparison handling

## Prerequisites
- **Java 11 or higher**
- **Maven 3.6+**
- **Chrome browser** (default, others configurable)
- **Internet connection** for accessing UI Testing Playground

## Quick Setup
```bash
# 1. Clone the repository
git clone [repository-url]

# 2. Navigate to project directory
cd qa-automation-playground

# 3. Install dependencies
mvn clean install

# 4. Run tests
mvn test
```

## Planned Features 📋

### Phase 1: Enhanced Testing (Next Sprint)
- **Multi-browser support** - Firefox, Edge, Safari configurations
- **Environment configurations** - Dev, staging, production settings
- **Cucumber tags** - Smoke, regression test organization
- **Additional page objects** - Complete UI Testing Playground coverage
- **API testing integration** - REST Assured for backend validation

### Phase 2: Enterprise Features (Future)
- **Parallel test execution** - TestNG parallel configuration
- **Docker containerization** - Consistent test execution environment
- **CI/CD integration** - GitHub Actions, Jenkins pipeline support
- **Selenium Grid** - Distributed test execution
- **ExtentReports** - Enhanced HTML reporting with charts and metrics

### Phase 3: Advanced Capabilities (Long-term)
- **Database validation** - JDBC integration for data verification
- **Performance testing** - Basic performance metrics collection
- **Visual regression** - Screenshot comparison capabilities
- **Test data management** - External data sources and generation
- **Cloud deployment** - AWS/Azure container execution

## Contributing
- Follow established coding standards and patterns
- Write clear, descriptive commit messages using conventional commits
- Include appropriate test coverage for new features
- Update documentation for any architectural changes
- Maintain consistent naming conventions across components

## Documentation Structure
- **README.md** - This overview and quick start guide
- **Code Comments** - Inline documentation for complex logic
- **Method Documentation** - JavaDoc for public methods
- **Constants Documentation** - Clear naming and organization

## Current Limitations
- **Single browser support** - Currently Chrome only
- **Single environment** - No environment-specific configurations
- **Limited test coverage** - Basic homepage scenarios implemented
- **No parallel execution** - Sequential test execution only
- **Basic reporting** - Console logs and Maven reports only

## Support
- **Review existing code** - Check implemented patterns in HomePage and HomePageSteps
- **Examine test structure** - Follow established patterns for new tests
- **Check console output** - Detailed logging shows execution flow
- **Maven reports** - Review `target/surefire-reports/` for detailed results

---

## Project Status: **Active Development**
**Current Version**: 0.1.0 - Foundation Implementation  
**Next Milestone**: Multi-browser and environment support  
**Target**: Enterprise-ready test automation framework

*This framework demonstrates modern test automation practices with clean architecture, maintainable code, and professional development standards suitable for enterprise environments.*
