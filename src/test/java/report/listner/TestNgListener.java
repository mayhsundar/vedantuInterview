package report.listner;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

import report.ExtentManager;
import report.ExtentTestManager;
import testBase.TestBase;

public class TestNgListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

        System.out.println("Test case Start and detail are" + result.getName());
        ExtentTestManager.startTest(result.getMethod().getMethodName(), "");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        System.out.println("Test case pass and detail are " + result.getName());

    }

    @Override
    public void onTestFailure(ITestResult result) {
        String failureImageFileName = "";
        if (!result.isSuccess()) {
            File imageFile = ((TakesScreenshot) TestBase.driver).getScreenshotAs(OutputType.FILE);
            failureImageFileName = result.getMethod().getMethodName()
                    + new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime()) + ".png";
            File failureImageFile = new File("./screenshot/" + failureImageFileName);
            try {
		FileUtils.deleteDirectory(new File("./screenshot/"));
                FileUtils.copyFile(imageFile, failureImageFile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.println("Test case Fail and detail are" + result.getName());
	String errorMsg = result.getThrowable().getMessage();
	errorMsg = errorMsg.split("\\(Session info:")[0];
	ExtentTestManager.getTest().log(LogStatus.FAIL, errorMsg,
		ExtentTestManager.getTest().addScreenCapture("../screenshot/" + failureImageFileName));

    }

    @Override
    public void onTestSkipped(ITestResult result) {

        System.out.println("Test case Skipped and detail are" + result.getName());
        ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

        System.out.println("Test case fail withSuccess Percentage and detail are" + result.getName());

    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test case Start context and detail are" + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {

        System.out.println("Test case finish context and detail are" + context.getName());
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }

}
