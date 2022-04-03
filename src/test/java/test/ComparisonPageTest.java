package test;

import hooks.Hooks;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import listenersMethods.CustomListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import steps.ComparisonPageSteps;
import steps.HeaderSteps;
import steps.ListingSteps;

@Listeners(CustomListener.class)
public class ComparisonPageTest extends Hooks {
    private HeaderSteps headerSteps = new HeaderSteps();
    private ListingSteps listingSteps = new ListingSteps();
    private ComparisonPageSteps comparisonPageSteps = new ComparisonPageSteps();

    //8
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Добавляем три товара в сравнение, заходим на страницу сравнения и смотрим то ли добавилось, сверяем названия")
    public void checkAddComparison(){
        headerSteps.checkSearchIsVisible();
        headerSteps.typingAppleSearchInput();
        headerSteps.clickSearchButton();
        listingSteps.clickThreeProductComparison();
        headerSteps.clickComparisonButton(); /**Тут же проверка заголовка (та ли страница открылась)*/
        comparisonPageSteps.checkNamesAddProducts();
    }

}
