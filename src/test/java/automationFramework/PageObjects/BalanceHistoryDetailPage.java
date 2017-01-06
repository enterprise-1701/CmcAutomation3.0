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


public class BalanceHistoryDetailPage extends BasePage {

	WebDriverWait wait = new WebDriverWait(driver, 90);

	// Element Locators
	private static final String DATE = "//*[@id='balanceHistoryDetail_loadJournalEntry_table']/div/div/table/tbody/tr/td[1]/span/span";
	private static final String PURSE = "//*[@id='balanceHistoryDetail_loadJournalEntry_table']/div/div/table/tbody/tr/td[2]/span";	
	private static final String ENTRY_TYPE = "//*[@id='balanceHistoryDetail_loadJournalEntry_table']/div/div/table/tbody/tr/td[3]/span";
	private static final String TRANSACTION_AMOUNT = "//*[@id='balanceHistoryDetail_loadJournalEntry_table']/div/div/table/tbody/tr/td[4]";
	private static final String END_BALANCE = "//*[@id='balanceHistoryDetail_loadJournalEntry_table']/div/div/table/tbody/tr/td[5]/span/span";
	private static final String CLOSE = "/html/body/div[1]/div/div/customer/view-edit-customer/fieldset/div/div/div[2]/workspace/balance-history/p-dialog/div/footer/div/button/span[2]";
	
	
	public BalanceHistoryDetailPage(WebDriver driver) {
		super(driver);
	}

	public String getPurse(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(PURSE)).getText();	
	}
	
	public String getEntryType(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(ENTRY_TYPE)).getText();	
	}
	
	public String getTransactionAmount(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(TRANSACTION_AMOUNT)).getText();	
	}
	
	public String getEndingBalance(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(END_BALANCE)).getText();	
	}
	
	
	public void clickClose(WebDriver driver) throws InterruptedException, AWTException{
		 driver.findElement(By.xpath(CLOSE)).click();	
	}
		
}