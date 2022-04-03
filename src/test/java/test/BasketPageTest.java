package test;

import hooks.Hooks;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listenersMethods.CustomListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import steps.BasketPageSteps;
import steps.HeaderSteps;
import steps.HomePageSteps;

@Listeners(CustomListener.class)
public class BasketPageTest extends Hooks {
    private HeaderSteps headerSteps = new HeaderSteps();
    private HomePageSteps homePageSteps = new HomePageSteps();
    private BasketPageSteps basketPageSteps = new BasketPageSteps();


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Добавляем один товар, заходим в корзину и смотрим добавилось ли")
    public void transitionBasket(){
        homePageSteps.checkDayProductsIsDisplayed();
        homePageSteps.clickBuyButtonDayProducts();
        headerSteps.clickBasketButton();
        basketPageSteps.checkBasketTitle();
        basketPageSteps.checkProductInBasket();
        basketPageSteps.checkOrderButtonIsDisplayed();
        basketPageSteps.checkNumberOfProducts();
        basketPageSteps.checkPriceBasket();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Добавляем два товара, заходим в корзину и смотрим добавилось ли, сверяем цены")
    public void checkAddTwoProductsInBasket(){
        homePageSteps.scrollHomePage();/**Тут же проверка на отображение Самых просматриваемых*/
        homePageSteps.clickMostViewProducts();
        headerSteps.clickBasketButton();
        basketPageSteps.checkNamesProductsInBasket();
        basketPageSteps.checkSumOrder();
    }

}
