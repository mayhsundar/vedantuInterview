package pages;

import java.util.List;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import driversmanager.DriverProvider;

public class HotelResultPage extends BasePage {

    @FindBy(css = "div[class*='hotelTileDt']")
    private List<WebElement> hotelsList;

    @FindBy(css = "input[name='min']")
    private WebElement priceMin;

    @FindBy(css = "input[name='max']")
    private WebElement priceMax;

    @FindBy(css = "button[class*='btnRangeGo']")
    private WebElement priceRangeSetButton;

    @FindBy(css = "p[class='apldFltr__item--text']")
    private List<WebElement> filtersTexts;

    @FindBy(css = "div[class='rcmPlaces']")
    private WebElement popularPlaces;

    @FindBy(css = "ul[class='rcmPlaces__list'] li label")
    private List<WebElement> polularCheckboxes;

    @FindBy(css = "a[class='primaryBtn']")
    private List<WebElement> searchPopularBtn;

    @FindBy(css = "#loginTrigger")
    private WebElement loginOptionsLink;

    @FindBy(css = "a[data-cy='userMenuMyProfile']")
    private WebElement opemMyProfileLink;

    @FindBy(xpath = "//a[text()='Clear']")
    private WebElement filterClearButton;

    private String parentString;

    public HotelResultPage() {
	driver = DriverProvider.getDriver();
	PageFactory.initElements(driver, this);
    }

    public void setMinPriceRange(String mP) {
	waitForElementTobeClickable(popularPlaces);
	waitForElementTobeClickable(priceMin);
	priceMin.sendKeys(mP);
    }

    public void setMaxPriceRange(String mP) {
	priceMax.sendKeys(mP + Keys.ENTER);
    }

    public void setPriceRangeFilter() {
	waitForElementTobeClickable(popularPlaces);
	priceRangeSetButton.click();

    }

    public boolean isFilterDoneForPrice(String min, String max) {
	waitForElementTobeClickable(filterClearButton);
	boolean matched = false;
	if (filtersTexts.size() > 0) {
	    for (WebElement c : filtersTexts) {
		if (c.getText().contains(min + "-" + max))
		    return true;
	    }
	}
	return matched;
    }

    public boolean selectHotel() {
	WebElement hotel = hotelsList.get(0);
	waitForElementTobeClickable(hotel);
	parentString = driver.getWindowHandle();
	boolean issueWithClick = true;
	try {
	    hotel.click();
	} catch (ElementClickInterceptedException e) {
	    System.err.println("Issue with clicking interceptio, retrying...");
	    issueWithClick = handleInterceptionClick(hotel);
	}
	return issueWithClick;
    }


    public void closeTheHotelPage() {
	for (String c : driver.getWindowHandles()) {
	    if (!c.equalsIgnoreCase(parentString)) {
		driver.switchTo().window(c);
		System.out.println(driver.getCurrentUrl());
		driver.close();
	    }
	}

	driver.switchTo().window(parentString);
    }

    public void selectLastPopularity() {
	WebElement lastCheckbox = polularCheckboxes.get(polularCheckboxes.size() - 1);
	clickByAction(lastCheckbox);
    }

    public void clickOnSearchPopularBtn() {
	jsClick(searchPopularBtn.get(0));
    }

    public void clickOnLoginOptionsLink() {
	jsClick(loginOptionsLink);
    }

    public void clickOnMyProfileLink() {
	jsClick(opemMyProfileLink);
    }

    public void clearFilter() {
	waitForElementToBeVisible(filterClearButton);
	filterClearButton.click();
    }

    public boolean isFilterClear() {
	boolean cleared = true;
	if (filtersTexts.size() != 0) {
	    cleared = false;
	}
	return cleared;
    }

}
