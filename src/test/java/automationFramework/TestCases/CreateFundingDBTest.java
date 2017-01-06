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
import automationFramework.PageObjects.CreateCustomerPage;
import automationFramework.PageObjects.CreateFundingPage;
import automationFramework.PageObjects.DashboardPage;
import automationFramework.PageObjects.NewCustomerDisplayPage;
import automationFramework.PageObjects.NewCustomerPage;
import automationFramework.Utilities.*;

public class CreateFundingDBTest {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private static String email;
	private static String userId;
	private static boolean fundingRecordFound;
	private static boolean ccRecordFound;
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

	//Funding source records no longer stored in postgress cmc_main.  Stored in Pal db now.
	@Test(priority=1 , enabled=false)
	public void createNewFundingDBcheck() throws Exception{
		
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
   	
	    PostgresAutomation psAuto = new PostgresAutomation();
	    psAuto.dbConnect();
	    fundingRecordFound = psAuto.dbFindFundingSource(email);
	    psAuto.dbDisconnect();
	    Assert.assertTrue(fundingRecordFound, "funding record was not found");
		driver.close();
		Log.info("createNewFundingDBCheck Completed");
		    
	}
	

	@Test(priority=1 , enabled=false)
	public void createNewFundingCancelDBcheck() throws Exception{
		
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
        cPage.clickCancel(driver);
        Utils.waitTime(3000);
   	
	    PostgresAutomation psAuto = new PostgresAutomation();
	    psAuto.dbConnect();
	    fundingRecordFound = psAuto.dbFindFundingSource(email);
	    psAuto.dbDisconnect();
	    Assert.assertFalse(fundingRecordFound, "funding record was found for a canceled record");
		driver.close();
		Log.info("createNewFundingCancelDBCheck Completed");
		    
	}
	
	@Test(priority=1 , enabled=false)
	public void createNewFundingCreditCardDBcheck() throws Exception{
		
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
  
	    PostgresAutomation psAuto = new PostgresAutomation();
	    psAuto.dbConnect();
	    ccRecordFound = psAuto.dbFindFundingSourceCreditCard(email);
	    psAuto.dbDisconnect();
	    Assert.assertTrue(ccRecordFound, "CC record was not found");
		driver.close();
		Log.info("createNewFundingccDBCheck Completed");
		    
	}
	
		
	@AfterMethod
	public void tearDown() {
		Log.info("TearDown Completed");
		Reporter.log("TearDown Completed");
		driver.quit();

	}
}