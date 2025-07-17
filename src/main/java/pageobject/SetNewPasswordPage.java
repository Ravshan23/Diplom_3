package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class SetNewPasswordPage {
    private final WebDriver webDriver;
    private WebDriverWait wait;

    // Локаторы элементов
    private final By recoverPasswordLink = By.xpath("//a[contains(@class, 'Auth_link__1fOlj') and text()='Восстановить пароль']");
    private final By logInLinkOnRecoverPasswordPage = By.xpath("//a[contains(@class, 'Auth_link__1fOlj') and text()='Войти']");

    public SetNewPasswordPage(WebDriver driver) {
        this.webDriver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public By getRecoverPasswordLinkElement() {
        return recoverPasswordLink;
    }

    @Step("Ожидание видимости Гипертекста Восстановить пароль")
    public WebElement waitForRecoverPasswordLinkElement() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(getRecoverPasswordLinkElement()));
    }

    @Step("Скролл до Гипертекста Восстановить пароль")
    public void navigateToRecoverPasswordLinkElement() {
        WebElement recoverPasswordLinkElement = waitForRecoverPasswordLinkElement();
        scrollToElement(recoverPasswordLinkElement);
    }

    @Step("Клик по гипертексту Восстановить пароль")
    public void clickRecoverPasswordLink() {
        webDriver.findElement(recoverPasswordLink).click();
    }

    public By getLogInLinkOnRecoverPasswordPageElement() {
        return logInLinkOnRecoverPasswordPage;
    }

    @Step("Ожидание видимости кнопки войти на странице восстановления пароля")
    public WebElement waitForLogInLinkOnRecoverPasswordPage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(getLogInLinkOnRecoverPasswordPageElement()));
    }
    @Step("Скролл до кнопки войти на странице восстановления пароля")
    public void navigateToLogInLinkOnRecoverPasswordPage() {
        WebElement logInLinkOnRecoverPasswordPage = waitForLogInLinkOnRecoverPasswordPage();
        scrollToElement(logInLinkOnRecoverPasswordPage);
    }

    @Step("Клик по кнопке 'Войти' на странице восстановления пароля")
    public void clickLogInLinkOnRecoverPasswordPage() {
        webDriver.findElement(logInLinkOnRecoverPasswordPage).click();
    }

    @Step("Прокрутка вниз до элемента")
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
