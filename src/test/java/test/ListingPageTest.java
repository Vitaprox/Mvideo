package test;

import hooks.Hooks;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listenersMethods.CustomListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import steps.HeaderSteps;
import steps.ListingSteps;

@Listeners(CustomListener.class)
public class ListingPageTest extends Hooks {
    private HeaderSteps headerSteps = new HeaderSteps();
    private ListingSteps listingSteps = new ListingSteps();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверяем работает ли поиск и правильные ли товары выводит")
    public void checkSearchProducts(){
        headerSteps.checkSearchIsVisible();
        headerSteps.typingAppleSearchInput();
        headerSteps.clickSearchButton();
        listingSteps.checkNameProductsContainsApple();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверяем работает ли поиск и сортировка цены по убыванию, проверяем правильные ли товары выводит")
    public void checkSortInListing(){
        headerSteps.checkSearchIsVisible();
        headerSteps.typingAppleSearchInput();
        headerSteps.clickSearchButton();
        listingSteps.checkNameProductsContainsApple();
        listingSteps.checkSortDropdownIdDisplayed();
        listingSteps.checkTextSortDropdownMostPopular();
        listingSteps.clickSortDropdown();
        listingSteps.clickSortDropDownOptionDecreasingPrice();
        listingSteps.checkNameProductsContainsApple();
        listingSteps.checkSortPriceDecrease();
    }

}
