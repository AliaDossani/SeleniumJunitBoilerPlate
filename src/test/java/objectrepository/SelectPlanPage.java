package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectPlanPage {

	WebDriver driver;
	
	public SelectPlanPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath="//span[text()='Quick & Easy']")
    private WebElement qeasy;
	
	@FindBy(xpath="//button[@aria-label='2 Number of people']")
	 private WebElement twopeople;
	
	@FindBy(xpath="//button[@aria-label='5 Meals per week']")
	private WebElement fivemeals;
	
	@FindBy(xpath="//span[@data-test-id='total-discount-price'][text()='$64.90']")
	private WebElement firstboxtotal;
	
	@FindBy(xpath="//span[text()='SELECT THIS PLAN']")
	private WebElement select;
	
	public WebElement qeasy() {
		return qeasy;
	}
	
	public WebElement twopeople() {
		return twopeople;
	}
	
	public WebElement fivemeals() {
		return fivemeals;
	}
	
	public WebElement firstboxtotal() {
		return firstboxtotal;
	}
	
	public WebElement select() {
		return select;
	}
}
