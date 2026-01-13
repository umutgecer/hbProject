package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class WaitUtil {
    protected Logger logger = LogManager.getLogger(this.getClass());
    private final FluentWait<WebDriver> wait;

    public WaitUtil(WebDriver driver) {
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public WebElement waitForVisibility(By by) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            logger.info("Element not found: " + by);
            return null;
        }
    }

    public WebElement waitForClickable(By by) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            logger.info("Element not clickable: " + by);
            return null;
        }
    }

    public List<WebElement> waitForElementsVisible(By by) {
        try {
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        } catch (Exception e) {
            logger.info("Element not found: " + by);
            return List.of();
        }
    }

    public boolean isElementVisible(By by) {
        try {
            WebElement element = waitForVisibility(by);
            return element != null && element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}