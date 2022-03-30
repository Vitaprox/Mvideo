package test;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import steps.BasketPageSteps;
import steps.HeaderSteps;
import steps.HomePageSteps;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BasketPageTest {
    private HeaderSteps headerSteps = new HeaderSteps();
    private HomePageSteps homePageSteps = new HomePageSteps();
    private BasketPageSteps basketPageSteps = new BasketPageSteps();


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Добавляем один товар, заходим в корзину и смотрим добавилось ли")
    public void transitionBasket(){
        //Configuration.holdBrowserOpen = true;
        Configuration.browser = "firefox";
        Configuration.browserSize = "1920x1080";
        open("https://www.mvideo.ru/");
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
        //Configuration.holdBrowserOpen = true;
        open("https://www.mvideo.ru/");
        homePageSteps.scrollHomePage();/**Тут же проверка на отображение Самых просматриваемых*/
        homePageSteps.clickMostViewProducts();
        headerSteps.clickBasketButton();
        basketPageSteps.checkNamesProductsInBasket();
        basketPageSteps.checkSumOrder();
    }

    @AfterMethod
    public void close(){
        closeWebDriver();
    }
}
