package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static conf.TestManager.getDriver;
import static conf.TestManager.waitInSeconds;

public abstract class Element {
    public Element(By by){ this.by = by; }

    protected By by;
    protected Actions builder = new Actions(getDriver());
    WebDriverWait wait = new WebDriverWait(getDriver(), 10);

    protected WebElement composeWebElement(){ return getDriver().findElement(by);}
    protected List <WebElement> composeWebElements() { return  getDriver().findElements(by);}

    public void scrollAndClick(){
        ((JavascriptExecutor)getDriver()).executeScript("arguments[0].scrollIntoView(false);", composeWebElement());
        composeWebElement().click();
    }

    public boolean isPresent(){
        try {
            composeWebElement();
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public void waitForElement(){
        for(int i=0; i<20; i++){
            if(isPresent()){
                break;
            }else {
                waitInSeconds(1);
            }
        }
    }


    public void waitForElementDisplayed(){
        for(int i=0; i<10; i++){
            if(composeWebElement().isDisplayed()){
                break;
            }else{
                waitInSeconds(1);
            }
        }
    }


    public boolean isDisplayed(){
        return composeWebElement().isDisplayed();
    }

    public String getText(){
        return composeWebElement().getText();
    }

    public String getAttribute(String attribute){return  composeWebElement().getAttribute(attribute);}

    public void waitElementToBeClickable(){
        wait.until(ExpectedConditions.elementToBeClickable(composeWebElement()));
    }
}
