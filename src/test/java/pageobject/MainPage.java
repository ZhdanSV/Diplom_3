package pageobject;

import org.junit.Assert;
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
    private final By personalAccountButton = By.xpath(".//nav/a[@class='AppHeader_header__link__3D_hX']");
    private final By bunSection = By.xpath(".//span[text()='Булки']/parent::div");
    private final By souseSection = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By fillingsSection = By.xpath(".//span[text()='Начинки']/parent::div");
    private final By loadingImage = By.xpath(".//img[@class='Modal_modal__loading__3534A']");
    private static final String EXPECTED_CONDITION = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadingPage() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(personalAccountButton));
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until((ExpectedConditions.invisibilityOfElementLocated(loadingImage)));
    }

    public void waitForScrollingSection(String section) {
        switch (section) {
            case "bun":
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.attributeToBe(bunSection, "class", EXPECTED_CONDITION));
                break;
            case "souse":
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.attributeToBe(souseSection, "class", EXPECTED_CONDITION));
                break;
            case "fillings":
                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.attributeToBe(fillingsSection, "class", EXPECTED_CONDITION));
                break;
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
