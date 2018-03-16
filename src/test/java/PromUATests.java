import conf.MyListeners;
import conf.TestManager;
import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(MyListeners.class)
@Epic("Test task")
@Feature("Prom.ua tests")
public class GoogleTests extends TestManager{

    @Test(groups = "All", alwaysRun = true, priority = 0, description = "Open Google start page")
    @Description("Test Description: I open 'google.com' and checking title")
    @Severity(SeverityLevel.BLOCKER)
    @Story("User should be able to open Google start page")
    public void openGoogleStartPage(){
        googleStartPage.verifyTitle();
    }

    @Test(groups = "All", alwaysRun = true, priority = 0, description = "Test that always fails")
    @Description("Test Description: Test to fail")
    @Story("This test should always fails")
    public void testToFail(){
        googleStartPage.failedVerification();
    }

}
