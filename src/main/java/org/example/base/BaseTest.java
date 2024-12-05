package org.example.base;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {

    static WebDriver webDriver = null;

    // driver ve gidilecek siteyi tanımlama
    @Before
    public void setUp(){

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\omerf\\Selenium\\ChromeDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-notifications");
        options.addArguments("disable-popup-blocking");
        setWebDriver(new ChromeDriver(options));
        getWebDriver().navigate().to("https://www.beymen.com/tr");

    }

    public static WebDriver getWebDriver(){
        return webDriver;
    }

    public static void setWebDriver(WebDriver driver){
        BaseTest.webDriver = driver;
    }

    public void tearDown(){
        getWebDriver().quit();
    }
}
