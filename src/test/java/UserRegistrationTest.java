import client.StellarBurgerClient;
import config.BrowserFactory;
import config.TestConfig;
import io.qameta.allure.junit4.DisplayName;
import model.Credentials;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.RegisterPage;

public class UserRegistrationTest {

    private WebDriver driver;
    private RegisterPage registerPage;
    private StellarBurgerClient stellarBurgerClient;
    private String accessToken; // Для хранения токена созданного пользователя

    @Before
    public void init() {
        driver = BrowserFactory.getWebDriver();
        driver.get(TestConfig.BASE_URI);
        registerPage = new RegisterPage(driver);
        stellarBurgerClient = new StellarBurgerClient(TestConfig.BASE_URI);
    }

    @Test
    @DisplayName("Регистрация нового пользователя с валидными данными")
    public void shouldRegisterSuccessfully() {
        registerPage.clickLKButton();
        registerPage.clickRegisterLink();
        registerPage.enterRegistrationInfo("Иван", "ivan34567891@example.com", "123456");
        registerPage.clickRegisterButton();

        // Устойчивый способ проверки заголовка
        Assert.assertTrue("Заголовок 'Вход' не отображается", registerPage.isEnterTitleDisplayed());
    }

    @Test
    @DisplayName("Ошибка при регистрации с коротким паролем")
    public void shouldShowErrorForShortPassword() {

        registerPage.clickLKButton();
        registerPage.clickRegisterLink();
        registerPage.enterRegistrationInfo("Иван", "ivan34567891@example.com", "123");
        registerPage.clickRegisterButton();
        registerPage.getWrongPasswordErrorMessage();
        Assert.assertTrue(registerPage.isWrongPasswordErrorMessageDisplayed());
    }


    @After
    public void cleanUp() {
        // Получаем токен пользователя через API, чтобы затем удалить
        Credentials credentials = new Credentials("ivan34567891@example.com", "123456");
        accessToken = stellarBurgerClient.loginUser(credentials)
                .extract()
                .path("accessToken");
        // Удаление пользователя, если он был создан
        if (accessToken != null) {
            stellarBurgerClient.deleteUser(accessToken);
        }

        // Закрытие браузера после теста
        if (driver != null) {
            driver.quit();
        }
    }
}