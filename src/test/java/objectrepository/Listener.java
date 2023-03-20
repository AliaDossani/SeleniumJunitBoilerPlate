package objectrepository;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

	WebDriver driver;
	public void onTestStart(ITestResult result) {
		System.out.println("Test started");
	}
	
	public void onFailure(ITestContext context) throws Exception {
		System.out.println("Test failed");
		File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File desFile=new File("/Users/arbaazdossani/eclipse-workspace/SelenimJunitBoilerPlate/screenshot/failure.png");
		 FileUtils.copyFile(screenshot, desFile);
	}
}
