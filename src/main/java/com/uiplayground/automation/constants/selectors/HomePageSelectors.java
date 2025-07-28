// src/main/java/com/uiplayground/automation/constants/selectors/HomePageSelectors.java
package com.uiplayground.automation.constants.selectors;

public final class HomePageSelectors {
    
    private HomePageSelectors() {}
    
    // Main Page Elements
    public static final String PAGE_TITLE = ".container h1";
    public static final String PAGE_DESCRIPTION = ".container p";
    public static final String TEST_LINKS = ".container .row .col-sm h3 a";
    
    // Specific Link Selectors
    public static final String DYNAMIC_ID_LINK = "a[href*='dynamicid']";
    public static final String CLASS_ATTRIBUTE_LINK = "a[href*='classattr']";
}
