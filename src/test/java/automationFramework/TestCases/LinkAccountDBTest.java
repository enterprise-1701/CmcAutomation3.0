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
import automationFramework.PageObjects.BasePage;
import automationFramework.PageObjects.LinkAccountPage;
import automationFramework.Utilities.*;

public class LinkAccountDBTest {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private static String email;
	private static boolean subSystemRecordFound;
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

	
	@Test(priority=1 , enabled=false)
	public void createLinkAccountDBcheck() throws Exception{
		
		/*
		coreTest.signIn(driver);
		coreTest.createCustomer(driver);
		email = coreTest.getEmail();
        NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
        nPage3.clickFundingSource(driver);
        CreateFundingPage cPage = new CreateFundingPage(driver);
        cPage.selectPaymentType(driver);
        cPage.enterName(driver, Global.CCNAME );
        cPage.enterCC(driver, Global.CC);
        cPage.selectMonth(driver);
        cPage.selectYear(driver);
        cPage.clickSubmit(driver);
        Utils.waitTime(3000);
        */
		
		email = "anand.singh@cubic.com";
	    PostgresAutomation psAuto = new PostgresAutomation();
	    psAuto.dbConnect();
	    subSystemRecordFound = psAuto.dbFindSubSystem(email);
	    psAuto.dbDisconnect();
	    Assert.assertTrue(subSystemRecordFound, "subsystem record was not found");
		driver.close();
		Log.info("createLinkAccountDBcheck Completed");
		    
	}
	
	@Test(priority=1 , enabled=true)
	public void createLinkAccountCancellDBcheck() throws Exception{
		
			coreTest.signIn(driver);
			coreTest.createCustomer(driver);  
			email = coreTest.getEmail();
		    BasePage bPage = new BasePage(driver);
			bPage.clickLinkAccount(driver);
		    LinkAccountPage lPage = new LinkAccountPage(driver);
		    lPage.selectAgency(driver);
		    lPage.selectTravelMode(driver);
		    lPage.selectTokenType(driver);
		    lPage.enterBankAccount(driver, Global.CC);
		    lPage.selectExpMonth(driver);
		    lPage.selectExpYear(driver);
		    lPage.enterNickName(driver, Global.NICKNAME);
		    lPage.getBalance(driver);
			lPage.clickCancel(driver);
			Assert.assertTrue(lPage.isLinkAccountDisplayed(driver));    	
			PostgresAutomation psAuto = new PostgresAutomation();
			psAuto.dbConnect();
			subSystemRecordFound = psAuto.dbFindSubSystem(email);
			psAuto.dbDisconnect();
			Assert.assertFalse(subSystemRecordFound, "subsystem record was not found");
			driver.close();
			Log.info("createLinkAccountCancelDBcheck Completed");
		    
	}
		
		
	@AfterMethod
	public void tearDown() {
		Log.info("TearDown Completed");
		Reporter.log("TearDown Completed");
		driver.quit();

	}
}