package steps;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.ComparisonPage;


public class ComparisonPageSteps {
    private ComparisonPage comparisonPage = Selenide.page(new ComparisonPage());


    @Step("Проверка, что отображаются в сравнении именно те товары, что мы добавили")
    public void checkNamesAddProducts() {
        boolean sameProductsInComparison = comparisonPage.checkNamesAddProducts();
        Assert.assertTrue(sameProductsInComparison, "Добавленные товары не соответствуют товарам на странице");
    }
}
