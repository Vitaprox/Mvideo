package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import patternWebElement.CartProductsElements;

public class HomePage {

    private final String CART_PRODUCTS_DAY = "//mvid-day-product[contains(@class, 'main')]";

    @FindBy(xpath = "//mvid-day-products-block")
    private SelenideElement dayProductsBlock;

    @FindBy(xpath = "//mvid-simple-product-collection-mp[.//h2[contains(text(), 'Самые просматриваемые')]]")
    private SelenideElement mostViewedBlock;

    @FindBy(xpath = CART_PRODUCTS_DAY + "//button")
    private SelenideElement buttonsDay;

    @FindBy(xpath = CART_PRODUCTS_DAY + "//div[contains(@class, 'title')]/a")
    private SelenideElement nameProductDay;

    @FindBy(xpath = CART_PRODUCTS_DAY + "//span[contains(@class, 'price__main-value')]")
    private SelenideElement priceProductsDay;

    @FindBy(xpath = "//div[contains(@class, 'mp-wrapper')]/*")
    private ElementsCollection mainBlocks;

    @FindBy(xpath = "//h2[contains(text(), 'Самые просматриваемые')]/..//a/div[text() != '']")
    private ElementsCollection mostViewName;

    @FindBy(xpath = "//h2[contains(text(), 'Самые просматриваемые')]/..//span[contains(@class, 'price__main-value')]")
    private ElementsCollection mostViewPrice;

    @FindBy(xpath = "//h2[contains(text(), 'Самые просматриваемые')]/..//mvid-icon[@type='cart']/..")
    private ElementsCollection mostViewBuyButton;

    /**
     * Запомнить в список данные самых просматриваемых товаров
     */
    public void writeProductsMostView() {
        CartProductsElements.createCarts(mostViewName, mostViewPrice, mostViewBuyButton);
    }

    /**
     * Клик по товару из Самых просматриваемых по его номеру
     */
    public void clickProductMostView(int numberProduct) {
        CartProductsElements.products.get(numberProduct - 1).button
                .scrollIntoView("{block: 'center'}")
                .click();
    }

    /**
     * Проверка и ожидание самых просматриваемых товаров
     */
    public void checkMostViewedVisible() {
        mostViewedBlock.shouldBe(Condition.visible);
    }

    public void clickBuyButtonDayProducts() {
        CartProductsElements.products.add(new CartProductsElements(nameProductDay.text(), Integer.parseInt(priceProductsDay.text().replaceAll("\\D+", "")), buttonsDay));
        buttonsDay
                .scrollIntoView("{block: 'center'}")
                .click();
    }

    /**
     * Товары дня отображаются
     */
    public boolean dayProductsIsDisplayed() {
        dayProductsBlock.shouldBe(Condition.visible);
        return dayProductsBlock.isDisplayed();
    }

    /**
     * Пролистываем странцу, чтоб она загрузилась полностью
     */
    public void scrollPage() {
        for (int i = 0; i < mainBlocks.size(); i++) {
            mainBlocks.get(i).scrollIntoView("{block: 'center'}");
        }
    }

}
