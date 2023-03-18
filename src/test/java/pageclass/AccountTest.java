package pageclass;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import objectrepository.AccountPage;
import objectrepository.LoginPage;

public class AccountTest {

	WebDriver driver;
	ExtentReports extent;
	Properties props=new Properties();
	FileInputStream fis;
	ExtentSparkReporter reporter;
	
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
	public void success() throws Throwable {
		ExtentTest etest=extent.createTest("TestThree started");
		driver.get(props.getProperty("URL"));
		//LoginTest lt=new LoginTest();
		//lt.config();
		//lt.logintest();
		LoginPage loginpage=new LoginPage(driver);
		driver.manage().timeouts().implicitlyWait(3000,TimeUnit.SECONDS);
		
		
		loginpage.username().sendKeys(props.getProperty("username"));
		loginpage.password().sendKeys(props.getProperty("password"));
		loginpage.login().click();
//		WebDriverWait wait=new WebDriverWait(driver,10);
//		WebElement 
		AccountPage accpage=new AccountPage(driver);
		 
		Assert.assertTrue(accpage.loginsuccessful().isDisplayed());
		
		
		}
	
	@AfterTest
	public void closure() {
		driver.close();
		extent.flush();
	}
}
