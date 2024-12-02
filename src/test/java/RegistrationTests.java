import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.RegistrationPage;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import java.util.Random;


@RunWith(Parameterized.class)
public class RegistrationTests {

    private final WebDriver webDriver;
    private final String name;
    private final String email;
    private String password;
    Random random = new Random();
    private RegistrationPage objRegistrationPage;
    private LoginPage objLoginPage;
    private MainPage objMainPage;

    public RegistrationTests(String webDriver) {
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
        webDriver.get("https://stellarburgers.nomoreparties.site/register");
        objRegistrationPage = new RegistrationPage(webDriver);
        objLoginPage = new LoginPage(webDriver);
        objMainPage = new MainPage(webDriver);
        objRegistrationPage.waitForLoadingPage();
    }


    @Step("input fields and click registration button")
    public void registration(String email, String name, String password) {
        objRegistrationPage.registration(email, name, password);
    }

    @Step("wait for loading login page")
    public void checkRegistrationDone() {
        objLoginPage.waitForLoadingPage();
    }

    @Step("input email, password and click login button")
    public void login(String email, String password) {
        objLoginPage.login(email, password);
    }

    @Step("wait for load main Page")
    public void checkLogin() {
        objMainPage.waitForLoadingPage();
    }

    @Step("check exception message")
    public void checkExceptionMessage() {
        objRegistrationPage.waitErrorMessage();
    }

    @Test
    @DisplayName("Check successful registration")
    public void successfulRegistration() {
        registration(email, name, password);
        checkRegistrationDone();
        login(email, password);
        checkLogin();
    }

    @Test
    @DisplayName("Check registration with invalid password")
    public void invalidPasswordRegistration() {
        password = "123";
        registration(email, name, password);
        checkExceptionMessage();
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }
}
