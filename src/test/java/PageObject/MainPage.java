package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*
https://stellarburgers.nomoreparties.site/
 */
public class MainPage {
    private final WebDriver driver;
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath(".//a[@href='/account']");
    private final By bunSection = By.xpath(".//span[text()='Булки']");
    private final By souseSection = By.xpath(".//span[text()='Соусы']");
    private final By fillingsSection = By.xpath(".//span[text()='Начинки']");
    private final By bunsField = By.xpath(".//h2[text()='Булки']");
    private final By souseField = By.xpath(".//h2[text()='Соусы']");
    private final By fillingsField = By.xpath(".//h2[text()='Начинки']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(personalAccountButton));
    }

    public void waitForScrollingSection(String section) {
        switch (section) {
            case "bun":
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.visibilityOfElementLocated(bunsField));
            case "souse":
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.visibilityOfElementLocated(souseField));
            case "fillings":
            default:
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.visibilityOfElementLocated(fillingsField));
        }
    }

    public  void clickBunSection() {
        driver.findElement(bunSection).click();
    }

    public  void clickSouseSection() {
        driver.findElement(souseSection).click();
    }

    public  void clickFillingsSection() {
        driver.findElement(fillingsSection).click();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }
}
