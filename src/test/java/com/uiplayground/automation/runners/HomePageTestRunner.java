package com.uiplayground.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {
        "com.uiplayground.automation.steps",
        "com.uiplayground.automation.hooks"
    },
    plugin = {
        "pretty",
        "html:target/cucumber-reports/html",                    
        "json:target/cucumber-reports/json/cucumber.json",      
        "junit:target/cucumber-reports/junit/cucumber.xml"      
    },
    tags = "@smoke or @homepage"
)

public class HomePageTestRunner extends AbstractTestNGCucumberTests {
}
