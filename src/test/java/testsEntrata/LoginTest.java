package testsEntrata;

import controllers.Constants;
import listeners.Reporter;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.PageFactoryInitializer;
import utils.Element;
import utils.RandomGenerator;

public class LoginTest extends PageFactoryInitializer {


    @BeforeMethod
    public void setup() throws Exception {
        getWebDriver().get(Constants.get("url"));
        log.info("Handling cookie popup");
        homePage().cookieConsent("accept");
    }

    @Test(description = "Login for Property Manager")
    public void loginWithPropertyManager() {
        Reporter.info("clicking on user SignIn page");
        homePage().clickOnSignIn();

        Reporter.info("Navigating to Property Manager loginPage");
        loginPage().clickPropertyManagerOnSignIn();

        Reporter.info("Logging in as a Property Manager");
        // will send credentials from this method but as we are sending random
        loginPage().logInAsAPropertyManager(
                RandomGenerator.random().internet().emailAddress(),
                RandomGenerator.random().internet().password()
        );
    }

    @Test(description = "Login for Resident")
    public void loginWithResident() {
        homePage().clickOnSignIn();
        loginPage().clickOnResidentSignInBtn();
        //verify app
        residentialPortalPage().redirectToApp();
        residentialPortalPage().verifyAppRedirection();
        getWebDriver().navigate().back();
        //verify website
        residentialPortalPage().redirectToWebsite();
        residentialPortalPage().verifyWebsiteRedirection();
    }


}
