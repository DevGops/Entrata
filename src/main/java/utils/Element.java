
package utils;

import java.io.File;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import controllers.BaseTest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import listeners.Reporter;

public class Element extends BaseTest {

	static int waitInSeconds = 30;
	/**
	 * This method is for click the specific element
	 * 
	 * @param element
	 * @param elementName
	 */
	public static void clickUsingBy(By element, String elementName) {
		try {
			ExplicitWaiting.explicitWaitElementToBeClickable(element, waitInSeconds);
			getWebDriver().findElement(element).click();
		} catch (Exception ex) {
			log.info(elementName + " is not Clickable");
		}
	}

	/**
	 * This method is for click the specific element
	 * 
	 * @param element
	 * @param elementName
	 */
	public static void clickUsingWebElement(WebElement element, String elementName) {
		try {
			ExplicitWaiting.explicitWaitElementToBeClickable(element, waitInSeconds);
			element.click();
		} catch (Exception ex) {
			log.info(elementName + " is not Clickable");
			ex.printStackTrace();
		}
	}

	/**
	 * This method is for clear the previous text and enter new text
	 * 
	 * @param element
	 * @param text
	 * @param elementName
	 */
	public static void clearEnterText(By element, String text, String elementName) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(element, waitInSeconds);
			if (getWebDriver().findElement(element).isEnabled()) {
				clearTextKeys(element, elementName);
				getElement(element).sendKeys(text.trim());
			}
		} catch (Exception ex) {
			log.info(elementName + " is not Enabled");
			ex.printStackTrace();
		}
	}

	/**
	 * This method is clear the entire text using keys operation
	 * 
	 * @param element
	 * @param elementName
	 */
	public static void clearTextKeys(By element, String elementName) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(element, waitInSeconds);
			if (getElement(element).isEnabled()) {
				getElement(element).sendKeys(Keys.chord(Keys.CONTROL, "a"));
				getElement(element).sendKeys(Keys.BACK_SPACE);
			}
		} catch (Exception ex) {
			log.info(elementName + " is not Enabled");
			ex.printStackTrace();
		}
	}

	/**
	 * This method is for enter new text
	 * 
	 * @param element
	 * @param text
	 * @param elementName
	 */
	public static void enterText(By element, String text, String elementName) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(getElement(element), waitInSeconds);
			getElement(element).sendKeys(text);

		} catch (Exception ex) {
			log.info(elementName + " is not Enabled");
			ex.printStackTrace();
		}
	}

	/**
	 * This method return name of the screenshot
	 * 
	 * @param methodName
	 * @return String
	 */
	public static String getScreenshotName(String methodName) {
		Date d = new Date();
		String fileName = methodName + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".png";
		return fileName;
	}

	/**
	 * This method is used to take screenshots
	 * 
	 * @param methodName
	 * @return String
	 */
	public static String takeScreenshot(String methodName) {
		String fileName = getScreenshotName(methodName);
		String directory = "Reports/screenshots/";
		new File(directory).mkdirs();
		String path = directory + fileName;
		try {
			File screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(path));
		} catch (Exception e) {
		}
		return "screenshots/" + fileName;
	}

	/**
	 * This method return web element
	 * 
	 * @param element
	 * @return WebElement
	 */
	public static WebElement getElement(By element) {
		WebElement webElement = null;
		try {
			webElement = getWebDriver().findElement(element);
			return webElement;
		} catch (Exception e) {
			log.info("Xpath is not found");
		}
		return webElement;
	}

	/**
	 * This method return list of web elements
	 * 
	 * @param element
	 * @return List<WebElement> - WebElement
	 */
	public static List<WebElement> getElementList(By element) {
		List<WebElement> elementList = null;
		try {
			elementList = getWebDriver().findElements(element);
			return elementList;
		} catch (Exception e) {
			log.info("Xpath is not found");
			e.printStackTrace();
		}
		return elementList;
	}

	/**
	 * This method return text of web element
	 * 
	 * @param element
	 * @return String
	 */
	public static String getText(By element) {
		try {
			ExplicitWaiting.explicitWaitVisibilityOfElement(element, waitInSeconds);
			return getWebDriver().findElement(element).getText().trim();
		} catch (Exception e) {
			log.info("Xpath is not found");
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * This method is for click the specific element
	 * 
	 * @param element
	 * @param elementName
	 */
	public static void clickUsingByInstant(By element, String elementName, int wait) {
		try {
			ExplicitWaiting.explicitWaitElementToBeClickable(element, wait);
			getWebDriver().findElement(element).click();
		} catch (Exception ex) {
			log.info(elementName + " is not Clickable");
		}
	}

	/**
	 * This method is used to select the option using select class
	 *
	 * @param selectorElement
	 * @param value
	 * @param elementName
	 * @param wait
	 */
	public static void selectByUsingValue(By selectorElement, String value ,String elementName, int wait) {

		Select sel = new Select(getWebDriver().findElement(selectorElement));
		try {
//			ExplicitWaiting.explicitWaitElementToBeClickable(selectorElement, wait);
			sel.selectByValue(value);
		} catch (Exception ex) {
			log.info("unable to select "+value);
		}
	}
}
