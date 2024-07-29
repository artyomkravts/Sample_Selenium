import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pages.MainPage;
import pages.OrderPage;

import java.time.Duration;

@RunWith(Parameterized.class)
public class MainOrderScenarioTest extends BaseTest{

    private String orderButton;
    private String name;
    private String lastname;
    private String address;
    private int stationIndex;
    private String phoneNumber;
    private String ddmmyyyy;
    private int daysOfRent;
    private String color; // black / grey / both
    private String comment;
    private boolean expectedResult;

    public MainOrderScenarioTest(String orderButton, String name, String lastname, String address, int stationIndex, String phoneNumber, String ddmmyyyy, int daysOfRent, String color, String comment, boolean expectedResult) {
        this.orderButton = orderButton;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.stationIndex = stationIndex;
        this.phoneNumber = phoneNumber;
        this.ddmmyyyy = ddmmyyyy;
        this.daysOfRent = daysOfRent;
        this.color = color;
        this.comment = comment;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"upper", "Артём", "Кравцов", "Москва, д.5", 4, "79255151876", "25.07.2025", 2, "grey", null, true},
                {"upper","Артём", "Кравцов", "Москва, д.5", 4, "79255151876", "10.07.2024", 2, "grey", null, false},
                {"lower","Артём", "Кравцов", "Москва, д.5", 10, "89255151876", "25.07.2025", 2, "both", null, true},
                {"lower","Артём", "Кравцов", "Москва, д.5", 10, "89255151876", "25.07.2025", 2, "none", null, false},
        };
    }
    @Before

    @After


    @Test
    public void mainOrderScenario() {
        OrderPage orderPage = new OrderPage(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickOrderButton(orderButton);
        orderPage.fillName(name);
        orderPage.fillLastname(lastname);
        orderPage.fillAddress(address);
        orderPage.clickMetro(stationIndex); // 4 - Сокольники
        orderPage.fillPhone(phoneNumber);
        orderPage.clickNextButton();
        orderPage.fillDate(ddmmyyyy);
        orderPage.chooseRentPeriod(daysOfRent);
        orderPage.selectColor(color);
        orderPage.fillComment(comment);
        orderPage.clickMakeOrder();
        orderPage.clickNo();
        orderPage.clickMakeOrder();
        orderPage.clickYes();

        Assert.assertEquals("Видимость окна подтверждения заказа не совпадает с ожидаемой для данных: " +
                "name=" + name +
                ", lastname=" + lastname +
                ", address=" + address +
                ", stationIndex=" + stationIndex +
                ", phoneNumber=" + phoneNumber +
                ", ddmmyyyy=" + ddmmyyyy +
                ", daysOfRent=" + daysOfRent +
                ", color=" + color +
                ", comment=" + comment, expectedResult, orderPage.isOrderMadeWindowVisible());
    }


}
