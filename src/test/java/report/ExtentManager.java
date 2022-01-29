package report;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
    public static ExtentReports extent = null;

    private ExtentManager() {
    }

    public static ExtentReports getInstance() {
        if (extent == null) {
            extent = new ExtentReports("./report/ExtentReportResults.html", true);
        }


        return extent;
    }
}
