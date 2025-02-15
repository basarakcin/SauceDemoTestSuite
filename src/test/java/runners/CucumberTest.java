package runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

/**
 * Test Execution Configuration
 * --------------------------
 * Document ID: EXEC-001
 * 
 * 1. Test Suite Organization:
 *    - Smoke Suite (TAG: @smoke)
 *      Coverage: Critical business paths
 *      Execution: Every commit
 *    
 *    - Regression Suite (TAG: @regression)
 *      Coverage: Full functionality
 *      Execution: Nightly
 *    
 *    - Security Suite (TAG: @security)
 *      Coverage: Security features
 *      Execution: Weekly
 * 
 * 2. Test Execution Parameters:
 *    - Parallel execution: Disabled
 *    - Retry count: 1
 *    - Timeout: 30 seconds per step
 * 
 * 3. Reporting:
 *    - HTML reports
 *    - Screenshot capture
 *    - Logging level: INFO
 * 
 * 4. Environment Management:
 *    - WebDriver cleanup
 *    - Resource management
 *    - Session handling
 * 
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "stepdefinitions,config")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, 
    value = "pretty," +
            "html:target/cucumber-reports/report.html")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "not @ignore")
public class CucumberTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CucumberTest.class);

    // This hook ensures cleanup even if Cucumber's hooks don't run
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            LOGGER.info("=== JVM Shutdown Hook - Final Cleanup ===");
            try {
                config.WebDriverConfig.getInstance().quitDriver();
            } catch (Exception e) {
                LOGGER.error("Error during final cleanup: {}", e.getMessage(), e);
            }
        }));
    }
} 