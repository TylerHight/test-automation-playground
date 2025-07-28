# QA Automation Playground

A test automation framework built with Selenium WebDriver, Cucumber BDD, and TestNG for testing the UI Testing Playground website. This project demonstrates modern test automation practices with Page Object Model, behavior-driven development, and maintainable architecture.

## Technology Stack
- **Java 11** - Core programming language
- **Selenium WebDriver 4.15.0** - Browser automation
- **Cucumber 7.14.0** - BDD test framework with Gherkin syntax
- **TestNG 7.8.0** - Test execution and management
- **Maven 3.x** - Build and dependency management
- **Log4j2 2.21.1** - Comprehensive logging framework
- **ExtentReports 5.1.1** - Enhanced test reporting
- **Rest Assured 5.3.2** - API testing capabilities
- **PicoContainer** - Dependency injection for step definitions

## Project Structure
```
src/
├── main/java/com/uiplayground/automation/
│   ├── constants/
│   │   ├── pages/                         # Page-specific constants
│   │   ├── selectors/                     # CSS/XPath selectors
│   │   └── messages/                      # Error and validation messages
│   ├── core/
│   │   ├── annotations/ElementName.java   # Custom element naming
│   │   ├── config/ConfigManager.java      # Configuration management
│   │   ├── driver/DriverManager.java      # WebDriver lifecycle
│   │   └── reporting/
│   │       ├── ReportManager.java         # Test reporting
│   │       └── ScreenshotManager.java     # Screenshot utilities
│   ├── models/
│   │   └── api/                           # API data models
│   ├── services/                          # API service classes
│   └── pages/
│       ├── base/BasePage.java             # Base page functionality
│       └── playground/                    # Application-specific pages
├── main/resources/
│   └── config/config.properties           # Framework configuration
└── test/
    ├── java/com/uiplayground/automation/
    │   ├── runners/                       # TestNG Cucumber runners
    │   │   ├── ui/                        # UI-specific test runners
    │   │   └── api/                       # API-specific test runners
    │   ├── steps/                         # BDD step definitions
    │   │   ├── ui/                        # UI step definitions
    │   │   └── api/                       # API step definitions
    │   ├── utils/                         # Test utilities
    │   │   └── AssertionUtils.java        # Enhanced assertion methods
    │   └── hooks/                         # Cucumber lifecycle hooks
    └── resources/
        ├── features/                      # Cucumber feature files
        │   ├── ui/                        # UI test scenarios
        │   └── api/                       # API test scenarios
        ├── extent.properties              # ExtentReports configuration
        ├── extent-config.xml              # ExtentReports customization
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
- **ExtentReports integration** with detailed test reporting
- **PicoContainer** dependency injection for step definitions

### Test Implementation
- **Homepage title validation** - Verify correct title display
- **Homepage title empty validation** - Ensure title is not null or empty
- **Test scenario links** - Count and verify presence of test scenarios
- **Dynamic ID page** - Basic page object example
- **Headless browser support** - Configurable execution mode

### Architecture Patterns
- **Page Object Model** - Clean separation of page-specific functionality
- **Singleton Pattern** - ConfigManager for centralized configuration
- **Constants Pattern** - Organized constants for maintainability
- **Method chaining** - Fluent interface for readable test code
- **Organized test runners** - Structured by test type (UI/API)

## Usage

### Running Tests
```bash
# Run all tests
mvn test

# Run specific test runner
mvn test -Dtest=HomePageTestRunner

# Run tests in headless mode
mvn test -Dheadless=true

# Clean and run tests
mvn clean test

# Run with quiet output (less verbose)
mvn test -q
```

### Configuration
```properties
# config/config.properties
baseUrl=http://www.uitestingplayground.com
browser=chrome
headless=false
explicitWait=10
implicitWait=5
screenshotsPath=target/screenshots
reportsPath=test-output/extent-reports
```

### Sample Test Execution Output
```bash
[INFO] Running com.uiplayground.automation.runners.ui.HomePageTestRunner
[INFO] Navigating to homepage
[INFO] Retrieving page title
[INFO] Validating the page title content
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
```

## Test Coverage (Current)
- ✅ **Homepage title verification** - Validates correct page title display
- ✅ **Homepage title non-empty check** - Ensures title exists and is not empty
- ✅ **Test scenario links** - Counting and verification
- ✅ **Dynamic ID page** - Basic page object example

## Reporting
- **ExtentReports** - HTML reports with test details at `test-output/SparkReport/Spark.html`
- **ExtentReports PDF** - PDF reports at `test-output/PdfReport/ExtentPdf.pdf`
- **Maven Surefire Reports** - Available in `target/surefire-reports/`
- **Console Logging** - Detailed step-by-step execution logs
- **Screenshots** - Captured on test failures in `target/screenshots/`

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
- **ReportManager** - Centralized reporting management

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
- **Additional page objects** - Complete UI Testing Playground coverage
- **Additional API test scenarios** - REST Assured implementation examples

### Phase 2: Enterprise Features (Future)
- **Parallel test execution** - TestNG parallel configuration
- **Docker containerization** - Consistent test execution environment
- **CI/CD integration** - GitHub Actions, Jenkins pipeline support
- **Selenium Grid** - Distributed test execution

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
- **CODE_DEVELOPMENT_GUIDE.md** - Detailed development standards
- **Code Comments** - Inline documentation for complex logic
- **Method Documentation** - JavaDoc for public methods
- **Constants Documentation** - Clear naming and organization

## Current Limitations
- **Limited browser support** - Currently Chrome only (headless option available)
- **Single environment** - No environment-specific configurations
- **Limited test coverage** - Basic homepage scenarios implemented
- **No parallel execution** - Sequential test execution only

## Support
- **Review existing code** - Check implemented patterns in HomePage and HomePageSteps
- **Examine test structure** - Follow established patterns for new tests
- **Check console output** - Detailed logging shows execution flow
- **Check reports** - View ExtentReports for detailed test execution details

---

## Project Status: **Active Development**
**Current Version**: 0.2.0 - ExtentReports Integration  
**Next Milestone**: Multi-browser and environment support  
**Target**: Enterprise-ready test automation framework

*This framework demonstrates modern test automation practices with clean architecture, maintainable code, and professional development standards suitable for enterprise environments.*
