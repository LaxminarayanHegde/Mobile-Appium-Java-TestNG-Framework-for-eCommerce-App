package pageObjects;

import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.LoggerUtils;

import java.time.Duration;
import static org.testng.Assert.fail;

public class RegistrationPage {
    AppiumDriver driver;
    ExtentTest extentTest;

    public RegistrationPage(AppiumDriver driver){
        this.driver = driver;
    }

    private final By nameTextField = By.xpath("//android.widget.TextView[@text='Your Name']/following-sibling::android.widget.EditText");
    private final By genderFemaleRadioButton = By.xpath("//android.widget.RadioButton[@text='Female']");
    private final By genderMaleRadioButton = By.xpath("//android.widget.RadioButton[@text='Male']");
    private final By letsShopButton = By.id("com.androidsample.generalstore:id/btnLetsShop");
    private final By registrationPageTitle = By.xpath("//android.widget.TextView[@text='General Store']");
    private final By productsPageTitle = By.xpath("//android.widget.TextView[contains(@text,'Product')]");
    private final By errorToastNotification = By.xpath("(//android.widget.Toast)[1]");

    public void enterNameOnTheInputTextBox(String name){
        driver.findElement(nameTextField).sendKeys(name);
    }

    public void selectGenderRadioButton(String gender){
        if(gender.equalsIgnoreCase("male"))
            driver.findElement(genderMaleRadioButton).click();
        else
            driver.findElement(genderFemaleRadioButton).click();
    }

    public void clickLetsShopButton(){
        driver.findElement(letsShopButton).click();
    }

    public void verifyUserLandedOnProductsScreen() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement productsPageTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productsPageTitle));
        extentTest = LoggerUtils.getExtentReportObject().createTest("Visibility of Product page title");
        assert productsPageTitleElement != null;
        Assert.assertTrue(productsPageTitleElement.isDisplayed());
        Assert.assertEquals(productsPageTitleElement.getText(), "Products");
    }

    public void verifyUserNotLandedOnProductsScreen()  {
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(productsPageTitle));
            fail("App should not let the user to complete the registration but its allowing");
        } catch (Exception e) {
            Assert.assertTrue(driver.findElement(registrationPageTitle).isDisplayed());
            Assert.assertEquals(driver.findElement(registrationPageTitle).getText(), "General Store");
        }
    }
}
