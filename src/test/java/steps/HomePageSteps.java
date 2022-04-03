package steps;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.HeaderPage;
import pages.HomePage;
import patternWebElement.CartProductsElements;


public class HomePageSteps {

    private HeaderPage headerPage = Selenide.page(new HeaderPage());
    private HomePage homePage = Selenide.page(new HomePage());

    @Step("Проверка отображется ли блок Товары дня")
    public void checkDayProductsIsDisplayed() {
        boolean displayProdDay = homePage.dayProductsIsDisplayed();
        Assert.assertTrue(displayProdDay, "Товары дня не отображаются");
    }

    @Step("Клик по кнопке купить у товара и проверка клика")
    public void clickBuyButtonDayProducts() {
        CartProductsElements.clearCartList();
        homePage.clickBuyButtonDayProducts();
        headerPage.shouldBeBasketCount("1");
    }

    @Step("Прокрутка страницы и проверка отображения самых просматриваемых товаров")
    public void scrollHomePage() {
        homePage.scrollPage();
        homePage.checkMostViewedVisible();
    }


    @Step("Клики по товарам и проверки, что клик прошел")
    public void clickMostViewProducts() {
        CartProductsElements.clearCartList();
        homePage.writeProductsMostView();
        homePage.clickProductMostView(1);
        headerPage.shouldBeBasketCount("1");
        headerPage.closeWindowProducts();
        homePage.clickProductMostView(2);
        headerPage.shouldBeBasketCount("2");
    }
}
