package org.example.test;

import org.example.base.BaseTest;
import org.example.page.AddProductToCartPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// testlerimizi çalıştırdığımız class
public class AddProductToCartPageTest extends BaseTest {

    AddProductToCartPage addProductToCartPage;

    @Before
    public void before(){
        addProductToCartPage = new AddProductToCartPage(getWebDriver());
    }

    @Test
    public void test(){
        addProductToCartPage.assertTitle().clickAcceptCookies().clickGenderManButton().clickSearchBox().
                readExcelValueAndInputSearchBox(0,0).clearSearchBox().readExcelValueAndInputSearchBox(0,1)
                .clickEnter().selectRandomProductAndWriteToFileAndAddToCart();

    }

    @After
    public void after(){

    }


}
