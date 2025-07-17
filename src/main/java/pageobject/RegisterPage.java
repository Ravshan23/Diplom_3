package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private WebDriver webDriver;
    private WebDriverWait wait;

    // Локаторы элементов
    private final By lkButton = By.xpath("//p[contains(text(), 'Личный Кабинет')]");
    private final By registerLink = By.xpath("//a[contains(@class, 'Auth_link__1fOlj') and @href='/register']");
    private final By inputFields = By.xpath(".//form[starts-with(@class, 'Auth_form')]//fieldset//div[@class='input__container']//input");
    private final By registerButton = By.xpath("//button[contains(@class, 'button_button__33qZ0') and contains(text(), 'Зарегистрироваться')]");
    private final By enterTitle = By.xpath(".//main//h2");
    private final By wrongPasswordErrorMessage = By.xpath(".//*[text()='Некорректный пароль']");
    private final By logInButtonUnderRegistryForm = By.xpath("//a[text()='Войти']");
    private final By modalOverlay = By.className("Modal_modal_overlay__x2ZCr");

    // Конструктор класса
    public RegisterPage(WebDriver driver) {
        webDriver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public By getLkButton() {
        return lkButton;
    }

    @Step("Ожидание видимости кнопки Личный кабинет")
    public WebElement waitForLKButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(getLkButton()));
    }

    @Step("Ожидание исчезновения модального окна")
    public void waitForModalToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(modalOverlay));
    }
    @Step("Проверка отображения заголовка 'Вход'")
    public boolean isEnterTitleDisplayed() {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(enterTitle));
            return element.isDisplayed();
        } catch (StaleElementReferenceException e) {
            // Переищем элемент, если он устарел
            WebElement refreshedElement = wait.until(ExpectedConditions.refreshed(
                    ExpectedConditions.visibilityOfElementLocated(enterTitle)
            ));
            return refreshedElement.isDisplayed();
        }
    }
    @Step("Клик по кнопке 'Личный кабинет'")
    public void clickLKButton() {
        waitForModalToDisappear(); // Ждём пока модалка исчезнет
        WebElement lkbutton = wait.until(ExpectedConditions.elementToBeClickable(lkButton));
        lkbutton.click();
    }

    public By getRegisterLinkElement() {
        return registerLink;
    }

    @Step("Ожидание видимости гипертекста регистрации")
    public WebElement waitForRegisterLinkElement() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(getRegisterLinkElement()));
    }

    @Step("Скролл до гипертекста регистрации")
    public void navigateToRegisterLinkElement() {
        WebElement registerLinkElement = waitForRegisterLinkElement();
        scrollToElement(registerLinkElement);
    }

    @Step("Клик по тексту 'Зарегистрироваться'")
    public void clickRegisterLink() {
        WebElement registerlink = webDriver.findElement(registerLink);
        registerlink.click();
    }


    @Step("Ввод информации для регистрации")
    public void enterRegistrationInfo(String name, String email, String password) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(inputFields));
        webDriver.findElements(inputFields).get(0).sendKeys(name);
        webDriver.findElements(inputFields).get(1).sendKeys(email);
        webDriver.findElements(inputFields).get(2).sendKeys(password);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickRegisterButton() {
        WebElement registerbutton = webDriver.findElement(registerButton);
        registerbutton.click();
    }


    @Step("Ожидание видимости заголовка Вход")
    public WebElement waitForEnterTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(enterTitle));
    }


    @Step("Прокрутка вниз до элемента")
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public By getLogInButtonUnderRegistryFormElement() {
        return logInButtonUnderRegistryForm;
    }

    @Step("Клик по кнопке под формой регистрации")
    public void clickLogInButtonUnderRegistryFormElement() {
        WebElement logInButtonUnderRegistryFormElement = webDriver.findElement(logInButtonUnderRegistryForm);
        logInButtonUnderRegistryFormElement.click();
    }

    @Step("Ожидание видимости кнопки под формой регистрации")
    public WebElement waitForLogInButtonUnderRegistryForm() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(getLogInButtonUnderRegistryFormElement()));
    }

    @Step("Получение сообщения об ошибке Некорректный пароль")
    public String getWrongPasswordErrorMessage() {
        return webDriver.findElement(wrongPasswordErrorMessage).getText();
    }

    @Step("Проверка отображения сообщения о некорректном пароле")
    public boolean isWrongPasswordErrorMessageDisplayed(){
        return webDriver.findElement(wrongPasswordErrorMessage).isDisplayed();
    }
}