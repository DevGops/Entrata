package controllers;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest extends BrowserFactory {
	private static final ThreadLocal<WebDriver> localWebDriverInstance = new ThreadLocal<WebDriver>();
	public static Logger log = null;

	Constants con;

	/**
	 * This method returns setup config
	 *
	 */
	@BeforeSuite
	public void setupConfig() {
		con = new Constants();
		con.readConfigFile();
		con.initConfig();
	}

	/**
	 * This method used for headless testing
	 * 
	 * @throws Exception
	 */
	@Parameters({ "browser" })
	@BeforeTest(alwaysRun = true)
	public void suiteSetup(String browser) throws Exception {
		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().arch64().setup();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			break;
		default:
			throw new NotFoundException("Browser Not Found. Please Provide a Valid Browser");
		}
	}

	/**
	 * This is method initialize the web driver and read the log4j file
	 * 
	 * @throws Exception
	 */
	@Parameters({ "browser" })
	@BeforeClass(alwaysRun = true)
	public void beforeMethod(String browser) throws Exception {
		System.out.println("Browser: " + browser);
		new BaseTest();
		//set web driver based on browser parameter
		setWebDriver(createDriver(browser));

		log = Logger.getLogger(getClass());
		String path = System.getProperty("user.dir");
		PropertyConfigurator.configure(path + "./src/main/resources/log4jFiles/log4j.properties");
		driver.manage().window().maximize();
	}

	/**
	 * Sets the given WebDriver as thread-local varible value
	 * 
	 * @param driver
	 */
	public void setWebDriver(WebDriver driver) {
		localWebDriverInstance.set(driver);
	}

	/**
	 * Returns the webdriver instance using threadLocal
	 * 
	 * @return WebDriver
	 */
	public static WebDriver getWebDriver() {
		return localWebDriverInstance.get();
	}

	/**
	 * This method quit the driver after class
	 * 
	 * @throws Exception
	 */
	@AfterClass(alwaysRun = true)
	public void afterMethod() throws Exception {
		getWebDriver().quit();
	}
}
