package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import patternWebElement.CartProductsElements;

public class ComparisonPage {
    @FindBy(xpath = "//h1[contains(text(), 'Сравнение товаров')]")
    private SelenideElement comparisonTitle;


    @FindBy(xpath = "//div[contains(@class, 'c-compare-cell__pinable-product-tile')]/..//a[contains(@class, 'fl-product-tile-title__link')]")
    private ElementsCollection nameProducts;

    public boolean checkNamesAddProducts(){
        int count = 0;
        boolean flag = false;
        for (SelenideElement el: nameProducts){
            for (int i = 0; i<nameProducts.size(); i++){
                if (el.text().equals(CartProductsElements.products.get(i).name)) count++;
            }
        }
        if (count==nameProducts.size()) flag=true;

        return flag;
    }

    public void checkComparisonTitleIsVisible(){
        comparisonTitle.shouldBe(Condition.visible);
    }
}
