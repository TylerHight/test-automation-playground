# UI Test Automation Framework

## Overview
A comprehensive Selenium-based test automation framework implementing Behavior-Driven Development (BDD) with Cucumber feature files, TestNG test runner, and Page Object Model architecture. Designed for enterprise-level QA automation with focus on Selenium automation practice and professional development.

## Technology Stack
- **Java 11** - Programming language
- **Selenium WebDriver 4.15.0** - Browser automation
- **Cucumber 7.14.0** - BDD framework and Gherkin parser
- **TestNG 7.8.0** - Test execution and management
- **Maven** - Build automation and dependency management
- **Log4j 2.21.1** - Logging framework
- **WebDriverManager 5.6.2** - Automatic driver management
- **ExtentReports 5.1.1** - Test reporting and visualization

## Core Framework Components

### Test Infrastructure
- **DriverManager** - ThreadLocal WebDriver instance lifecycle and configuration
- **ConfigManager** - Centralized configuration management with classpath loading
- **TestSetupHooks** - Cucumber hooks for scenario setup/teardown and driver management
- **ReportingHooks** - Cucumber scenario lifecycle monitoring and failure handling

### Reporting & Monitoring
- **ScreenshotManager** - Automatic screenshot capture on failures with feature organization
- **ReportManager** - ExtentReports integration optimized for Cucumber scenarios
- **@ElementName** - Enhanced logging with readable element names

### Configuration Files
- **config.properties** - Framework and Cucumber-specific configuration
- **log4j2.xml** - Logging configuration and appenders
- **testng.xml** - TestNG suite configuration for Cucumber runner execution

## Usage

### Running Tests
```bash
# Run all tests
mvn test

# Run specific Cucumber tags
mvn test -Dcucumber.filter.tags="@smoke"

# Run with specific browser
mvn test -Dbrowser=chrome

# Run in headless mode
mvn test -Dheadless=true

# Run specific test runner
mvn test -Dtest=HomePageTestRunner
```

## Project Structure
```
src/
├── main/java/com/uiplayground/automation/
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
├── main/resources/
│   └── config.properties                  # Framework configuration
└── test/
    ├── java/com/uiplayground/automation/
    │   ├── runners/                       # TestNG Cucumber runners
    │   ├── steps/                         # BDD step definitions
    │   └── hooks/                         # Cucumber lifecycle hooks
    └── resources/
        ├── features/                      # Cucumber feature files
        ├── log4j2.xml                     # Logging configuration
        └── testng.xml                     # TestNG suite configuration
```

## Current Features
- **BDD test authoring** with Gherkin syntax and Cucumber integration
- **Page Object Model** implementation with method chaining
- **Automatic WebDriver management** (Chrome, Firefox, Edge) with ThreadLocal support
- **Cucumber scenario lifecycle management** with dedicated hooks
- **Screenshot capture** on failures with feature-based organization
- **ExtentReports integration** optimized for Cucumber scenarios
- **Element name caching** for enhanced logging and debugging
- **Comprehensive logging** with Log4j2 configuration
- **Configuration management** with environment-specific settings
- **Cross-platform execution** (Windows/Linux/Mac)

## Cucumber-Specific Features
- **Feature-based test organization** with Gherkin scenarios
- **Tag-based test execution** for smoke, regression, and custom test suites
- **Scenario-level reporting** with detailed step execution logs
- **Cucumber hooks integration** for setup/teardown operations
- **Step definition management** with proper glue configuration
- **Cucumber report generation** in multiple formats (HTML, JSON, XML)

## Enterprise Capabilities
- **Professional Architecture** - Clean separation of concerns with established design patterns
- **Scalable Design** - ThreadLocal WebDriver support for future parallel execution
- **Maintainable Codebase** - Clear documentation and consistent coding standards
- **Comprehensive Reporting** - Multiple reporting formats with screenshot integration
- **Configuration Management** - Centralized settings for easy environment management

## Planned Features
- **Parallel test execution** with TestNG and Cucumber integration
- **Multi-environment support** with environment-specific configurations
- **API testing integration** with REST Assured for end-to-end validation
- **Database validation utilities** with JDBC integration
- **Docker containerization** for consistent test execution
- **CI/CD pipeline integration** (Jenkins, GitHub Actions)
- **Selenium Grid integration** for distributed testing
- **Advanced reporting features** with trend analysis and metrics

## Configuration

### Framework Settings
```properties
# Basic Configuration
baseUrl=http://www.uitestingplayground.com
browser=chrome
explicitWait=10

# Cucumber Settings
cucumber.reports.path=target/cucumber-reports
cucumber.screenshots.organize.by.feature=true
cucumber.step.logging.enabled=true
```

### Test Execution
```xml
<!-- testng.xml -->
<suite name="UI Test Automation Suite">
    <test name="Cucumber Tests">
        <classes>
            <class name="com.uiplayground.automation.runners.HomePageTestRunner"/>
        </classes>
    </test>
</suite>
```

## Architecture Highlights

### Design Patterns
- **Page Object Model** - Encapsulates page-specific functionality
- **Singleton Pattern** - ConfigManager and DriverManager
- **Factory Pattern** - WebDriver instantiation
- **ThreadLocal Pattern** - Thread-safe WebDriver management

### Key Components
- **Cucumber Hooks** - Clean test lifecycle management
- **Custom Annotations** - Enhanced element identification
- **Fluent Interface** - Method chaining for readable code
- **Comprehensive Logging** - Detailed execution tracking

## Getting Started

### Prerequisites
- Java 11 or higher
- Maven 3.6+
- Chrome/Firefox/Edge browser

### Quick Setup
1. Clone the repository
2. Run `mvn clean install`
3. Execute tests with `mvn test`

## Quick Start
See [GETTING_STARTED.md](GETTING_STARTED.md) for detailed setup instructions.

## Documentation
- [ARCHITECTURE.md](ARCHITECTURE.md) - Technical implementation details
- [CODING_STANDARDS.md](CODING_STANDARDS.md) - Development guidelines
- [TROUBLESHOOTING.md](TROUBLESHOOTING.md) - Common issues and solutions

## Reporting
- **Cucumber Reports** - Available in `target/cucumber-reports/`
- **ExtentReports** - Enhanced reporting with scenario details
- **Screenshots** - Automatically captured on failures, organized by feature
- **Logs** - Comprehensive logging with configurable levels

## Best Practices
- Follow BDD principles with clear, readable scenarios
- Use Page Object Model for maintainable test code
- Implement proper wait strategies for stable tests
- Utilize meaningful element names with @ElementName annotation
- Organize tests with appropriate Cucumber tags

## Contributing
- Follow established coding standards
- Write clear, descriptive commit messages
- Include appropriate test coverage
- Update documentation for new features
- Use consistent naming conventions

## Support
- Review existing documentation in the project
- Check the troubleshooting guide for common issues
- Examine existing test examples for patterns
- Follow established coding standards for consistency
