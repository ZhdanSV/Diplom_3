package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/*
https://stellarburgers.nomoreparties.site/account/profile
 */
public class AccountPage {
    private final By constructorButton = By.xpath(".//li/a[@href='/']");
    private final By logotype = By.xpath(".//div/a[@href='/']");
    private final By exitButton = By.xpath(".//button[text()='Выход']");
    private final WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.elementToBeClickable(exitButton));
    }

    public void clickExitButton() {
        driver.findElement(exitButton).click();
    }

    public void clickLogotype() {
        driver.findElement(logotype).click();
    }

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }


}
