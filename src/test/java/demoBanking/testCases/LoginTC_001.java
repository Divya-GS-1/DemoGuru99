package demoBanking.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import demoBanking.pageObjects.LoginPage;


public class LoginTC_001 extends BaseClass {

	@Test
	public void Login_page() {
		LoginPage login = new LoginPage(driver);
		login.getLogin(userid, userpwd);
		extentTest.log(Status.INFO, "Login to App is successful");
		logger.info("Login to App is successful");
		
		
		String Title=driver.getTitle();
		extentTest.log(Status.INFO, "Obtained the Page Title: "+Title);
		logger.info("Obtained the Page Title: "+Title);
		 
		if(Title.contains("HomePage")) {
			Assert.assertTrue(true);
			logger.info("Successfuly page Title is Verified");
		} else {
			Assert.assertTrue(false);
			logger.info("Verification of Title is failed");
		}
	}
	
	@Test
	public void dummy() {
		
		logger.info("Dummay testcase");
		extentTest.log(Status.INFO, "Logged in to App successfully");
	}

}
