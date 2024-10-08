package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class OrderConfirmationPage {

    private final WebDriver driver;

    //Кнопка "Да" на странице подтверждения заказа
    private final By yesBtn = By.xpath(".//button[text()='Да']");

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Подтвердить заказ
    public SuccessOrderPage confirmOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT)).until(ExpectedConditions.elementToBeClickable(yesBtn));
        driver.findElement(yesBtn).click();
        return new SuccessOrderPage(driver);
    }
}
