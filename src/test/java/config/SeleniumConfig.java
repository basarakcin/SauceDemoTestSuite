package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeleniumConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeleniumConfig.class);
    
    static {
        LOGGER.info("Configuring Selenium system properties");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
    }
} 