package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners implements ITestListener {

    ExtentTest extentTest;
    ExtentReports extent = LoggerUtils.getExtentReportObject();

    public void onTestStart(ITestResult result){
        extentTest = extent.createTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result){
        extentTest.pass("Test Passed");
    }

    public void onTestFailure(ITestResult result){
        extentTest.fail(result.getThrowable());
        String testCaseName = result.getMethod().getMethodName();
        AppiumDriver driver = null;
        try {
            driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        try {
            extentTest.addScreenCaptureFromPath(LoggerUtils.getPathForScreenshot(driver, testCaseName), testCaseName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onTestSkipped(ITestResult result){
        extentTest.fail(result.getThrowable());
    }

    public void onStart(ITestContext context){

    }

    public void onFinish(ITestContext context){
        int passed = context.getPassedTests().size();
        int failed = context.getFailedTests().size();
        int skipped = context.getSkippedTests().size();
        int total =   passed + failed + skipped;
        double passPercentage = (passed * 100) / total;
        double failPercentage = (failed * 100) / total;
        double skipPercentage = (skipped * 100) / total;

        extent.setSystemInfo("Total tests ", String.valueOf(total));
        extent.setSystemInfo("Passed tests ", String.valueOf(passed));
        extent.setSystemInfo("Failed tests ", String.valueOf(failed));
        extent.setSystemInfo("Pass % ", String.format("%.2f %%", passPercentage));
        extent.setSystemInfo("Fail % ", String.format("%.2f %%", failPercentage));

        extent.flush();
    }
}
