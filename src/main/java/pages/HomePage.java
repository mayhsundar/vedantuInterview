package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import driversmanager.DriverProvider;

public class HomePage extends BasePage {

    @FindBy(css = "li[data-cy='menu_Hotels']")
    private WebElement navigationsHotelLink;

    @FindBy(css = "*[class*='landingCard ']")
    private WebElement loggedInUser;

    @FindBy(css = "a[class*='SearchBtn']")
    private WebElement searchButton;

    @FindBy(css = "*[data-cy='loggedInUser']")
    private WebElement loggedInUserText;

    public HomePage() {
	driver = DriverProvider.getDriver();
	PageFactory.initElements(driver, this);
    }

    public void clickOnHotelLink() {
	waitForElementTobeClickable(navigationsHotelLink);
	jsClick(navigationsHotelLink);
    }

    public void isUserLoggedIn() {
	waitForElementTobeClickable(loggedInUser);
	Assert.assertTrue(loggedInUser.isDisplayed(), "User could not be able to login");
    }

    public void waitUntilUserLoggedIn() {
	waitForElementToBeDisplayNormalWait(loggedInUserText);
    }


}
