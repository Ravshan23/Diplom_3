package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserFactory {

    // Путь к установленному Yandex Browser берётся из переменной окружения
    private static final String YANDEX_BROWSER_PATH = System.getenv("YANDEX_BROWSER_PATH");

    public static WebDriver getWebDriver() {
        switch (TestConfig.BROWSER.toUpperCase()) {
            case "CHROME":
                return initChrome();
            case "YANDEX":
                return initYandex();
            default:
                throw new IllegalArgumentException("Указан неизвестный браузер: " + TestConfig.BROWSER);
        }
    }

    // Запуск драйвера для Chrome
    private static WebDriver initChrome() {
        WebDriverManager.chromedriver().setup();  // Установка chromedriver автоматически
        ChromeOptions chromeOptions = new ChromeOptions();
        return new ChromeDriver(chromeOptions);
    }

    // Запуск драйвера для Яндекс.Браузера
    private static WebDriver initYandex() {
        WebDriverManager.chromedriver().setup();  // Используем тот же chromedriver
        ChromeOptions yandexOptions = new ChromeOptions();

        // Подключение исполняемого файла Yandex Browser
        yandexOptions.setBinary(YANDEX_BROWSER_PATH);


        return new ChromeDriver(yandexOptions);
    }
}