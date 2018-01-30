package conf;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static conf.TestManager.getDriver;

public class MyListeners extends TestListenerAdapter{

    @Override
    public void onTestFailure(ITestResult testResult){
        System.out.println(testResult.getName()+ " was failed. Please take a look at screenshot in target/testResults/"+testResult.getName());
        File scrFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        DateFormat dateFormat = new SimpleDateFormat("dd.MM-HH:mm");
        try {
            FileUtils.copyFile(scrFile, new File("target/testResults/"+testResult.getName()+" "+dateFormat.format(new Date())+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult testResult){
        System.out.println(testResult.getName() + " was successful!");
    }

    @Override
    public void onTestSkipped(ITestResult testResult){
        System.out.println(testResult.getName() + " was skipped");
    }
}
