package steps;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.BasketPage;
import patternWebElement.CartProductsElements;

public class BasketPageSteps {
    private BasketPage basketPage = Selenide.page(new BasketPage());

    @Step("Сверяем товар на текущей страницей с названием того товара, у которого нажимали кнопку купить")
    public void checkProductInBasket() {
        boolean sameProductsInBasket = basketPage.checkCartProductIsDisplayed(CartProductsElements.products.get(0).name);
        Assert.assertTrue(sameProductsInBasket, "В корзине не тот товар");
    }

    @Step("Проверка отображается ли кнопка оформления заказа")
    public void checkOrderButtonIsDisplayed() {
        boolean ButtonIsDisplayed = basketPage.checkSetAnOrderButtonIsDisplayed();
        Assert.assertTrue(ButtonIsDisplayed, "Кнопка заказа не отображается");
    }

    @Step("Проверка, что в корзине один товар")
    public void checkNumberOfProducts() {
        String actualNumber = basketPage.getNumberOfProductsText();
        String expectedNumber = "В корзине 1 товар";
        Assert.assertEquals(actualNumber, expectedNumber, "Другое количество товара в корзине");
    }

    @Step("Проверка равна ли сумма одного товара и сумма заказа")
    public void checkPriceBasket() {
        int productHomePrice = CartProductsElements.products.get(0).price;
        int productInBasketPrice = basketPage.getCartPriceInt();
        int orderPrice = basketPage.getOrderPriceInt();
        Assert.assertEquals(productHomePrice, productInBasketPrice, orderPrice, "Сумма товара, на который кликнули, того который в карточке и заказа не равны");
    }

    @Step("Проверка те ли товары попали в корзину, на которые мы кликнули")
    public void checkNamesProductsInBasket() {
        boolean sameProductsInBasket = basketPage.checkNamesProductsInBasket();
        Assert.assertTrue(sameProductsInBasket, "Товары в корзине не те, что мы выбрали");
    }

    @Step("Проверка ровняется ли сумма стоимости всех товаров цене заказа")
    public void checkSumOrder() {
        int priceAddedProducts = CartProductsElements.products.get(0).price + CartProductsElements.products.get(1).price;
        int orderPrice = basketPage.getOrderPriceInt();
        Assert.assertEquals(priceAddedProducts, orderPrice, "Ошибка в сумме заказа");
    }

    @Step("Проверка есть ли нас странице заголовок 'Моя корзина'")
    public void checkBasketTitle() {
        boolean checkBasketTitleIdDisplayed = basketPage.checkBasketTitle();
        Assert.assertTrue(checkBasketTitleIdDisplayed, "Заголовок 'Моя корзина' не отображается. Возможно из-за того, что в корзине не было товаров на момент её открытия");
    }
}
