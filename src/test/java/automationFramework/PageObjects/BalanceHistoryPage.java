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


public class BalanceHistoryPage extends BasePage {

	WebDriverWait wait = new WebDriverWait(driver, 90);

	// Element Locators
	private static final String DATE = "//*[@id='balanceHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[21/span";
	private static final String PURSE = ".//*[@id='balanceHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[3]/span";	
	private static final String ENTRY_TYPE = ".//*[@id='balanceHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[4]/span";
	private static final String TAMOUNT = ".//*[@id='balanceHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[5]";
	private static final String END_BALANCE = ".//*[@id='balanceHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[6]/span/span";
	private static final String TOTAL_BALANCE = ".//*[@id='accountBalance_totalBalance_div']";
	private static final String FIRST_RECORD = "//*[@id='balanceHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[2]/span/span";

	
	public BalanceHistoryPage(WebDriver driver) {
		super(driver);
	}


	public String getPurse(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(PURSE)).getText();	
	}
	
	public String getEntryType(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(ENTRY_TYPE)).getText();	
	}
	
	public String getTransactionAmount(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(TAMOUNT)).getText();	
	}
	
	public String getEndingBalance(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(END_BALANCE)).getText();	
	}
	
	public String getTotalBalance(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(TOTAL_BALANCE)).getText();	
	}
	
	public void clickFirstRecord(WebDriver driver) throws InterruptedException, AWTException{
		 driver.findElement(By.xpath(FIRST_RECORD)).click();	
	}
		
}