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
		extentTest.log(Status.INFO, " === Logged in to App successfully === ");
		String Title=driver.getTitle();
		extentTest.log(Status.INFO, " === Logged in successfully and obtained the Page Title === "+Title);
		logger.info(" === Logged in successfully and obtained the Page Title === ");
		 
		if(Title.contains("HomePage1")) {
			Assert.assertTrue(true);
			logger.info(" === Successfuly Title is Verified === ");
		} else {
			Assert.assertTrue(false);
			logger.info(" === Verification of Title is failed === ");
		}
	}
	
	@Test
	public void dummy() {
		
		logger.info(" === Dummay testcase === ");
		extentTest.log(Status.INFO, " === Logged in to App successfully === ");
	}

}
