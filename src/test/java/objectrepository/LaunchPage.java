package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LaunchPage {

	WebDriver driver;
	
	public LaunchPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//li[@aria-label='Sign Up']")
	private WebElement signup;
	
	@FindBy(xpath="//input[@name='firstname']")
	private WebElement firstname;
	
	@FindBy(id="inputLastName")
	private WebElement lastname;
	
	@FindBy(id="inputEmail")
	private WebElement email;
	
	@FindBy(xpath="//div[@class='flag-container']/ul/li/span[text()='India (भारत)']")
	private WebElement flagcode;
	
	@FindBy(id="inputPhone")
	private WebElement phonenum;
	
//	@FindBy(id="inputFirstName")
//	private WebElement firstname;
//	
//	@FindBy(id="inputFirstName")
//	private WebElement firstname;
	
	public WebElement signup() {
	return signup;	
	}

	public WebElement firstname() {
		return firstname;	
		}
	
	public WebElement lastname() {
		return lastname;	
		}
	
	public WebElement email() {
		return email;	
		}
	
	public WebElement flagcode() {
		return flagcode;	
		}
	
	public WebElement phonenum() {
		return phonenum;	
		}
}
