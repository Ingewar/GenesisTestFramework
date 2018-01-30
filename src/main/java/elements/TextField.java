package elements;

import org.openqa.selenium.By;

public class TextField extends Element{

    public TextField(By by){super(by);}

    public void type(String input){
        composeWebElement().click();
        composeWebElement().clear();
        composeWebElement().sendKeys(input);
    }
}
