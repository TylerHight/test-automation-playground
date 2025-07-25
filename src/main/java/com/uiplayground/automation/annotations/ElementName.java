package com.uiplayground.automation.annotations;

/**
 * Custom annotation to provide readable names for Webelements
 * Used for logging and reporting purposes
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ElementName {
    String value();
}
