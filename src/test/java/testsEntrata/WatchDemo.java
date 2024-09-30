package testsEntrata;

import controllers.Constants;
import listeners.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.PageFactoryInitializer;

public class WatchDemo extends PageFactoryInitializer {

	@BeforeMethod
	public void setup() throws Exception {
		getWebDriver().get(Constants.get("url"));
		log.info("Handling cookie popup");
		homePage().cookieConsent("accept");
	}

	@Test( description = "Verify form submission for Watch_Demo")
	public void VerifyWatchDemoFormSubmission() throws Exception {
		Reporter.info("Fill Watch Demo Form");
		homePage().WatchDemo("a Resident","1 - 10");
	}

	@Test( description = "Verify form submission for Watch_Demo")
	public void VerifyWatchDemoFormSubmission2() throws Exception {
		Reporter.info("Fill Watch Demo Form");
		homePage().WatchDemo("an Owner/Operator or Property Manager","101 - 200");

	}
}