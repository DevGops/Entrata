package listeners;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import controllers.Constants;
import controllers.BaseTest;

public class Reporter extends BaseTest {

	public static ExtentReports extent;
	public static ExtentHtmlReporter reporter;
	public static String ReportFileName;

	/**
	 * This constructor sets all the configuration for extent report
	 * 
	 * @throws Exception
	 */
	public Reporter() throws Exception {
		reporter = new ExtentHtmlReporter(getReportName());
		reporter.config().setEncoding("utf-8");
		reporter.config().setDocumentTitle("AutomationReport");
		reporter.config().setReportName(getReportName());

		extent = new ExtentReports();
		extent.setSystemInfo("Environemnt", "DEV");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		reporter.config().setTheme(Theme.STANDARD);
		extent.attachReporter(reporter);
	}

	/**
	 * This method is for printing the pass statement and log
	 * 
	 * @param description
	 * @return String
	 */
	public static String pass(String description) {
		Listeners.getExtentTest().get().pass(description);
		log.info(description);
		return description;
	}

	/**
	 * This method is for printing the info statement and log
	 * 
	 * @param description
	 * @return String
	 */
	public static String info(String description) {
		Listeners.getExtentTest().get().info(description);
		log.info(description);
		return description;
	}

	/**
	 * This method is for printing the error statement and log
	 * 
	 * @param description
	 * @return String
	 */
	public static String error(String description) {
		Listeners.getExtentTest().get().error(description);
		log.info(description);
		return description;
	}

	/**
	 * This method is for printing the fail statement and log
	 * 
	 * @param description
	 * @return String
	 */
	public static String fail(String description) {
		Listeners.getExtentTest().get().fail(description);
		log.info(description);
		return description;
	}

	/**
	 * This method is for printing the skip statement and log
	 * 
	 * @param description
	 * @return String
	 */
	public static String skip(String description) {
		Listeners.getExtentTest().get().skip(description);
		log.info(description);
		return description;
	}

	/**
	 * This method is for printing the Ribbon logo on html report
	 */
	public void export() {
		try {
			extent.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method return the name of the report using date
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getReportName() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Date date = new Date();
		ReportFileName = Constants.get("site") + "_"+ dateFormat.format(date) +".html";
		return System.getProperty("user.dir") + "/Reports/" + ReportFileName;
	}

	/**
	 * This method is for printing the fail statement and log
	 * 
	 * @param description
	 * @return String
	 */
	public static void reportAssertFail(String description) {
		Listeners.getExtentTest().get().fail(description);
		log.info(description);
		Assert.fail(description);
	}
}
