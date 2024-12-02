import PageObject.*;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class PersonalAccountTests {

    private final WebDriver webDriver;
    private final String name;
    private final String email;
    private String password;
    Random random = new Random();
    private LoginPage objLoginPage;
    private MainPage objMainPage;
    private AccountPage objAccountPage;
    private String authToken;
    private String user;

    public PersonalAccountTests(String webDriver) {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        this.webDriver = Browser.getWebDriver(webDriver);
        name = "Naruto";
        email = "tester"+random.nextInt(10000)+ "@yandex.ru";
        password = "123456";
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"chrome"},
                {"yandex"}
        };
    }

    @Before
    public void setUp() {
        webDriver.get("https://stellarburgers.nomoreparties.site/");
        objLoginPage = new LoginPage(webDriver);
        objMainPage = new MainPage(webDriver);
        objAccountPage = new AccountPage(webDriver);
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
