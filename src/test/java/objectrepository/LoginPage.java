package objectrepository;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "input-email")
	private WebElement username;

	@FindBy(id = "input-password")
	private WebElement password;

	@FindBy(xpath = "//input[@value=\"Login\"]")
	private WebElement login;

	@FindBy(xpath = "//div[text()=\" Warning: No match for E-Mail Address and/or Password.\"]")
	private WebElement errmessage;

	public WebElement errmessage() {
		return errmessage;
	}
//	@FindBy(linkText="My Account")
//	private WebElement account;

	public WebElement username() {
		return username;
	}

	public WebElement password() {
		return password;
	}

	
	public void setup(String browser) {
		if (browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");

			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			driver.get("http://tutorialsninja.com/demo/index.php?route=account/login");
		} else if (browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get("http://tutorialsninja.com/demo/index.php?route=account/login");
			
		}
	}

	public void login(WebDriver driver, String name, String pass) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		username.sendKeys(name);
		password.sendKeys(pass);

		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
	}

}
