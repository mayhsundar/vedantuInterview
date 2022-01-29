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
        loginPage.isLoginButtonIsDisplay();
        loginPage.clickOnLoginButton();
	loginPage.enterLoginCredsInGoogleSignInBox(Properties.googleEmail, Properties.googlePassword);
	homePage.isUserLoggedIn();
	homePage.clickOnHotelLink();

	try {
	    Thread.sleep(10000);
	} catch (InterruptedException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	HotelsPage.selectCity();
	HotelsPage.selectDate();
	HotelsPage.selectPerson();
	HotelsPage.clickSearchButton();
	hotelResultPage.setMinPriceRange(min);
//	hotelResultPage.setMaxPriceRange(max);
//	Assert.assertTrue(hotelResultPage.isFilterDoneForPrice(min, max));
	hotelResultPage.selectHotel();
	hotelResultPage.closeTheHotelPage();
	hotelResultPage.sortByPopularity();
	hotelResultPage.clickOnSearchPopularBtn();
	hotelResultPage.selectHotel();
	hotelResultPage.closeTheHotelPage();

	hotelResultPage.clickOnLoginOptionsLink();
	hotelResultPage.clickOnMyProfileLink();

	myAccountPage.clickOnLogOutLink();

	try {
	    Thread.sleep(7000);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
