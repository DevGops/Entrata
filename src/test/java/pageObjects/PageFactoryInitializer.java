package pageObjects;

import controllers.BaseTest;
import org.openqa.selenium.support.PageFactory;
import pageObjects.entrataPages.HomePage;
import pageObjects.entrataPages.ResidentialPortalPage;
import pageObjects.entrataPages.SignInPage;

public class PageFactoryInitializer extends BaseTest {

//	/**
//	 * @return This method return LoginPage
//	 */
//	public LoginPage loginPage() {
//		return PageFactory.initElements(getWebDriver(), LoginPage.class);
//	}
//
    /**
     * @return This method return HomePage
     */
    public HomePage homePage() {
        return PageFactory.initElements(getWebDriver(), HomePage.class);
    }

    /**
     * @return This method return LoginPage
     */
    public SignInPage loginPage() {
        return PageFactory.initElements(getWebDriver(), SignInPage.class);
    }

    /**
     * @return This method return HomePage
     */
    public ResidentialPortalPage residentialPortalPage() {
        return PageFactory.initElements(getWebDriver(), ResidentialPortalPage.class);
    }
}