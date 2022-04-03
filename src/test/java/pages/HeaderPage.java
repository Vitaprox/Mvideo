package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.executeJavaScript;


import static com.codeborne.selenide.Selenide.$x;

public class HeaderPage {

    private static String XPATH_BUTTON_USING_TITLE = "//mvid-header-icon[.//p[contains(text(), '%s') and contains(@class, 'title')]]";

    @FindBy(xpath = "//a[contains(@class, 'logo') and contains(@href, 'https://www.mvideo.ru/')]")
    private SelenideElement logo;

    @FindBy(xpath = "//a[@href='https://www.mvideo.ru/cart']/mvid-bubble")
    private SelenideElement basketCount;

    @FindBy(xpath = "//mvid-header-icon[@title='Избранное']//mvid-bubble")
    private SelenideElement favoriteCount;

    @FindBy(xpath = "//mvid-header-icon[contains(@title, 'Корзина')]")
    private SelenideElement basketButton;

    @FindBy(xpath = "//input[contains(@class, 'input__field') and @id='1']")
    private SelenideElement searchInput;

    @FindBy(xpath = "//div[contains(@class, 'search-icon-wrap')]")
    private SelenideElement searchButton;

    @FindBy(xpath = "//mvid-header-icon[contains(@tooltiptype, 'profile')]")
    private SelenideElement profileButton;

    @FindBy(xpath = "//div[contains(@class, 'modal-wrapper__content')][.//h2[contains(text(), 'Вход или регистрация')]]")
    private SelenideElement loginForm;

    @FindBy(xpath = "//button[contains(@class, 'login-form-top-nav__btn')]")
    private SelenideElement closeLoginFormButton;

    @FindBy(xpath = "//input[@type='tel']")
    private SelenideElement inputPhone;

    @FindBy(xpath = "//button[contains(@class, 'login-form__button')]")
    private SelenideElement loginButtonContinue;

    @FindBy(xpath = "//button[contains(@class, 'login-form__link')]")
    private SelenideElement entityLinkLoginForm;

    @FindBy(xpath = "//mvid-header-icon[contains(@title, 'Сравнение')]")
    private SelenideElement comparisonProductsButton;

    @FindBy(xpath = "//mvid-header-icon[@buttontext='Сравнить товары']//mvid-bubble")
    private SelenideElement comparisonCount;

    @FindBy(xpath = "//mvid-header-icon[contains(@title, 'Избранное')]")
    private SelenideElement favouriteButton;

    @FindBy(xpath = "//span[contains(@class, 'location-text')]")
    private SelenideElement locationCityText;

    @FindBy(xpath = "//div[contains(@class, 'modal-layout')][.//h3[contains(text(), 'Выберите город')]]")
    private SelenideElement windowСhooseCity;

    /**
     * Можно просто закрыть все мешающиеся окна с добавленными товарами, появляющиеся после кликов после того, как они повятся через js
     */
    public void closeWindowProducts() {
        executeJavaScript("let elements = document.getElementsByClassName('tooltip__item');" +
                "for (let elem of elements) " +
                "elem.style.display = 'none';");
    }

    /**
     * Проверка, что в кнопке выбора города отображаетя нужных город
     */
    public void shouldBeVisibleLocation(String text) {
        locationCityText.shouldBe(Condition.text(text));
    }

    public void checkCloseWindowCity() {
        windowСhooseCity.shouldBe(Condition.hidden);
    }

    public void clickCity(String nameCity) {
        $x("//ul[contains(@class, 'location-select__location-list')]/li[contains(text(), '" + nameCity + "')]")
                .scrollIntoView("{block: 'center'}")
                .click();
    }

    public boolean isDisplayedWindowChooseCity() {
        return windowСhooseCity.isDisplayed();

    }

    public void clickLocationCity() {
        locationCityText
                .scrollIntoView("{block: 'center'}")
                .click();
    }

    public void clickFavoriteButton() {
        favouriteButton
                .scrollIntoView("{block: 'center'}")
                .click();
    }

    public void clickComparisonButton() {
        comparisonProductsButton
                .scrollIntoView("{block: 'center'}")
                .click();
    }

    /**
     * Ожидание нужной циферки товаров в сравнении в хедере
     */
    public void shouldBeComparisonCount(String count) {
        comparisonCount.shouldBe(Condition.text(count));
    }

    /**
     * Ожидание нужной циферки товаров в избраных товарах в хедере
     */
    public void shouldBeFavoriteCount(String count) {
        favoriteCount.shouldBe(Condition.text(count));
    }

    /**
     * Проверка, что ссылка для юр лиц отображается
     */
    public boolean checkEntityFormIIsDisplayed() {
        return entityLinkLoginForm.isDisplayed();
    }

    public boolean checkNotActiveLoginButton() {
        return Boolean.parseBoolean(loginButtonContinue.getAttribute("disabled"));
    }

    public boolean checkInputPhoneIsDisplayed() {
        return inputPhone.isDisplayed();
    }

    public boolean checkCloseButtonIsDisplayed() {
        return closeLoginFormButton.isDisplayed();
    }

    public void checkLoginFormIsVisible() {
        loginForm.shouldBe(Condition.visible);
    }

    public void clickProfileButton() {
        profileButton
                .scrollIntoView("{block: 'center'}")
                .click();
    }

    /**
     * Печатаем текст в поисковой строке
     */
    public void typingSearchInput(String text) {
        searchInput.setValue(text);
    }

    /**
     * Проверка текста, который напечатали в поиске
     */
    public String checkSearchInputValue() {
        return searchInput.getValue();
    }

    public void clickSearchButton() {
        searchButton
                .scrollIntoView("{block: 'center'}")
                .click();
    }

    public void checkSearchIsVisible() {
        searchInput.shouldBe(Condition.visible);
    }

    /**
     * Ожидание нужной циферки товаров в корзине хедера
     */
    public void shouldBeBasketCount(String count) {
        basketCount.shouldBe(Condition.text(count));
    }

    /**
     * Получаем циферку товаров в корзине хедера в int
     */
    public int getBasketCountInt() {
        return Integer.parseInt(basketCount.text());
    }


    public void clickBasketButton() {
        basketButton
                .scrollIntoView("{block: 'center'}")
                .click();
    }

    /**
     * Проверка отображается ли кнопка шапки
     */
    public boolean buttonIsDisplayed(String buttonText) {
        return $x(String.format(XPATH_BUTTON_USING_TITLE, buttonText)).isDisplayed();
    }

    /**
     * Проверка активна ли кнопка шапки
     */
    public boolean buttonIsActive(String buttonText) {
        String classes = $x(String.format(XPATH_BUTTON_USING_TITLE, buttonText)).getAttribute("class");
        boolean x = true;
        if (classes.contains("disabled")) x = false;
        return x;
    }

}