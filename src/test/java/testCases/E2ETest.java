package testCases;

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

	String min = "1000";
	String max = "2000";
        navigateTo(Properties.baseUrl);
//        loginPage.isLoginButtonIsDisplay();
//        loginPage.clickOnLoginButton();
//	loginPage.enterLoginCredsInGoogleSignInBox(Properties.googleEmail, Properties.googlePassword);
//	homePage.waitUntilUserLoggedIn();
//
//	homePage.clickOnHotelLink();
//	HotelsPage.selectCity();
//	HotelsPage.selectDate();
//	HotelsPage.selectPerson();
//	HotelsPage.clickSearchButton();
//	hotelResultPage.setMinPriceRange(min);
//	hotelResultPage.setMaxPriceRange(max);
//	hotelResultPage.setPriceRangeFilter();
//	Assert.assertTrue(hotelResultPage.isFilterDoneForPrice(min, max));
//	Assert.assertTrue(hotelResultPage.selectHotel(), "Hotel could not be selected due to an issue");
//	hotelResultPage.closeTheHotelPage();
//	hotelResultPage.clearFilter();
//	Assert.assertTrue(hotelResultPage.isFilterClear());
//	hotelResultPage.selectLastPopularity();
//	hotelResultPage.clickOnSearchPopularBtn();
//	Assert.assertTrue(hotelResultPage.selectHotel(), "Hotel could not be selected due to an issue");
//	hotelResultPage.closeTheHotelPage();
//
//	hotelResultPage.clickOnLoginOptionsLink();
//	hotelResultPage.clickOnMyProfileLink();
//
//	myAccountPage.clickOnLogOutLink();

    }

}
