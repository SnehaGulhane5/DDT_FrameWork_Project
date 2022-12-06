package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class Emp_Page {
WebDriver driver;
public Emp_Page(WebDriver driver)
{
this.driver=driver;
}
@FindBy (xpath="//b[normalize-space()='PIM']")
WebElement click_Pim;
@FindBy (name="btnAdd")
WebElement click_Add;
@FindBy (name="firstName")
WebElement firstName;
@FindBy (name="middleName")
WebElement middleName;
@FindBy (name="lastName")
WebElement lastName;
@FindBy (name="employeeId")
WebElement BeforeEid;
@FindBy (id="btnSave")
WebElement click_Save;
@FindBy (name="personal[txtEmployeeId]")
WebElement AfterEid;
public boolean verifyEmp(String fname,String mname,String lname) {
	Actions ac=new Actions(driver);
	ac.moveToElement(this.click_Pim).click().perform();
	ac.moveToElement(this.click_Add).click().perform();
	this.firstName.sendKeys(fname);
	this.middleName.sendKeys(mname);
	this.lastName.sendKeys(lname);
	String expected=this.BeforeEid.getAttribute("value");
	this.click_Save.click();
	String actual=this.AfterEid.getAttribute("value");
	if(expected.equals(actual)) {
		Reporter.log("Employee is Matching::"+expected+"     "+actual,true);
		return true;
	}
	else {
		Reporter.log("Employee is Not Matching::"+expected+"     "+actual,true);
		return false;
	}
}

}
