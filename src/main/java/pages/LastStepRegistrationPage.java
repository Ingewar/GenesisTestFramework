package pages;

import elements.Text;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static conf.TestManager.randomNumber;
import static conf.TestManager.waitInSeconds;
import static org.testng.Assert.*;

public class LastStepRegistrationPage extends Page{

    //TEXT ELEMENTS
    Text myCabinetText = new Text(By.cssSelector("span.x-header__controls-link-text"));

    //ASSERT METHODS
    @Step("Verify that My Cabinet text is displayed after submit create new Company form")
    public void myCabinetIsDisplayed(){
        assertTrue(myCabinetText.isDisplayed());
    }

    @Step("Verify that inbox has email with such title")
    public void checkEmailApi(String emailTitle, String email){
        //mailsac.com API may return empty array in response for some emails, checked via postman - same result ¯\_(ツ)_/¯
        String url ="https://mailsac.com/api/addresses/"+randomNumber+email+"/messages";
        String response = null;
        for(int i = 0; i < 30; i++){
            waitInSeconds(1);
            try {
                URL requestUrl = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
                connection.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine); }
                in.close();
                response = content.toString();
                connection.disconnect();
            } catch (Throwable e) {
                e.printStackTrace();
            }
            if(response.contains(emailTitle)){
                break;
            }
        }
        assertTrue(response.contains(emailTitle));
    }
}
