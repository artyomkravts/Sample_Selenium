package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {

    private WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    // Поле для номера заказа в окне после нажатия Go!
    private static final By INPUT_ORDER_NUMBER_ON_SEARCH_PAGE = By.xpath("//*[@class='Input_Input__1iN_Z Track_Input__1g7lq Input_Responsible__1jDKN']");
    // Кнопка Посмотреть -- в окне после нажатия Го
    private static final By SEARCH_BUTTON_ON_SEARCH_PAGE = By.xpath("private static final By");
    // Кнопка Отменить заказ
    private static final By CANCEL_ORDER_BUTTON = By.xpath("//*[@class='Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i']");
    // Кнопка Посмотреть
    private static final By CHECK_ORDER_BUTTON = By.xpath("//*[@class='Button_Button__ra12g Button_Middle__1CSJM']");


    public boolean isNoSuchOrderPageVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement cancelOrderButton = wait.until(ExpectedConditions.presenceOfElementLocated(CANCEL_ORDER_BUTTON));
            if (cancelOrderButton.isDisplayed()) {
                return false;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Cancel Order Button is not present in the DOM");
        } catch (Exception e) {
            e.printStackTrace();
        }
        WebElement checkOrderButton = wait.until(ExpectedConditions.visibilityOfElementLocated(CHECK_ORDER_BUTTON));
        return checkOrderButton.isDisplayed();
    }
}
