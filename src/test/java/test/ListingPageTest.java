package test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import steps.HeaderSteps;
import steps.HomePageSteps;
import steps.ListingSteps;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class ListingPageTest {
    private HeaderSteps headerSteps = new HeaderSteps();
    private HomePageSteps homePageSteps = new HomePageSteps();
    private ListingSteps listingSteps = new ListingSteps();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверяем работает ли поиск и правильные ли товары выводит")
    public void checkSearchProducts(){
        //Configuration.holdBrowserOpen = true;
        open("https://www.mvideo.ru/");
        headerSteps.checkSearchIsDisplayed();
        headerSteps.typingAppleSearchInput();
        headerSteps.clickSearchButton();
        listingSteps.checkNameProductsContainsApple();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверяем работает ли поиск и сортировка цены по убыванию, проверяем правильные ли товары выводит")
    public void checkSortInListing(){
        //Configuration.holdBrowserOpen = true;
        open("https://www.mvideo.ru/");
        headerSteps.checkSearchIsDisplayed();
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

    @AfterMethod
    public void close(){
        closeWebDriver();
    }
}
