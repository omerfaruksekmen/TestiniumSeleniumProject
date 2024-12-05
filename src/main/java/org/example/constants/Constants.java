package org.example.constants;

import org.openqa.selenium.By;

// Site içerisindeki web elementlerin konumlarını tuttuğumuz class
public class Constants {

    // sabitler olarak konumları tutup, istediğimiz yerde keyword ile çağırabiliyoruz.
    // böylece tekrar tekrar locator ile yol tanımı gerekmiyor.
    public static final By COOKIE_BUTTON = By.xpath("//button[@id='onetrust-accept-btn-handler']");
    public static final By GENER_MAN_BUTTON = By.xpath("//button[@id='genderManButton']");
    public static final By SEARCH_BOX = By.xpath("//input[@placeholder='Ürün, Marka Arayın']");
    public static final By SEARCH_BOX2 = By.xpath("(//input[@placeholder='Ürün, Marka Arayın'])[2]");
    public static final By PRODUCT_LIST = By.cssSelector(".o-productList__item");
    public static final By PRODUCT_TITLE = By.cssSelector(".m-productCard__title");
    public static final By PRODUCT_PRICE = By.cssSelector(".m-productCard__newPrice");
    public static final By ADD_TO_CART_BUTTON = By.cssSelector(".m-productCard__stock.-hasMultiStock");
    public static final By ADD_TO_CART = By.xpath("//button[@id='addBasket']");
    public static final By PRODUCT_SIZE_LIST = By.cssSelector(".m-variation__item");
}
