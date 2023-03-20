package pageclass;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
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
	Properties props = new Properties();
	FileInputStream fis;
	ExtentSparkReporter reporter;

	@BeforeClass
	public void config() throws Exception {
//		String filename=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter("reports/index.html");
		reporter.config().setReportName("TutorialsNinja Report");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Projectname", "TutorialsNinja");
		extent.setSystemInfo("Version", "1");
		extent.setSystemInfo("Tested By", "Alia");

		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		fis = new FileInputStream("data.properties");
		props.load(fis);

		driver.get(props.getProperty("URL"));

	}

	@Test
	@Parameters({ "username", "password" })
	public void success(String username, String password) throws Throwable {
		ExtentTest etest = extent.createTest("TestThree started");
		LoginPage loginpage = PageFactory.initElements(driver, LoginPage.class);
		driver.get(props.getProperty("URL"));

		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);

		loginpage.login(driver, username, password);

		AccountPage accpage = new AccountPage(driver);

		Assert.assertTrue(accpage.loginsuccessful().isDisplayed());

	}

	@AfterTest
	public void closure() {
		driver.close();
		extent.flush();
	}
}
