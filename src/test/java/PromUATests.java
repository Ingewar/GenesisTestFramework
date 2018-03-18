import conf.MyListeners;
import conf.TestManager;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(MyListeners.class)
@Epic("Test task")
@Feature("Prom.ua tests")
public class PromUATests extends TestManager{

    @Test(groups = "All", alwaysRun = true, priority = 0, description = "New Company registration test")
    @Description("Test Description: I create new Company account")
    @Severity(SeverityLevel.BLOCKER)
    @Story("User should be able to pass registration as company")
    public void registrationAsCompany(){
        promUAHomePage.openCompanyRegistrationPage()
                .createNewCompany("testmail@mailsac.com", "pass")
                .myCabinetIsDisplayed();
    }


    //'плеер' value was replaces by "шорты" due to issue with suggested category displayed in autocomplete section
    // test with "добер" value will fail on desktop browser due to some bug in prom.ua app - autocomplete doesn't work
    @DataProvider(name = "searchValues")
    public Object [][] searchValues(){
        return new Object[][]{
                {"телев", "телевизор"},
                {"шорты", "шорты"},
                {"добер", "доберман"}
        };
    }

    @Test(groups = "All", alwaysRun = true, priority = 0, description = "Search autocomplete test", dataProvider = "searchValues")
    @Description("Test Description: I search for a product using autocomplete")
    @Severity(SeverityLevel.BLOCKER)
    @Story("User should be able to search product via autocomplete")
    public void searchAutoCompleteTest(String searchValue, String expectedValue){
        promUAHomePage.searchWithAutoComplete(searchValue, expectedValue)
                .verifySearchResultPage(expectedValue);
    }


    @Test(groups = "All", alwaysRun = true, priority = 0, description = "Success email test")
    @Description("Test Description: I create new Company account and getting success registration email")
    @Severity(SeverityLevel.BLOCKER)
    @Story("User should get success registration email after finish registration")
    public void verifyGettingRegistrationSuccessEmail(){
        promUAHomePage.openCompanyRegistrationPage()
                .createNewCompanyForApiTest("autotest@mailsac.com", "pass")
                .checkEmailApi("Подтвердите email для дальнейшей работы с Prom.ua", "autotest@mailsac.com");
    }

    @DataProvider(name = "searchText")
    public Object [][] searchText(){
        return new Object[][]{
                {"плеер"},
                {"телевизор"}
        };
    }

    @Test(groups = "All", alwaysRun = true, priority = 0, description = "Search autocomplete test", dataProvider = "searchText")
    @Description("Test Description: I search for a product should see search value in products name on first two pages")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Search result products should contain search value in their names on first two pages")
    public void searchTest(String searchText){
        promUAHomePage.searchProduct(searchText)
                .verifyProductsName(searchText)
                .openNextSearchResultPage()
                .verifyProductsName(searchText);
    }

}
