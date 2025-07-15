package config;

public class TestConfig {
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site/";

    // Браузер для запуска тестов. По умолчанию Chrome.
    public static final String BROWSER = System.getProperty("browser", "CHROME");
}
