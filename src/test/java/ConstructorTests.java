import pageobject.MainPage;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


public class ConstructorTests extends BaseURIAndAPIs {

    private final WebDriver webDriver;
    private MainPage objMainPage;

    public ConstructorTests() {
        this.webDriver = GetBrowser.getWebDriver();
    }

    @Before
    public void setUp() {
        webDriver.get(MAIN_PAGE);
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
        clickSouseSection();
        clickBunSection();
    }

    @Test
    @DisplayName("Check going to Souse")
    public void goingToSouse() {
        clickSouseSection();
    }

    @Test
    @DisplayName("Check going to Fillings")
    public void goingToFillings() {
        clickFillingsSection();
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }
}
