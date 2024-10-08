package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;
import java.util.Random;

public class AboutRentOrderPage {

    private final WebDriver driver;

    //Поле выбора даты "Когда привезти самокат"
    private final By deliveryDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Поле ввода комментария для курьера
    private final By description = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Поле "Срок аренды"
    private final By rentalPeriod = By.xpath(".//div[@class='Dropdown-root']");
    //Кнопка "Заказать"
    private final By nextBtn = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    Random random = new Random();
    private final int sevenDaysRandom = random.nextInt(7) + 1; // Рандом числа от 1 до 7
    private final By rentalPeriodDays = By.xpath(".//div[@class='Dropdown-menu']/div['" + sevenDaysRandom + "']");
    private final int colorsRandom = random.nextInt(2) + 1; // Рандом для выбора чекбокса цвета самоката
    private final By scooterColor = By.xpath(".//div[@class='Order_Checkboxes__3lWSI']/label['" + colorsRandom + "']/input");

    public AboutRentOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrderConfirmationPage aboutRentOrderPage(String deliveryDate, String informationForCourier) {
        driver.findElement(this.deliveryDate).sendKeys(deliveryDate, Keys.ENTER);
        driver.findElement(rentalPeriod).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until(ExpectedConditions.elementToBeClickable(rentalPeriodDays));
        driver.findElement(rentalPeriodDays).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until(ExpectedConditions.elementToBeClickable(scooterColor));
        driver.findElement(scooterColor).click();
        driver.findElement(description).sendKeys(informationForCourier);
        driver.findElement(nextBtn).click();

        return new OrderConfirmationPage(driver);
    }
}
