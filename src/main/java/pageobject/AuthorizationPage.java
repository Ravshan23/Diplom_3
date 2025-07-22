package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthorizationPage {
    private final WebDriver driver;
    private WebDriverWait wait;

    //Кнопка для входа в аккаунт
    private final By logInToAccountButton = By.xpath("//button[text()='Войти в аккаунт']");

    // Инпуты для логина и пароля
    private final By inputFields = By.xpath(".//form[starts-with(@class, 'Auth_form')]//fieldset//div[@class=" +
            "'input__container']//input");
    // Кнопка авторизации
    private final By logInButton = By.xpath(".//form[starts-with(@class, 'Auth_form')]/button");

    // Кнопка для оформления заказа после входа
    private final By makeOrderButton = By.xpath("//div[@class='BurgerConstructor_basket__container__2fUl3 mt-10']//button[text()='Оформить заказ']");

    public AuthorizationPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Нажать на кнопку 'Войти в аккаунт'")
    public void pressLogInToAccount() {
        driver.findElement(logInToAccountButton).click();
    }

    @Step("Вписать Email")
    public void typeEmail(String email) {
        driver.findElements(inputFields).get(0).sendKeys(email);
    }

    @Step("Вписать пароль")
    public void typePassword(String password) {
        driver.findElements(inputFields).get(1).sendKeys(password);
    }

    @Step("Нажать на кнопку входа'")
    public void pressLoginButton() {
        driver.findElement(logInButton).click();
    }

    @Step("Войти в систему")
    public void doLogin(String email, String password) {
        typeEmail(email);
        typePassword(password);
        pressLoginButton();
    }

    public By getMakeOrderButton() {
        return makeOrderButton;
    }

    @Step("Подождать появления кнопки 'Оформить заказ'")
    public WebElement waitUntilOrderButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(getMakeOrderButton()));
    }

    @Step("Проверить, появилась ли кнопка 'Оформить заказ'")
    public boolean checkOrderButtonIsHere() {
        WebElement makeOrderButton = waitUntilOrderButtonVisible();
        return makeOrderButton.getText().equals("Оформить заказ");
    }
}
