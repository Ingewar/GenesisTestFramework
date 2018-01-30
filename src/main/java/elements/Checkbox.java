package elements;

import org.openqa.selenium.By;

public class Checkbox extends Element{

    public Checkbox(By by){super(by);}

    public void check(){
        if(!composeWebElement().isSelected()){
            composeWebElement().click();
        }else {
            return;
        }
    }

    public void uncheck(){
        if(composeWebElement().isSelected()){
            composeWebElement().click();
        }else {
            return;
        }
    }
}
