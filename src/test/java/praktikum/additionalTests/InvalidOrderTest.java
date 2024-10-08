package praktikum.additionalTests;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.DriverRule;
import praktikum.pages.MainPage;
import praktikum.pages.StatusPage;

public class InvalidOrderTest {
    @Rule
    public DriverRule factory = new DriverRule();

    private String INVALID_ORDER_ID = "666";    //переменная с невалидным номером заказа

    @Test
    public void openMainPage() throws Exception {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);

        mainPage.open();

        mainPage.clickOnStatus();   //Клик по полю ввода номера заказа
        mainPage.enterOrderId(INVALID_ORDER_ID);    //Ввод в поле невалидного номера заказа

        StatusPage statusPage = mainPage.clickOnGo();   //Клик по кнопке "Go"
        statusPage.checkErrorMessage();     //Проверка появление изображение с текстом "Такого заказа нет"
    }
}
