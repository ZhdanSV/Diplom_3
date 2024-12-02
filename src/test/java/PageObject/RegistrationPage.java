package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*
https://stellarburgers.nomoreparties.site/register
 */
public class RegistrationPage {
    private final WebDriver driver;
    private final By emailField = By.xpath(".//label[text() = 'Email']/parent::div/input");
    private final By passwordField = By.xpath(".//label[text() = 'Пароль']/parent::div/input");
    private final By nameField = By.xpath(".//label[text() = 'Имя']/parent::div/input");
    private final By registrationButton = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private final By errorMessage = By.xpath(".//p[text()='Некорректный пароль']");
    private final By loginButton = By.xpath(".//a[text()='Войти']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(registrationButton)));
    }

    public void clickRegisterButton() {
        driver.findElement(registrationButton).click();
    }

    public void inputEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void inputName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void inputPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void waitErrorMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOf(driver.findElement(errorMessage)));
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void registration(String email, String name, String password) {
        inputName(name);
        inputEmail(email);
        inputPassword(password);
        clickRegisterButton();
    }

}
