package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By accordionItem = By.xpath(".//div[@class='accordion__item']");
    By blockAboutImportant = By.xpath(".//div[contains(text(), 'Вопросы о важном')]");
    By acceptCookie = By.id("rcc-confirm-button");
    By orderButtons = By.xpath(".//button[text()='Заказать']");

    public void sclTillBlockAboutImp(){
        WebElement element = driver.findElement(blockAboutImportant);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void setAcceptCookie() {
        driver.findElement(acceptCookie).click();
    }
    public void clickOrderButton(int index) {
        driver.findElements(orderButtons).get(index).click();
    }

    public void clickAccordionItem(int index) {
        driver.findElements(accordionItem).get(index).click();
    }

    public void checkAccordionItemPanelDisplayed(int index) {
        WebElement accordionItemPanel = driver.findElements(accordionItem).get(index)
                .findElement(By.xpath(".//div[@class='accordion__panel']"));

        assertTrue(accordionItemPanel.isDisplayed());
    }
    public void checkAccordionItemTextMatchesExpectedText(int index, String expectedText) {
        String accordionItemText = driver.findElements(accordionItem).get(index)
                .findElement(By.xpath(".//div[@class='accordion__panel']/p"))
                .getText();

        assertEquals(expectedText, accordionItemText);
    }

    // Step
    public void checkAccordionItemOpensAndShowsExpectedText(int index, String expectedText) {
        checkAccordionItemPanelDisplayed(index);
        checkAccordionItemTextMatchesExpectedText(index, expectedText);
    }

}
