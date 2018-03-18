package pages;

import elements.Button;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.List;

import static conf.TestManager.browser;
import static conf.TestManager.encodeToUTF8;
import static conf.TestManager.getDriver;
import static org.testng.Assert.*;

public class SearchResultPage extends PromUAHomePage{

    //BUTTONS
    Button nextPageButton = new Button(By.cssSelector("[data-qaid='next']"));

    //SIMPLE METHODS
    public Boolean checkSearchResultUrl(String searchText){
        return getDriver().getCurrentUrl().contains(encodeToUTF8(searchText));
    }

    public Boolean checkValueOfsearchField(String searchText){
        return searchField.getAttribute("value").equals(searchText);
    }

    //ACTION METHODS

    @Step("Open next search result page")
    public SearchResultPage openNextSearchResultPage(){
        if(nextPageButton.isPresent()){
            nextPageButton.click();
        }
        return this;
    }

    @Step("Verify search result")
    public void verifySearchResultPage(String searchText){
        if(browser.equals("chromeEmulator")){
            assertTrue(checkSearchResultUrl(searchText));
        }else {
            assertTrue(checkSearchResultUrl(searchText) && checkValueOfsearchField(searchText));
        }
    }

    @Step("Verify that all products on page have searched value in their names")
    public SearchResultPage verifyProductsName(String productName){
        List<WebElement> products;
        if(browser.contains("chromeEmulator")){
            products = getDriver().findElements(By.className("b-products__title"));
        }else{
            products = getDriver().findElements(By.xpath("//a[@data-qaid='product_name']/span"));
        }
        Iterator <WebElement> prodIter = products.iterator();
        boolean result = true;
        while (prodIter.hasNext()){
            String prodText = prodIter.next().getText();
            if(prodText.toLowerCase().contains(productName.toLowerCase()) != true){
                result = false;
                System.out.println(prodText+ " Do not contain " + productName);
            }
        }
        assertTrue(result);
        return this;
    }

}
