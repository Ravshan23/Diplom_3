import client.StellarBurgerClient;
import config.BrowserFactory;
import config.TestConfig;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.AuthorizationPage;
import pageobject.RegisterPage;
import pageobject.SetNewPasswordPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class AuthorizationPageTest {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationPageTest.class);

    private WebDriver driver;
    private WebDriverWait wait;
    private AuthorizationPage authorizationPage;
    private RegisterPage registryPage;
    private SetNewPasswordPage recoverPasswwordPage;
    private StellarBurgerClient stellarBurgerClient;
    private String accessToken; // Для хранения токена созданного пользователя
    private String email; // Для хранения email пользователя
    private String password; // Для хранения пароля пользователя

    @Before
    public void prepareEnvironment() {
        //Запуск браузера
        driver = BrowserFactory.getWebDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Переход на стартовую страницу
        driver.get(TestConfig.BASE_URI);

        // Подключение к page object'ам
        authorizationPage = new AuthorizationPage(driver);
        registryPage = new RegisterPage(driver);
        recoverPasswwordPage = new SetNewPasswordPage(driver);

        // Создание клиента для работы с API
        stellarBurgerClient = new StellarBurgerClient(TestConfig.BASE_URI);

        //  Подготовка пользователя для теста
        createUserForTest();
    }

    @Step("Создание нового пользователя")
    private void createUserForTest() {
        // Генерация учётных данных
        email = "testuser" + System.currentTimeMillis() + "@example.com";
        password = "password123";
        User user = new User(email, password, "Test User");

        // Отправка запроса на регистрацию
        accessToken = stellarBurgerClient.registerUser(user)
                .extract()
                .path("accessToken");
    }

    @Test
    @DisplayName("Проверка входа по кнопке «Войти в аккаунт» на главной")
    public void shouldLoginViaMainPageButton() {
        logger.info("Запуск сценария: вход через кнопку на главной");

        // Шаг 1:Нажатие кнопки входа на главной
        logger.debug("Нажатие на кнопку 'Войти в аккаунт'");
        authorizationPage.pressLogInToAccount();
        authorizationPage.doLogin(email, password);

        // Шаг 5:Проверка доступности кнопки оформления заказа
        logger.debug("Проверка текста кнопки 'Оформить заказ'");
        assertTrue(authorizationPage.checkOrderButtonIsHere());


        logger.info("Сценарий выполнен успешно");
    }

    @Test
    @DisplayName("Проверка входа через кнопку 'Личный кабинет'")
    public void shouldLoginThroughProfileButton() {
        registryPage.waitForLKButton();
        registryPage.clickLKButton();
        authorizationPage.doLogin(email, password);
        assertTrue(authorizationPage.checkOrderButtonIsHere());
    }

    @Test
    @DisplayName("Проверка входа через кнопку в форме регистрации")
    public void shouldLoginFromRegistrationPage() {
        registryPage.waitForLKButton();
        registryPage.clickLKButton();
        registryPage.navigateToRegisterLinkElement();
        registryPage.clickRegisterLink();
        registryPage.waitForLogInButtonUnderRegistryForm();
        registryPage.clickLogInButtonUnderRegistryFormElement();
        authorizationPage.doLogin(email, password);
        assertTrue(authorizationPage.checkOrderButtonIsHere());
    }

    @Test
    @DisplayName("Проверка входа через кнопку в форме восстановления пароля")
    public void shouldLoginFromPasswordRecoveryPage() {
        registryPage.waitForLKButton();
        registryPage.clickLKButton();
        recoverPasswwordPage.navigateToRecoverPasswordLinkElement();
        recoverPasswwordPage.clickRecoverPasswordLink();
        recoverPasswwordPage.navigateToLogInLinkOnRecoverPasswordPage();
        recoverPasswwordPage.clickLogInLinkOnRecoverPasswordPage();
        authorizationPage.doLogin(email, password);
        assertTrue(authorizationPage.checkOrderButtonIsHere());
    }

    @After
    public void shutDown() {
        // Удаление тестового пользователя
        if (accessToken != null) {
            stellarBurgerClient.deleteUser(accessToken);
        }

        // Закрытие браузера
        if (driver != null) {
            driver.quit();
        }
    }
}