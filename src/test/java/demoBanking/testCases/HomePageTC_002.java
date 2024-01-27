package demoBanking.testCases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import demoBanking.pageObjects.HomePage;
import demoBanking.pageObjects.LoginPage;

public class HomePageTC_002 extends BaseClass {
	@Test
	public void Home_page() {
		LoginPage login = new LoginPage(driver);
		login.getLogin(userid, userpwd);
  
		HomePage home = new HomePage(driver);
		System.out.println("Entering to HomePage of Demo.Guru99");
		extentTest.log(Status.INFO, " === Logged in to App successfully === ");
		home.Home_Page();
		logger.info(" --Entered wrong ID-- ");
		String alertText = driver.switchTo().alert().getText();
		logger.info(" === Alert seen: " + alertText+"  === ");
		extentTest.log(Status.INFO, " === Alert seen: \"" + alertText+"\"  === ");
		driver.switchTo().alert().accept();
		logger.info(" === Alert accepted === ");
		extentTest.log(Status.INFO, " === Alert accepted === ");
		logger.info("line is added");
		extentTest.log(Status.INFO, " === line is added === ");

	}

}
