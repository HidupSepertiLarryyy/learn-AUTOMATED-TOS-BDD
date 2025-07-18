package reports;

// 👉 Setup report Extent HTML.

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static ExtentReports getReport() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/AutomationReport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}
