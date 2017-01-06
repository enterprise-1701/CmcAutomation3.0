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

public class CreateOrderTest {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private static final String AMOUNT = "$1.00";
	private static final String BALANCE = "$1.00";
	
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

	
	
	@Test(priority=1 , enabled=true)
	public void createOrderSubmit() throws Exception{
		
		coreTest.signIn(driver);
		coreTest.createCustomer(driver);
        NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
        nPage3.clickFundingSource(driver);
        CreateFundingPage cPage = new CreateFundingPage(driver);
        cPage.selectPaymentType(driver, 1);
        cPage.enterName(driver, Global.CCNAME );
        cPage.enterCC(driver, Global.CC);
        cPage.selectMonth(driver);
        cPage.selectYear(driver);
        cPage.clickSubmit(driver);
        Utils.waitTime(5000);
        cPage.clickCreateOrder(driver);
        CreateOrderPage oPage = new CreateOrderPage(driver);
        oPage.selectPurseType(driver);
        oPage.selectOrderType(driver);
        oPage.selectOrderAmount(driver);
        oPage.clickSubmit(driver);
    	Utils.waitTime(3000);
		oPage.clickBalanceHistoryExpand(driver);
		Utils.waitTime(3000);
		Assert.assertEquals(oPage.getPurse(driver),  Global.PURSE);
		Assert.assertEquals(oPage.getEntryType(driver), Global.ENTRY_TYPE);
		Assert.assertEquals(oPage.getTransactionAmount(driver), AMOUNT);
		Assert.assertEquals(oPage.getEndingBalance(driver), BALANCE);
        driver.close();
      	
	}
	

	//test case paying with expired funding source.  You have to go back and change the expiration date via database
	@Test(priority=2 , enabled=true)
	public void createOrderExpiredCard() throws Exception{
		
		coreTest.signIn(driver);
		coreTest.createCustomer(driver);
        NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
        nPage3.clickFundingSource(driver);
        CreateFundingPage cPage = new CreateFundingPage(driver);
        cPage.selectPaymentType(driver, 1);
        cPage.enterName(driver, Global.CCNAME );
        cPage.enterCC(driver, Global.CC);
        cPage.selectMonth(driver);
        cPage.selectYear(driver);
        cPage.clickSubmit(driver);
        Utils.waitTime(5000);
        cPage.clickCreateOrder(driver);
        CreateOrderPage oPage = new CreateOrderPage(driver);
        oPage.selectPurseType(driver);
        oPage.selectOrderType(driver);
        oPage.selectOrderAmount(driver);
        oPage.clickSubmit(driver);
    	Utils.waitTime(3000);
		oPage.clickBalanceHistoryExpand(driver);
		Utils.waitTime(3000);
		Assert.assertEquals(oPage.getPurse(driver),  Global.PURSE);
		Assert.assertEquals(oPage.getEntryType(driver), Global.ENTRY_TYPE);
		Assert.assertEquals(oPage.getTransactionAmount(driver), AMOUNT);
		Assert.assertEquals(oPage.getEndingBalance(driver), BALANCE);
        driver.close();
      	
	}
	

	
	@Test(priority=3 , enabled=true)
	public void createOrderCancel() throws Exception{
		
		coreTest.signIn(driver);
		coreTest.createCustomer(driver);
        NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
        nPage3.clickFundingSource(driver);
        CreateFundingPage cPage = new CreateFundingPage(driver);
        cPage.selectPaymentType(driver, 1);
        cPage.enterName(driver, Global.CCNAME );
        cPage.enterCC(driver, Global.CC);
        cPage.selectMonth(driver);
        cPage.selectYear(driver);
        cPage.clickSubmit(driver);
        Utils.waitTime(5000);
        cPage.clickCreateOrder(driver);
        CreateOrderPage oPage = new CreateOrderPage(driver);
        oPage.selectPurseType(driver);
        oPage.selectOrderType(driver);
        oPage.selectOrderAmount(driver);
        oPage.clickCancel(driver);
        Assert.assertTrue(oPage.isCreateOrderDisplayed(driver), "create order link should be displayed!");
		driver.close();
      	
	}
		
		
	@AfterMethod
	public void tearDown() {
		Log.info("TearDown Complete");
		Reporter.log("TearDown Complete");
		driver.quit();

	}
}