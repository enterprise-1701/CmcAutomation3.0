package automationFramework.PageObjects;


import java.awt.AWTException;
import java.io.IOException;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.gargoylesoftware.htmlunit.WebClient;
import automationFramework.Utilities.Global;
import automationFramework.Utilities.Utils;

public class StatusPage extends BasePage {

	WebDriverWait wait = new WebDriverWait(driver, 90);

	// Element Locators
	private static final String VERSION = "html/body/div[1]/table/tbody/tr[2]/td[4]";	
	private static final String BUILD = "html/body/div[1]/table/tbody/tr[3]/td[3]";
	private static final String MACHINE = "html/body/div[1]/table/tbody/tr[4]/td[3]";
	
	public StatusPage(WebDriver driver) {
		super(driver);
	}

	public String getVersion(WebDriver driver) throws Exception{
		getLandingPage(Global.URL3);
		return driver.findElement(By.xpath(VERSION)).getText();
	}
	
	
	public String getBuild(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(BUILD)).getText();
	}
	
	public String getMachine(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(MACHINE)).getText();
	}
	
}