import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.ProductsPage;
import pageObjects.RegistrationPage;
import utils.DriverUtility;

public class CartPageTests extends DriverUtility {
    String productName = "Air Jordan 1 Mid SE";

    @Test(groups = {"regression"})
    public void verifyProductPurchaseFlowFOrSingleProductOnCartPage(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        registrationPage.enterNameOnTheInputTextBox("Tester Name");
        registrationPage.selectGenderRadioButton("Male");
        registrationPage.clickLetsShopButton();
        registrationPage.verifyUserLandedOnProductsScreen();

        productsPage.addProductToCart(productName);
        productsPage.clickCartIconButton();

        cartPage.verifyCartPageTitleDisplayed();
        cartPage.validateProductNameOnCartPage(productName);
        cartPage.validateTotalProductPriceOnCartPage();
        cartPage.clickTermsAndConditionCheckBox();
        cartPage.clickPurchaseButton();
    }
}
