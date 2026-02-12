package com.tajhotels.automation.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.tajhotels.automation.utils.RunContext;

import java.nio.file.Path;

public final class ExtentManager {

    private static ExtentReports extent;

    private ExtentManager() {}

    public static synchronized ExtentReports getExtent() {
        if (extent == null) {
            String reportName = "ExtentReport_" + RunContext.nowTs() + ".html";
            Path reportPath = RunContext.reportDir().resolve(reportName);

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath.toString());
            spark.config().setTheme(Theme.DARK); // similar to your screenshot
            spark.config().setReportName("Taj Automation Execution");
            spark.config().setDocumentTitle("Taj Automation Report");
            spark.config().setTimeStampFormat("dd MMM yyyy, HH:mm:ss");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}