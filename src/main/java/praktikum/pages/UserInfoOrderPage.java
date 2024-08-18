package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;
import java.util.Random;

public class UserInfoOrderPage {

    private final WebDriver driver;

    private final By firstName = By.xpath(".//input[@placeholder='* Имя']");    //Поле ввода имени
    private final By lastName = By.xpath(".//input[@placeholder='* Фамилия']");     //Поле ввода фамилии
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");   //Поле ввода адреса
    private final By metro = By.xpath(".//input[@placeholder='* Станция метро']");      //Дропдаун выбора станции метро
    Random random = new Random();
    private final int randomNumberMetroStation = random.nextInt(157) + 1;
    private final By metroStation = By.xpath(".//button[@value='" + randomNumberMetroStation + "']");   //Поле со станцией метро
    private final By phoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");     //Поле ввода номера телефона
    private final By nextBtn = By.xpath(".//button[contains(text(),'Далее')]");     //Кнопка "Далее"

    public UserInfoOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public AboutRentOrderPage userInfoOrderPage(String nameInput, String lastNameInput, String addressInput, String phoneInput) {
        driver.findElement(firstName).sendKeys(nameInput);
        driver.findElement(lastName).sendKeys(lastNameInput);
        driver.findElement(address).sendKeys(addressInput);
        driver.findElement(phoneNumber).sendKeys(phoneInput);
        driver.findElement(metro).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT)).until(ExpectedConditions.elementToBeClickable(metroStation));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(metroStation));
        driver.findElement(metroStation).click();
        driver.findElement(nextBtn).click();

        return new AboutRentOrderPage(driver);
    }
}
