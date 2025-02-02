package automationFramework.PageObjects;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class UnregistredCustomerPage extends BasePage {

	WebDriverWait wait = new WebDriverWait(driver, 90);

	// Element Locators
	private static final String ACCOUNT_NUMBER = "html/body/div/div/div/customer/workspace/fieldset/div[2]/div/div[2]/subsystem-details-transit/div/p-panel/div/div[2]/div/token-pass-details/div/table/tbody/tr[1]/td[2]";
	private static final String TOKEN_TYPE = ".//*[@id='tokens_list_tbl']/div/div/table/tbody/tr/td[1]/span";
	private static final String PAN_NO = ".//*[@id='tokens_list_tbl']/div/div/table/tbody/tr/td[2]/span/div[1]";
	private static final String STATUS =  ".//*[@id='tokens_list_tbl']/div/div/table/tbody/tr/td[3]/span";
	private static final String BALANCE = "html/body/div/div/div/customer/workspace/fieldset/div[2]/div/div[3]/subsystem-purse-details/div/p-panel/div/div[1]/header/div/span[2]";
	

	public UnregistredCustomerPage(WebDriver driver) {
		super(driver);
	}

	public String getAccountNumber(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ACCOUNT_NUMBER)).getText();	
	}
	
	public String getTokenType(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN_TYPE)).getText();	
	}
	
	public String getPanNo(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(PAN_NO)).getText();	
	}

	public String getStatus(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(STATUS)).getText();	
	}
	
	public String getBalance(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(BALANCE)).getText();	
	}
	
}