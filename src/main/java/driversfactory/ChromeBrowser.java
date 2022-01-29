package driversfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeBrowser implements BrowserFactory {

    @Override
    public WebDriver getDriver() {
	ChromeOptions options = new ChromeOptions();
	options.addArguments("allow-running-insecure-content");
	options.addArguments("--start-fullscreen");
	options.addArguments("--disable-notifications");
	setDriverBinaryPath();

	return new ChromeDriver(options);
    }

    @Override
    public void setDriverBinaryPath() {
	WebDriverManager.chromedriver().setup();

    }

}
