package pages;

import elements.Button;
import elements.TextField;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static conf.TestManager.*;

public class CompanyRegistrationPage extends Page{

    //Fields
    TextField emailLoginField = new TextField(By.xpath("//input[@type='email']"));
    TextField passwordRegistationField = new TextField(By.xpath("//input[@type='password']"));
    TextField phoneNumberField = new TextField(By.xpath("//input[@type='tel']"));
    TextField companyNameField = new TextField(By.xpath("//input[@type='text' and @name='company_name']"));

    //BUTTONS
    Button createSiteButton = new Button(By.xpath("//button[@type='submit']"));
    Button answerOptionMajorButton = new Button(By.cssSelector("span.x-drop-down__value"));
    Button answerOptionSelectButton = new Button(By.xpath("//ul[@class='x-drop-down__list  js-dropdown']//li[2]"));

    @Step("Fill and submit new company registration form")
    public LastStepRegistrationPage createNewCompany(String email, String pass) {
        emailLoginField.type(addUniqValue(email));
        passwordRegistationField.type(addUniqValue(pass));
        if(browser.equals("chromeEmulator")){
            phoneNumberField.type(uniqPhoneNumber);
            companyNameField.type(addUniqValue("TestCompany"));
            answerOptionMajorButton.click();
            answerOptionSelectButton.click();
        }
        createSiteButton.click();
        return new LastStepRegistrationPage();
    }

    @Step("Fill and submit new company registration form for API test")
    public LastStepRegistrationPage createNewCompanyForApiTest(String email, String pass) {
        emailLoginField.type(randomNumber+email);
        passwordRegistationField.type(addUniqValue(pass));
        if(browser.equals("chromeEmulator")){
            phoneNumberField.type(uniqPhoneNumber);
            companyNameField.type(addUniqValue("TestCompany"));
            answerOptionMajorButton.click();
            answerOptionSelectButton.click();
        }
        createSiteButton.click();
        return new LastStepRegistrationPage();
    }
}
