package steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;
import pages.*;

import static io.netty.util.internal.SystemPropertyUtil.contains;


public class HeaderSteps {

    private HeaderPage headerPage = Selenide.page(new HeaderPage());
    private BasketPage basketPage = Selenide.page(new BasketPage());
    private ListingPage listingPage = Selenide.page(new ListingPage());
    private ComparisonPage comparisonPage = Selenide.page(new ComparisonPage());
    private FavoritePage favoritePage = Selenide.page(new FavoritePage());


    public void checkThatStatusButtonIsActive() {
        boolean activityStatus = headerPage.buttonIsActive("Статус заказа");
        Assert.assertTrue(activityStatus, "Кнопка статуса заказа неактивна");
    }

    public void checkThatStatusButtonIsDisplayed() {
        boolean displayedStatus = headerPage.buttonIsDisplayed("Статус заказа");
        Assert.assertTrue(displayedStatus, "Кнопка статуса заказа не отображается");
    }

    public void checkThatProfileButtonIsActive() {
        boolean activityProfile = headerPage.buttonIsActive("Войти");
        Assert.assertTrue(activityProfile, "Кнопка профиля неактивна");
    }

    public void checkThatProfileButtonIsDisplayed() {
        boolean displayedProfile = headerPage.buttonIsDisplayed("Войти");
        Assert.assertTrue(displayedProfile, "Кнопка профиля не отображается");
    }

    public void checkThatComparisonButtonIsNotActive() {
        boolean activityComparison = headerPage.buttonIsActive("Сравнение");
        Assert.assertFalse(activityComparison, "Кнопка сравнения неактивна");
    }

    public void checkThatComparisonButtonIsDisplayed() {
        boolean displayedComparison = headerPage.buttonIsDisplayed("Сравнение");
        Assert.assertTrue(displayedComparison, "Кнопка сравнения не отображается");
    }

    public void checkThatFavouritesButtonIsNotActive() {
        boolean activityFavorite = headerPage.buttonIsActive("Избранное");
        Assert.assertFalse(activityFavorite, "Кнопка избранного неактивна");
    }

    public void checkThatFavouritesButtonIsDisplayed() {
        boolean displayedFavorite = headerPage.buttonIsDisplayed("Избранное");
        Assert.assertTrue(displayedFavorite, "Кнопка избранного не отображается");
    }

    public void checkThatBasketButtonIsNotActive() {
        boolean activityBasket = headerPage.buttonIsActive("Корзина");
        Assert.assertFalse(activityBasket, "Кнопка корзины неактивна");
    }

    public void checkThatBasketButtonIsDisplayed() {
        boolean displayedBasket = headerPage.buttonIsDisplayed("Корзина");
        Assert.assertTrue(displayedBasket, "Кнопка корзины не отображается");
    }

    /**Проверка правильная ли цифра отображается у корзины после добавления товара в корзину*/
    public void checkBasketCount(){
        int actualBasketCount = headerPage.getBasketCountInt();
        int expectedBasketCount = 1;
        Assert.assertEquals(actualBasketCount,expectedBasketCount, "Цифра у корзины не соотвтствует действительности");
    }

    /**Проверка получилось ли кликнуть и перейти на страницу корзины*/
    public void clickBasketButton(){
        headerPage.clickBasketButton();
        String expectedUrl = "https://www.mvideo.ru/cart";
        String actualUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl, "Моя корзина не открылась");
    }

    public void checkSearchIsDisplayed(){
        boolean displayedSearch = headerPage.checkSearchIsDisplayed();
        Assert.assertTrue(displayedSearch, "Строка поиска не отображается");
    }

    public void typingAppleSearchInput(){
        headerPage.typingSearchInput("apple");
        String actualSearchInputValue = headerPage.checkSearchInputValue();
        String expectedSearchInputValue = "apple";
        Assert.assertEquals(actualSearchInputValue, expectedSearchInputValue, "В поисковую строку был введен другой текст");
    }

    public void clickSearchButton(){
        headerPage.clickSearchButton();
        listingPage.checkProductsIsVisible();
        String expectedUrl = "https://www.mvideo.ru/product-list-page?q=apple&category=smartfony-205";
        String actualUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    /**Клик на нопку профиля и проверка открылось ли окно*/
    public void clickProfileButton(){
        headerPage.clickProfileButton();
        headerPage.checkLoginFormIsVisible();
    }

    public void checkCloseButtonIsDisplayed(){
        boolean displayCloseButton = headerPage.checkCloseButtonIsDisplayed();
        Assert.assertTrue(displayCloseButton, "Кнопка закрытия формы авторизации не отображается");
    }

    public void checkInputPhoneIsDisplayed(){
        boolean displayInputPhone = headerPage.checkInputPhoneIsDisplayed();
        Assert.assertTrue(displayInputPhone, "Инпут для ввода телефона не отображается");
    }

    public void checkActiveLoginButton(){
        boolean notActiveLoginButton = headerPage.checkNotActiveLoginButton();
        Assert.assertTrue(notActiveLoginButton, "Кнопка продолжить активна, хотя не должна");
    }

    public void checkEntityFormIIsDisplayed(){
        boolean displayLink = headerPage.checkEntityFormIIsDisplayed();
        Assert.assertTrue(displayLink, "Ссылка для юр лиц не отображается");
    }

    public void clickComparisonButton(){
        headerPage.clickComparisonButton();
        comparisonPage.checkComparisonTitleIsVisible();
        String expectedUrl = "https://www.mvideo.ru/product-comparison";
        String actualUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        boolean entry = actualUrl.contains(expectedUrl);
        Assert.assertTrue(entry, "Открылась не та страница");
    }

    public void clickFavoriteButton(){
        headerPage.clickFavoriteButton();
        favoritePage.checkFavoriteIsVisible();
        String expectedUrl = "https://www.mvideo.ru/wish-list";
        String actualUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        boolean entry = actualUrl.contains(expectedUrl);
        Assert.assertTrue(entry, "Открылась не та страница");
    }

    public void clickLocationCity(){
        headerPage.clickLocationCity();
        boolean displayWindowCity = headerPage.isDisplayedWindowChooseCity();
        Assert.assertTrue(displayWindowCity, "Окно с выбором города не отображается");
    }

    public void chooseCity(){
        headerPage.clickCity("Краснодар");
        headerPage.checkCloseWindowCity();
    }


    public void checkLocationCityText(){
        headerPage.shouldBeVisibleLocation("Краснодар");
    }

}
