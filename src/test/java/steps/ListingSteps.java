package steps;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.HeaderPage;
import pages.ListingPage;
import patternWebElement.CartProductsElements;


public class ListingSteps {
    private HeaderPage headerPage = Selenide.page(new HeaderPage());
    private ListingPage listingPage = Selenide.page(new ListingPage());


    @Step("Прокрутка страницы и проверка, что в названиях всех товаров есть слово apple")
    public void checkNameProductsContainsApple() {
        listingPage.scrollRowBlocks();
        listingPage.lastRowShouldBeVisible();
        boolean appleIsPresent = listingPage.checkContainsInNameProd("apple");
        Assert.assertTrue(appleIsPresent, "В названии товаров нет слова apple");
    }

    @Step("Проверка, что выпадающий список отображается")
    public void checkSortDropdownIdDisplayed() {
        boolean displayDropdown = listingPage.checkSortDropdownIdDisplayed();
        Assert.assertTrue(displayDropdown, "Выпадающий список не отображается");
    }

    @Step("Проверка, что выпающем списке правильный текст")
    public void checkTextSortDropdownMostPopular() {
        String expectedText = "Сначала популярные";
        String actualText = listingPage.getTextSortDropdown();
        Assert.assertEquals(expectedText, actualText, "В дроплисте неправильный текст");
    }

    @Step("Клик на выпадающий список и проверка, что выпадающий список открылся")
    public void clickSortDropdown() {
        listingPage.clickSortDropdown();
        listingPage.checkSortDropdownOptionsIsVisible();
    }

    @Step("Клик на строку выпадающего списка 'Сначала дороже'")
    public void clickSortDropDownOptionDecreasingPrice() {
        listingPage.clickSortDropdownOption("Сначала дороже");
        String expectedText = "Сначала дороже";
        String actualText = listingPage.getTextSortDropdown();
        Assert.assertEquals(actualText, expectedText, "Отображается выбор неправильного фильтра");
    }

    @Step("Прокрутка, чтоб загрузились все товары на странице и проверка, что сортировка цены по убыванию")
    public void checkSortPriceDecrease() {
        listingPage.scrollRowBlocks();
        listingPage.lastRowShouldBeVisible();
        boolean sortIsDecrease = listingPage.checkSortPriceDecrease();
        Assert.assertTrue(sortIsDecrease, "Сортировка неправильная");
    }

    /**
     * Клик на кнопки сравнения у первых трех товаров, а также проверка, что последняя кнопка "активировалась", а в сравнении в хедере число 3
     */
    @Step("Клик на кнопки сравнения у первых трех товаров")
    public void clickThreeProductComparison() {
        CartProductsElements.clearCartList();
        listingPage.writeCartProductsComparison();
        listingPage.clickProductIcon(1);
        headerPage.shouldBeComparisonCount("1");
        headerPage.closeWindowProducts();
        listingPage.clickProductIcon(2);
        headerPage.shouldBeComparisonCount("2");
        listingPage.clickProductIcon(3);
        listingPage.shouldBeIconButtonActive(3);
        headerPage.shouldBeComparisonCount("3");
    }

    /**
     * Клик на кнопки понравилось у первых трех товаров, а также проверка, что последняя кнопка "активировалась", а в избранных товарах в хедере число 3
     */
    @Step("Клик на кнопки понравилось у первых трех товаров")
    public void clickThreeProductFavorite() {
        CartProductsElements.clearCartList();
        listingPage.writeCartProductsFavorite();
        listingPage.clickProductIcon(1);
        headerPage.shouldBeFavoriteCount("1");
        headerPage.closeWindowProducts();
        listingPage.clickProductIcon(2);
        headerPage.shouldBeFavoriteCount("2");
        listingPage.clickProductIcon(3);
        headerPage.shouldBeFavoriteCount("3");
        listingPage.shouldBeIconButtonActive(3);
    }

}
