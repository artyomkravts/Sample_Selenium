package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    /// Элементы
    // URL
    public static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    // Верхняя кнопка заказать
    private static final By UPPER_ORDER_BUTTON = By.className("Button_Button__ra12g");
    // Нижняя кнопка заказать
    private static final By LOWER_ORDER_BUTTON = By.xpath("//*[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    // Кнопка Яндекс
    private static final By YANDEX_BUTTON = By.className("Header_LogoYandex__3TSOI");
    // Кнопка Самокат
    private static final By SAMOKAT_BUTTON = By.className("Header_LogoScooter__3lsAR");
    // Кнопка статус заказа
    private static final By ORDER_STATUS_BUTTON = By.className("Header_Link__1TAG7");
    // Поле введите номер заказа
    private static final By INPUT_ORDER_NUMBER = By.xpath("//*[@class='Input_Input__1iN_Z Header_Input__xIoUq']");
    // Кнопка Go!
    private static final By GO_BUTTON = By.xpath("//*[@class='Button_Button__ra12g Header_Button__28dPO']");

    /// Методы
    // Для аккордеонов
    public void open() {
        driver.get(PAGE_URL);
    }
    public void clickAccordionHeading(int index) {
        By ACCORDION_HEADING_ = By.id("accordion__heading-" + index);
        WebElement accordion = driver.findElement(ACCORDION_HEADING_);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", accordion);
        accordion.click();
    }
    // Текст внутри аккордеона
    public String getTextOfAccordionPanel(int index) {
        By ACCORDION_PANEL_ = By.id("accordion__panel-" + index);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(ACCORDION_PANEL_));
        return element.getText();
    }
    // Для заказа
    private void clickUpperOrderButton() {
        WebElement upperOrderButton = driver.findElement(UPPER_ORDER_BUTTON);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", upperOrderButton);
        upperOrderButton.click();
    }
    private void clickLowerOrderButton() {
        WebElement lowerOrderButton = driver.findElement(LOWER_ORDER_BUTTON);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lowerOrderButton);
        lowerOrderButton.click();
    }
    public void clickOrderButton(String upperORlower) {
        if (upperORlower == "upper") clickUpperOrderButton();
        else if (upperORlower == "lower") clickLowerOrderButton();
        else throw new IllegalArgumentException("Choose either upper or lower");
    }
    public void clickSamokatButton() {
        driver.findElement(SAMOKAT_BUTTON).click();
    }
    public void clickYandexButton() {
        driver.findElement(YANDEX_BUTTON).click();
    }
    public void clickOrderStatusButton() {
        driver.findElement(ORDER_STATUS_BUTTON).click();
    }
    public void fillOrderStatusInput(String orderNumber) {
        driver.findElement(INPUT_ORDER_NUMBER).sendKeys(orderNumber);
    }
    public void clickGoButton() {
        driver.findElement(GO_BUTTON).click();
    }
}
