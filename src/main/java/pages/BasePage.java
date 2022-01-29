package pages;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;

    protected void scrollToView(WebElement element) {
        if (element.isEnabled()) {
            ((JavascriptExecutor) driver).
                    executeScript("arguments[0].scrollIntoView(true);", element);
        }

    }

    protected void waitForElementTobeClickable(WebElement webElement) {
        new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(webElement));
    }


    protected void wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    protected void waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));

    }

    protected boolean waitForElementToBeDisplay(WebElement element) {
	FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
		.pollingEvery(Duration.ofSeconds(2));
        wait.until((Function<WebDriver, WebElement>) driver -> element);
        return element.isDisplayed();
    }

    protected void waitForListOfElementToBeDisplay(List<WebElement> element) {
	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
		.ignoring(StaleElementReferenceException.class).pollingEvery(Duration.ofSeconds(2));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(element)));
    }

    protected void waitForPageToLoad() {
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    protected void waitForElementToBeInvisible(WebElement element) {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.invisibilityOf(element));

    }

    protected void refreshPage() {
        driver.navigate().refresh();
        waitForPageToLoad();

    }


    public static void mouseHoverJScript(WebDriver driver, WebElement element) {
        new Actions(driver).moveToElement(element).click().build().perform();

    }


    protected void jsClick(WebElement element) {
	waitForElementTobeClickable(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }




    public void enterText(String text, WebElement element) {
        String js = "arguments[0].setAttribute('value','" + text + "')";
        ((JavascriptExecutor) driver).executeScript(js, element);
    }


    protected void selectByValue(WebElement el, String value) {
	waitForElementTobeClickable(el);
	new Select(el).selectByValue(value);
    }


}
