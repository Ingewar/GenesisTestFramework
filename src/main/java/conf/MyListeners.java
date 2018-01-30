package conf;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static conf.TestManager.driver;
import static conf.TestManager.getDriver;

public class MyListeners implements ITestListener{

    @Override
    public void onTestFailure(ITestResult testResult){
        System.out.println(testResult.getName()+ " was failed.");
        System.out.println("Screenshot captured for test case: " + testResult.getName());

        //SAVE SCREENSHOT
        saveScreenshotPNG(driver);

        //Save log on allure
        saveTextLog(testResult.getName() + " failed and screenshot taken!");
    }

    @Override
    public void onTestStart(ITestResult iTestResult) { }

    @Override
    public void onTestSuccess(ITestResult testResult){
        System.out.println(testResult.getName() + " was successful!");
    }

    @Override
    public void onTestSkipped(ITestResult testResult){
        System.out.println(testResult.getName() + " was skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) { }

    @Override
    public void onStart(ITestContext iTestContext) { }

    @Override
    public void onFinish(ITestContext iTestContext) { }

    // TEXT ATTACHMENTS FOR ALLURE
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    // TEXT ATTACHMENT FOR ALLURE
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message){
        return message;
    }

}
