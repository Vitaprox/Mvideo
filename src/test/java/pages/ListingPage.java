package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import patternWebElement.CartProductsElements;

import java.util.ArrayList;
import java.util.Collections;

import static com.codeborne.selenide.Selenide.$x;

public class ListingPage {

    @FindBy(xpath = "//mvid-dropdown[@icontype='sort']")
    private SelenideElement sortDropdown;

    @FindBy(xpath = "//mvid-dropdown[@icontype='sort']//div[contains(@class, 'dropdown__options')]")
    private SelenideElement sortDropdownOptions;

    @FindBy(xpath = "//a[contains(@class, 'product-title__text')]")
    private ElementsCollection nameProducts;

    @FindBy(xpath = "//span[contains(@class, 'price__main-value')]")
    private ElementsCollection priceProducts;

    @FindBy(xpath = "//button/mvid-icon[@type='histogram']")
    private ElementsCollection comparisonProdButton;

    @FindBy(xpath = "//button/mvid-icon[@type='love']")
    private ElementsCollection favoriteProdButton;

    public void writeCartProductsFavorite(){
        nameProducts.get(0).shouldBe(Condition.visible);
        CartProductsElements.createCarts(nameProducts, priceProducts, favoriteProdButton);
    }

    public void writeCartProductsComparison(){
        nameProducts.get(0).shouldBe(Condition.visible);
        CartProductsElements.createCarts(nameProducts, priceProducts, comparisonProdButton);
    }

    public void shouldBeIconButtonActive(int numberProduct){
        CartProductsElements.products.get(numberProduct-1).button.shouldBe(Condition.cssClass("active"));
    }

    public void clickProductIcon(int numberProduct){
        CartProductsElements.products.get(numberProduct-1).button.scrollIntoView("{block: 'center'}").click();
    }

    public void checkSortDropdownOptionsIsVisible(){
        sortDropdownOptions.shouldBe(Condition.visible);
    }

    public boolean checkContainsInNameProd(String text){
        nameProducts.get(0).shouldBe(Condition.visible);
        boolean flag = true;

        for(SelenideElement el: nameProducts){
            if (!el.text().toLowerCase().contains(text.toLowerCase())) flag = false;
        }

        return flag;
    }

    public boolean checkSortPriceDecrease(){
        priceProducts.get(0).shouldBe(Condition.visible);
        boolean flag = false;
        ArrayList<Integer> prices = new ArrayList<>();
        ArrayList<Integer> pricesSorted = new ArrayList<>();
        int priceProd;
        for(SelenideElement el: priceProducts){
            priceProd = Integer.parseInt(el.text().replaceAll("\\D+",""));
            prices.add(priceProd);
            pricesSorted.add(priceProd);
        }
        Collections.sort(pricesSorted, Collections.reverseOrder());

        if (prices.equals(pricesSorted)) flag = true;


        return flag;
    }

    public void clickSortDropdownOption(String nameOption){
        $x("//mvid-dropdown[@icontype='sort']//div[contains(@class, 'dropdown__option') and contains(text(), '"+nameOption+"')]").scrollIntoView("{block: 'center'}").click();
    }

    public void clickSortDropdown(){
        sortDropdown.scrollIntoView("{block: 'center'}").click();
    }

    public boolean checkSortDropdownIdDisplayed(){
        return sortDropdown.isDisplayed();
    }

    public String getTextSortDropdown(){
        return sortDropdown.text();
    }

    public void checkProductsIsVisible(){
        nameProducts.get(0).shouldBe(Condition.visible);
    }
}
