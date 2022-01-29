package testBase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import driversmanager.DriverManager;
import pages.HomePage;
import pages.HotelResultPage;
import pages.HotelsPage;
import pages.LoginPage;
import pages.MyAccountPage;
import report.listner.TestNgListener;

@Listeners(TestNgListener.class)
public class TestBase {

    public static WebDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected HotelsPage HotelsPage;
    protected HotelResultPage hotelResultPage;
    protected MyAccountPage myAccountPage;


    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() throws Exception {
	driver = new DriverManager(System.getProperty("driver")).initBrowser();
        loginPage = new LoginPage();
	homePage = new HomePage();
	HotelsPage = new HotelsPage();
	hotelResultPage = new HotelResultPage();
	myAccountPage = new MyAccountPage();

    }

    protected void navigateTo(String url) {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        if (null != driver) {
//            driver.quit();
            driver = null;
        }
    }
}

