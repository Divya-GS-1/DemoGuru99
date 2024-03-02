package demoBanking.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import demoBanking.pageObjects.HomePage;
import demoBanking.pageObjects.LoginPage;

public class HomePageTC_002 extends BaseClass {
	@Test
	public void Home_page() {
		LoginPage login = new LoginPage(driver);
		login.getLogin(userid, userpwd);
		extentTest.log(Status.INFO, "Login to App is successful");
		logger.info("Login to App is successful");
		
		
		HomePage home = new HomePage(driver);
		extentTest.log(Status.INFO, "Demo.Guru99 HomePage is displayed");
		logger.info("Demo.Guru99 HomePage is displayed");
		
		home.searchCustomer("1234");
		extentTest.log(Status.INFO, "Entered wrong/invalid ID for search");
		logger.info("Entered wrong/invalid ID for search");
		
		
		String alertText = driver.switchTo().alert().getText();
		extentTest.log(Status.INFO, "Alert is displayed: " + alertText+"  === ");
		logger.info("Alert is displayed: " + alertText+"  === ");
		
		if(alertText.contains("Customer does not exist")) {
			Assert.assertTrue(true);
			logger.info("Successfuly verified search user functionality with invalid user id");
		} else {
			Assert.assertTrue(false);
			logger.info("Search with invalid user id is failed");
		}
		
		driver.switchTo().alert().accept();
		logger.info("Alert is accepted");
		extentTest.log(Status.INFO, "Alert is accepted");
	}

}
