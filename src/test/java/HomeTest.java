import extentions.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.HomePage;


@RunWith(Parameterized.class)
public class HomeTest {
    private final int accordionItemIndex;
    private final String accordionExpectedText;

    public HomeTest(int accordionItemIndex, String accordionExpectedText) {
        this.accordionItemIndex = accordionItemIndex;
        this.accordionExpectedText = accordionExpectedText;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {
                        0,
                        "Сутки — 400 рублей. Оплата курьеру — наличными или картой."
                },
                {
                        1,
                        "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."
                },
        };
    }

    private WebDriver driver;

    @Before
    public void setup() {
        driver = WebDriverFactory.get();
        HomePage orderPage = new HomePage(driver);
        orderPage.setAcceptCookie();

    }

    @Test
    public void checkAccordionItemOpensAndShowsExpectedText() {
        HomePage homePage = new HomePage(driver);
        homePage.sclTillBlockAboutImp();
        homePage.clickAccordionItem(accordionItemIndex);
        homePage.checkAccordionItemOpensAndShowsExpectedText(accordionItemIndex, accordionExpectedText);
    }



    @After
    public void tearDown() {
        driver.quit();
    }
}
