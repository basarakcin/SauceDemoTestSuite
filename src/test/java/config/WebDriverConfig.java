package config;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverConfig.class);
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static WebDriverConfig instance;

    private WebDriverConfig() {
        // Private constructor for singleton to prevent leaks
    }

    public static synchronized WebDriverConfig getInstance() {
        if (instance == null) {
            instance = new WebDriverConfig();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                LOGGER.info("JVM Shutdown Hook - Cleaning up WebDriver");
                instance.quitDriver();
            }));
        }
        return instance;
    }

    public WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            initializeDriver();
        }
        return driverThreadLocal.get();
    }

    private void initializeDriver() {
        LOGGER.info("Initializing WebDriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-site-isolation-trials");
        
        WebDriver driver = new ChromeDriver(options);
        driverThreadLocal.set(driver);
    }

    public void takeScreenshot(String scenarioName) {
        if (driverThreadLocal.get() instanceof TakesScreenshot) {
            LOGGER.info("Taking screenshot for scenario: {}", scenarioName);
            try {
                ((TakesScreenshot) driverThreadLocal.get()).getScreenshotAs(OutputType.BYTES);
            } catch (WebDriverException | IllegalStateException e) {
                LOGGER.error("Failed to take screenshot: {}", e.getMessage());
            }
        }
    }

    private void safeSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            LOGGER.info("=== Starting WebDriver quit process for thread {} ===", Thread.currentThread().getName());
            try {
                String currentUrl = driver.getCurrentUrl();
                LOGGER.info("Last URL before quit: {}", currentUrl);
                
                LOGGER.info("Attempting to close browser window");
                driver.close();
                
                driverThreadLocal.remove();
                safeSleep(500);
                
                LOGGER.info("Attempting to quit WebDriver");
                driver.quit();
                
                LOGGER.info("=== WebDriver quit successful ===");
            } catch (Exception e) {
                LOGGER.error("Primary quit failed: {}", e.getMessage());
                try {
                    LOGGER.info("Attempting force quit");
                    driver.quit();
                    LOGGER.info("Force quit successful");
                } catch (Exception ex) {
                    LOGGER.error("Force quit failed: {}", ex.getMessage());
                    try {
                        ProcessBuilder processBuilder = new ProcessBuilder("taskkill", "/F", "/IM", "chrome.exe");
                        Process process = processBuilder.start();
                        process.waitFor();
                        LOGGER.info("Chrome process killed");
                    } catch (IOException | InterruptedException killEx) {
                        LOGGER.error("Failed to kill Chrome process: {}", killEx.getMessage());
                        if (killEx instanceof InterruptedException) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            } finally {
                LOGGER.info("Cleaning up ThreadLocal resources");
                driver = null;
                LOGGER.info("=== ThreadLocal cleanup complete ===");
            }
        } else {
            LOGGER.warn("No WebDriver instance found to quit for thread {}", Thread.currentThread().getName());
        }
    }
} 