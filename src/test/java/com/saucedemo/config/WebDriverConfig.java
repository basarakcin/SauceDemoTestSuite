package com.saucedemo.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverConfig {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = initializeDriver();
        }
        return driver;
    }

    public static WebDriver initializeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-site-isolation-trials");

        return new ChromeDriver(options);
    }

    public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
            WebDriverConfig.driver = null;
        }
    }
}