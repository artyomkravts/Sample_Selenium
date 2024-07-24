import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.MainPage;

import java.time.Duration;

@RunWith(Parameterized.class)
public class DropdownListParameterizedTest {

    private WebDriver driver;

    private int index;
    private String text;

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }
    public DropdownListParameterizedTest(int index, String text) {
        this.index = index;
        this.text = text;
    }

    /* Создаём приватное поле driver и экземпляр веб драйвера каждому тесту для:
     * 1) инкапсуляции
     * 2) того, чтобы тесты использовали каждый свой драйвер и не аффектили друг друга
     * 3) экономии ресурсов
     */
    @Before
    public void setUp() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void after() {
        driver.quit();
    }

    @Test
    public void checkAccordionText_sampleText_matches() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickAccordionHeading(index);
        Assert.assertEquals(text, mainPage.getTextOfAccordionPanel(index));
    }
}
