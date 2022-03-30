package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import patternWebElement.CartProductsElements;

public class HomePage {
    @FindBy(xpath = "//mvid-day-products-block")
    private SelenideElement dayProductsBlock;

    @FindBy(xpath = "//mvid-simple-product-collection-mp[.//h2[contains(text(), 'Самые просматриваемые')]]")
    private SelenideElement mostViewedBlock;

    @FindBy(xpath = "//div[contains(@class, 'product-container')]/mvid-day-product//button")
    private ElementsCollection buttonsDay;

    @FindBy(xpath = "//mvid-day-product//div[contains(@class, 'title')]/a")
    private ElementsCollection nameProductDay;

    @FindBy(xpath = "//div[contains(@class, 'mp-wrapper')]/*")
    private ElementsCollection mainBlocks;

    @FindBy(xpath = "//h2[contains(text(), 'Самые просматриваемые')]/..//a/div[text() != '']")
    private ElementsCollection mostViewName;

    @FindBy(xpath = "//h2[contains(text(), 'Самые просматриваемые')]/..//span[contains(@class, 'price__main-value')]")
    private ElementsCollection mostViewPrice;

    @FindBy(xpath = "//h2[contains(text(), 'Самые просматриваемые')]/..//mvid-icon[@type='cart']/..")
    private ElementsCollection mostViewBuyButton;

    @FindBy(xpath = "//mvid-day-products-block//span[contains(@class, 'price__main-value')]")
    private ElementsCollection priceProductsDay;

    public void writeProductsMostView(){
        CartProductsElements.createCarts(mostViewName, mostViewPrice, mostViewBuyButton);
    }

    public void clickProductMostView(int numberProduct){
        CartProductsElements.products.get(numberProduct-1).button.scrollIntoView("{block: 'center'}").click();
    }

    public void checkMostViewedVisible(){
        mostViewedBlock.shouldBe(Condition.visible);
    }

    public void clickBuyButtonDayProducts() {
        for (int i = 0; i < buttonsDay.size(); i++) {
            if (buttonsDay.get(i).isDisplayed()) {
                CartProductsElements.products.add(new CartProductsElements(nameProductDay.get(i).text(),Integer.parseInt(priceProductsDay.get(i).text().replaceAll("\\D+","")),buttonsDay.get(i)));
                buttonsDay.get(i).scrollIntoView("{ behavior: 'auto', block: 'center', inline: 'center'}").click();
            }
        }

    }

    public boolean dayProductsIsDisplayed() {
        dayProductsBlock.shouldBe(Condition.visible);
        return dayProductsBlock.isDisplayed();
    }

    public void scrollPage(){
        for (int i = 0; i<mainBlocks.size(); i++){
            mainBlocks.get(i).scrollIntoView("{block: 'center'}");
        }
    }

}
