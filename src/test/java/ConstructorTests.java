import org.junit.After;
import org.junit.Before;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

public class ConstructorTests {

    private final WebDriver webDriver;

    public ConstructorTests(String webDriver) {
        this.webDriver = Browser.getWebDriver(webDriver);
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"chrome"},
                {"fireFox"},
                {"IE"}
        };
    }

    @Before
    public void setUp() {
        webDriver.get("https://stellarburgers.nomoreparties.site");
    }



    @After
    public void tearDown() {
        webDriver.quit();
    }
}
