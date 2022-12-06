package commonFunctions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil {
public static boolean verifyLogin(String username,String password) {
	driver.get("http://orangehrm.qedgetech.com/");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(username);
	driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
	driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
	String expected="dashboard";
	String actual=driver.getCurrentUrl();
	if(actual.contains(expected)) {
		Reporter.log("Login Success::"+expected+"   "+actual,true);
		return true;
	}
	else {
		String errormessage=driver.findElement(By.xpath("//span[@id='spanMessage']")).getText();
		Reporter.log(errormessage+"     "+expected+"   "+actual,true);
		return false;
	}
}
}
