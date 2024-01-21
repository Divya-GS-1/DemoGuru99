package demoBanking.Library;

import java.awt.Color;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport implements ITestListener {

	ExtentReports report;
	ExtentSparkReporter spark;
	ExtentTest test_handle;

	public void onStart(ITestContext context) {
		String Timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String report_name = "./test-output/DemoAutomation-Report-" + Timestamp + ".html";

		report = new ExtentReports();
		spark = new ExtentSparkReporter(report_name);
		spark.config().setDocumentTitle("DemoGuru99");
		spark.config().setReportName("DemoGuru99-Automation Testing");
		report.attachReporter(spark);
		report.setSystemInfo("OS", System.getenv("OS"));

	}

	public void onTestStart(ITestResult result) {
		test_handle = report.createTest(result.getName());
		test_handle.generateLog(Status.INFO, result.getName() + "	---Test started--- ");
	}

	public void onTestSuccess(ITestResult result) {
		test_handle.log(Status.PASS, MarkupHelper
				.createLabel("Successfully completed the " + result.getName() + " Test ", ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult result) {
	test_handle.log(Status.FAIL, MarkupHelper.createLabel(" Sorry "+result.getName()+" Test failed. Refer below INFO for exceptions", ExtentColor.RED));
	test_handle.log(Status.INFO, result.getThrowable().getMessage());
	String screenshotPath="./bankDemoScreenshots/"+result.getName()+".png";
	File file =new File(screenshotPath);
	if(file.exists()) {
		test_handle.fail(result.getName(), MediaEntityBuilder.createScreenCaptureFromPath("."+screenshotPath).build());
	}
	  }

	public void onFinish(ITestContext context) {
		report.flush();
	}
}
