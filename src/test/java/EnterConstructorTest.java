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
import pageobject.AuthorizationPage;
import pageobject.RegisterPage;
import pageobject.MainPage;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

public class EnterConstructorTest {

    private WebDriver driver;
    private AuthorizationPage authorizationPage;
    private RegisterPage registryPage;
    private MainPage mainPage;
    private StellarBurgerClient stellarBurgerClient;
    private String accessToken; // Токен, полученный после регистрации
    private String email; // Email для авторизации
    private String password; // Пароль для авторизации

    @Before
    public void initBrowserAndRegisterUser() {
        driver = BrowserFactory.getWebDriver();

        try {
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60)); // если не задано
            driver.get(TestConfig.BASE_URI);
            System.out.println("Page loaded: " + TestConfig.BASE_URI);
        } catch (TimeoutException e) {
            System.err.println("Page did not load in time: " + e.getMessage());
            throw e;
        }

        authorizationPage = new AuthorizationPage(driver);
        registryPage = new RegisterPage(driver);
        mainPage = new MainPage(driver);
        stellarBurgerClient = new StellarBurgerClient(TestConfig.BASE_URI);
        registerAndLoginUser();
    }

    @Step("Авторизация пользователя через интерфейс")
    private void registerAndLoginUser() {
        email = "testuser" + System.currentTimeMillis() + "@example.com";
        password = "password123";
        User user = new User(email, password, "Test User");
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
    @DisplayName("Переход в раздел конструктора через основную кнопку")
    public void shouldOpenConstructorViaButton() {
        mainPage.waitUntilConstructorButton();
        mainPage.clickConstructorButton();
        mainPage.waitForAssembleBurgerText();
        assertTrue(mainPage.isAssembleBurgerTextDisplayed());
    }

    @Test
    @DisplayName("Переход в раздел конструктора через логотип")
    public void shouldOpenConstructorViaLogo() {
        mainPage.waitForLogo();
        mainPage.clickLogo();
        mainPage.waitForAssembleBurgerText();
        assertTrue(mainPage.isAssembleBurgerTextDisplayed());
    }

    @After
    public void closeBrowserAndDeleteUser() {
        // Удаление тестового пользователя после выполнения тестов
        if (accessToken != null) {
            stellarBurgerClient.deleteUser(accessToken);
        }

        // Закрытие браузера
        if (driver != null) {
            driver.quit();
        }
    }
}