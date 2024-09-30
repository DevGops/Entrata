package pageObjects.entrataPages;

import listeners.Reporter;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import pageObjects.PageFactoryInitializer;
import utils.Element;
import utils.RandomGenerator;

import java.util.List;
import java.util.Optional;

public class HomePage extends PageFactoryInitializer {

    // Locators
    private By firstName = By.id("FirstName");
    private By lastName = By.id("LastName");
    private By email = By.id("Email");
    private By companyName = By.id("Company");
    private By phoneNumber = By.id("Phone");
    private By jobTitle = By.id("Title");
    private By count = By.id("Unit_Count__c");
    private By user = By.id("demoRequest");
    private By watchDemoBtn = By.cssSelector("[class='mktoButton']");
    private List<WebElement> signinBtn = getWebDriver().findElements(By.cssSelector(".signin-button,.signin-button-2"));
    private List<WebElement> navaigationBar = getWebDriver().findElements(By.cssSelector(".nav-menu.w-nav-menu,.nav-menu-2.w-nav-menu"));
    //


    /**
     * This method clicks on the sign-in btn
     */
    public void clickOnSignIn(){
        WebElement sign;
        Reporter.info("Click on Sign-In Button");
        if(signinBtn.size()>1){
            sign = signinBtn.get(1);
        }
        else sign = signinBtn.get(0);

        Element.clickUsingWebElement(sign,"SignInBtn");
        Reporter.info("Assert that user landed on Sign In Page");
        Assertions.assertThat(getWebDriver().getCurrentUrl()).containsSequence("/sign-in");
    }


    /**
     * This method is used to fill form of Watch demo
     * @param userType
     * @param unitCount
     */
    public void WatchDemo(String userType, String unitCount) {
        Element.clearEnterText(
                firstName,
                RandomGenerator.random().name().firstName(),
                "First_Name_Input");

        Element.clearEnterText(
                lastName,
                RandomGenerator.random().name().lastName(),
                "Last_Name_Input");

        Element.clearEnterText(
                email,
                RandomGenerator.random().internet().emailAddress(),
                "Email_Input");

        Element.clearEnterText(
                companyName,
                RandomGenerator.random().company().name(),
                "Company_Name_Input");

        Element.clearEnterText(
                phoneNumber,
                RandomGenerator.random().phoneNumber().cellPhone(),
                "Phone_Number_Input");

        Element.selectByUsingValue(count,unitCount,"Unit_Count",10);

        Element.clearEnterText(
                jobTitle,
                RandomGenerator.random().company().profession(),
                "Job_Title_Input");

        Element.selectByUsingValue(user,userType,"User_Type",10);

        //commented to not submit the form
        //Element.getElement(watchDemoBtn).click();

    }

    /**
     * @param decision accept/decline cookies 1 accept 2. decline
     */
    public void cookieConsent(String decision){
        if(Element.getElement(By.id("cookie-"+decision)).isDisplayed()) {
            Element.getElement(By.id("cookie-" + decision)).click();
        }
    }


    /**
     * This method selects and navigates to page that has been passed as argument
     * @param navDropDownOption
     * @param pageOption
     */
    public void selectNavigationDropdownOptions(String navDropDownOption, String pageOption) throws InterruptedException {
        Reporter.info("click on Dropdown option: "+navDropDownOption);
        WebElement activeNavBar;
        if(navaigationBar.size()>1){
            activeNavBar = navaigationBar.get(1);
        }
        else activeNavBar = navaigationBar.get(0);

        List<WebElement> navOptions =
                activeNavBar
                        .findElements(
                                By.xpath("//div[@class='dropdown-menu-text---brix' and text()='"+navDropDownOption+"']"));

        for (WebElement ele : navOptions) {
            if(ele.getText().equals(navDropDownOption)){
             Actions ac = new Actions(getWebDriver());
             ac.moveToElement(ele).perform();
             break;
            }
        }
        Reporter.info("click on page: "+pageOption);
        List<WebElement> subNavOptions =
                getWebDriver().findElements(By.xpath("//a[contains(@class,'sub-menu')]"));

        switch (navDropDownOption) {
            case "Products":
                Element.clickUsingWebElement(
                        activeNavBar.findElement(By.cssSelector(
                                ".mega-nav a[href='/"+navDropDownOption.toLowerCase()+"/"+pageOption.toLowerCase()+"']")
                        )
                        ,pageOption);
                break;
            case "Solutions":
            case "Resources":
                if(subNavOptions.size()>10) {
                    getWebDriver()
                            .findElements(By.xpath("//a[text()='" + pageOption + "']"))
                            .get(1).click();
                }else
                    getWebDriver()
                        .findElements(By.xpath("//a[text()='"+pageOption+"']"))
                        .get(0).click();

                Reporter.info("click after sub click =======");
                Thread.sleep(3000);
                break;
            default:
                throw new IllegalArgumentException(navDropDownOption+ " -> "+pageOption +" is not a valid option");

        }
    }


    /**
     * Verifies the page navigation
     * @param title
     */
    public void verifySuccessfulNavigation(String title) {
        Reporter.info("verify "+title+ " page is open");
        Assertions.assertThat(getWebDriver().getTitle()).contains(title);
    }
}
