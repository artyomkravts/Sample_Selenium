import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class BaseTest {
    WebDriver driver;

    @Before // MainOrderScenario
    public void setUp() {
        driver = getDriver(Browser.CHROME);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @After
    public void after() {
        driver.quit();
    }


    WebDriver getDriver(Browser browser) {
        switch (browser) {
            case CHROME:
                ChromeOptions chromeOptions  = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                return new ChromeDriver(chromeOptions);
            case FIREFOX:
                FirefoxOptions firefoxOptions  = new FirefoxOptions();
                firefoxOptions .addArguments("--no-sandbox", "--disable-dev-shm-usage");
                return new FirefoxDriver(firefoxOptions);
            default:
                throw new RuntimeException("unable to create web driver");
        }
    }
}

enum Browser {
    CHROME, FIREFOX;
}
