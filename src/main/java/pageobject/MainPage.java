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

    // Локаторы для заголовков разделов
    private final By bunsHeader = By.xpath("//h2[text()='Булки']");
    private final By saucesHeader = By.xpath("//h2[text()='Соусы']");
    private final By fillingsHeader = By.xpath("//h2[text()='Начинки']");

    // Локаторы для первых ингредиентов в каждом разделе
    private final By bunsIngredient = By.xpath("//h2[text()='Булки']/following-sibling::ul//a[contains(@class, 'BurgerIngredient_ingredient__1TVf6')]");
    private final By saucesIngredient = By.xpath("//h2[text()='Соусы']/following-sibling::ul//a[contains(@class, 'BurgerIngredient_ingredient__1TVf6')]");
    private final By fillingsIngredient = By.xpath("//h2[text()='Начинки']/following-sibling::ul//a[contains(@class, 'BurgerIngredient_ingredient__1TVf6')]");
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

    @Step("Ожидание видимости таба Булки")
    public WebElement waitForBunsTab() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(bunsTab));
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






    @Step("Ожидание видимости заголовка 'Булки'")
    public WebElement waitForBunsHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(bunsHeader));
    }

    @Step("Проверка текста заголовка 'Булки'")
    public boolean isBunsHeaderDisplayed() {
        WebElement fillingsHeader = waitForBunsHeader();
        return fillingsHeader.getText().equals("Булки");
    }

    @Step("Ожидание видимости заголовка 'Соусы'")
    public WebElement waitForSaucesHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(saucesHeader));
    }

    @Step("Проверка текста заголовка 'Соусы'")
    public boolean isSaucesHeaderDisplayed() {
        WebElement fillingsHeader = waitForSaucesHeader();
        return fillingsHeader.getText().equals("Соусы");
    }


    @Step("Ожидание видимости заголовка 'Начинки'")
    public WebElement waitForFillingsHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsHeader));
    }

    @Step("Проверка текста заголовка 'Начинки'")
    public boolean isFillingsHeaderDisplayed() {
        WebElement fillingsHeader = waitForFillingsHeader();
        return fillingsHeader.getText().equals("Начинки");
    }




    @Step("Ожидание видимости ингредиента 'Булки'")
    public WebElement waitForBunsIngredient() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(bunsIngredient));
    }
    @Step("Проверка отображения ингредиента 'Булки'")
    public boolean isBunsIngredientDisplayed() {
        return waitForBunsIngredient().isDisplayed();
    }

    @Step("Ожидание видимости ингредиента 'Соусы'")
    public WebElement waitForSaucesIngredient() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(saucesIngredient));
    }
    @Step("Проверка отображения ингредиента 'Соусы'")
    public boolean isSaucesIngredientDisplayed() {
        return waitForSaucesIngredient().isDisplayed();
    }

    @Step("Ожидание видимости ингредиента 'Начинки'")
    public WebElement waitForFillingsIngredient() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsIngredient));
    }
    @Step("Проверка отображения ингредиента 'Начинки'")
    public boolean isFillingsIngredientDisplayed() {
        return waitForFillingsIngredient().isDisplayed();
    }

    @Step("Прокрутка вниз до элемента")
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }


    @Step("Проверка, находится ли элемент в пределах viewport")
    public boolean isElementInViewport(WebElement element) {
        // Получаем координаты и размеры элемента
        org.openqa.selenium.Rectangle rect = element.getRect();

        // Получаем размеры окна браузера
        int windowWidth = driver.manage().window().getSize().getWidth();
        int windowHeight = driver.manage().window().getSize().getHeight();

        // Проверяем, что элемент полностью находится в пределах viewport
        boolean isInViewport = rect.getX() >= 0
                && rect.getY() >= 0
                && rect.getX() + rect.getWidth() <= windowWidth
                && rect.getY() + rect.getHeight() <= windowHeight;

        return isInViewport;
    }
}