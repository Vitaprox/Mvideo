package steps;

import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import pages.HeaderPage;
import pages.HomePage;
import patternWebElement.CartProductsElements;


public class HomePageSteps {

    private HeaderPage headerPage = Selenide.page(new HeaderPage());
    private HomePage homePage = Selenide.page(new HomePage());

    /**Проверка отображется ли блок Товары дня*/
    public void checkDayProductsIsDisplayed(){
        boolean displayProdDay = homePage.dayProductsIsDisplayed();
        Assert.assertTrue(displayProdDay, "Товары дня не отображаются");
    }

    /**Клик по кнопке купить у товара и проверка клика (кнопка корзины должна стать активной*/
    public void clickBuyButtonDayProducts(){
        CartProductsElements.clearCartList();
        homePage.clickBuyButtonDayProducts();
        headerPage.shouldBeBasketCount("1");
    }

    /**Прокрутка всей главной страницы, чтоб все её элементы стали отображаться и проверка отображается ли блок самых просматриваемых товаров*/
    public void scrollHomePage(){
        homePage.scrollPage();
        homePage.checkMostViewedVisible();
    }

    /**Клики по товарам и проверки, что клик прошел*/
    public void clickMostViewProducts(){
        CartProductsElements.clearCartList();
        homePage.writeProductsMostView();
        homePage.clickProductMostView(1);
        headerPage.shouldBeBasketCount("1");
        homePage.clickProductMostView(2);
        headerPage.shouldBeBasketCount("2");
    }
}
