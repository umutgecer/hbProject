package pages;

import base.BaseStep;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.JsonReader;

public class HomePage extends BaseStep {

    public static final By BASKET_AREA = By.id("shoppingCart");
    public static final By COOKIES_ACCEPT_BUTTON = By.id("onetrust-accept-btn-handler");
    public static final By HB_PREMIUM_LOGO = By.xpath("//div[@data-test-id='nonPremium-logo']");
    public static final By SEARCH_BOX_INPUT = By.xpath("//input[@data-test-id='search-bar-input']");
    public static final By SEARCH_RESULT_PRODUCT_LIST = By.cssSelector("li[class*='productListContent']");

    public static final String URL = "https://www.hepsiburada.com/";
    public static final String PAGE_HEAD_TITLE = "Türkiye'nin En Çok Tavsiye Edilen E-ticaret Markası Hepsiburada";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void checkHomePageIsOpened() {
        clickElement(COOKIES_ACCEPT_BUTTON);
        checkPageUrl(URL);
        checkPageTitle(PAGE_HEAD_TITLE);
        waitForElement(HB_PREMIUM_LOGO);
        waitForElement(BASKET_AREA);
    }

    public void searchPopularProductInSearchBox() {
        String keyword = JsonReader.getSearchKeyword();

        clickElement(SEARCH_BOX_INPUT);
        sendText(SEARCH_BOX_INPUT, keyword);
        sendEnter(SEARCH_BOX_INPUT);
    }

    public void selectRandomProductInList() {
        clickRandomElement(SEARCH_RESULT_PRODUCT_LIST);
    }

    public void searchProductAndGoToDetails() {
        checkHomePageIsOpened();
        searchPopularProductInSearchBox();
        selectRandomProductInList();
    }

}