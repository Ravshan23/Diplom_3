import config.BrowserFactory;
import config.TestConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.MainPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class TabScrollTest {
    private static final Logger logger = LoggerFactory.getLogger(TabScrollTest.class);

    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;

    @Before
    public void init() {
        //Настройка браузера и открытие страницы
        driver = BrowserFactory.getWebDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize(); // Максимизировать окно браузера
        driver.get(TestConfig.BASE_URI);
        mainPage = new MainPage(driver);
    }

    @Test
    public void verifyBunsTabScroll() {
        // Сначала переключаемся на "Соусы", затем возвращаемся на "Булки"
        mainPage.clickSaucesTab();
        mainPage.waitForBunsTab();
        mainPage.clickBunsTab();
        mainPage.waitForBunsHeader();
        assertTrue(mainPage.isBunsHeaderDisplayed());

        //  Проверка, что заголовок раздела "Булки" отображается
        mainPage.waitForBunsIngredient();
        assertTrue(mainPage.isBunsIngredientDisplayed());
        // Проверка видимости первого ингредиента и его положения на экране
        assertTrue(mainPage.isElementInViewport(mainPage.waitForBunsIngredient()));
    }

    @Test
    public void verifySaucesTabScroll() {
        // Сначала кликаем по "Начинкам", затем переходим в "Соусы"
        mainPage.waitForFillingsTab();
        mainPage.clickFillingsTab();
        mainPage.waitForSaucesTab();
        mainPage.clickSaucesTab();
        mainPage.waitForSaucesHeader();
        assertTrue(mainPage.isSaucesHeaderDisplayed());
        // Ожидаем, что первый ингредиент в разделе "Соусы" станет видимым
        mainPage.waitForSaucesIngredient();
        assertTrue(mainPage.isSaucesIngredientDisplayed());
        // Проверяем, что ингредиент находится в пределах viewport
        assertTrue(mainPage.isElementInViewport(mainPage.waitForSaucesIngredient()));
    }

    @Test
    public void verifyFillingsTabScroll() {
        // Для смены раздела: сначала "Соусы", затем "Начинки"
        mainPage.waitForSaucesTab();
        mainPage.clickSaucesTab();
        mainPage.waitForFillingsTab();
        mainPage.clickFillingsTab();

        // Проверка наличия заголовка и ингредиента в блоке "Начинки"
        mainPage.waitForFillingsHeader();
        mainPage.isFillingsHeaderDisplayed();
        mainPage.waitForFillingsIngredient();
        assertTrue(mainPage.isFillingsIngredientDisplayed());
        // Скролл к элементу и проверка, что он виден на экране
        mainPage.scrollToElement(mainPage.waitForFillingsIngredient());
        assertTrue(mainPage.isElementInViewport(mainPage.waitForFillingsIngredient()));
    }

    @After
    public void tearDown() {
        // Закрываем браузер
        if (driver != null) {
            driver.quit();
        }
    }
}