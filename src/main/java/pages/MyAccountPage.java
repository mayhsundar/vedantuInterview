package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import driversmanager.DriverProvider;

public class MyAccountPage extends BasePage {

    @FindBy(css = "ul[class='sdeBarNav'] li")
    private List<WebElement> sideAccountOptions;


    public MyAccountPage() {
	driver = DriverProvider.getDriver();
	PageFactory.initElements(driver, this);
    }

    public void clickOnLogOutLink() {
	WebElement logOutButton = sideAccountOptions.stream().filter(e -> e.getText().toLowerCase().contains("logout"))
		.findFirst().get();
	jsClick(logOutButton);
    }
}
