package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*
https://stellarburgers.nomoreparties.site/login
 */
public class LoginPage {
    private final WebDriver driver;
    private final By emailField = By.xpath(".//label[text() = 'Email']/parent::div/input");
    private final By passwordField = By.xpath(".//label[text() = 'Пароль']/parent::div/input");
    private final By loginButton = By.xpath(".//button[text() = 'Войти']");
    private final By registrationButton = By.xpath(".//a[text() = 'Зарегистрироваться']");
    private final By forgotButton = By.xpath(".//a[text()='Восстановить пароль']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    public void clickForgotButton() {
        driver.findElement(forgotButton).click();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void inputEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void inputPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void waitForLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.elementToBeClickable(loginButton));
    }

    public void login(String email, String password) {
        inputEmail(email);
        inputPassword(password);
        clickLoginButton();
    }
}
