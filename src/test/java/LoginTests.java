import PageObject.ForgotPage;
import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.RegistrationPage;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class LoginTests {

    private static final Logger log = LoggerFactory.getLogger(LoginTests.class);
    private WebDriver webDriver;
    private final String name;
    private final String email;
    private String password;
    Random random = new Random();
    private RegistrationPage objRegistrationPage;
    private LoginPage objLoginPage;
    private MainPage objMainPage;
    private ForgotPage objForgotPage;
    private String authToken;
    private String user;

    public LoginTests(String webDriver) {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        this.webDriver = Browser.getWebDriver(webDriver);
        name = "Naruto";
        email = "tester"+random.nextInt(10000)+ "@yandex.ru";
        password = "123456";
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"chrome"},
                {"fireFox"}
        };
    }

    @Before
    public void setUp() {
        webDriver.get("https://stellarburgers.nomoreparties.site");
        objRegistrationPage = new RegistrationPage(webDriver);
        objLoginPage = new LoginPage(webDriver);
        objMainPage = new MainPage(webDriver);
        objForgotPage = new ForgotPage(webDriver);
        objMainPage.waitForLoadingPage();
        user =  "{\"email\": \""+email+"\",\n" +
                "\"password\": \""+password+"\",\n" +
                "\"name\": \""+name+"\"}";
        authToken = given()
                .header("Content-type", "application/json")
                .body(user)
                .post("/api/auth/register")
                .then()
                .statusCode(200)
                .body("success", notNullValue())
                .extract()
                .path("accessToken")
                .toString()
                .replace("Bearer ", "");
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

    @Step
    public void clickRegistrationOnLoginPage() {
        objLoginPage.clickRegistrationButton();
        objRegistrationPage.waitForLoadingPage();
    }

    @Step
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
        try {
            given()
                    .header("Content-type", "application/json")
                    .auth().oauth2(authToken)
                    .delete("/api/auth/user")
                    .then()
                    .statusCode(202);
        } catch (IllegalArgumentException illegalArgumentException) {
            return;
        }
    }

}
