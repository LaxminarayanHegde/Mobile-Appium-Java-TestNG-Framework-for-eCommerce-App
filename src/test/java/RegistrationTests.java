import org.testng.annotations.Test;
import pageObjects.RegistrationPage;
import utils.DriverUtility;

public class RegistrationTests extends DriverUtility {

    @Test(groups={"smoke", "regression"})
    public void verifyRegistrationHappyPathScenario() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.enterNameOnTheInputTextBox("Tester Name");
        registrationPage.selectGenderRadioButton("Male");
        registrationPage.clickLetsShopButton();
        registrationPage.verifyUserLandedOnProductsScreen();
    }
}
