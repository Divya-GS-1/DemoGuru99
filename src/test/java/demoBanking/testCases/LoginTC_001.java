package demoBanking.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import demoBanking.pageObjects.LoginPage;


public class LoginTC_001 extends BaseClass {

	@Test
	public void Login_page() {
		LoginPage login = new LoginPage(driver);
		login.getLogin(userid, userpwd);
		System.out.println("Logged in successfully");
		String Title=driver.getTitle();
		System.out.println("Page Title: "+Title);
		logger.info("Logged in successfully and obtained the Page Title ----");
		System.out.println("line in tc 1 is	 added");
		System.out.println(" second line is added");
		if(Title.contains("HomePage")) {
			Assert.assertTrue(true);
			logger.info(" ---Successfuly Title is Verified--- ");
		} else {
			Assert.assertTrue(false);
			logger.info(" ---Verification of Title is failed--- ");
		}
	}

}
