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

    @FindBy(css = "#username")
    private WebElement userName;

    @FindBy(css = "button[data-cy='continueBtn']")
    private WebElement continueButton;

    @FindBy(id = "password")
    private WebElement enterPasswordTextField;

    @FindBy(css = "button[data-cy='login']")
    private WebElement login;

    @FindBy(css = "span[data-cy='modalClose']")
    private WebElement closeMobilePopUp;

    @FindBy(css = "span[data-cy='userNotPresent']")
    private WebElement wrongUserNameError;

    @FindBy(css = "span[data-cy='serverError']")
    private WebElement wrongPasswordErrorMessage;

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

    public void isUserNameTextIsDisplay() {
        waitForElementToBeDisplay(userName);
        Assert.assertTrue(userName.isDisplayed(), "user unable to enter the user id in text field");
    }


    public void enterUserId(String id) {
        waitForElementToBeDisplay(userName);
        userName.sendKeys(id);
    }

    public void isContinueButtonIsDisplay() {
        waitForElementToBeDisplay(continueButton);
        Assert.assertTrue(continueButton.isDisplayed(), String.format("%s button is not display", continueButton.getText()));
    }


    public void clickContinueButton() {
        System.out.println(continueButton.getText());
        waitForElementTobeClickable(continueButton);
        continueButton.click();
        jsClick(continueButton);
    }

    public void isEnterPasswordTextFieldIsDisplay() {
        waitForElementToBeDisplay(enterPasswordTextField);
        Assert.assertTrue(enterPasswordTextField.isDisplayed(), " enter password text filed  is not display");
    }


    public void clickOnEnterPasswordTextField(String password) {
        waitForElementToBeDisplay(enterPasswordTextField);
        enterPasswordTextField.sendKeys(password);
    }

    public void isLoginIsDisplay() {
        waitForElementToBeDisplay(login);
        Assert.assertTrue(login.isDisplayed(), " login button  is not display");
    }


    public void clickOnLogin() {
        waitForElementToBeDisplay(login);
        jsClick(login);
    }


    public void isMobilePopUpDisplay() {
        waitForElementToBeDisplay(closeMobilePopUp);
        Assert.assertTrue(closeMobilePopUp.isDisplayed(), " close Mobile PopUp  is not display");
    }


    public void clickOnCloseMobilePopUp() {
        waitForElementToBeDisplay(closeMobilePopUp);
        jsClick(closeMobilePopUp);
    }

    public void isErrorMessageDisplayForWrongUserName() {
        waitForElementToBeDisplay(wrongUserNameError);
        Assert.assertTrue(wrongUserNameError.isDisplayed(), " error message is not display");
        System.out.println(wrongUserNameError.getText());
        Assert.assertEquals(wrongUserNameError.getText(),"This username does not exist. CLICK HERE If your are trying to create a new personal account" ," after login with incorrect data error message is not displaying");
    }

    public void isErrorMessageDisplayForWrongPWD() {
        waitForElementToBeDisplay(wrongPasswordErrorMessage);
        Assert.assertTrue(wrongPasswordErrorMessage.isDisplayed(), " error message  is not display");
        Assert.assertEquals(wrongPasswordErrorMessage.getText(),"Either Username or Password is incorrect." ," after login with incorrect data error message is not displaying");
    }

    public void isUserLoggedIn() {
	Assert.assertTrue(wrongPasswordErrorMessage.isDisplayed(), " error message  is not display");
	Assert.assertEquals(wrongPasswordErrorMessage.getText(), "Either Username or Password is incorrect.",
		" after login with incorrect data error message is not displaying");
    }


}
