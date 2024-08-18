package praktikum.additionalTests;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.DriverRule;
import praktikum.EnvConfig;
import praktikum.pages.MainPage;

import static org.junit.Assert.assertEquals;

public class YandexLogoTest {

    @Rule
    public DriverRule factory = new DriverRule();

    @Test
    public void openMainPage() throws Exception {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);

        mainPage.open();

        mainPage.yandexLogoClick();
        Thread.sleep(3000);
        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);

        String newURL=driver.getCurrentUrl();
        assertEquals(EnvConfig.YANDEX_URL,newURL);
    }
}

