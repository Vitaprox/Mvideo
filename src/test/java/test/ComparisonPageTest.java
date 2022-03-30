package test;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import steps.ComparisonPageSteps;
import steps.HeaderSteps;
import steps.HomePageSteps;
import steps.ListingSteps;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class ComparisonPageTest {
    private HeaderSteps headerSteps = new HeaderSteps();
    private HomePageSteps homePageSteps = new HomePageSteps();
    private ListingSteps listingSteps = new ListingSteps();
    private ComparisonPageSteps comparisonPageSteps = new ComparisonPageSteps();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Добавляем три товара в сравнение, заходим на страницу сравнения и смотрим то ли добавилось, сверяем названия")
    public void checkAddComparison(){
        //Configuration.holdBrowserOpen = true;
        Configuration.browser = "firefox";
        Configuration.browserSize = "1920x1080";
        open("https://www.mvideo.ru/");
        headerSteps.checkSearchIsDisplayed();
        headerSteps.typingAppleSearchInput();
        headerSteps.clickSearchButton();
        listingSteps.clickThreeProductComparison();
        headerSteps.clickComparisonButton(); /**Тут же проверка заголовка (та ли страница открылась)*/
        comparisonPageSteps.checkNamesAddProducts();
        closeWebDriver();
    }

}
