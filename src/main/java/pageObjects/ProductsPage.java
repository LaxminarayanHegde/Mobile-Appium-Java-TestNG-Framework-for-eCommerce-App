package pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage {

    AppiumDriver driver;
    String productPrice;

    public ProductsPage(AppiumDriver driver){
       this.driver = driver;
    }

    By cartIcon = By.id("com.androidsample.generalstore:id/appbar_btn_cart");
    By productsPageTitle = By.xpath("//android.widget.TextView[@text='Products']");
    By productCollectionViewList = By.xpath("//android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView");

    public void addProductToCart(String productName){
        List<WebElement> productList = driver.findElements(productCollectionViewList);
        for(int i = 0; i < productList.size(); i++){
            if(productList.get(i).getText().equals("Air Jordan 1 Mid SE")){
                WebElement addToCartButton = driver.findElement(By.xpath("//android.widget.TextView[@text='"+productName+"']/following-sibling::android.widget.LinearLayout/android.widget.TextView[@text='ADD TO CART']"));
                productPrice = driver.findElement(By.xpath("//android.widget.TextView[@text= '"+productName+"']/following-sibling::android.widget.LinearLayout/android.widget.TextView")).getText();
                addToCartButton.click();
            }
        }
    }

    public void clickCartIconButton(){
        driver.findElement(cartIcon).click();
    }
}
