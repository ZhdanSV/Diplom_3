import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class GetBrowser {
    public static WebDriver getWebDriver() {
        String browser = System.getProperty("browser");
        if (browser == null) {
            return createChromeDriver();
        }
        switch (browser) {
            case "chrome":
                return createChromeDriver();
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

    private static WebDriver createChromeDriver() {
        System.setProperty("webdriver.chrome.driver",
                String.format("%s/%s", System.getenv("CHROMEWEBDRIVERS"),
                        System.getenv("CHROME_BROWSER_DRIVER_FILENAME")));
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        chromeOptions.setBinary(System.getenv("CHROME_BROWSER_PATH"));

        return new ChromeDriver(chromeOptions);
    }
}
