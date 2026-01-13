package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import utils.WaitUtil;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class BaseStep {

    protected WebDriver driver;
    protected Logger logger = LogManager.getLogger(this.getClass());
    private final WaitUtil waitUtil;
    private final Random random = new Random();
    private static double productPrice;
    private static double basketPrice;

    public BaseStep(WebDriver driver) {
        this.driver = driver;
        this.logger = LogManager.getLogger(this.getClass());
        this.waitUtil = new WaitUtil(driver);
    }

    public void checkPageUrl(String url) {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(url), "Page URL verification failed!");
        logger.info("URL checked: " + url);
    }

    public void checkPageTitle(String title) {
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(title), "Page title verification failed!");
        logger.info("Title checked");
    }

    public void clickElement(By by) {
        for (int i = 0; i < 3; i++) {
            try {
                WebElement element = waitForClickable(by);
                element.click();
                logger.info("Element clicked: " + by);
                return;
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                logger.warn("Click attempt " + (i + 1) + " failed for: " + by + ". Retrying...");
            }
        }
        WebElement element = waitForClickable(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        logger.info("Element clicked JS: " + by);
    }

    public WebElement waitForElement(By by) {
        WebElement element = waitUtil.waitForVisibility(by);
        if (element == null) {
            throw new NoSuchElementException("Element not found: " + by);
        }
        logger.info("Element found: " + by);
        return element;
    }

    public WebElement waitForClickable(By by) {
        WebElement element = waitUtil.waitForClickable(by);
        if (element == null) {
            throw new NoSuchElementException("Element not clickable: " + by);
        }
        logger.info("Element clickable: " + by);
        return element;
    }

    public boolean checkElementVisibility(By by) {
        boolean visible = waitUtil.isElementVisible(by);
        logger.info("Element visible checked: " + by);
        return visible;
    }

    public void clickRandomElement(By by) {
        List<WebElement> elements = waitUtil.waitForElementsVisible(by);
        if (elements.isEmpty()) {
            throw new NoSuchElementException("Elements not found!!: " + by);
        }
        WebElement randomElement = elements.get(random.nextInt(elements.size()));
        moveToAndClick(randomElement);
        logger.info("Random element clicked: " + by);
    }

    public void goReviewsAndRateCheckResult(By reviewContainer, By thumbsUp, By successText) {
        WebElement container = waitForElement(reviewContainer);
        List<WebElement> thumbsUpElements = waitUtil.waitForElementsVisible(thumbsUp);
        if (thumbsUpElements.isEmpty()) {
            logger.warn("Thumbs up not found");
            return;
        }

        WebElement firstThumbsUp = thumbsUpElements.get(0);
        moveToAndClick(firstThumbsUp);
        logger.info("Thumbs up clicked");

        WebElement thankYouSpan = waitForElement(successText);
        Assert.assertTrue(thankYouSpan.getText().trim().contains("Teşekkür"), "Success Text Not Verified");
        logger.info("Success text checked");
    }

    public void switchToNextTab() {
        String mainTab = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainTab)) {
                driver.switchTo().window(handle);
                logger.info("Tab switched");
                return;
            }
        }
        Assert.fail("No new tab found");
    }

    public void sortByLatestReviews(By by) {
        WebElement element = waitForClickable(by);
        moveToAndClick(element);
        logger.info("Sort changed to 'latest reviews'");
    }

    public double getProductPrice(By by) {
        WebElement priceElement = waitForElement(by);
        productPrice = parsePriceWithRegex(priceElement.getText());
        logger.info("Get product price: " + productPrice);
        return productPrice;
    }

    public void addToCart(By addToCartButtonLocator) {
        clickElement(addToCartButtonLocator);
        logger.info("Product added to cart");
    }

    public double getBasketPrice(By by) {
        WebElement priceElement = waitForElement(by);
        basketPrice = parsePriceWithRegex(priceElement.getText());
        logger.info("Get basket price: " + basketPrice);
        return basketPrice;
    }

    public void verifyCartPrice() {
        Assert.assertEquals(basketPrice, productPrice, "Cart price not match!");
        logger.info("Cart price equals");
    }

    private double parsePriceWithRegex(String priceText) {
        String cleaned = priceText.replaceAll("[^0-9,]", "").replace(",", ".");
        return Double.parseDouble(cleaned);
    }

    public void sendText(By by, String text) {
        WebElement element = waitForClickable(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        element.clear();
        element.sendKeys(text);
        logger.info("Text sent input " + text);
    }

    public void sendEnter(By by) {
        WebElement element = waitForClickable(by);
        new Actions(driver).sendKeys(element, Keys.ENTER).perform();
        logger.info("Enter sent input " + by);
    }

    public void comparePriceThenSelectLowPrice(By sellerRowLocator, By sellerPrice, By sellerBasketButton) {
        List<WebElement> sellers = waitUtil.waitForElementsVisible(sellerRowLocator);
        Assert.assertFalse(sellers.isEmpty(), "No sellers found");

        double lowestPrice = Double.MAX_VALUE;
        WebElement lowestButton = null;

        for (WebElement seller : sellers) {
            try {
                WebElement priceElement = seller.findElement(sellerPrice);
                if (priceElement.isDisplayed()) {
                    double price = parsePriceWithRegex(priceElement.getText());
                    if (price < lowestPrice) {
                        lowestPrice = price;
                        lowestButton = seller.findElement(sellerBasketButton);
                    }
                }
            } catch (Exception ignored) {
            }
        }

        Assert.assertNotNull(lowestButton, "No valid seller button");
        moveToAndClick(lowestButton);
        logger.info("Lowest price selected: " + lowestPrice);
    }

    private void moveToAndClick(WebElement element) {
        new Actions(driver).moveToElement(element).perform();
        element.click();
    }
}