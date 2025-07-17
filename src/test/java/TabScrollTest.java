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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TabScrollTest {

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
    public void shouldDisplayBunsSectionWhenClicked() throws InterruptedException {
        mainPage.waitForSaucesTab();
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();
        assertEquals("Не сработал переход к разделу Булки", "Булки", mainPage.fetchActiveTabLabel());
    }
    @Test
    public void shouldShowSaucesSectionOnTabClick() throws InterruptedException {
        mainPage.waitForSaucesTab();
        mainPage.clickSaucesTab();
        assertEquals("Не сработал переход к разделу Соусы", "Соусы", mainPage.fetchActiveTabLabel());
    }

    @Test
    public void shouldOpenFillingsSectionCorrectly() throws InterruptedException {
        mainPage.waitForFillingsTab();
        mainPage.clickFillingsTab();
        assertEquals("Не сработал переход к разделу Начинки", "Начинки", mainPage.fetchActiveTabLabel());
    }

    @After
    public void tearDown() {
        // Закрываем браузер
        if (driver != null) {
            driver.quit();
        }
    }
}