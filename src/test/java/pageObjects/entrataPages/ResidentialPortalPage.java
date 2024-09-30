package pageObjects.entrataPages;

import listeners.Reporter;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.PageFactoryInitializer;
import utils.Element;

public class ResidentialPortalPage extends PageFactoryInitializer {

    //locator
    private By appBtn = By.cssSelector(".app-button");


    //methods
    public void redirectToApp() {
        Reporter.info("Verify redirection to residential user app");
        Element.clickUsingBy(appBtn,"Residential App");
    }

    public void verifyAppRedirection() {
        Assertions.assertThat(Element.getText(By.cssSelector(".two-third h1"))).containsSequence("app");
    }

    public void redirectToWebsite() {
        Reporter.info("Verify redirection to residential user website");
        loginPage().clickOnResidentSignInBtn();
    }

    public void verifyWebsiteRedirection() {
        getWebDriver().findElement(By.cssSelector(".website-button")).click();
        Assertions.assertThat(Element.getText(By.cssSelector(".two-third h1"))).doesNotContain("app");
    }

    //
}
