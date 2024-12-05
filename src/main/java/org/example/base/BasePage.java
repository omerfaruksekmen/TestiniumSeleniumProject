package org.example.base;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Time;
import java.time.Duration;

public class BasePage {

    WebDriver driver = null;
    WebDriverWait wait = null;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    // element bulma
    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    // tıklama
    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        findElement(locator).click();
    }

    // veri gönderme
    public void sendKeys(By locator, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        findElement(locator).sendKeys(text);
    }

    // klavye tuşları kullanabilecek şekilde veri gönderme
    public void sendKeysCharSequence(By locator, CharSequence keysToSend) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        findElement(locator).sendKeys(keysToSend);
    }

    // element üzerine hover
    public void hoverElement(By locator) {
        Actions action = new Actions(driver);
        action.moveToElement(findElement(locator)).build().perform();
    }

    // text alma
    public String getText(By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        return findElement(locator).getText();
    }

    // text karşılaştırma
    public void assertTextEquals(By locator, String expectedText) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        String actualText = findElement(locator).getText();
        Assert.assertEquals(expectedText, actualText, "Element text mismatch");
    }

    // title karşılaştırma
    public void assertTitleEquals(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    // excel verisi okuma
    public String readCell(String filePath, String sheetName, int row, int column) {
        String cellValue = "";
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            Row sheetRow = sheet.getRow(row);
            Cell cell = sheetRow.getCell(column);
            cellValue = cell.getStringCellValue();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cellValue;
    }

    // içerik temizleme
    public void clearInput(By locator) {
        findElement(locator).clear();
    }

}
