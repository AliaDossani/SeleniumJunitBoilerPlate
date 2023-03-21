package pageclass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.beust.jcommander.internal.Lists;

import io.github.bonigarcia.wdm.WebDriverManager;
import objectrepository.LaunchPage;

public class LaunchTest {
	WebDriver driver;
	Properties prop;
	FileInputStream fis;
	
	@Test
	public void login() throws Exception {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver=new ChromeDriver(options);
		
		prop=new Properties();
		fis=new FileInputStream("data.properties");
		prop.load(fis);
		
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url1"));
		
		driver.manage().timeouts().getImplicitWaitTimeout();
		LaunchPage launchpage=new LaunchPage(driver);
		launchpage.signup().click();
		List<String> browserTabs = Lists.newArrayList(driver.getWindowHandles());

		driver.switchTo().window(browserTabs.get(1));
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(40));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav[@class='master-breadcrumb']")));
		
		
		launchpage.firstname().sendKeys(prop.getProperty("fname"));
		launchpage.lastname().sendKeys(prop.getProperty("lname"));
		launchpage.email().sendKeys(prop.getProperty("email"));
		//launchpage.flagcode().click();
		driver.findElement(By.xpath("//div[@class='flag-container']")).click();
		driver.findElement(By.xpath("//div[@class='flag-container']/ul/li/span[text()='India (भारत)']")).click();
		launchpage.phonenum().sendKeys(prop.getProperty("phonenum"));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("windows.scrollBy(0,500)", "");
		
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name, 're-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));

		wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-checkmark"))).click();
		
		
		
		
		
	}
	@AfterTest
	public void closure() {
		driver.quit();
	}
	

	
}
