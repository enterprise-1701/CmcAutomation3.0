package automationFramework.PageObjects;


import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import automationFramework.Utilities.Global;


public class TravelHistoryPage extends BasePage {

	WebDriverWait wait = new WebDriverWait(driver, 90);

	// Element Locators
	private static final String TIMING = "//*[@id='travelHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[1]/span/span";
	
	private static final String LOCATION = "//*[@id='travelHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[2]/span";	
	
	private static final String AGENCY = "//*[@id='travelHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[3]/span";
	
	
	private static final String TOKEN = "//*[@id='travelHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[5]/span/span[1]";
	
	private static final String PRODUCT = "//*[@id='travelHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[6]/span";
	
	private static final String FARE = "//*[@id='travelHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[7]/span";
	
	private static final String UNPAID_FARE = "//*[@id='travelHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[8]/span/span";
	
	private static final String TRAVEL_MODE =  ".//*[@id='travelHistory_list_tbl']/div/div[1]/table/thead/tr/th[4]/span[2]";
	
	
	public TravelHistoryPage(WebDriver driver) {
		super(driver);
	}

	public String getTiming(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(TIMING)).getText();	
	}
	
	public String getLocation(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(LOCATION)).getText();	
	}
	
	public String getAgency(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(AGENCY)).getText();	
	}
	
	public String getTravelMode(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(TRAVEL_MODE)).getText();	
	}
	
	public String getToken(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(TOKEN)).getText();	
	}
	
	public String getProduct(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(PRODUCT)).getText();	
	}
	
	public String getFare(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(FARE)).getText();	
	}
	
	public String getUnpaidFare(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(UNPAID_FARE)).getText();	
	}
		
}