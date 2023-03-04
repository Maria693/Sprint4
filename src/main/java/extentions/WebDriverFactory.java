package extentions;
import config.AppConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    private static ChromeDriver driver;


    public static WebDriver get() {

        System.setProperty("webdriver.chrome.driver", "/Users/ermakova20/Downloads/WebDriver/bin/chromedriver_mac64/chromedriver");

        String BrowserName = System.getenv().get("browser");

        WebDriver driver;
        switch (BrowserName) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;

            default:
                throw new RuntimeException("Browser " + BrowserName + " not exist");

        }


        driver.get(AppConfig.URL);
        return driver;
    }
}
