package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {

    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    /// Элементы
    // Поле имя
    private static final By NAME_INPUT = By.xpath("//*[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder = '* Имя']");
    // Поле Фамилия
    private static final By LASTNAME_INPUT = By.xpath("//*[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder = '* Фамилия']");
    // Поле Адрес
    private static final By ADDRESS_INPUT = By.xpath("//*[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder = '* Адрес: куда привезти заказ']");
    // Поле станция метро
    private static final By METRO_LIST_INPUT = By.xpath("//*[@class='select-search__input' and @placeholder = '* Станция метро']");
    // Кнопка Сокольники //*[@class = 'Order_SelectOption__82bhS select-search__option' and @value = '4'] // для других станций меняем value
    // Поле телефон
    private static final By PHONE_INPUT = By.xpath("//*[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder = '* Телефон: на него позвонит курьер']");
    // Кнопка далее
    private static final By NEXT_BUTTON = By.xpath("//*[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    // Дата когда привезти самокат
    private static final By DATE_INPUT = By.xpath("//*[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder = '* Когда привезти самокат']");
    // Срок аренды
    private static final By PERIOD_INPUT = By.xpath("//*[@class='Dropdown-placeholder' and text() = '* Срок аренды']");
    // Длительность //*[@class = 'Dropdown-option' and text() = 'сутки']"
    // Цвет чёрный жемчуг
    private static final By BLACK_COLOR = By.id("black");
    // Цвет серая безысходность
    private static final By GREY_COLOR = By.id("grey");
    // Комментарий для курьера
    private static final By COMMENT_INPUT = By.xpath("//*[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder = 'Комментарий для курьера']");
    // Кнопка назад
    private static final By BACK_BUTTON = By.xpath("//*[@class='Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i']");
    // Кнопка заказать
    private static final By MAKE_ORDER_BUTTON = By.xpath("//*[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    // Кнопка да в окне Хотите оформить заказ?
    private static final By YES_BUTTON = By.xpath("//*[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Да']");
    // Кнопка нет в окне Хотите оформить заказ?
    private static final By NO_BUTTON = By.xpath("//*[@class = 'Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i' and text() = 'Нет']");
    // Окно Заказ оформлен
    private static final By ORDER_MADE_WINDOW = By.xpath("//*[@class='Order_ModalHeader__3FDaJ' and contains(normalize-space(.), 'Заказ оформлен')]");

    /// Методы
    public void fillName(String text) {
        driver.findElement(NAME_INPUT).sendKeys(text);
    }

    public void fillLastname(String text) {
        driver.findElement(LASTNAME_INPUT).sendKeys(text);
    }

    public void fillAddress(String text) {
        driver.findElement(ADDRESS_INPUT).sendKeys(text);
    }

    public void clickMetro(int stationIndex) {
        driver.findElement(METRO_LIST_INPUT).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement metroStation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class = 'Order_SelectOption__82bhS select-search__option' and @value = '" + stationIndex + "']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", metroStation);
        metroStation.click();
    }
    public void fillPhone(String text) {
        driver.findElement(PHONE_INPUT).sendKeys(text);
    }

    public void clickNextButton() {
        driver.findElement(NEXT_BUTTON).click();
    }

    public void fillDate(String ddmmyyyy) {
        driver.findElement(DATE_INPUT).sendKeys(ddmmyyyy);
        driver.findElement(By.xpath("//*[contains(@class, 'react-datepicker') and text() = '" + ddmmyyyy.substring(0, 2) + "']")).click();
    }

    public void chooseRentPeriod(int days) {
        driver.findElement(PERIOD_INPUT).click();
        String rentPeriod = getDaysOfRent(days);
        driver.findElement(By.xpath("//*[@class = 'Dropdown-option' and text() = '" + rentPeriod + "']")).click();
    }

    public void clickBlack() {
        driver.findElement(BLACK_COLOR).click();
    }

    public void clickGrey() {
        driver.findElement(GREY_COLOR).click();
    }

    public void fillComment(String text) {
        if (text != null && !text.isEmpty()) {
            driver.findElement(COMMENT_INPUT).sendKeys(text);
        }
    }
    public void clickMakeOrder() {
        driver.findElement(MAKE_ORDER_BUTTON).click();
    }
    public void clickYes() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(YES_BUTTON));
        element.click();
    }
    public void clickNo() {
        driver.findElement(NO_BUTTON).click();
    }
    public boolean isOrderMadeWindowVisible() {
        return driver.findElement(ORDER_MADE_WINDOW).isDisplayed();
    }

    private String getDaysOfRent(int days) {
        switch (days) {
            case 1:
                return "сутки";
            case 2:
                return "двое суток";
            case 3:
                return "трое суток";
            case 4:
                return "четверо суток";
            case 5:
                return "пятеро суток";
            case 6:
                return "шестеро суток";
            case 7:
                return "семеро суток";
            default:
                throw new IllegalArgumentException("Invalid days value: " + days);
        }
    }
    public void selectColor(String color) {
        if (color.equalsIgnoreCase("black")) {
            clickBlack();
        } else if (color.equalsIgnoreCase("grey")) {
            clickGrey();
        } else if (color.equalsIgnoreCase("both")) {
            clickBlack();
            clickGrey();
        } else if (color.equalsIgnoreCase("none")) {

        } else {
            throw new IllegalArgumentException("Invalid color value: " + color);
        }
    }
}
