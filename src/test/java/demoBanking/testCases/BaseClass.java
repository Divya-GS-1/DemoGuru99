package demoBanking.testCases;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import demoBanking.Library.ReadConfig;
import demoBanking.Library.Utility;

public class BaseClass {
	ReadConfig read = new ReadConfig();
	String App_link = read.getApplicationURL();
	String userid = read.getUserName();
	String userpwd = read.getPassword();

	WebDriver driver;
	Logger logger;

	@BeforeMethod
	@Parameters("Browser")
	public void start_Test(String browser) {
		if(browser.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		}else {
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.get(App_link);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		logger = Logger.getLogger("Automation Test");
		PropertyConfigurator.configure("./log4j.properties");
	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			logger.info(result.getName() + "Test case is failed");
			Utility.capturescreen(driver, result.getName());
		}
		driver.quit();
	}
}
