package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import patternWebElement.CartProductsElements;

import static com.codeborne.selenide.Selenide.$x;

public class BasketPage {
    @FindBy(xpath = "//span[contains(@class, 'c-header-checkout__logo') and contains(text(), 'Моя корзина')]")
    private SelenideElement basketTitle;

    @FindBy(xpath = "//input[contains(@value,'Перейти к оформлению')]")
    private SelenideElement setAnOrderButton;

    @FindBy(xpath = "//div[contains(@class, 'c-orders-list__content')]//span[contains(@class, 'c-cost-line__title')]")
    private SelenideElement numberOfProducts;

    @FindBy(xpath = "//span[contains(@class, 'c-cart-item__price')]")
    private SelenideElement cartPrice;

    @FindBy(xpath = "//span[contains(@class, 'c-cost-line__text')]")
    private SelenideElement orderPrice;

    @FindBy(xpath = "//a[contains(@class, 'c-cart-item__title')]")
    private ElementsCollection cartsTitle;

    /**
     * Получить цену одного товара в заказе цифрой
     */
    public int getCartPriceInt() {
        return Integer.parseInt(cartPrice
                .text()
                .replaceAll("\\D+", ""));
    }


    /**
     * Получить цену всего заказа цифрой
     */
    public int getOrderPriceInt() {
        return Integer.parseInt(orderPrice
                .text()
                .replaceAll("\\D+", ""));
    }

    /**
     * Получить количество товаров в корзине
     */
    public String getNumberOfProductsText() {
        return numberOfProducts.text();
    }

    /**
     * Кнопка оформить заказ отображается
     */
    public boolean checkSetAnOrderButtonIsDisplayed() {
        return setAnOrderButton.isDisplayed();
    }

    /**
     * Проверка, что товар с нужным названием отображается
     */
    public boolean checkCartProductIsDisplayed(String nameProduct) {
        return $x("//div[contains(@class, 'c-cart__order')][.//a[contains(text(), '" + nameProduct + "')]]").isDisplayed();
    }

    /**
     * Проверка те ли мы товары оказались в нашей корзине,что мы доабавили
     */
    public boolean checkNamesProductsInBasket() {
        boolean flag = false;
        int count = 0;
        for (int i = 0; i < cartsTitle.size(); i++) {
            if (cartsTitle.get(i)
                    .text()
                    .equals(CartProductsElements.products.get(i).name)) count++;
        }
        if (count == cartsTitle.size()) {
            flag = true;
        }

        return flag;
    }

    /**
     * Проверка заголовка корзины
     */
    public boolean checkBasketTitle() {
        return basketTitle.isDisplayed();
    }
}
