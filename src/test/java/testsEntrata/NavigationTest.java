package testsEntrata;

import controllers.Constants;
import listeners.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.PageFactoryInitializer;

public class NavigationTest extends PageFactoryInitializer {

    @BeforeMethod
    public void setup() throws Exception {
        getWebDriver().get(Constants.get("url"));
        log.info("Handling cookie popup");
        homePage().cookieConsent("accept");
    }

    @Test( description = "Navigation Test done on homepage for Products nav options")
    public void navigationFlowTest1() throws Exception {
        homePage().selectNavigationDropdownOptions("Products","ResidentPay");
        homePage().verifySuccessfulNavigation("ResidentPay");
    }

    @Test( description = "Navigation Test done on homepage for Solutions nav options")
    public void navigationFlowTest2() throws Exception {
        homePage().selectNavigationDropdownOptions("Solutions","Homebody");
        homePage().verifySuccessfulNavigation("Homebody");
    }

//    @Test( description = "Navigation Test done on homepage Resources nav options")
//    public void navigationFlowTest3() throws Exception {
//        homePage().selectNavigationDropdownOptions("Resources","Blog");
//        homePage().verifySuccessfulNavigation("Blog");
//    }
}
