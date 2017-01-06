package automationFramework.TestCases;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import automationFramework.PageObjects.*;
import automationFramework.Utilities.*;

public class BalanceHistoryTest {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private static final String TRANSACTION_AMOUNT = "$5.00";
	private static final String ENDING_BALANCE = "$154.80";
	private static final String TOTAL_BALANCE = "$154.80";
	private static final String ENTRY_TYPE = "LoadValue";
	
	static WebDriver driver;
	static String browser;
	
	CoreTest coreTest = new CoreTest();
	
	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browser) throws InterruptedException {

		Logging.setLogConsole();
		Logging.setLogFile();
		Log.info("Setup Started");
		Log.info("Current OS: " + WindowsUtils.readStringRegistryValue(Global.OS));
		Log.info("Current Browser: " + browser);
		driver = Utils.openBrowser(browser);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Log.info("Setup Completed");
	}
	//Detail balance history for type of load and type of charge

	//These will be updated once balance history code is stable 
	@Test(enabled=true)
	public void checkBalanceHistory()throws Exception{
	
		coreTest.signIn(driver);
	    SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, Global.CUSTOMERTYPE);
		sPage.clickSearch(driver);
		sPage.clickRecord(driver);
		sPage.clickSecurityBox(driver);
		sPage.clickContiune(driver);
		sPage.clickBalanceHistory(driver);
		Utils.waitTime(5000);
		BalanceHistoryPage bPage = new BalanceHistoryPage(driver);
		Assert.assertEquals(bPage.getPurse(driver),  Global.PURSE);
		Assert.assertEquals(bPage.getEntryType(driver), Global.ENTRY_TYPE);
		Assert.assertEquals(bPage.getTransactionAmount(driver),  TRANSACTION_AMOUNT);
		Assert.assertEquals(bPage.getEndingBalance(driver),  ENDING_BALANCE);
		Assert.assertEquals(bPage.getEndingBalance(driver),  TOTAL_BALANCE);
		Log.info("checkBalanceHistory Completed");
		driver.close();
	}
	

	@Test(enabled=true)
	public void checkBalanceHistoryDetail()throws Exception{
	
		coreTest.signIn(driver);
	    SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, Global.CUSTOMERTYPE);
		sPage.clickSearch(driver);
		sPage.clickRecord(driver);
		sPage.clickSecurityBox(driver);
		sPage.clickContiune(driver);
		sPage.clickBalanceHistory(driver);
		Utils.waitTime(5000);
		BalanceHistoryPage bPage = new BalanceHistoryPage(driver);
		bPage.clickFirstRecord(driver);
		Utils.waitTime(5000);
		BalanceHistoryDetailPage bdPage = new BalanceHistoryDetailPage(driver);
		//Utils.handleMultipleWindows(driver, "Balance History Detail");
		Utils.waitTime(5000);
		Assert.assertEquals(bdPage.getPurse(driver),  Global.PURSE);
		Assert.assertEquals(bdPage.getEntryType(driver),  ENTRY_TYPE);
		Assert.assertEquals(bdPage.getTransactionAmount(driver),  TRANSACTION_AMOUNT);
		Assert.assertEquals(bdPage.getEndingBalance(driver),  ENDING_BALANCE);
		bdPage.clickClose(driver);
		driver.close();
	}


	
	//private methods 
	private SearchPage getSearchPage() throws Exception{
		
		DashboardPage dashPage = new DashboardPage(driver);
		dashPage.clickCustomerTab(driver);
		dashPage.switchToFrame(driver);
	    SearchPage sPage = new SearchPage(driver);
	    return sPage;	
	}
	
		
	@AfterMethod
	public void tearDown() {
		Log.info("TearDown Complete");
		Reporter.log("TearDown Complete");
		driver.quit();

	}
}