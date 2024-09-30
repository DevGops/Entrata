package controllers;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;

public class BrowserFactory {

	static WebDriver driver;
	/**
	 * This method sets up configurations for running browser
	 * 
	 * @return WebDriver
	 */
	static WebDriver createDriver(String browser) {
		switch (browser.toLowerCase()) {
		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--window-size=1600,900");
			chromeOptions.setAcceptInsecureCerts(true);
			chromeOptions.addArguments("ignore-certificate-errors");
			chromeOptions.addArguments("--incognito");
			chromeOptions.addArguments("--no-proxy-server");
			driver = new ChromeDriver(chromeOptions);
			break;
		case "firefox":
			FirefoxOptions fo = new FirefoxOptions();
			fo.setAcceptInsecureCerts(true);
			driver = new FirefoxDriver();
			break;
		case "edge":
			EdgeOptions eo = new EdgeOptions();
			eo.setAcceptInsecureCerts(true);
			driver = new EdgeDriver();
			break;
		default:
			throw new NotFoundException("Browser Not Found. Please Provide a Valid Browser");
		}
		return driver;
	}

}
