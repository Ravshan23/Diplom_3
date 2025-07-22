import config.BrowserFactory;
import config.TestConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.MainPage;
import java.time.Duration;
import static org.junit.Assert.assertEquals;

public class TabScrollTest {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void init() {
        //Настройка браузера и открытие страницы
        driver = BrowserFactory.getWebDriver();

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