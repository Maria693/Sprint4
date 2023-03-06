import extentions.WebDriverFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.OrderPage;
import org.junit.After;
import org.junit.Before;



@RunWith(Parameterized.class)
public class OrderTest {

    private final int homePageOrderButtonIndex;
    private final String name;
    private final String surname;
    private final String address;
    private final String subwayStation;
    private final String phone;
    private final String date;
    private final String daysOfRentOption;
    private final String color;
    private final String comment;

    public OrderTest (int homePageOrderButtonIndex, String name, String surname, String address, String phone, String subwayStation, String date, String daysOfRentOption, String color, String comment) {
        this.homePageOrderButtonIndex = homePageOrderButtonIndex;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.subwayStation = subwayStation;
        this.phone = phone;
        this.date = date;
        this.daysOfRentOption = daysOfRentOption;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][] {
                {0, "Маша", "Ермакова", "г. Санкт-Петербург, ул. Верности, д. 11", "89811547390", "Черкизовская", "02.03.2023", "сутки", "black", "Привет, курьер!"},
                {1, "Галя", "Телефоновна", "г. Москва, ул. Просвещения, д. 20", "89811547391", "Сокольники", "05.03.2024", "трое суток", "grey", "Пока, курьер!"},
                {0, "Саша", "Петров", "г. Саров, ул. Силкина, д. 44", "89811547392", "Чистые пруды", "05.03.2023", "пятеро суток", "black", "Здарова!"}
        };
    }
    private WebDriver driver;

    @Before
    public void setup(){
        driver = WebDriverFactory.get();
        HomePage orderPage = new HomePage(driver);
        orderPage.setAcceptCookie();

    }

    @Test
    public void checkOrder()  {

        (new HomePage(driver)).clickOrderButton(homePageOrderButtonIndex);

        OrderPage orderPage = new OrderPage(driver);
        orderPage.checkOrderContentContainerDisplayed();
        orderPage.setNameInput(name);
        orderPage.setSurnameInput(surname);
        orderPage.setAddressInput(address);
        orderPage.setSubwayStationInput(subwayStation);
        orderPage.clickSubwayStationSelect(subwayStation);
        orderPage.setPhoneInput(phone);
        orderPage.clickNextButton();
        orderPage.setDateInput(date);
        orderPage.clickColor(color);
        orderPage.clickDaysOfRentInput();
        orderPage.clickDaysOfRentOption(daysOfRentOption);
        orderPage.setCommentInput(comment);
        orderPage.clickOrderButton();
        orderPage.checkOrderModalDisplayed();
        orderPage.clickConfirmButton();
        orderPage.checkCheckStatusButtonDisplayed();

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}