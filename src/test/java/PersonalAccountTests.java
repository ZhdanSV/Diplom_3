import pageobject.*;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import java.util.Random;
import static io.restassured.RestAssured.given;


public class PersonalAccountTests extends BaseURIAndAPIs {

    private final WebDriver webDriver;
    private final String name;
    private final String email;
    private String password;
    Random random = new Random();
    private LoginPage objLoginPage;
    private MainPage objMainPage;
    private AccountPage objAccountPage;
    private String authToken;
    private UserData user;


    public PersonalAccountTests() {
        this.webDriver = GetBrowser.getWebDriver();
        name = "Naruto";
        email = "tester"+random.nextInt(10000)+ "@yandex.ru";
        password = "123456";
    }

    @Before
    public void setUp() {
        webDriver.get(MAIN_PAGE);
        objLoginPage = new LoginPage(webDriver);
        objMainPage = new MainPage(webDriver);
        objAccountPage = new AccountPage(webDriver);
        objMainPage.waitForLoadingPage();
        user =  new UserData(email, password, name);
        authToken = registerAndGetToken(user);
    }

    @Step("Click login button on main page")
    public void clickLoginButtonOnMainPage() {
        objMainPage.clickLoginButton();
        objLoginPage.waitForLoadingPage();
    }

    @Step("input email, password and click login button")
    public void login(String email, String password) {
        objLoginPage.login(email, password);
        objMainPage.waitForLoadingPage();
    }

    @Step("Click personal account on Main page")
    public void clickPersonalAccountOnMainPage() {
        objMainPage.clickPersonalAccountButton();
        objAccountPage.waitForLoadingPage();
    }

    @Step("Click constructor")
    public void clickConstructor() {
        objAccountPage.clickConstructorButton();
        objMainPage.waitForLoadingPage();
    }

    @Step("Click logotype")
    public void clickLogotype() {
        objAccountPage.clickLogotype();
        objMainPage.waitForLoadingPage();
    }

    @Step("click exit button")
    public void clickExitButton() {
        objAccountPage.clickExitButton();
        objMainPage.waitForLoadingPage();
    }

    @Test
    @DisplayName("Going to Personal account")
    public void goingToPersonalAccount() {
        clickLoginButtonOnMainPage();
        login(email, password);
        clickPersonalAccountOnMainPage();
    }

    @Test
    @DisplayName("Going to Constructor by click Constructor")
    public void goingToConstructorByClickConstructor() {
        clickLoginButtonOnMainPage();
        login(email, password);
        clickPersonalAccountOnMainPage();
        clickConstructor();
    }

    @Test
    @DisplayName("Going to Constructor by click Logotype")
    public void goingToConstructorByClickLogotype() {
        clickLoginButtonOnMainPage();
        login(email, password);
        clickPersonalAccountOnMainPage();
        clickLogotype();
    }

    @Test
    @DisplayName("Exit from account")
    public void exitFromAccount() {
        clickLoginButtonOnMainPage();
        login(email, password);
        clickPersonalAccountOnMainPage();
        clickExitButton();
    }

    @After
    public void tearDown() {
        webDriver.quit();
        if (authToken!=null) {
            given()
                    .header("Content-type", "application/json")
                    .auth().oauth2(authToken)
                    .delete("/api/auth/user")
                    .then()
                    .statusCode(202);
        }
    }
}
