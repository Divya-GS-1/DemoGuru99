package demoBanking.pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
		
		@FindBy(xpath = "//*[text()='Edit Customer']")
		WebElement Edit_Cust;
		
		@FindBy(name = "cusid")
		WebElement Enter_Id;
		
		@FindBy(name = "AccSubmit")
		WebElement Acc_Submit;
		

	public void Home_Page() {
		Edit_Cust.click();
		Enter_Id.sendKeys("1234567");
		Acc_Submit.click();
				
	}
	
}
