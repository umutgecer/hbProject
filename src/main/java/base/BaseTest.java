package base;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest {
    protected WebDriver driver;
    protected Logger logger = LogManager.getLogger(this.getClass());

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        logger.info("START TESTING......BROWSER: {}", browser);
        driver = DriverFactory.getDriver(browser);
        driver.get("https://www.hepsiburada.com");
    }

    @AfterMethod
    public void teardown() {
        logger.info("ENDING TESTING......!!!");
        DriverFactory.quitDriver();
    }
}