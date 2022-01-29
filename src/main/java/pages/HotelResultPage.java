package pages;

import java.util.List;

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

    private String parentString;

    public HotelResultPage() {
	driver = DriverProvider.getDriver();
	PageFactory.initElements(driver, this);
    }

    public void setMinPriceRange(String mP) {
	waitForElementTobeClickable(popularPlaces);

//	try {
//	    Thread.sleep(6000);
//	} catch (InterruptedException e) {
//	    // TODO Auto-generated catch block
//	    e.printStackTrace();
//	}
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
	boolean matched = false;
	if (filtersTexts.size() > 0) {
	    for (WebElement c : filtersTexts) {
		if (c.getText().contains(min + "-" + max))
		    return true;
	    }
	}
	return matched;
    }

    public void selectHotel() {
	parentString = driver.getWindowHandle();
	hotelsList.get(0).click();
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

    public void sortByPopularity() {
	polularCheckboxes.get(polularCheckboxes.size() - 1).click();
    }

    public void clickOnSearchPopularBtn() {
	searchPopularBtn.get(0).click();
    }

    public void clickOnLoginOptionsLink() {
	jsClick(loginOptionsLink);
    }

    public void clickOnMyProfileLink() {
	jsClick(opemMyProfileLink);
    }
}
