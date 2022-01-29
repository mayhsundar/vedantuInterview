package pages;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
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
import org.testng.Assert;

public class BasePage {

    protected WebDriver driver;

    protected void scrollToView(WebElement element) {
	if (element.isEnabled()) {
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

    }

    protected void waitForElementTobeClickable(WebElement webElement) {
	new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(webElement));
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

    protected void waitForElementToBeDisplayNormalWait(WebElement element) {
	WebElement el = element;
	try {
	    waitForElementTobeClickable(el);
	} catch (StaleElementReferenceException e) {
	    System.err.println("stale element occured.. now retying..");
	    el = handleStaleElementException(element);
	}

	if (null == el) {
	    Assert.assertNotNull(el, "Element was stale even trying after some retries.");
	}

    }

    private WebElement handleStaleElementException(WebElement el) {
	int retry = 1;
	By locator = getByFromElement(el);
	while (retry < 5) {
	    try {
		return driver.findElement(locator);
	    } catch (StaleElementReferenceException e) {
		System.err.println("Again stale element occured, retying, retry count = " + retry);
	    }
	    retry++;
	}
	return null;
    }

    protected void waitForListOfElementToBeDisplay(List<WebElement> element) {
	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
		.ignoring(StaleElementReferenceException.class).pollingEvery(Duration.ofSeconds(2));
	wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(element)));
    }

    protected void waitForPageToLoad() {
	new WebDriverWait(driver, 30).until(webDriver -> ((JavascriptExecutor) webDriver)
		.executeScript("return document.readyState").equals("complete"));
    }

    protected void waitForElementToBeInvisible(WebElement element) {
	new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOf(element));

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

    private By getByFromElement(WebElement element) {

	By by = null;
	String[] selectorWithValue = (element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + ""))
		.split(":");

	String selector = selectorWithValue[0].trim();
	String value = selectorWithValue[1].trim();

	switch (selector) {
	case "id":
	    by = By.id(value);
	    break;
	case "className":
	    by = By.className(value);
	    break;
	case "tagName":
	    by = By.tagName(value);
	    break;
	case "xpath":
	    by = By.xpath(value);
	    break;
	case "css selector":
	    by = By.cssSelector(value);
	    break;
	case "linkText":
	    by = By.linkText(value);
	    break;
	case "name":
	    by = By.name(value);
	    break;
	case "partialLinkText":
	    by = By.partialLinkText(value);
	    break;
	default:
	    throw new IllegalStateException("locator : " + selector + " not found!!!");
	}
	return by;
    }

    protected boolean handleInterceptionClick(WebElement el) {
	waitForPageToLoad();
	int retry = 1;
	while (retry < 5) {
	    try {
		clickByAction(el);
		return true;
	    } catch (ElementClickInterceptedException e) {
		System.err.println("Again interception occured, retying, retry count = " + retry);
		retry++;
	    }
	}

	return false;
    }

    protected void clickByAction(WebElement el) {
	new Actions(driver).moveToElement(el).click().build().perform();
    }

}
