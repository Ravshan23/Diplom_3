package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private WebDriverWait wait;

    // Локатор кнопки "Конструктор"
    private final By constructorButton = By.xpath("//a[@class='AppHeader_header__link__3D_hX']");

    // Локатор логотипа
    private final By logo = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']//a");

    // Локатор текста "Соберите бургер"
    private final By assembleBurgerText = By.xpath("//h1[contains(@class, 'text_type_main-large') and text()='Соберите бургер']");

    // Локаторы для табов
    private final By bunsTab = By.xpath("//div[contains(@class, 'tab_tab__1SPyG')][1]"); // Первый таб
    private final By saucesTab = By.xpath("//div[contains(@class, 'tab_tab__1SPyG')][2]"); // Второй таб
    private final By fillingsTab = By.xpath("//div[contains(@class, 'tab_tab__1SPyG')][3]"); // Третий таб
    private By activeTab = By.xpath(".//div[contains(@class,'current')]/span");

    // Локаторы для первых ингредиентов в каждом разделе

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Ожидание видимости кнопки 'Конструктор'")
    public WebElement waitUntilConstructorButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(constructorButton));
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Клик по логотипу")
    public void clickLogo() {
        driver.findElement(logo).click();
    }

    @Step("Ожидание видимости Логотипа'")
    public WebElement waitForLogo() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logo));
    }

    @Step("Ожидание видимости текста 'Соберите бургер'")
    public WebElement waitForAssembleBurgerText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(assembleBurgerText));
    }

    @Step("Проверка текста 'Соберите бургер'")
    public boolean isAssembleBurgerTextDisplayed() {
        WebElement assembleBurgerText = waitForAssembleBurgerText();
        return assembleBurgerText.getText().equals("Соберите бургер");
    }


    @Step("Клик по табу Булки")
    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }

    @Step("Ожидание видимости таба Соусы")
    public WebElement waitForSaucesTab() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(saucesTab));
    }

    @Step("Клик по табу Соусы")
    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    @Step("Ожидание видимости таба Начинки")
    public WebElement waitForFillingsTab() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsTab));
    }

    @Step("Клик по табу Начинки")
    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
    }


    @Step("Чтение названия выбранной вкладки")
    public String fetchActiveTabLabel() throws InterruptedException {
        Thread.sleep(1500);
        return driver.findElement(activeTab).getText();
    }
    }

