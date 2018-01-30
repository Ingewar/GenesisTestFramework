package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class Dropdown extends Element{

    public Dropdown(By by){super(by);}

    protected Select composeDropDown(){
        return new Select(composeWebElement());
    }

    public void selectOptionByValue(String value){
        composeDropDown().selectByValue(value);
    }

    public void selectOptionByText(String text){
        composeDropDown().selectByVisibleText(text);
    }
}
