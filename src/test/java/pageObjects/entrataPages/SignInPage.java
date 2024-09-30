package pageObjects.entrataPages;

import listeners.Reporter;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import pageObjects.PageFactoryInitializer;
import utils.Element;
import utils.RandomGenerator;

public class SignInPage extends PageFactoryInitializer {

    //locators
    By propertyManagerLoginBtn = By.xpath("//div[text()='Property Manager Login']//parent::a");
    By residentLoginBtn = By.xpath("//div[text()='Resident Login']");

    //
    By propertyManagerUsername = By.id("entrata-username");
    By propertyManagerPassword = By.id("entrata-password");
    By propertyManagerSinginBtn = By.xpath("//*[text()='Sign In']");


    //methods
    public void clickPropertyManagerOnSignIn(){
        Reporter.info("Click on Sign-In Button");
        Element.clickUsingBy(propertyManagerLoginBtn,"PropertyManagerLoginBtn");
        Assertions.assertThat(getWebDriver().getCurrentUrl()).isEqualTo("https://sso.entrata.com/entrata/login");
    }

    public void logInAsAPropertyManager(String username,String password){
        Reporter.info("  Fill username as "+ username);
        Element.enterText(propertyManagerUsername,
                          username ,
                     "Property_Manager_Username"
                          );
        Reporter.info("  Fill password as "+ password);

        Element.enterText(propertyManagerPassword,
                            password,
                            "Property_Manager_Password"
                            );
        Element.clickUsingBy(propertyManagerSinginBtn,"PropertyManagerSigninBtn");
        Reporter.info("  Assert that ["+ username +" "+ password +"] are invalid");
        Assertions.assertThat(Element.getText(By.id("statusMessage")))
                .isEqualTo("The username and password provided are not valid. Please try again.");
    }

    public void clickOnResidentSignInBtn(){
        Reporter.info("Navigate to Residential Sign-In page");
        Element.clickUsingBy(residentLoginBtn,"ResidentLoginBtn");
        Assertions.assertThat(getWebDriver().getTitle()).isEqualTo("Welcome to the Resident Portal App");

    }

}
