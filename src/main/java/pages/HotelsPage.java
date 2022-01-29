package pages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import driversmanager.DriverProvider;
import utils.Properties;

public class HotelsPage extends BasePage {

    @FindBy(css = "#city")
    private WebElement inputCity;

    @FindBy(css = "li[role='option']")
    private List<WebElement> citySuggestions;

    @FindBy(css = "div[class*='DayPicker-Day']:not([class*='disabled']):not([aria-disabled='true'])")
    private List<WebElement> dates;

    @FindBy(css = "div[class*='roomGuests']")
    private WebElement roomsGuestsBox;

    @FindBy(css = "ul[data-cy='adultCount'] li")
    private List<WebElement> adultsCountLink;

    @FindBy(css = "li[data-cy*='children-']")
    private List<WebElement> childCountLink;

    @FindBy(css = "select[data-cy*='childAge-']")
    private List<WebElement> childAges;

    @FindBy(css = "button[data-cy='submitGuest']")
    private WebElement submitGuestFormLink;

    @FindBy(css = "button[data-cy='submit']")
    private WebElement searchButton;

    public HotelsPage() {
	driver = DriverProvider.getDriver();
	PageFactory.initElements(driver, this);
    }

    public void selectCity() {
	waitForElementTobeClickable(inputCity);
	inputCity.click();
	waitForElementTobeClickable(citySuggestions.get(0));
	List<WebElement> matchingWebElement = citySuggestions.stream()
		.filter(e -> e.getText().toLowerCase().contains(Properties.hotelcity.toLowerCase()))
		.collect(Collectors.toList());
	if (matchingWebElement.size() == 0)
	    System.out.println("No matching city");
	else {
	    waitForElementTobeClickable(matchingWebElement.get(0));
	    jsClick(matchingWebElement.get(0));
	}

    }

    public void selectDate() {
	DateTimeFormatter df = DateTimeFormatter.ofPattern("E MMM dd yyyy");

	String checkIn = LocalDateTime.now().format(df);
	String checkout = LocalDateTime.now().plusWeeks(1).plusDays(1).format(df);

	for (WebElement e : dates) {
	    if (e.getAttribute("aria-label").equals(checkIn))
		e.click();
	    if (e.getAttribute("aria-label").equals(checkout)) {
		e.click();
		break;
	    }
	}
    }

    public void selectPerson() {

	int adults = 2;
	int child = 1;
	int ageChild = 5;

	waitForElementTobeClickable(roomsGuestsBox);
	roomsGuestsBox.click();
	waitForElementToBeDisplay(adultsCountLink.get(0));
	adultsCountLink.get(adults - 1).click();
	if (child > 0) {
	    childCountLink.get(child).click();
	    waitForElementTobeClickable(childAges.get(0));
	    selectByValue(childAges.get(0), String.valueOf(ageChild));
	}

	submitGuestFormLink.click();
    }

    public void clickSearchButton() {
	waitForElementTobeClickable(searchButton);
	jsClick(searchButton);
    }

}
