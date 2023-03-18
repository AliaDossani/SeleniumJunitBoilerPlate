package pageclass;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import objectrepository.LoginPage;

public class LoginTest{

	WebDriver driver;
	ExtentReports extent;
	Properties props=new Properties();
	FileInputStream fis;
	
	
	
	
	@BeforeClass
	public void config() throws Exception{
//		String filename=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter("reports/index.html");
		reporter.config().setReportName("TutorialsNinja Report");
		
	    extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Projectname", "TutorialsNinja");
		extent.setSystemInfo("Version", "1");
		extent.setSystemInfo("Tested By", "Alia");
		
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		
		fis=new FileInputStream("data.properties");
		props.load(fis);
		
		driver.get(props.getProperty("URL"));
		//LoginPage loginpage=new LoginPage(driver);
		
		}
	
	@Test
	public void logintest() throws Exception {
		ExtentTest etest=extent.createTest("TestOne started");
		
		driver.get(props.getProperty("URL"));
		
		
		LoginPage loginpage=new LoginPage(driver);
		driver.manage().timeouts().implicitlyWait(3000,TimeUnit.SECONDS);
		
		
		loginpage.username().sendKeys(props.getProperty("username"));
		loginpage.password().sendKeys(props.getProperty("password"));
		loginpage.login().click();
		}
	@Test
	public void faillogintest() throws IOException {
		ExtentTest etest=extent.createTest("TestTwo started");
		//fis=new FileInputStream("data.properties");
		//props.load(fis);
		driver.get(props.getProperty("URL"));
		
		LoginPage loginpage=new LoginPage(driver);
		driver.manage().timeouts().implicitlyWait(3000,TimeUnit.SECONDS);
		
		
		loginpage.username().sendKeys(props.getProperty("username1"));
		loginpage.password().sendKeys(props.getProperty("password1"));
		loginpage.login().click();
		
		
		
	}
	
	@AfterTest
	public void closure() {
		driver.close();
		extent.flush();
	}
	
}
