package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static conf.TestManager.getDriver;

public abstract class Page {

    protected Actions builder = new Actions(getDriver());
    public WebDriverWait wait = new WebDriverWait(getDriver(),5);

    protected String title;
    public boolean isPresent(){
        return getDriver().getTitle().contentEquals(title);
    }

    public String getTitle(){return title;}

    public void scrollPage(String pixels){
        ((JavascriptExecutor)getDriver()).executeScript("scroll(0,"+pixels+")");
    }
}
