package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class LoggerUtils {

    public static ExtentReports getExtentReportObject(){
        String filePath = System.getProperty("user.dir") + "/reports/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
        reporter.config().setReportName("Mobile Automation Results");
        reporter.config().setDocumentTitle("Test Results");
        reporter.config().setTimelineEnabled(false);

        ExtentReports extentReport = new ExtentReports();
        extentReport.attachReporter(reporter);
        extentReport.setSystemInfo("Tester", "Laxminarayan");
        return extentReport;
    }

    public static String getPathForScreenshot(AppiumDriver driver, String testCaseName) throws IOException {
        File sourcePath = driver.getScreenshotAs(OutputType.FILE);
        String screenshotFilePath = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
        File destinationPath = new File(screenshotFilePath);
        FileUtils.copyFile(sourcePath, destinationPath);
        return screenshotFilePath;
    }
}
