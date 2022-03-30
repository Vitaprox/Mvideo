package test;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import steps.FavoritePageSteps;
import steps.HeaderSteps;
import steps.HomePageSteps;
import steps.ListingSteps;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class FavoritePageTest {
    private HeaderSteps headerSteps = new HeaderSteps();
    private HomePageSteps homePageSteps = new HomePageSteps();
    private ListingSteps listingSteps = new ListingSteps();
    private FavoritePageSteps favoritePageSteps = new FavoritePageSteps();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Добавляем три товара в понравившиеся, заходим на страницу понравившихся товаров и смотрим то ли добавилось, сверяем названия")
    public void checkAddFavorites(){
        //Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "firefox";
        open("https://www.mvideo.ru/");
        headerSteps.checkSearchIsDisplayed();
        headerSteps.typingAppleSearchInput();
        headerSteps.clickSearchButton();
        listingSteps.clickThreeProductFavorite();
        headerSteps.clickFavoriteButton(); /**Тут же проверка заголовка (та ли страница открылась)*/
        favoritePageSteps.checkNameAddProducts();
        closeWebDriver();
    }

}
