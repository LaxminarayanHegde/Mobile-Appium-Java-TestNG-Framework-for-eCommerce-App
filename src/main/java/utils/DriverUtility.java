package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverUtility {

    public AppiumDriver driver;

    @BeforeMethod(alwaysRun = true)
    public AppiumDriver getMobileDriver() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setAppPackage("com.androidsample.generalstore");
        options.setAppActivity("com.androidsample.generalstore.MainActivity");
        options.setDeviceName("emulator-5554");

        driver = new AppiumDriver(new URL("http://127.0.0.1:4723"), options);
        return driver;
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver(){
        if(driver != null)
            driver.quit();
    }
}
