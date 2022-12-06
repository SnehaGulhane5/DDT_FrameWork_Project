package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import util.ExcelFileUtil;

public class DriverScript extends AppUtil{
	String inputpath="D:\\eclipse-workspace\\DDT_FrameWork\\TestInput\\TestData.xlsx";
	String outputpath="D:\\eclipse-workspace\\DDT_FrameWork\\TestOutPut\\DataDrivenResults.xlsx";
	@Test
	public void startTest() throws Throwable {
		//create object for excelfile util class
		ExcelFileUtil xl=new ExcelFileUtil(inputpath);
		//count no of rows
		int rc=xl.rowCount("Login");
		Reporter.log("No. of rows are::"+rc,true);
		for(int i=1;i<=rc;i++) {
			String user=xl.getCellData("Login", i, 0);
			String pass=xl.getCellData("Login", i, 1);
			boolean res=FunctionLibrary.verifyLogin(user, pass);
			if(res) {
				xl.setCellData("Login", i, 2, "Login Success", outputpath);
				xl.setCellData("Login", i, 3, "Pass", outputpath);	
			}
			else {
				File screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screen,new File("./Screen/"+i+"Homepage.png"));
				xl.setCellData("Login",i,2,"Login Fail",outputpath);
				xl.setCellData("Login",i,3,"Fail",outputpath);
			}
		}
	}
}
