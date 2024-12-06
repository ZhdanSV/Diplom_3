import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegistrationPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import java.util.Random;

import static io.restassured.RestAssured.given;


public class RegistrationTests extends BaseURIAndAPIs {

    private final WebDriver webDriver;
    private final String name;
    private final String email;
    private String password;
    Random random = new Random();
    private RegistrationPage objRegistrationPage;
    private LoginPage objLoginPage;
    private MainPage objMainPage;
    private String authToken;
    private UserData user;


    public RegistrationTests() {
        this.webDriver = GetBrowser.getWebDriver();
        name = "Naruto";
        email = "tester"+random.nextInt(10000)+ "@yandex.ru";
        password = "123456";
    }


    @Before
    public void setUp() {
        webDriver.get(REGISTERATION_PAGE);
        objRegistrationPage = new RegistrationPage(webDriver);
        objLoginPage = new LoginPage(webDriver);
        objMainPage = new MainPage(webDriver);
        objRegistrationPage.waitForLoadingPage();
        user = new UserData(email, password, name);
    }

    @Test
    @DisplayName("Check successful registration")
    public void successfulRegistration() {
        objRegistrationPage.registration(email, name, password);
        objLoginPage.waitForLoadingPage();
        authToken = loginAndGetToken(user);
        objMainPage.waitForLoadingPage();
    }

    @Test
    @DisplayName("Check registration with invalid password")
    public void invalidPasswordRegistration() {
        password = "123";
        objRegistrationPage.registration(email, name, password);
        objRegistrationPage.waitErrorMessage();
    }

    @After
    public void tearDown() {
        webDriver.quit();
        if (authToken!=null) {
            deleteUser(authToken);
        }
    }
}
