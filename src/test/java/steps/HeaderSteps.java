package steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.*;


public class HeaderSteps {

    private HeaderPage headerPage = Selenide.page(new HeaderPage());
    private ListingPage listingPage = Selenide.page(new ListingPage());
    private ComparisonPage comparisonPage = Selenide.page(new ComparisonPage());
    private FavoritePage favoritePage = Selenide.page(new FavoritePage());


    @Step("Проверка, что кнопка Статус заказа активна")
    public void checkThatStatusButtonIsActive() {
        boolean activityStatus = headerPage.buttonIsActive("Статус заказа");
        Assert.assertTrue(activityStatus, "Кнопка статуса заказа неактивна");
    }

    @Step("Проверка, что кнопка Статус заказа отображается")
    public void checkThatStatusButtonIsDisplayed() {
        boolean displayedStatus = headerPage.buttonIsDisplayed("Статус заказа");
        Assert.assertTrue(displayedStatus, "Кнопка статуса заказа не отображается");
    }

    @Step("Проверка, что кнопка Войти активна")
    public void checkThatProfileButtonIsActive() {
        boolean activityProfile = headerPage.buttonIsActive("Войти");
        Assert.assertTrue(activityProfile, "Кнопка профиля неактивна");
    }

    @Step("Проверка, что кнопка Войти отображается")
    public void checkThatProfileButtonIsDisplayed() {
        boolean displayedProfile = headerPage.buttonIsDisplayed("Войти");
        Assert.assertTrue(displayedProfile, "Кнопка профиля не отображается");
    }

    @Step("Проверка, что кнопка сравнения товаров неактивна")
    public void checkThatComparisonButtonIsNotActive() {
        boolean activityComparison = headerPage.buttonIsActive("Сравнение");
        Assert.assertFalse(activityComparison, "Кнопка сравнения неактивна");
    }

    @Step("Проверка, что кнопка сравнения товаров отображается")
    public void checkThatComparisonButtonIsDisplayed() {
        boolean displayedComparison = headerPage.buttonIsDisplayed("Сравнение");
        Assert.assertTrue(displayedComparison, "Кнопка сравнения не отображается");
    }

    @Step("Проверка, что кнопка избранных товаров неактивна")
    public void checkThatFavouritesButtonIsNotActive() {
        boolean activityFavorite = headerPage.buttonIsActive("Избранное");
        Assert.assertFalse(activityFavorite, "Кнопка избранного неактивна");
    }

    @Step("Проверка, что кнопка избранных товаров отображается")
    public void checkThatFavouritesButtonIsDisplayed() {
        boolean displayedFavorite = headerPage.buttonIsDisplayed("Избранное");
        Assert.assertTrue(displayedFavorite, "Кнопка избранного не отображается");
    }

    @Step("Проверка, что кнопка корзины неактивна")
    public void checkThatBasketButtonIsNotActive() {
        boolean activityBasket = headerPage.buttonIsActive("Корзина");
        Assert.assertFalse(activityBasket, "Кнопка корзины неактивна");
    }

    @Step("Проверка, что кнопка корзины отображается")
    public void checkThatBasketButtonIsDisplayed() {
        boolean displayedBasket = headerPage.buttonIsDisplayed("Корзина");
        Assert.assertTrue(displayedBasket, "Кнопка корзины не отображается");
    }

    @Step("Проверка правильная ли цифра отображается у корзины после добавления товара в корзину")
    public void checkBasketCount() {
        int actualBasketCount = headerPage.getBasketCountInt();
        int expectedBasketCount = 1;
        Assert.assertEquals(actualBasketCount, expectedBasketCount, "Цифра у корзины не соотвтствует действительности");
    }

    @Step("Проверка получилось ли кликнуть на кнопку корзины и перейти на её страницу")
    public void clickBasketButton() {
        headerPage.clickBasketButton();
        String expectedUrl = "https://www.mvideo.ru/cart";
        String actualUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl, "Моя корзина не открылась");
    }

    @Step("Проверка инпута поиска на отображение")
    public void checkSearchIsVisible() {
        headerPage.checkSearchIsVisible();
    }

    @Step("Проверка, что мы напечатали именно слово apple")
    public void typingAppleSearchInput() {
        headerPage.typingSearchInput("apple");
        String actualSearchInputValue = headerPage.checkSearchInputValue();
        String expectedSearchInputValue = "apple";
        Assert.assertEquals(actualSearchInputValue, expectedSearchInputValue, "В поисковую строку был введен другой текст");
    }

    @Step("Клик по кнопке поиска и проверка, что товары, которые мы ищем загрузились")
    public void clickSearchButton() {
        headerPage.clickSearchButton();
        listingPage.checkProductsIsVisible();
        String expectedUrl = "https://www.mvideo.ru/product-list-page?q=apple&category=smartfony-205";
        String actualUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Step("Клик на нопку профиля и проверка открылось ли окно")
    public void clickProfileButton() {
        headerPage.clickProfileButton();
        headerPage.checkLoginFormIsVisible();
    }

    @Step("Проверка, что кнопка закрытия формы авторизации отображается")
    public void checkCloseButtonIsDisplayed() {
        boolean displayCloseButton = headerPage.checkCloseButtonIsDisplayed();
        Assert.assertTrue(displayCloseButton, "Кнопка закрытия формы авторизации не отображается");
    }

    @Step("Проверка, что ипнут для телефона отображается")
    public void checkInputPhoneIsDisplayed() {
        boolean displayInputPhone = headerPage.checkInputPhoneIsDisplayed();
        Assert.assertTrue(displayInputPhone, "Инпут для ввода телефона не отображается");
    }

    @Step("Проверка, что кнопка продолжить в авторизации неактивна, если не введен номер")
    public void checkActiveLoginButton() {
        boolean notActiveLoginButton = headerPage.checkNotActiveLoginButton();
        Assert.assertTrue(notActiveLoginButton, "Кнопка продолжить активна, хотя не должна");
    }

    @Step("Проверка, что ссылка для юр лиц в авторизации отображается")
    public void checkEntityFormIIsDisplayed() {
        boolean displayLink = headerPage.checkEntityFormIIsDisplayed();
        Assert.assertTrue(displayLink, "Ссылка для юр лиц не отображается");
    }

    @Step("Клик по кнопке сравнения, проверка отображается ли заголовок и url")
    public void clickComparisonButton() {
        headerPage.clickComparisonButton();
        comparisonPage.checkComparisonTitleIsVisible();
        String expectedUrl = "https://www.mvideo.ru/product-comparison";
        String actualUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        boolean entry = actualUrl.contains(expectedUrl);
        Assert.assertTrue(entry, "Открылась не та страница");
    }

    @Step("Клик по кнопке избранного, проверка отображается ли заголовок и url")
    public void clickFavoriteButton() {
        headerPage.clickFavoriteButton();
        favoritePage.checkFavoriteIsVisible();
        String expectedUrl = "https://www.mvideo.ru/wish-list";
        String actualUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        boolean entry = actualUrl.contains(expectedUrl);
        Assert.assertTrue(entry, "Открылась не та страница");
    }

    @Step("Клик по кнопке города и проверка, что открылось окно после клика")
    public void clickLocationCity() {
        headerPage.clickLocationCity();
        boolean displayWindowCity = headerPage.isDisplayedWindowChooseCity();
        Assert.assertTrue(displayWindowCity, "Окно с выбором города не отображается");
    }

    @Step("Клик по городу Краснодар в окне выбора города, проверка, что окно закрылось")
    public void chooseCity() {
        headerPage.clickCity("Краснодар");
        headerPage.checkCloseWindowCity();
    }

    @Step("Проверка, что хедере отображается нужный город")
    public void checkLocationCityText() {
        headerPage.shouldBeVisibleLocation("Краснодар");
    }

}
