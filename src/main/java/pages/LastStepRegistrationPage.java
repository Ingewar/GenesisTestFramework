package pages;

import elements.Text;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static org.testng.Assert.*;

public class LastStepRegistrationPage extends Page{

    //TEXT ELEMENTS
    Text myCabinetText = new Text(By.cssSelector("span.x-header__controls-link-text"));

    //ASSERT METHODS
    @Step("Verify that My Cabinet text is displayed after submit create new Company form")
    public void myCabinetIsDisplayed(){
        assertTrue(myCabinetText.isDisplayed());
    }
}
