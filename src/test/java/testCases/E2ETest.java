package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import annotation.Author;
import annotation.TestCaseNotes;
import annotation.TesterName;
import testBase.TestBase;
import utils.Categories;
import utils.Properties;

public class E2ETest extends TestBase {


    @Author(name = TesterName.SHYAM)
    @TestCaseNotes(Steps = "https://www.makemytrip.com || click on login button|| enter the  valid user id || enter the valid password|| login", expecatedResult = "User should be login successfully")
    @Test(groups = {Categories.SMOKE})
    public void e2eTest() {

	initExtentTest();

	String min = "1000";
	String max = "2000";
	setExtentLog("Openig website:", "navigating to " + Properties.baseUrl);
	navigateTo(Properties.baseUrl);
	loginPage.isLoginButtonIsDisplay();
	loginPage.clickOnLoginButton();
	setExtentLog("", "clicking the google login button");
	loginPage.enterLoginCredsInGoogleSignInBox(Properties.googleEmail, Properties.googlePassword);
	setExtentLog("Google signin process:", "Entering google creds to login");
	homePage.waitUntilUserLoggedIn();

	setExtentLog("Hotel booking process:", "click on hotels link");
	homePage.clickOnHotelLink();
	setExtentLog("", "selecting city " + Properties.hotelcity);
	HotelsPage.selectCity();

	setExtentLog("CheckIn and Checkout time set", "selecting staying time: 1 week + 1 day from today");
	HotelsPage.selectDate();

	setExtentLog("Person selection", "selecting number of persons");
	HotelsPage.selectPerson();

	setExtentLog("Search hotels", "clickig on search hotels button");
	HotelsPage.clickSearchButton();

	String hotelLink;

	setExtentLog("Price filter", "setting min: " + min + " and max: " + max);
	hotelResultPage.setMinPriceRange(min);
	hotelResultPage.setMaxPriceRange(max);
	hotelResultPage.setPriceRangeFilter();

	setExtentLog("", "checking if filter was added or not");
	Assert.assertTrue(hotelResultPage.isFilterDoneForPrice(min, max));

	Assert.assertTrue(hotelResultPage.selectHotel(), "Hotel could not be selected due to an issue");
	hotelLink = hotelResultPage.closeTheHotelPage();

//	setExtentLog("Hotel link after price filter",
//		"Check this hotel:<a href='" + hotelLink + "'>open hotel link</a>");

	setExtentLog("Clearing filter", "checking if filter was removed or not");
	hotelResultPage.clearFilter();

	setExtentLog("Add popular filter", "selecting last popular filter option");
	hotelResultPage.selectLastPopularity();

	setExtentLog("", "clicking on search button");
	hotelResultPage.clickOnSearchPopularBtn();

	setExtentLog("Open hotel", "clicking on first hotel result");
	Assert.assertTrue(hotelResultPage.selectHotel(), "Hotel could not be selected due to an issue");

	hotelLink = hotelResultPage.closeTheHotelPage();
	System.out.println(hotelLink);
//	setExtentLog("Hotel link after popular filter",
//		"Check this hotel:<a href='" + hotelLink + "'>open hotel link</a>");

	hotelResultPage.clickOnLoginOptionsLink();
	hotelResultPage.clickOnMyProfileLink();
	setExtentLog("Logout process", "logging out from the application");
	myAccountPage.clickOnLogOutLink();
	setExtentLog("Logout process", "has been logged out :)");

    }



}
