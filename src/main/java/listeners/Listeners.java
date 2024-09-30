package listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Set;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import controllers.Constants;
import utils.Element;

public class Listeners implements ITestListener, IAnnotationTransformer, IRetryAnalyzer {

	ExtentReports extent;
	ExtentTest test;
	Reporter report;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	int counter = 0;
	int retryLimit = 0;

	@Override
	public void onStart(ITestContext context) {
		try {
			report = new Reporter();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extent = Reporter.extent;
	}

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		getExtentTest().set(test);
		System.out.println("OnTestStart -> " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.pass(result.getMethod().getMethodName() + " is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String path = Element.takeScreenshot(methodName);

		try {
			getExtentTest().get().fail("<b><font color=red> Screenshot of failure</font></b>",
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (Exception ex) {
			getExtentTest().get().fail("Test Failed, unable to attach screenshot.");
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		 Reporter.skip(result.getMethod().getMethodName() + " is Skipped");
		 extent.removeTest(test);

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// extent.flush();
		Set<ITestResult> passedTestCasesCount = context.getPassedTests().getAllResults();
		Constants.passedTestCasesCount = String.valueOf(passedTestCasesCount.size());

		Set<ITestResult> failedTestCasesCount = context.getFailedTests().getAllResults();
		Constants.failedTestCasesCount = String.valueOf(failedTestCasesCount.size());

		Set<ITestResult> skippedTests = context.getSkippedTests().getAllResults();
		Constants.skippedTestCasesCount = String.valueOf(skippedTests.size());

		Constants.totalTestCasesCount = String
				.valueOf(passedTestCasesCount.size() + failedTestCasesCount.size() + skippedTests.size());

		report.export();
	}

	public static ThreadLocal<ExtentTest> getExtentTest() {
		return extentTest;
	}

	public static void setExtentTest(ThreadLocal<ExtentTest> extentTest) {
		Listeners.extentTest = extentTest;
	}

	@Override
	public boolean retry(ITestResult result) {
		//Reporter.info(result.getMethod().getMethodName() + " is retrying");
		if (counter < retryLimit) {
			counter++;
			return true;
		}
		return false;
	}
	
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(Listeners.class);
	}	
}
