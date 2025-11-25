import org.testng.annotations.Test;
import pageObjects.ProductsPage;
import pageObjects.RegistrationPage;
import utils.DriverUtility;

public class ProductsTests extends DriverUtility {

    @Test(groups = {"regression", "smoke"})
    public void verifyUserAbleToAddTheProductToCart(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        registrationPage.enterNameOnTheInputTextBox("Tester Name");
        registrationPage.selectGenderRadioButton("Male");
        registrationPage.clickLetsShopButton();
        registrationPage.verifyUserLandedOnProductsScreen();
        productsPage.addProductToCart("Air Jordan 1 Mid SE");
        productsPage.clickCartIconButton();
    }
}
