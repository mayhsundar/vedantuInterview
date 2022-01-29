package driversfactory;

import org.openqa.selenium.WebDriver;

public interface BrowserFactory {
    String driver = System.getProperty("driver");
    WebDriver getDriver();
    void setDriverBinaryPath();

}
