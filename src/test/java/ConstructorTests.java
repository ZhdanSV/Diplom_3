import PageObject.MainPage;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

@RunWith(Parameterized.class)
public class ConstructorTests {

    private final WebDriver webDriver;
    private MainPage objMainPage;

    public ConstructorTests(String webDriver) {
        this.webDriver = Browser.getWebDriver(webDriver);
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
        webDriver.get("https://stellarburgers.nomoreparties.site");
        objMainPage = new MainPage(webDriver);
        objMainPage.waitForLoadingPage();
    }

    @Step("Click Bun section")
    public void clickBunSection() {
        objMainPage.clickBunSection();
        objMainPage.waitForScrollingSection("bun");
    }

    @Step("Click Souse section")
    public void clickSouseSection() {
        objMainPage.clickSouseSection();
        objMainPage.waitForScrollingSection("souse");
    }

    @Step("Click Fillings section")
    public void clickFillingsSection() {
        objMainPage.clickFillingsSection();
        objMainPage.waitForScrollingSection("fillings");
    }

    @Test
    @DisplayName("Check going to Buns")
    public void goingToBuns() {
        clickFillingsSection();
        clickBunSection();
    }

    @Test
    @DisplayName("Check going to Buns")
    public void goingToSouse() {
        clickSouseSection();
    }

    @Test
    @DisplayName("Check going to Buns")
    public void goingToFillings() {
        clickFillingsSection();
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }
}
