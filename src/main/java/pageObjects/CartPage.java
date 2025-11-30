package pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.CommonMethods;

import java.time.Duration;

public class CartPage {

    public AppiumDriver driver;

    public CartPage(AppiumDriver driver){
        this.driver = driver;
    }

    private final By cartPageTitle = By.xpath("//android.widget.TextView[@text='Cart']");
    private final By productPrice = By.xpath("//android.widget.TextView[@resource-id = 'com.androidsample.generalstore:id/productPrice']");
    private final By totalPurchaseAmountValue = By.xpath("//android.widget.TextView[@text = 'Total Purchase Amount:  ']/following-sibling::android.widget.TextView");
    private final By termsAndConditionCheckBox = By.xpath("//android.widget.CheckBox");
    private final By purchaseButton = By.xpath("//android.widget.Button[@text='Visit to the website to complete purchase']");

    public void verifyCartPageTitleDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(cartPageTitle)).isDisplayed());
    }

    public void validateProductNameOnCartPage(String productName){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='"+productName+"']")));
        assert product != null;
        String productNameFromUI = product.getText();
        Assert.assertEquals(productNameFromUI, productName);
    }

    public void validateTotalProductPriceOnCartPage(){
        String productPriceString = driver.findElement(productPrice).getText();
        double formattedProductPrice = Double.parseDouble(CommonMethods.getCurrencyFormattedString(productPriceString));
        double totalProductPrice = Double.parseDouble(CommonMethods.getCurrencyFormattedString(driver.findElement(totalPurchaseAmountValue).getText()));
        Assert.assertEquals(formattedProductPrice, totalProductPrice);
    }

    public void clickTermsAndConditionCheckBox(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(termsAndConditionCheckBox));
        driver.findElement(termsAndConditionCheckBox).click();
    }

    public void clickPurchaseButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(purchaseButton));
        driver.findElement(purchaseButton).click();
    }
}
