package pages;

import elements.Button;
import elements.TextField;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static conf.TestManager.browser;
import static org.testng.Assert.*;
import static conf.TestManager.getDriver;

public class PromUAHomePage extends Page{

    public PromUAHomePage(){
        title="Prom.ua — Лидер онлайн-торговли в Украине";
    }

    private String CompanyRegistrationPageUrl = "https://prom.ua/join-now?source_id=txt.register&locale=ru";

    //TEXTFIELD
    TextField searchField = new TextField(By.cssSelector("[data-qaid='search_input']"));
    TextField mobileSearchField = new TextField(By.cssSelector("[name='search_term']"));

    //BUTTONS
    Button searchIcon = new Button(By.xpath("//div[@class='b-header__item'][2]//span"));

    //SIMPLE METHODS
    public void selectAutoComplete(String productName){
        if(browser.equals("chromeEmulator")){
            getDriver().findElement(By.xpath("//div[@class='b-grids__item' and contains(.,'"+
                    productName+"')]/following-sibling::div[@class='b-grids__item h-width-140 h-text-right h-vertical-middle']")).click();
        }else{
            getDriver().findElement(By.xpath("//span[@class='b-autocomplete__text' and contains(.,'"+productName+"')]")).click();
        }
    }


    //ACTION STEP METHODS
    @Step("Open Company Registration page")
    public CompanyRegistrationPage openCompanyRegistrationPage() {
        getDriver().get(CompanyRegistrationPageUrl);
        return new CompanyRegistrationPage();
    }

    @Step("Search for a product using autocomplete")
    public SearchResultPage searchWithAutoComplete(String searchText, String fullText){
        if(browser.equals("chromeEmulator")){
            searchIcon.click();
            mobileSearchField.type(searchText);
        }else {
            searchField.type(searchText);
        }
        selectAutoComplete(fullText);
        return new SearchResultPage();
    }


    //ASSERT STEP METHODS
    @Step("Verify title of Google start page")
    public void verifyTitle(){
        assertTrue(true);
    }

    @Step("Verification method that always fails")
    public void failedVerification() {
        assertTrue(false);
    }

}
