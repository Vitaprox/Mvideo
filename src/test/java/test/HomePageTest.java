package test;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import steps.HeaderSteps;
import steps.HomePageSteps;

import static com.codeborne.selenide.Selenide.*;

public class HomePageTest {
    private HeaderSteps headerSteps = new HeaderSteps();
    private HomePageSteps homePageSteps = new HomePageSteps();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверяем активны ли видны и активны ли кнопки хедера: Статус заказа, Войти, Сравнения, Понравившиеся и Корзина")
    public void checkHeaderButtonsActiveAndDisplayed(){
        //Configuration.holdBrowserOpen = true;
        open("https://www.mvideo.ru/");
        headerSteps.checkThatStatusButtonIsActive();
        headerSteps.checkThatStatusButtonIsDisplayed();
        headerSteps.checkThatProfileButtonIsActive();
        headerSteps.checkThatProfileButtonIsDisplayed();
        headerSteps.checkThatComparisonButtonIsNotActive();
        headerSteps.checkThatComparisonButtonIsDisplayed();
        headerSteps.checkThatFavouritesButtonIsNotActive();
        headerSteps.checkThatFavouritesButtonIsDisplayed();
        headerSteps.checkThatBasketButtonIsNotActive();
        headerSteps.checkThatBasketButtonIsDisplayed();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверяем, активируется ли кнопка корзины, когда в неё добавляется товар")
    public void checkActivationBasketHeader(){
//        Configuration.holdBrowserOpen = true;
        Configuration.pageLoadTimeout = 60000;
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "firefox";
        open("https://www.mvideo.ru/");
        homePageSteps.checkDayProductsIsDisplayed();
        homePageSteps.clickBuyButtonDayProducts(); /**Тут же проверка на отображение корзины (подтвержение, что сработал клик)*/
        headerSteps.checkBasketCount();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверяем работает ли окно авторизации и активные в нем кнопки и ссылки")
    public void checkAuthorisationWindow(){
        open("https://www.mvideo.ru/");
        headerSteps.clickProfileButton(); /**Там же проверка открылось ли окно с заголовком Вход или регистрация*/
        headerSteps.checkCloseButtonIsDisplayed();
        headerSteps.checkInputPhoneIsDisplayed();
        headerSteps.checkActiveLoginButton();
        headerSteps.checkEntityFormIIsDisplayed();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверяем окно изменения города, выбираем другой город")
    public void checkEditCity(){
        //Configuration.holdBrowserOpen = true;
        open("https://www.mvideo.ru/");
        headerSteps.clickLocationCity(); /**Тут же проверка открылось ли окно*/
        headerSteps.chooseCity(); /**Тут же проверка закрылось ли окно*/
        headerSteps.checkLocationCityText();
    }

    @AfterMethod
    public void close(){
        closeWebDriver();
    }

}
