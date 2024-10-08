package praktikum.additionalTests;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.DriverRule;
import praktikum.EnvConfig;
import praktikum.pages.MainPage;

import static org.junit.Assert.assertEquals;

public class ScooterLogoTest {

    @Rule
    public DriverRule factory = new DriverRule();

    @Test
    public void openMainPage() throws Exception {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);

        mainPage.open();

        mainPage.scooterLogoClick();

        String newURL=driver.getCurrentUrl();
        assertEquals(EnvConfig.BASE_URL,newURL);

    }
}