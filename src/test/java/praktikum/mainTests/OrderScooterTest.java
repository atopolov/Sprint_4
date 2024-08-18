package praktikum.mainTests;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.DriverRule;
import praktikum.pages.*;

@RunWith(Parameterized.class)

public class OrderScooterTest {

    @Rule
    public DriverRule factory = new DriverRule();

    private final String firstName; //  Имя
    private final String lastName; // Фамилия
    private final String address; // Адрес
    private final String phoneNumber; // Номер телефона
    private final String deliveryDate; // Дата доставки
    private final String description; // Комментарий для курьера

    public OrderScooterTest(String firstName, String lastName, String address, String phone, String dateToDelivery, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phone;
        this.deliveryDate = dateToDelivery;
        this.description = description;
    }

    @Parameterized.Parameters()
    public static Object[][] orderParams() {
        return new Object[][]{
                {"Андрей", "Тополов", "Ленинский, 79", "+79214297724", "17.08.2024", "2ая парадная"},
                {"Иван", "Сергеев", "ул. Народная, д. 35, кв. 79", "89621680777", "24.08.2024", "Позвонить"}
        };
    }

    @Test // Оформление заказа через кнопку "Заказать" в шапке страницы
    public void orderByClickHeader() {
        
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);

        mainPage.open();
        mainPage.acceptCookies();

        UserInfoOrderPage userInfoOrderPage;
        userInfoOrderPage = mainPage.headerOrderBtnClick();

        AboutRentOrderPage aboutRentOrderPage;
        aboutRentOrderPage = userInfoOrderPage.userInfoOrderPage(firstName, lastName, address, phoneNumber);

        OrderConfirmationPage orderConfirmationModal;
        orderConfirmationModal = aboutRentOrderPage.aboutRentOrderPage(deliveryDate, description);

        SuccessOrderPage successOrderModal;
        successOrderModal = orderConfirmationModal.confirmOrder();

        Assert.assertEquals("Ошибка", "Заказ оформлен", successOrderModal.getOrderHeader());
    }

    @Test // Оформление заказа через кнопку "Заказать" в разделе "Как это работает"
    public void orderByClickBody() {
        WebDriver webdriver = factory.getDriver();
        MainPage mainPage = new MainPage(webdriver);

        mainPage.open();
        mainPage.acceptCookies();

        UserInfoOrderPage userInfoOrderPage;
        userInfoOrderPage = mainPage.bottomOrderBtnClick();

        AboutRentOrderPage aboutRentOrderPage;
        aboutRentOrderPage = userInfoOrderPage.userInfoOrderPage(firstName, lastName, address, phoneNumber);

        OrderConfirmationPage orderConfirmationModal;
        orderConfirmationModal = aboutRentOrderPage.aboutRentOrderPage(deliveryDate, description);

        SuccessOrderPage successOrderModal;
        successOrderModal = orderConfirmationModal.confirmOrder();

        Assert.assertEquals("Ошибка", "Заказ оформлен", successOrderModal.getOrderHeader());

        /*
        Для браузера Google Chrome тест падает из-за отличия текста. Вместо ожидаемого текста "Заказ оформлен"
        отображается текст "Хотите оформить заказ?"
         */

    }
}
