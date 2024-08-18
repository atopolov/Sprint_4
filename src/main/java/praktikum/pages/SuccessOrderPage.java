package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class SuccessOrderPage {

    private final WebDriver driver;

    private final By orderSuccessResult = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");

    public SuccessOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getOrderHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderSuccessResult));
        String[] header = driver.findElement(orderSuccessResult).getText().split("\n");
        return header[0];
    }


}
