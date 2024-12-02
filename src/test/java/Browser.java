import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Browser {
    public static WebDriver getWebDriver(String browserName) {
        switch (browserName) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                return new ChromeDriver(chromeOptions);
            case "yandex":
                default:
                    return createYandexDriver();
        }
    }

    private static WebDriver createYandexDriver() {
        System.setProperty("webdriver.chrome.driver",
                String.format("%s/%s", System.getenv("WEBDRIVERS"),
                        System.getenv("YANDEX_BROWSER_DRIVER_FILENAME")));
        ChromeOptions options = new ChromeOptions();
        options.setBinary(System.getenv("YANDEX_BROWSER_PATH"));
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        return new ChromeDriver(options);
    }
}
