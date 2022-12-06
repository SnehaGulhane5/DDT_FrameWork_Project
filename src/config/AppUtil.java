package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {
	public static WebDriver driver;
@BeforeTest
public static void setUp() {
	driver=new ChromeDriver();
	driver.manage().window().maximize();
}
@AfterTest
public static void tearDown() {
	driver.close();
}
}
