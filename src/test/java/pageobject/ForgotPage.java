package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*
https://stellarburgers.nomoreparties.site/forgot-password
 */
public class ForgotPage {
    private final By recoverButton = By.xpath(".//button[text() = 'Восстановить']");
    private final By loginButton = By.xpath(".//a[text()='Войти']");
    private final WebDriver driver;

    public ForgotPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.elementToBeClickable(recoverButton));
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }


}
