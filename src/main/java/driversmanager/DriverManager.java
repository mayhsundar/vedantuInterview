package driversmanager;

import org.openqa.selenium.WebDriver;

import constants.BrowserNames;
import driversfactory.ChromeBrowser;

public class DriverManager {

    private String browser;

    public DriverManager(String browserName) {
	this.browser = browserName;
    }

    public WebDriver initBrowser() {
	WebDriver webDr = null;

	switch (browser) {
	case BrowserNames.CHROME:
	    webDr = new ChromeBrowser().getDriver();
	    break;

	default:
	    webDr = new ChromeBrowser().getDriver();
	}
	DriverProvider.setDriver(webDr);
	return webDr;

    }

}
