package demoBanking.testCases;

import org.testng.annotations.Test;

import demoBanking.pageObjects.HomePage;
import demoBanking.pageObjects.LoginPage;

public class HomePageTC_002 extends BaseClass {
	@Test
	public void Home_page() {
		LoginPage login = new LoginPage(driver);
		login.getLogin(userid, userpwd);

		HomePage home = new HomePage(driver);
		System.out.println("Entering to HomePage of Demo.Guru99");
		home.Home_Page();
		logger.info(" --Entered wrong ID-- ");
		String alertText = driver.switchTo().alert().getText();
		System.out.println("Alert seen: " + alertText);
		driver.switchTo().alert().accept();
		logger.info(" --Alert accepted-- ");
		System.out.println("line is added");

	}

}
