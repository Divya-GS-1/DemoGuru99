package demoBanking.testCases;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import demoBanking.Library.ReadConfig;
import demoBanking.Library.Utility;

public class BaseClass {
	
	static ExtentReports extentReport;
	ExtentTest extentTest;
	
	ReadConfig read = new ReadConfig();
	public final String App_link = read.getApplicationURL();
	public final String userid = read.getUserName();
	public final String userpwd = read.getPassword();

	public static WebDriver driver;
	Logger logger;
	
	@BeforeTest
	public void initialize() {
		System.out.println(" === @BeforeTest method started === ");
		String reportFileName = System.getProperty("user.dir")+File.separator+"reports"+File.separator+"index.html";
		extentReport = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(reportFileName);
		spark.config().setDocumentTitle("DemoGuru99");
		spark.config().setReportName("DemoGuru99-Automation Testing");
		extentReport.attachReporter(spark);
		extentReport.setSystemInfo("Environment", "QA");
		extentReport.setSystemInfo("OS", System.getenv("OS"));
		System.out.println(" === @BeforeTest method ended === ");
	}
/*	
	@BeforeTest
	@Parameters("Browser")
	public void setup(String browser) 
	{
		System.out.println("@BeforeTest method executed");
		if(browser.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		}else {
			driver = new FirefoxDriver();
		}
	}
*/	
	
	@BeforeMethod
	@Parameters("Browser")
	public void methodSetup(String browser,Method method) 
	{
		System.out.println(" === @BeforeMethod method starte === ");
		startBrowser(browser);
		
		logger = Logger.getLogger(method.getName().toString());
		PropertyConfigurator.configure("./log4j.properties");
		
		extentTest=extentReport.createTest(method.getName().toString());
		extentTest.log(Status.INFO, " === "+method.getName().toString()+ " test case STARTED === on  "+ browser);
		System.out.println(" === @BeforeMethod method ended === ");
	}

	@AfterMethod
	public void tearDown(ITestResult result) 
	{
		System.out.println(" === @AfterMethod method started === ");
        if (driver!=null){
            System.out.println("[INFO] === deleteAllCookies === ");
            driver.manage().deleteAllCookies();
            }
        if (result.getStatus() == ITestResult.FAILURE)
        {
            System.out.println("[INFO] === Test case "+result.getName()+" FAILED === "); //Which Test Failed and Why (Method Name Output)
            extentTest.log(Status.FAIL,  MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));
            //String filePath=Utility.capturescreen(driver, result.getName());
            String filePath = System.getProperty("user.dir")+File.separator+"Screenshots"+File.separator+result.getName()+".png";
            File file=new File(filePath);
            System.out.println("screenshot file exist: "+ file.exists());
            if(file.exists()) {
            	extentTest.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(filePath).build());
            }
        }
        else if (result.getStatus() == ITestResult.SUCCESS)
        {
            System.out.println("[INFO] === Test case "+result.getName()+" PASSED === ");
            extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED.", ExtentColor.GREEN));
            System.out.println("====================================================================================================="); 
            System.out.println("Automation Test Run: " + result.getMethod().toString());
            System.out.println("=====================================================================================================");
        } else if (result.getStatus() == ITestResult.SKIP)
        {
            System.out.println("[INFO] === Test case "+result.getName()+" SKIPPED === ");
            extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED.", ExtentColor.ORANGE));
        }
        driver.quit();
        System.out.println(" === @AfterMethod method ended === ");
	}
	
	   @AfterTest
       public void testComplete() {
		   System.out.println(" === @AfterTest method started === ");
		   extentReport.flush();
		   System.out.println(" === @AfterTest method ended === ");
       }
	   
	   @AfterSuite
	   public void suiteComplete() {
		   System.out.println(" === @AfterSuite method executed === ");
		   
	   }
	   
	public void startBrowser(String browser) 
	{
		if(browser.equalsIgnoreCase("Chrome")) {
			ChromeOptions options = new ChromeOptions();
		    HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		    chromePrefs.put("profile.default_content_settings.popups", 0);
		    chromePrefs.put("download.default_directory","./Downloads" );
		    options.setExperimentalOption("prefs", chromePrefs);
		    options.addArguments("--test-type");
		    options.addArguments("start-maximized", "disable-popup-blocking");
			driver = new ChromeDriver(options);
			
		}else {
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.get(App_link);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
}
