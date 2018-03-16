package pages;

import io.qameta.allure.Step;

import static org.testng.Assert.*;
import static conf.TestManager.getDriver;

public class GoogleStartPage extends Page{

    public GoogleStartPage(){title="Google";}


    //ASSERT METHODS
    @Step("Verify title of Google start page")
    public void verifyTitle(){
        assertEquals(getDriver().getTitle(), this.getTitle());
    }

    @Step("Verification method that always fails")
    public void failedVerification() {
        assertTrue(false);
    }
}
