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
import util.ExcelFileUtil;

public class DriverScriptPOM {
	WebDriver driver;
	String inputpath="D:\\eclipse-workspace\\DDT_FrameWork\\TestInput\\Employee.xlsx";
	String  outputpath="D:\\eclipse-workspace\\DDT_FrameWork\\TestOutPut\\EmpResults.xlsx";
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
	public void startTest() throws Throwable {
		ExcelFileUtil xl=new ExcelFileUtil(inputpath);
		int rc=xl.rowCount("EmpData");
		Reporter.log("No. of rows are::"+rc,true);
		for(int i=1;i<=rc;i++) {
			String fname=xl.getCellData("EmpData", i, 0);
			String mname=xl.getCellData("EmpData", i, 1);
			String lname=xl.getCellData("EmpData", i, 2);
			Emp_Page emp=PageFactory.initElements(driver, Emp_Page.class);
			boolean res=emp.verifyEmp(fname, mname, lname);
			if(res) {
				xl.setCellData("EmpData", i, 3, "Pass", outputpath);
			}
			else 
			{
				xl.setCellData("EmpData", i, 3, "Fail", outputpath);
			}
		}
	}
@AfterTest
public void tearDown() {
	Logout_Page logout=PageFactory.initElements(driver, Logout_Page.class);
	logout.verifyLogout();
	driver.close();
}
}