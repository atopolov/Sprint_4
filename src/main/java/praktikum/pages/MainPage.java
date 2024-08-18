package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class MainPage {
    private final WebDriver driver;
    //Логотип "Яндекс"
    private final By yandexLogo = By.className("Header_LogoYandex__3TSOI");
    //Логотип "Самокат"
    private final By scooterLogo = By.className("Header_LogoScooter__3lsAR");
    //Кнопка "Go"
    private final By goButton = By.cssSelector(".Header_Button__28dPO");
    //Поле ввода номера заказа
    private final By orderField = By.className("Input_Input__1iN_Z");
    //Кнопка "Статус заказа"
    private final By statusButton = By.className("Header_Link__1TAG7");
    //Кнопка "Заказать" в шапке страницы
    private final By headerOrderBtn = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[1]");
    //Кнопка "Заказать" в разделе "Как это работает"
    private final By bodyOrderBtn = By.xpath("//div[@class='Home_FinishButton__1_cWm']/button");
    //Блок FAQ в разделе "Вопросы о важном"
    private final By accordion = By.xpath("//div[@class='accordion']");
    //Блок с ответами в разделе "Вопросы о важном"
    private final By accordionAnswer = By.xpath(".//div[(@data-accordion-component='AccordionItemPanel' and not(@hidden))]");
    //Кнопка "да все привыкли" в блоке куки
    private final By cookiesBtn = By.xpath(".//button[@class='App_CookieButton__3cvqF']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод для открытия главной страницы
    public void open() {
        driver.get(EnvConfig.BASE_URL);
    }

    //Метод для нажатия на логотип "Яндекс"
    public void yandexLogoClick() {
        driver.findElement(yandexLogo).click(); // Клик по логотипу "Яндекс"
    }

    //Метод для нажатия на логотип "Самокат"
    public MainPage scooterLogoClick() {
        driver.findElement(scooterLogo).click(); // Клик по кнопке "Заказать"
        return new MainPage(driver); // Возвращаем объект следующей страницы
    }

    //Метод для принятия куки
    public void acceptCookies() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until(ExpectedConditions.elementToBeClickable(cookiesBtn));
        driver.findElement(cookiesBtn).click();
    }

    //Метод для нажатия на вопрос в блоке "Вопросы о важном"
    public void questionClick(String text) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(accordion)); // Скроллим до блока
        try {
            driver.findElement(By.xpath(".//div[@data-accordion-component='AccordionItemButton' and contains(text(),'" + text + "')]")).click();
        } catch (Exception e) {
            System.out.println("На странице не найден элемент с текстом: " + text);
        }
    }

    //Проверка содержания текста ответа в разделе "Вопросы о важном"
    public void checkAnswersText(String answerParam) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.IMPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(accordionAnswer));
        String equalResult = driver.findElement(accordionAnswer).getText();
        assertEquals(equalResult, answerParam);
    }

    //Нажатие кнопки "Заказать" в шапке страницы
    public UserInfoOrderPage headerOrderBtnClick() {
        driver.findElement(headerOrderBtn).click(); // Клик по кнопке "Заказать"
        return new UserInfoOrderPage(driver); // Возвращаем объект следующей страницы
    }

    //Нажатие кнопки "Заказать" в разделе "Как это работает"
    public UserInfoOrderPage bottomOrderBtnClick() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(bodyOrderBtn)); // Скроллим до места расположения кнопки "Заказать"
        driver.findElement(bodyOrderBtn).click(); // Клик по кнопке "Заказать"
        return new UserInfoOrderPage(driver); // Возвращаем объект следующей страницы
    }

    public StatusPage clickOnGo() {
        driver.findElement(goButton).click();

        return new StatusPage(driver);
    }

    public void enterOrderId(String orderNumber) {
        driver.findElement(orderField).sendKeys(orderNumber);
    }

    public void clickOnStatus() {
        driver.findElement(statusButton).click();
    }

}