package patternWebElement;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;

/**Класс для более удобного обращения с элементами карточек*/
public class CartProductsElements {
    public String name;
    public int price;
    public SelenideElement button;

    public static ArrayList<CartProductsElements> products = new ArrayList<>();

    public static void createCarts(ElementsCollection name, ElementsCollection price, ElementsCollection buy){
        int count = name.size();
        for (int i = 0; i<count; i++){
            products.add(new CartProductsElements(name.get(i).text(), Integer.parseInt(price.get(i).text().replaceAll("\\D+","")) , buy.get(i)));
        }
    }

    public static void clearCartList(){
        products.clear();
    }

    public CartProductsElements(String name, int price,SelenideElement button){
        this.name=name;
        this.price=price;
        this.button=button;
    }
}
