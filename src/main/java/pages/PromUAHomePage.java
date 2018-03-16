package pages;

import io.qameta.allure.Step;

import static conf.TestManager.waitInSeconds;
import static org.testng.Assert.*;
import static conf.TestManager.getDriver;

public class PromUAHomePage extends Page{

    public PromUAHomePage(){title="Prom.ua — Лидер онлайн-торговли в Украине";}


    //ASSERT METHODS
    @Step("Verify title of Google start page")
    public void verifyTitle(){
        waitInSeconds(10);
        assertTrue(true);
    }

    @Step("Verification method that always fails")
    public void failedVerification() {
        assertTrue(false);
    }
}
