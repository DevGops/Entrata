package utils;

import controllers.BaseTest;
import listeners.Reporter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExplicitWaiting extends BaseTest {

    /**
     * To Wait Until Element to be Clickable
     */
    public static void explicitWaitElementToBeClickable(WebElement element, int Seconds) {
        WebDriverWait clickableWait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(Seconds));
        clickableWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * To Wait Until Element to be Visible
     */
    public static void explicitWaitVisibilityOfElement(WebElement element, int Seconds) {
        WebDriverWait elementToBeVisible = new WebDriverWait(getWebDriver(), Duration.ofSeconds(Seconds));
        elementToBeVisible.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * To Wait Until Element to be Clickable
     */
    public static void explicitWaitElementToBeClickable(By element, int Seconds) {
        WebDriverWait clickableWait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(Seconds));
        clickableWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * To Wait Until Element to be Visible
     */
    public static void explicitWaitVisibilityOfElement(By element, int Seconds) {
        WebDriverWait elementToBeVisible = new WebDriverWait(getWebDriver(), Duration.ofSeconds(Seconds));
        elementToBeVisible.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

}