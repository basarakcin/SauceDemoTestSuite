package config;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.TestContext;

public class Hooks {
    private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);
    private final TestContext testContext;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before
    public void setUp(Scenario scenario) {
        LOGGER.info("Starting scenario: {} [{}]", scenario.getName(), scenario.getId());
    }

    @After(order = 1)
    public void takeScreenshotOnFailure(Scenario scenario) {
        LOGGER.info("Executing screenshot step for scenario: {}", scenario.getName());
        WebDriver driver = testContext.getDriver();
        if (driver != null) {
            try {
                LOGGER.info("Checking scenario status: {}", scenario.getName());
                if (scenario.isFailed()) {
                    LOGGER.warn("Taking screenshot for failed scenario: {}", scenario.getName());
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", "Screenshot");
                }
            } catch (WebDriverException | IllegalStateException e) {
                LOGGER.error("Error taking screenshot for scenario {}: {}", scenario.getName(), e.getMessage());
            }
        }
    }

    @After(order = 0)
    public void tearDown(Scenario scenario) {
        LOGGER.info("=== Starting tearDown for scenario: {} ===", scenario.getName());
        try {
            if (scenario.getName().contains("locked out user")) {
                LOGGER.info("Special handling for locked out user scenario");
                WebDriver driver = testContext.getDriver();
                if (driver != null) {
                    String currentUrl = driver.getCurrentUrl();
                    LOGGER.info("Current URL before locked user teardown: {}", currentUrl);
                    // Force cleanup for locked out user
                    driver.manage().deleteAllCookies();
                    driver.navigate().refresh();
                }
            }
            testContext.tearDown();
            LOGGER.info("=== Successfully completed tearDown for scenario: {} ===", scenario.getName());
        } catch (Exception e) {
            LOGGER.error("Error in tearDown for scenario {}: {}", scenario.getName(), e.getMessage(), e);
            try {
                // Force quit as a last resort
                WebDriver driver = testContext.getDriver();
                if (driver != null) {
                    driver.quit();
                }
            } catch (Exception ex) {
                LOGGER.error("Failed force quit: {}", ex.getMessage());
            }
            throw new RuntimeException("Failed to tear down test context", e);
        } finally {
            LOGGER.info("=== Finished tearDown for scenario: {} ===", scenario.getName());
        }
    }
} 