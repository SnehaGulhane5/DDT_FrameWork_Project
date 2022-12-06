package driverFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commonFunctions.Emp_Page;
import commonFunctions.Login_Page;
import commonFunctions.Logout_Page;
public class TestScript {
WebDriver driver;
@BeforeTest
public void setUp() {
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.get("http://orangehrm.qedgetech.com/");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Login_Page login=PageFactory.initElements(driver, Login_Page.class);
	login.verifyLogin("Admin", "Qedge123!@#");
}
@Test
public void startTest() {
	Emp_Page emp=PageFactory.initElements(driver, Emp_Page.class);
	boolean res=emp.verifyEmp("Selenium", "Manual", "Testing");
	Reporter.log("Add Employee"+res, true);
	
}
@AfterTest
public void tearDown() {
	Logout_Page logout=PageFactory.initElements(driver, Logout_Page.class);
	logout.verifyLogout();
	driver.close();
}
}
