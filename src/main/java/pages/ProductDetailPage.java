package pages;

import base.BaseStep;
import org.openqa.selenium.*;

import static pages.HomePage.BASKET_AREA;

public class ProductDetailPage extends BaseStep {

    public static final By PRODUCT_DETAIL_REVIEWS_BUTTON = By.xpath("//button[contains(text(),'Değerlendirmeler')]");
    public static final By REVIEWS_SORT_BUTTON = By.xpath("(//div[.='Varsayılan']//div[1])[2]");
    public static final By REVIEWS_BUTTON_LATEST_EVALUATION = By.xpath("//div[.='En yeni değerlendirme']");
    public static final By PRODUCT_DETAIL_ADD_BASKET_BUTTON = By.xpath("//button[@data-test-id='addToCart']");
    public static final By REVIEW_SUCCESS_TEXT = By.xpath("//span[normalize-space()='Teşekkür Ederiz.']");
    public static final By REVIEW_AREA_CONTAINER = By.xpath("//body");
    public static final By REVIEW_THUMBS_UP_BUTTON = By.xpath("//div[.//text()[normalize-space()='Bu değerlendirme faydalı mı?']]//div[contains(@class,'thumbsUp')]");

    public static final By OTHER_SELLER_AREA = By.xpath("(//div[@data-test-id='other-merchants'])[1]");
    public static final By ALL_OTHER_SELLER_BUTTON = By.xpath("//button[contains(text(),'Tümünü gör')]");

    public static final By OTHER_SELLER_LIST_ITEM = By.cssSelector("div[class*='mnWNji9']");
    public static final By OTHER_SELLER_PRICE = By.cssSelector("[data-test-id='price-current-price']");
    public static final By OTHER_SELLER_BASKET_BUTTON = By.xpath(".//button[contains(text(),'Sepete ekle')]");
    public static final By PRODUCT_DETAIL_PRICE = By.xpath("//div[@data-test-id='default-price']//span");
    public static final By POPUP_GO_BASKET_BUTTON = By.xpath(".//button[contains(text(),'Sepete git')]");

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public void checkProductDetailIsPageOpen() {
        switchToNextTab();
        checkElementVisibility(BASKET_AREA);
        checkElementVisibility(OTHER_SELLER_AREA);
    }

    public void openReviewsTabAndSortByNewest() {
        sortByLatestReviews(PRODUCT_DETAIL_REVIEWS_BUTTON);
        sortByLatestReviews(REVIEWS_SORT_BUTTON);
        sortByLatestReviews(REVIEWS_BUTTON_LATEST_EVALUATION);
    }

    public void goReviewsAndRateCheck() {
        goReviewsAndRateCheckResult(REVIEW_AREA_CONTAINER, REVIEW_THUMBS_UP_BUTTON, REVIEW_SUCCESS_TEXT);
    }

    public void checkOtherSellersArea() {
        waitForElement(OTHER_SELLER_AREA);
        checkElementVisibility(OTHER_SELLER_AREA);
        clickElement(ALL_OTHER_SELLER_BUTTON);
    }

    public void findLowestPriceAndAddBasket() {
        comparePriceThenSelectLowPrice(OTHER_SELLER_LIST_ITEM, OTHER_SELLER_PRICE, OTHER_SELLER_BASKET_BUTTON);
    }

    public void getProductPrice() {
        getProductPrice(PRODUCT_DETAIL_PRICE);
    }

    public void addProductCartAndGoBasket() {
        waitForElement(PRODUCT_DETAIL_ADD_BASKET_BUTTON);
        addToCart(PRODUCT_DETAIL_ADD_BASKET_BUTTON);
        checkElementVisibility(BASKET_AREA);
        clickElement(POPUP_GO_BASKET_BUTTON);
    }

}