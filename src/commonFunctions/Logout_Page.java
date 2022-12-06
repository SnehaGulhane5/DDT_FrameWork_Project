package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Logout_Page {
@FindBy(id="welcome")
WebElement click_welcome;
@FindBy (linkText="Logout")
WebElement click_logout;
public void verifyLogout() {
	click_welcome.click();
	click_logout.click();
}
}
