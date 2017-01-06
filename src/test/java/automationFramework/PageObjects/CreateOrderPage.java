package automationFramework.PageObjects;


import java.awt.AWTException;
import java.io.IOException;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.gargoylesoftware.htmlunit.WebClient;
import automationFramework.Utilities.Global;
import automationFramework.Utilities.Global;
import automationFramework.Utilities.Utils;

public class CreateOrderPage extends BasePage {

	WebDriverWait wait = new WebDriverWait(driver, 90);

	// Element Locators
	    private static final String PURSE = "//*[@id='orderCreationFrom_purseId_sel']";
		private static final String ORDERTYPE = "//*[@id='orderCreationFrom_orderType_sel']";
		private static final String ORDERAMOUNT = "//*[@id='orderCreationFrom_orderAmount_sel']";
		private static final String FUNDINGSROUCE = "//*[@id='orderCreationFrom_fundingSource_sel']";
		private static final String SUBMIT = "//*[@id='orderCreationFrom']/div/button[1]";
		private static final String CANCEL = "//*[@id='orderCreationFrom']/div/button[2]";
		private static final String BALANCE_HISTORY_EXPAND = "html/body/div/div/div/customer/workspace/fieldset/div[2]/div/div[2]/customer-details/balance-history/p-panel/div/div[1]/a/span";	
		private static final String T_PURSE = "//*[@id='balanceHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[3]/span";
		private static final String T_ENTRY_TYPE = "//*[@id='balanceHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[4]/span";
		private static final String T_TRANSACTION_AMOUNT = "//*[@id='balanceHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[5]";
		private static final String T_ENDING_BALANCE = "//*[@id='balanceHistory_list_tbl']/div/div[1]/table/tbody/tr[1]/td[6]/span/span";
			
	public CreateOrderPage(WebDriver driver) {
		super(driver);
	}

	public void selectPurseType(WebDriver driver) throws InterruptedException, AWTException{
		Select mySelect= new Select(driver.findElement(By.xpath(PURSE)));
		mySelect.selectByIndex(1);
	}
	
	public void selectOrderType(WebDriver driver) throws InterruptedException, AWTException{
		Select mySelect= new Select(driver.findElement(By.xpath(ORDERTYPE)));
		mySelect.selectByIndex(1);
	}
	
	public void selectOrderAmount(WebDriver driver) throws InterruptedException, AWTException{
		Select mySelect= new Select(driver.findElement(By.xpath(ORDERAMOUNT)));
		mySelect.selectByIndex(1);
	}
	
	
	public void clickCancel(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(CANCEL)).click();	
	}
	
	public void clickSubmit(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(SUBMIT)).click();	
	}
	
	public void clickBalanceHistoryExpand(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(BALANCE_HISTORY_EXPAND)).click();	
	}
	
	public String getPurse(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(T_PURSE)).getText();	
	}
	
	public String getEntryType(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(T_ENTRY_TYPE)).getText();	
	}
	
	public String getTransactionAmount(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(T_TRANSACTION_AMOUNT)).getText();	
	}
	
	public String getEndingBalance(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(T_ENDING_BALANCE)).getText();	
	}	
}