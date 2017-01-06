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
import automationFramework.DAO.PostgresAutomation;
import automationFramework.PageObjects.CreateFundingPage;
import automationFramework.PageObjects.CreateOrderPage;
import automationFramework.PageObjects.NewCustomerDisplayPage;
import automationFramework.Utilities.*;

public class CreateOrderDBTest {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private static String email;
	private static boolean orderRecordFound;
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
	public void createOrderDBcheck() throws Exception{
		
			coreTest.signIn(driver);
			coreTest.createCustomer(driver);
			email = coreTest.getEmail();
	        NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
	        nPage3.clickFundingSource(driver);
	        CreateFundingPage cPage = new CreateFundingPage(driver);
	        cPage.selectPaymentType(driver, 1);
	        cPage.enterName(driver, Global.CCNAME );
	        cPage.enterCC(driver, Global.CC);
	        cPage.selectMonth(driver);
	        cPage.selectYear(driver);
	        cPage.clickSubmit(driver);
	        Utils.waitTime(3000);
	        cPage.clickCreateOrder(driver);
	        CreateOrderPage oPage = new CreateOrderPage(driver);
	        oPage.selectPurseType(driver);
	        oPage.selectOrderType(driver);
	        oPage.selectOrderAmount(driver);
	        oPage.clickSubmit(driver);
	        Utils.waitTime(3000);
	        PostgresAutomation psAuto = new PostgresAutomation();
	        psAuto.dbConnect();
	        orderRecordFound = psAuto.dbFindJournalEntry(email);
	        psAuto.dbDisconnect();
	        Assert.assertTrue(orderRecordFound, "order record was not found");
	        driver.close();
	        Log.info("createOrderDBcheck Completed");
		    
	}
	
	@Test(priority=2 , enabled=true)
	public void createOrderCancelDBcheck() throws Exception{
		
			coreTest.signIn(driver);
			coreTest.createCustomer(driver);
			email = coreTest.getEmail();
	        NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
	        nPage3.clickFundingSource(driver);
	        CreateFundingPage cPage = new CreateFundingPage(driver);
	        cPage.selectPaymentType(driver, 1);
	        cPage.enterName(driver, Global.CCNAME );
	        cPage.enterCC(driver, Global.CC);
	        cPage.selectMonth(driver);
	        cPage.selectYear(driver);
	        cPage.clickSubmit(driver);
	        Utils.waitTime(3000);
	        cPage.clickCreateOrder(driver);
	        CreateOrderPage oPage = new CreateOrderPage(driver);
	        oPage.selectPurseType(driver);
	        oPage.selectOrderType(driver);
	        oPage.selectOrderAmount(driver);
	        oPage.clickCancel(driver);
	        Utils.waitTime(3000);
	        PostgresAutomation psAuto = new PostgresAutomation();
	        psAuto.dbConnect();
	        orderRecordFound = psAuto.dbFindJournalEntry(email);
	        psAuto.dbDisconnect();
	        Assert.assertFalse(orderRecordFound, "order record was found for a canceled order");
	        driver.close();
	        Log.info("createOrderCancelDBcheck Completed");
		    
	}
	
		
	@AfterMethod
	public void tearDown() {
		Log.info("TearDown Completed");
		Reporter.log("TearDown Completed");
		driver.quit();

	}
}