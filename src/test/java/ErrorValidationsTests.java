import org.testng.annotations.Test;
import pageObjects.RegistrationPage;
import utils.DriverUtility;

public class ErrorValidationsTests extends DriverUtility {

    @Test(groups = {"regression", "negative"})
    public void verifyRegistrationFlowErrorValidation() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.selectGenderRadioButton("Male");
        registrationPage.clickLetsShopButton();
        registrationPage.verifyUserNotLandedOnProductsScreen();
    }
}
