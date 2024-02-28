package demoBanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(name = "uid")
	private WebElement input_id;
	
	@FindBy(name = "password")
	private WebElement input_pw;
	
	@FindBy(name = "btnLogin")
	private WebElement button_login;
	
	public void getLogin(String user_id, String user_pwd) {
		 input_id.sendKeys(user_id);
		 input_pw.sendKeys(user_pwd);
		 button_login.click();
	}

}
