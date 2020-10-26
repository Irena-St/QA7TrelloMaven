package ru.stqa.selenium.tests;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.selenium.SuiteConfiguration;
import ru.stqa.selenium.factory.WebDriverPool;
import ru.stqa.selenium.util.LogLog4j;

/**
 * Base class for TestNG-based test classes
 */
public class TestBase {
    public static final String LOGIN = "semenchuk833@gmail.com";
    public static final String PASSWORD = "123eva456bel";
    public static LogLog4j log4j = new LogLog4j();

    protected static URL gridHubUrl = null;
    protected static String baseUrl;
    protected static Capabilities capabilities;

    protected WebDriver driver;

    @BeforeSuite
    public void initTestSuite() throws IOException {
        SuiteConfiguration config = new SuiteConfiguration();
        baseUrl = config.getProperty("site.url");
        if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
            gridHubUrl = new URL(config.getProperty("grid.url"));
        }
        capabilities = config.getCapabilities();
    }

    @BeforeMethod
    public void initWebDriver() {

        driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
        // Driver initialization. Open Trello application
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=" + "en");
        driver = new ChromeDriver(options);
        driver.get(baseUrl);
    }
    @AfterMethod
    public void finishTest(){
        driver.quit();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        WebDriverPool.DEFAULT.dismissAll();
    }
}
