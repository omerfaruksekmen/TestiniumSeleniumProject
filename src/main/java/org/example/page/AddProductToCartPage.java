package org.example.page;

import static org.example.base.BaseTest.getWebDriver;
import static org.example.constants.Constants.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

// senaryo adımlarının gerçekleştirildiği class
public class AddProductToCartPage extends BasePage {

    private static final String filePath = "src/main/java/org/example/base/data.xlsx";
    private static final Logger log = LogManager.getLogger(AddProductToCartPage.class);

    public AddProductToCartPage(WebDriver driver) {
        super(driver);
    }

    public AddProductToCartPage assertTitle() {
        assertTitleEquals("Beymen.com – Türkiye’nin Tek Dijital Lüks Platformu");
        return this;
    }

    public AddProductToCartPage clickAcceptCookies(){
        click(COOKIE_BUTTON);
        return this;
    }

    public AddProductToCartPage clickGenderManButton(){
        click(GENER_MAN_BUTTON);
        return this;
    }

    public AddProductToCartPage clickSearchBox(){
        click(SEARCH_BOX);
        return this;
    }

    public AddProductToCartPage readExcelValueAndInputSearchBox(int row, int col){
        String cellValue = readCell(filePath, "dataList", row, col);
        sendKeys(SEARCH_BOX2, cellValue);
        return this;
    }

    public AddProductToCartPage clearSearchBox(){
        sendKeys(SEARCH_BOX2, Keys.CONTROL + "a");
        sendKeysCharSequence(SEARCH_BOX2, Keys.DELETE);
        return this;
    }

    public AddProductToCartPage clickEnter(){
        sendKeysCharSequence(SEARCH_BOX2, Keys.ENTER);
        return this;
    }

    public AddProductToCartPage selectRandomProductAndWriteToFileAndAddToCart() {
        // Ürünlerin listesinin alınması
        List<WebElement> products = getWebDriver().findElements(PRODUCT_LIST);

        // Rastgele bir ürün seçmek için Random sınıfı kullanıldı
        Random random = new Random();
        int randomIndex = random.nextInt(products.size()); // Ürünler arasından rastgele bir indeks seçilmesi
        WebElement selectedProduct = products.get(randomIndex);

        // Seçilen ürünün başlık ve fiyatının alınması
        String productTitle = selectedProduct.findElement(PRODUCT_TITLE).getText();
        String productPrice = selectedProduct.findElement(PRODUCT_PRICE).getText();

        // Ürün bilgilerini yazdır
        log.info("Seçilen Ürün: " + productTitle);
        log.info("Fiyat: " + productPrice);

        // Ürün bilgilerini bir dosyaya yaz
        writeProductInfoToFile(productTitle, productPrice);
        selectedProduct.findElement(ADD_TO_CART_BUTTON).click();
        /*List<WebElement> productSizes = getWebDriver().findElements(PRODUCT_SIZE_LIST);
        int randomIndex2 = random.nextInt(productSizes.size());
        WebElement selectedSizeProduct = productSizes.get(randomIndex2);
        selectedSizeProduct.click();*/
        click(ADD_TO_CART);
        return this;
    }

    // Ürün bilgilerini bir metin dosyasına yazma metodu
    private void writeProductInfoToFile(String title, String price) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("product_info.txt", true))) {
            writer.write("Ürün: " + title + "\n");
            writer.write("Fiyat: " + price + "\n");
            writer.newLine();
        } catch (IOException e) {
            log.error("Ürün bilgileri dosyaya yazılırken hata oluştu: ", e);
        }
    }

}
