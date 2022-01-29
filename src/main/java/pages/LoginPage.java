package pages;

import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import driversmanager.DriverProvider;

public class LoginPage extends BasePage {

    @FindBy(css = "div[data-cy='googleLogin']")
    private WebElement loginButton;

    @FindBy(css = "button[data-cy='continueBtn']")
    private WebElement continueButton;

    @FindBy(css = "button[data-cy='login']")
    private WebElement login;

    @FindBy(css = "#identifierId")
    private WebElement inputGoogleEmail;

    @FindBy(xpath = "//span[text()='Next']")
    private WebElement buttonNext;

    @FindBy(css = "input[type='password']")
    private WebElement inputGooglePassword;

    public LoginPage() {
	driver = DriverProvider.getDriver();
	PageFactory.initElements(driver, this);
    }

    private String parentWindowHandle;

    public void isLoginButtonIsDisplay() {
	waitForElementToBeDisplay(loginButton);
	Assert.assertTrue(loginButton.isDisplayed(),
		String.format("%s button is not displayed", loginButton.getText()));
    }

    public void clickOnLoginButton() {
	waitForElementToBeDisplay(loginButton);
	parentWindowHandle = driver.getWindowHandle();
	jsClick(loginButton);
    }

    public void clickOnGoogleLoginNextButton() {
	waitForElementToBeDisplay(buttonNext);
	Actions ac = new Actions(driver);
	ac.moveToElement(buttonNext).click().build().perform();
    }

    public void enterGoogleLoginPassword(String password) {
	waitForElementTobeClickable(inputGooglePassword);
	inputGooglePassword.sendKeys(password);
    }

    public void enterLoginCredsInGoogleSignInBox(String email, String pass) {
	Set<String> windows = driver.getWindowHandles();
	for (String w : windows) {
	    if (!w.equalsIgnoreCase(parentWindowHandle)) {
		driver.switchTo().window(w);
		waitForElementToBeDisplay(inputGoogleEmail);
		enterText(email, inputGoogleEmail);
		clickOnGoogleLoginNextButton();
		enterGoogleLoginPassword(pass);
		clickOnGoogleLoginNextButton();
	    }
	}

	driver.switchTo().window(parentWindowHandle);
//	waitForElementToBeInvisible(loginButton);
    }

    public void isContinueButtonIsDisplay() {
	waitForElementToBeDisplay(continueButton);
	Assert.assertTrue(continueButton.isDisplayed(),
		String.format("%s button is not display", continueButton.getText()));
    }

    public void clickContinueButton() {
	System.out.println(continueButton.getText());
	waitForElementTobeClickable(continueButton);
	continueButton.click();
	jsClick(continueButton);
    }

    public void isLoginIsDisplay() {
	waitForElementToBeDisplay(login);
	Assert.assertTrue(login.isDisplayed(), " login button  is not display");
    }

    public void clickOnLogin() {
	waitForElementToBeDisplay(login);
	jsClick(login);
    }

}
