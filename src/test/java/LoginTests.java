import pageobject.ForgotPage;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegistrationPage;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import java.util.Random;


public class LoginTests extends BaseURIAndAPIs {

    private WebDriver webDriver;
    private final String name;
    private final String email;
    private final String password;
    Random random = new Random();
    private RegistrationPage objRegistrationPage;
    private LoginPage objLoginPage;
    private MainPage objMainPage;
    private ForgotPage objForgotPage;
    private String authToken;
    private UserData user;


    public LoginTests() {
        webDriver = GetBrowser.getWebDriver();
        name = "Naruto";
        email = "tester"+random.nextInt(10000)+ "@yandex.ru";
        password = "123456";
    }


    @Before
    public void setUp() {
        webDriver.get(MAIN_PAGE);
        objRegistrationPage = new RegistrationPage(webDriver);
        objLoginPage = new LoginPage(webDriver);
        objMainPage = new MainPage(webDriver);
        objForgotPage = new ForgotPage(webDriver);
        objMainPage.waitForLoadingPage();
        user = new UserData(email, password, name);
        authToken = registerAndGetToken(user);
    }

    @Step("Click login button on main page")
    public void clickLoginButtonOnMainPage() {
        objMainPage.clickLoginButton();
        objLoginPage.waitForLoadingPage();
    }

    @Step("Click personal account on Main page")
    public void clickPersonalAccountOnMainPage() {
        objMainPage.clickPersonalAccountButton();
        objLoginPage.waitForLoadingPage();
    }

    @Step("input email, password and click login button")
    public void login(String email, String password) {
        objLoginPage.login(email, password);
        objMainPage.waitForLoadingPage();
    }

    @Step("click login button on registration form")
    public void clickLoginButtonFromRegistration() {
        objRegistrationPage.clickLoginButton();
        objLoginPage.waitForLoadingPage();
    }

    @Step("click login button on registration form")
    public void clickLoginButtonFromForgot() {
        objForgotPage.clickLoginButton();
        objLoginPage.waitForLoadingPage();
    }

    @Step("click Registration on login page")
    public void clickRegistrationOnLoginPage() {
        objLoginPage.clickRegistrationButton();
        objRegistrationPage.waitForLoadingPage();
    }

    @Step("click Forgot on login page")
    public void clickForgotOnLoginPage() {
        objLoginPage.clickForgotButton();
        objForgotPage.waitForLoadingPage();
    }

    @Test
    @DisplayName("Login by login button on main page")
    public void checkLoginFromMainPageByLoginButton() {
        clickLoginButtonOnMainPage();
        login(email,password);
    }

    @Test
    @DisplayName("Login by personal account button")
    public void checkLoginByPersonalAccountButton() {
        clickPersonalAccountOnMainPage();
        login(email,password);
    }

    @Test
    @DisplayName("Check login from registration form")
    public void checkLoginFromRegistrationForm() {
        clickLoginButtonOnMainPage();
        clickRegistrationOnLoginPage();
        clickLoginButtonFromRegistration();
        login(email,password);
    }

    @Test
    @DisplayName("Check login from forgot form")
    public void checkLoginFromForgotForm() {
        clickLoginButtonOnMainPage();
        clickForgotOnLoginPage();
        clickLoginButtonFromForgot();
        login(email,password);
    }


    @After
    public void tearDown() {
        webDriver.quit();
        if (authToken!=null) {
            deleteUser(authToken);
        }
    }

}
