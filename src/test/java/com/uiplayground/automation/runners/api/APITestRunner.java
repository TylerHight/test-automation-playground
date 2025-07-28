// File: src/test/java/com/uiplayground/automation/runners/api/APITestRunner.java
package com.uiplayground.automation.runners.api;

import com.uiplayground.automation.runners.BaseTestRunner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features/api",
    glue = {
        "com.uiplayground.automation.steps.api",
        "com.uiplayground.automation.hooks"
    },
    plugin = {
        "pretty",
        "html:target/cucumber-reports/html/api",                    
        "json:target/cucumber-reports/json/api.json",      
        "junit:target/cucumber-reports/junit/api.xml",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    },
    tags = "@api"
)
public class APITestRunner extends BaseTestRunner {
}
