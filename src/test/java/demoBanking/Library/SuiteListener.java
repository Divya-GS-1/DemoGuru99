package demoBanking.Library;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import demoBanking.testCases.BaseClass;

public class SuiteListener implements ITestListener, IAnnotationTransformer
{
	  public void onTestFailure(ITestResult result) {
		  TakesScreenshot ts = (TakesScreenshot)BaseClass.driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String destination = System.getProperty("user.dir")+File.separator+"Screenshots"+File.separator+result.getName()+".png";
			try {
				FileUtils.copyFile(source, new File(destination));
			} catch (Exception e) {
				System.out.println("Exception found is: " + e.getMessage());
				e.printStackTrace();
			}
		  }
	  
	  public void transform(
		      ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		   annotation.setRetryAnalyzer(RetryAnalyser.class);
		  }
	  
}
