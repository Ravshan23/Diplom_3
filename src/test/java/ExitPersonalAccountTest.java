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
import pageobject.PersonalAccountPage;
import pageobject.RegisterPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class ExitPersonalAccountTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private AuthorizationPage authorizationPage;
    private RegisterPage registryPage;
    private PersonalAccountPage personalAccountPage;
    private StellarBurgerClient stellarBurgerClient;
    private String accessToken; // Токен авторизованного пользователя
    private String email; // Email тестового пользователя
    private String password; // Пароль тестового пользователя

    @Before
    public void initializeEnvironment() {
        //Настройка драйвера и загрузка страницы
        driver = BrowserFactory.getWebDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(TestConfig.BASE_URI);

        authorizationPage = new AuthorizationPage(driver);
        registryPage = new RegisterPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
        stellarBurgerClient = new StellarBurgerClient(TestConfig.BASE_URI);
        registerAndLoginUser();
    }

    @Step("Регистрация и логин пользователя")
    private void registerAndLoginUser() {
        // Создание данных пользователя
        email = "testuser" + System.currentTimeMillis() + "@example.com";
        password = "password123";
        User user = new User(email, password, "Test User");

        // Регистрация пользователя через API
        accessToken = stellarBurgerClient.registerUser(user)
                .extract()
                .path("accessToken");

        registryPage.waitForLKButton();
        registryPage.clickLKButton();
        authorizationPage.typeEmail(email);
        authorizationPage.typePassword(password);
        authorizationPage.pressLoginButton();
        registryPage.clickLKButton();
    }

    @Test
    @DisplayName("Проверка разлогина при нажатии на Выход в ЛК")
    public void testLogOut() {
        personalAccountPage.waitForLogoutButton();
        personalAccountPage.clickLogoutButton();
        registryPage.waitForEnterTitle();
        assertTrue(registryPage.waitForEnterTitle().isDisplayed());
    }

    @After
    public void tearDown() {
        // Удаление пользователя через API
        if (accessToken != null) {
            stellarBurgerClient.deleteUser(accessToken);
        }

        // Закрытие браузера
        if (driver != null) {
            driver.quit();
        }
    }
}
