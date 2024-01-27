package demoBanking.Library;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility {

	public static String capturescreen(WebDriver driver, String Testcase_name) {
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = "./Screenshots/" + Testcase_name + ".png";
		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			System.out.println("Exception found is: " + e.getMessage());
			e.printStackTrace();
		}
		return destination;
	}
}
