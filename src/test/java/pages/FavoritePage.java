package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import patternWebElement.CartProductsElements;

public class FavoritePage {

    @FindBy(xpath = "//h1[contains(text(), 'Избранное')]")
    private SelenideElement favoriteTitle;

    @FindBy(xpath = "//h3[contains(@class, 'wishlist-product-title')]")
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

    public void checkFavoriteIsVisible(){
        favoriteTitle.shouldBe(Condition.visible);
    }
}
