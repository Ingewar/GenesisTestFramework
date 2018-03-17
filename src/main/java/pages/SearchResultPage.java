package pages;

import io.qameta.allure.Step;

import static conf.TestManager.browser;
import static conf.TestManager.encodeToUTF8;
import static conf.TestManager.getDriver;
import static org.testng.Assert.*;

public class SearchResultPage extends PromUAHomePage{

    //SIMPLE METHODS
    public Boolean checkSearchResultUrl(String searchText){
        return getDriver().getCurrentUrl().contains(encodeToUTF8(searchText));
    }

    public Boolean checkValueOfsearchField(String searchText){
        return searchField.getAttribute("value").equals(searchText);
    }

    @Step("Verify search result")
    public void verifySearchResultPage(String searchText){
        if(browser.equals("chromeEmulator")){
            assertTrue(checkSearchResultUrl(searchText));
        }else {
            assertTrue(checkSearchResultUrl(searchText) && checkValueOfsearchField(searchText));
        }
    }

}
