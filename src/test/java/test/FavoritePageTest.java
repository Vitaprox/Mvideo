package test;

import hooks.Hooks;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listenersMethods.CustomListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import steps.FavoritePageSteps;
import steps.HeaderSteps;
import steps.ListingSteps;

@Listeners(CustomListener.class)
public class FavoritePageTest extends Hooks {
    private HeaderSteps headerSteps = new HeaderSteps();
    private ListingSteps listingSteps = new ListingSteps();
    private FavoritePageSteps favoritePageSteps = new FavoritePageSteps();

    //9
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Добавляем три товара в понравившиеся, заходим на страницу понравившихся товаров и смотрим то ли добавилось, сверяем названия")
    public void checkAddFavorites(){
        headerSteps.checkSearchIsVisible();
        headerSteps.typingAppleSearchInput();
        headerSteps.clickSearchButton();
        listingSteps.clickThreeProductFavorite();
        headerSteps.clickFavoriteButton(); /**Тут же проверка заголовка (та ли страница открылась)*/
        favoritePageSteps.checkNameAddProducts();
    }

}
