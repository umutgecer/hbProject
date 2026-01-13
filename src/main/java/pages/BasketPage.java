package pages;

import base.BaseStep;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasketPage extends BaseStep {
    public static final By BASKET_PRICE = By.id("basket_payedPrice");

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public void checkBasketPrice() {
        getBasketPrice(BASKET_PRICE);
        verifyCartPrice();
    }
}