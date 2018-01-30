package conf;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestManager {

    protected static WebDriver driver;
    private String baseUrl = "http://google.com/";
    private static String uniqValue;
    public static String uniqPhoneNumber;

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        String system = System.getProperty("os.name");
        if(system.contains("Mac")) {
            System.out.println(system + " was detected.");
//            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodrivermac");
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedrivermac");
            System.out.println("Driver for " + system + " was set.");
//            driver = new FirefoxDriver();
            driver = new ChromeDriver();
            driver.manage().window().setPosition(new Point(0, 0));
            driver.manage().window().setSize(new Dimension(1440, 900));
        }else if(system.contains("Windows")){
            System.out.println(system + " was detected.");
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
            driver= new FirefoxDriver();
            driver.manage().window().maximize();
        } else {
            System.out.println(system + " was detected.");
//            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriverlinux");
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriverlinux");
            System.out.println("Driver for " + system + " was set.");
            driver = new ChromeDriver();
            driver.manage().window().setSize(new Dimension(1920,1080));//workaround for new Chrome update
//            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeMethod(alwaysRun = true)
    public void openBaseUrl(){
        driver.manage().deleteAllCookies();
        driver.get(baseUrl);
        uniqValue = RandomStringUtils.randomAlphanumeric(10);
        uniqPhoneNumber = "+380"+RandomStringUtils.randomNumeric(9);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }


    public static WebDriver getDriver(){ return driver; }

    public static void waitInSeconds(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Adding random value to all except empty string or it contains 'auto'
    public static String addUniqValue(String value) {
        String result = "";
        if (value.toLowerCase().contains("auto".toLowerCase())) {
            result = value;
        } else if (!value.equals("")) {
            if (value.contains("@")) {
                String[] arrOfStrings = value.split("@", 2);
                result = arrOfStrings[0] + "" + uniqValue + "@" + arrOfStrings[1];
            } else if (value.toLowerCase().contains("auto".toLowerCase())) {
                result = value;
            } else {
                result = value + "" + uniqValue;
            }
        }
        return result;
    }
}
