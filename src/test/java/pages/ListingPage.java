package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import patternWebElement.CartProductsElements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$x;

public class ListingPage {

    @FindBy(xpath = "//mvid-dropdown[@icontype='sort']")
    private SelenideElement sortDropdown;

    @FindBy(xpath = "//mvid-dropdown[@icontype='sort']//div[contains(@class, 'dropdown__options')]")
    private SelenideElement sortDropdownOptions;

    @FindBy(xpath = "//a[contains(@class, 'product-title__text')]")
    private ElementsCollection nameProducts;

    @FindBy(xpath = "//span[contains(@class, 'price__main-value')]")
    private ElementsCollection priceProducts;

    @FindBy(xpath = "//button/mvid-icon[@type='histogram']")
    private ElementsCollection comparisonProdButton;

    @FindBy(xpath = "//button/mvid-icon[@type='love']")
    private ElementsCollection favoriteProdButton;

    @FindBy(xpath = "//div[contains(@class, 'product-cards-row')]")
    private ElementsCollection rowBlocks;


    /**
     * Прокрутка всей страницы, чтоб загрузились все товары
     */
    public void scrollRowBlocks() {
        nameProducts.get(0).shouldBe(Condition.visible);
        for (SelenideElement el : rowBlocks) {
            el.scrollIntoView("{block: 'center'}");
        }
    }

    /**
     * Найти нижнюю строку товаров и проверить, что товары в ней загрузились
     */
    public void lastRowShouldBeVisible() {
        rowBlocks
                .last()
                .find(".product-card__title-line")
                .shouldBe(Condition.visible);
    }

    /**
     * Записываем в список товаров данные товаров для дальнешего использования
     */
    public void writeCartProductsFavorite() {
        nameProducts.get(0).shouldBe(Condition.visible);
        CartProductsElements.createCarts(nameProducts, priceProducts, favoriteProdButton);
    }

    /**
     * Записываем в список товаров данные товаров для дальнешего использования
     */
    public void writeCartProductsComparison() {
        nameProducts.get(0).shouldBe(Condition.visible);
        CartProductsElements.createCarts(nameProducts, priceProducts, comparisonProdButton);
    }

    /**
     * Проверка, что кнопка сравнения/понравилось активировалась после нажатия
     */
    public void shouldBeIconButtonActive(int numberProduct) {
        CartProductsElements.products.get(numberProduct - 1).button.shouldBe(Condition.cssClass("active"));
    }

    /**
     * Клик на кнопку сравнения/понравилось у товара
     */
    public void clickProductIcon(int numberProduct) {
        CartProductsElements.products.get(numberProduct - 1).button.scrollIntoView("{block: 'center'}").click();
    }

    /**
     * Проверка, что опции сортировки отображаются
     */
    public void checkSortDropdownOptionsIsVisible() {
        sortDropdownOptions.shouldBe(Condition.visible);
    }

    /**
     * Проверка что в назавнии товаров есть нужная нам подстрока
     */
    public boolean checkContainsInNameProd(String text) {
        boolean flag = true;

        for (SelenideElement el : nameProducts) {
            if (!el
                    .text()
                    .toLowerCase()
                    .contains(text.toLowerCase())) flag = false;
        }
        return flag;
    }

    /**
     * Проверка сортировки по убыванию
     */
    public boolean checkSortPriceDecrease() {
        boolean flag = false;
        ArrayList<Integer> prices = new ArrayList<>();
        ArrayList<Integer> pricesSorted = new ArrayList<>();
        int priceProd;
        for (SelenideElement el : priceProducts) {
            priceProd = Integer
                    .parseInt(el
                            .text()
                            .replaceAll("\\D+", ""));
            prices.add(priceProd);
            pricesSorted.add(priceProd);
        }
        Collections.sort(pricesSorted, Collections.reverseOrder());

        if (prices.equals(pricesSorted)) flag = true;
        return flag;
    }

    /**
     * Клик по опции сортировки (строке из выпадающего списка сортировки)
     */
    public void clickSortDropdownOption(String nameOption) {
        $x("//mvid-dropdown[@icontype='sort']//div[contains(@class, 'dropdown__option') and contains(text(), '" + nameOption + "')]").scrollIntoView("{block: 'center'}").click();
    }

    public void clickSortDropdown() {
        sortDropdown
                .scrollIntoView("{block: 'center'}")
                .click();
    }

    public boolean checkSortDropdownIdDisplayed() {
        return sortDropdown.isDisplayed();
    }

    /**
     * Возвращает название текущей сортировки товаров
     */
    public String getTextSortDropdown() {
        return sortDropdown.text();
    }

    /**
     * Проверка и ожидание, что товары загрузились
     */
    public void checkProductsIsVisible() {
        nameProducts.get(0).shouldBe(Condition.visible);
    }
}
