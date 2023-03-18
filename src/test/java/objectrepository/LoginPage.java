package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(id="input-email")
	private WebElement username;
	
	@FindBy(id="input-password")
	private WebElement password;
	
	@FindBy(xpath="//input[@value=\"Login\"]")
	private WebElement login;
	
	@FindBy(xpath= "//div[text()=\" Warning: No match for E-Mail Address and/or Password.\"]")
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
	
	public WebElement login() {
		return login;
	}
	

}
