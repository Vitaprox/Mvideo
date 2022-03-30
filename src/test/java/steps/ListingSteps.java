package steps;

import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import pages.HeaderPage;
import pages.ListingPage;
import patternWebElement.CartProductsElements;


public class ListingSteps {
    private HeaderPage headerPage = Selenide.page(new HeaderPage());
    private ListingPage listingPage = Selenide.page(new ListingPage());


    public void checkNameProductsContainsApple(){
        boolean appleIsPresent = listingPage.checkContainsInNameProd("apple");
        Assert.assertTrue(appleIsPresent, "В названии товаров нет слова apple");
    }

    public void checkSortDropdownIdDisplayed(){
        boolean displayDropdown = listingPage.checkSortDropdownIdDisplayed();
        Assert.assertTrue(displayDropdown, "Выпадающий список не отображается");
    }

    public void checkTextSortDropdownMostPopular(){
        String expectedText = "Сначала популярные";
        String actualText = listingPage.getTextSortDropdown();
        Assert.assertEquals(expectedText, actualText, "В дроплисте неправильный текст");
    }

    public void clickSortDropdown(){
        listingPage.clickSortDropdown();
        listingPage.checkSortDropdownOptionsIsVisible();
    }

    public void clickSortDropDownOptionDecreasingPrice(){
        listingPage.clickSortDropdownOption("Сначала дороже");
        String expectedText = "Сначала дороже";
        String actualText = listingPage.getTextSortDropdown();
        Assert.assertEquals(actualText, expectedText, "Отображается выбор неправильного фильтра");
    }


    public void checkSortPriceDecrease(){
        boolean sortIsDecrease = listingPage.checkSortPriceDecrease();
        Assert.assertTrue(sortIsDecrease, "Сортировка неправильная");
    }

    public void clickThreeProductComparison(){
        CartProductsElements.clearCartList();
        listingPage.writeCartProductsComparison();
        listingPage.clickProductIcon(1);
        listingPage.clickProductIcon(2);
        listingPage.clickProductIcon(3);
        listingPage.shouldBeIconButtonActive(3);
        headerPage.shouldBeComparisonCount("3");
    }

    public void clickThreeProductFavorite(){
        CartProductsElements.clearCartList();
        listingPage.writeCartProductsFavorite();
        listingPage.clickProductIcon(1);
        listingPage.clickProductIcon(2);
        listingPage.clickProductIcon(3);
        headerPage.shouldBeFavoriteCount("3");
        listingPage.shouldBeIconButtonActive(3);
    }

}
