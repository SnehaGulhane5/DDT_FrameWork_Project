package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login_Page {
//define Repository
	@FindBy (name="txtUsername")
	WebElement user;
	@FindBy (name="txtPassword")
	WebElement pass;
	@FindBy (name="Submit")
	WebElement login_Btn;
	//define method
	public void verifyLogin(String username,String password) {
		user.sendKeys(username);
		pass.sendKeys(password);
		login_Btn.click();
	}
	
	
}
