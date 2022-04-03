package steps;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.FavoritePage;

public class FavoritePageSteps {
    private FavoritePage favoritePage = Selenide.page(new FavoritePage());

    @Step("Проверка, что в избранных отображаются именно те товары, что мы добавили")
    public void checkNameAddProducts() {
        boolean sameProductsInFavorites = favoritePage.checkNamesAddProducts();
        Assert.assertTrue(sameProductsInFavorites, "Добавленные товары не соответствуют товарам на странице");
    }

}
