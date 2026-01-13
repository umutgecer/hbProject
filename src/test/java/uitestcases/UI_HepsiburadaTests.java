package uitestcases;

import base.BaseTest;
import org.testng.annotations.*;
import pages.BasketPage;
import pages.ProductDetailPage;
import pages.HomePage;

public class UI_HepsiburadaTests extends BaseTest {

    private HomePage homePage;
    private ProductDetailPage productDetailPage;
    private BasketPage basketPage;

    @BeforeMethod
    public void initPages() {
        homePage = new HomePage(driver);
        productDetailPage = new ProductDetailPage(driver);
        basketPage = new BasketPage(driver);
    }

    @Test(description = "Go Popular Product Then Check Reviews")
    public void case1_Check_Product_Reviews() {
        homePage.searchProductAndGoToDetails();
        productDetailPage.checkProductDetailIsPageOpen();
        productDetailPage.openReviewsTabAndSortByNewest();
        productDetailPage.goReviewsAndRateCheck();
    }

    @Test(description = "Check Other Seller Price List")
    public void case2_Check_Other_Seller_Price_List() {
        homePage.searchProductAndGoToDetails();
        productDetailPage.checkProductDetailIsPageOpen();
        productDetailPage.checkOtherSellersArea();
        productDetailPage.findLowestPriceAndAddBasket();
    }

    @Test(description = "Check Compare Price Between Product Detail And Basket")
    public void case3_Check_Compare_Price_Between_Product_Detail_And_Basket() {
        homePage.searchProductAndGoToDetails();
        productDetailPage.checkProductDetailIsPageOpen();
        productDetailPage.getProductPrice();
        productDetailPage.addProductCartAndGoBasket();
        basketPage.checkBasketPrice();
    }

}