package conf;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.PromUAHomePage;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestManager {

    protected static WebDriver driver;
    private String baseUrl = "https://prom.ua/";
    private static String uniqValue;
    public static String uniqPhoneNumber;
    public static String randomNumber;
    String system = System.getProperty("os.name");
    public static String browser = new PropertyFileReader().getPropertyValue("BROWSER");
    String emulator = new PropertyFileReader().getPropertyValue("EMULATOR");
    // Create instance of Prom.ua Home page
    protected PromUAHomePage promUAHomePage;

    //TODO refactor set up method
    @BeforeClass(alwaysRun = true)
    public void setUp(){
        System.out.println(system + " was detected.");
        switch (browser){
            case "chrome":
                if(system.contains("Mac")) {
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedrivermac");
                    driver = new ChromeDriver();
                    //Workaround for new Chrome update
                    driver.manage().window().setPosition(new Point(0, 0));
                    driver.manage().window().setSize(new Dimension(1440, 900));
                }else if(system.contains("Windows")){
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                    driver= new ChromeDriver();
                    driver.manage().window().maximize();
                } else {
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriverlinux");
                    driver = new ChromeDriver();
                    //workaround for new Chrome update
                    driver.manage().window().setSize(new Dimension(1920,1080));
                }
                System.out.println(browser+" driver for " + system + " was set.");
                break;
            case "firefox":
                if (system.contains("Mac")){
                    System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodrivermac");
                }else if(system.contains("Windows")){
                    System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                }else {
                    System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriverlinux");
                }
                System.out.println(browser+" driver for " + system + " was set.");
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;
            case "chromeEmulator":
                if(system.contains("Mac")){
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedrivermac");
                }else if (system.contains("Windows")){
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                }else{
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriverlinux");
                }
                Map<String, String> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceName", emulator);

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

                driver = new ChromeDriver(chromeOptions);
                System.out.println(browser+" - "+emulator+" driver for " + system + " was set.");
                break;
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        promUAHomePage = new PromUAHomePage();
    }

    @BeforeMethod(alwaysRun = true)
    public void openBaseUrl(){
        driver.manage().deleteAllCookies();
        driver.get(baseUrl);
        uniqValue = RandomStringUtils.randomAlphanumeric(10);
        uniqPhoneNumber = "+38050"+RandomStringUtils.randomNumeric(7);
        randomNumber = RandomStringUtils.randomNumeric(5);
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

    public static String encodeToUTF8(String text){
        String encodedText = null;
        try {
            encodedText = URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodedText;
    }

}
