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

public class DashboardPage extends BasePage {

	WebDriverWait wait = new WebDriverWait(driver, 90);

	// Element Locators
	private static final String DASHBOARD = "//*[@class='cmc-dashboard-capitalize cmc-dashboard-greeting']";	
	private static final String CUSTOMER = "//*[@id='header_customer_btn']";
	//private static final String CUSTOMER = "//*[@id='launchPad_customer_btn']/h4";
	

	
	//private static final String CUSTOMER = "//*[@id='launchPad_customer_btn']/h4";
	
	public DashboardPage(WebDriver driver) {
		super(driver);
	}

	public void clickCustomerTab(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(CUSTOMER)).click();
	}

}