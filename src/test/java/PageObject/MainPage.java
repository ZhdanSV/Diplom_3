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
    WebDriver driver;
    private By constructorButton;
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By placeOrderButton = By.xpath(".//button[text() = 'Оформить заказ']");
    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By logotype = By.xpath(".//a[@class='active']");
    private final By bunSection = By.xpath(".//span[text()='Булки']");
    private By souseSection;
    private By fillingsSection;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(bunSection));
    }


    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    public void clickLogotype() {
        driver.findElement(logotype).click();
    }


}
