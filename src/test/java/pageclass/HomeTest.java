package pageclass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import objectrepository.HomePage;
import objectrepository.SelectPlanPage;

public class HomeTest {

	WebDriver driver;
	Properties prop;
	FileInputStream fis;

	@BeforeMethod
	public void setup() throws Exception {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		prop = new Properties();
		fis = new FileInputStream("data.properties");
		prop.load(fis);

		driver.get(prop.getProperty("everyplateurl"));

		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		
		
	}

	@Test
	public void selectPlans() {
		

		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		HomePage homepage = new HomePage(driver);
		

		homepage.getstarted().click();
		
		SelectPlanPage selectplan=new SelectPlanPage(driver);
		
		
		WebElement meatveggie=driver.findElement(By.xpath("//button[@data-test-id='classic-preset']"));
		Actions a=new Actions(driver);
		a.moveToElement(meatveggie).click().perform();
		
		WebElement twopeople=driver.findElement(By.xpath("//button[@aria-label='2 Number of people']"));
		a.moveToElement(twopeople).click().perform();
		
		WebElement fivemeals=driver.findElement(By.xpath("//button[@aria-label='5 Meals per week']"));
		a.moveToElement(fivemeals).click().perform();
		
		
		System.out.println("clicked");
		
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Quick & Easy']")));
//		
//		selectplan.qeasy().sendKeys(Keys.ENTER);
//		
		
		//driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
//		a.moveToElement(selectplan.twopeople()).click().perform();
//		a.moveToElement(selectplan.fivemeals()).click().perform();
	//	driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
		
//		selectplan.twopeople().click();
//		selectplan.fivemeals().click();
//		
	Assert.assertEquals("64.90",driver.findElement(By.xpath("//span[@data-test-id='total-discount-price'][text()='$64.90']")));
selectplan.select().click();
	}
	
	@AfterTest
	public void closure() {
		driver.close();
	}
}
