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

public class LinkAccountABPTest {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private static final String POST_BALANCE = "$1.00";
	private static final String INITIAL_BALANCE = "$0.00";
	private static final String CANCEL_BALANCE = "$0.00";
	private static final String DATE = "03/15/2016 10:30 am";
	private static final String TRANSACTION = "Value Load";
	private static final String LOCATION = "Customer Service Center";
	private static final String PRODUCT = "MDTA Standard Pass";
	private static final String AMOUNT = "$23.54";
	private static final String ACCTLINKED_ERROR = "Account Already Linked";
	private static final String ACCT_NOT_ELGIBLE_ERROR = "Account Not Eligible";
	private static final String ACCT_AUTHORITY = "MDTA Authority";
	private static final String EXTERNAL_ACCTID = "Account:";
	static WebDriver driver;
	static String browser;
	static String currentToken;
	
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
	
	@Test(enabled=true)
	public void linkAccountVerifiedTest()throws Exception{
	
		//Increment the index and use the incremented index to get the newest token
		Log.info("Current Index is: " + Utils.getIndex());
		Utils.setIndex(Utils.incrementIndex());
		Log.info("New Incremented Index is: " + Utils.getIndex());
		int nextIndex = Integer.parseInt(Utils.getIndex());
		currentToken = Utils.readFile(nextIndex).toString();
		Log.info("Current Token is: " + currentToken);
		
		//ABP test client post tab
		Robots myRobot = new Robots();
		myRobot.abpTestClient(currentToken);
		
		//Back to selenium 
		coreTest.signIn(driver);
		coreTest.createCustomer(driver);
	    LinkAccountPage lPage = new LinkAccountPage(driver);
	    lPage.clickLinkAccount(driver);
	    Utils.waitTime(5000);
	    lPage.selectAgency(driver);
	    lPage.selectTravelMode(driver);
	    lPage.selectTokenType(driver);
	    
	    //Parse out the CC number using same token data as awt.Robot
	    String ABP_CC = currentToken.substring(0,16);
	    lPage.enterBankAccount(driver, ABP_CC);
	    lPage.selectExpMonth(driver);
	    lPage.selectExpYear(driver);
	    lPage.enterNickName(driver, Global.NICKNAME);
	    lPage.clickContinue(driver);
		lPage.getBalance(driver);
		Assert.assertEquals(lPage.getBalance(driver),  INITIAL_BALANCE);
		Assert.assertTrue(lPage.isLinkPanelDisplayed(driver));
		Assert.assertEquals(lPage.getDateTime(driver), DATE);
		Assert.assertEquals(lPage.getTransaction(driver), TRANSACTION);
		Assert.assertEquals(lPage.getLocation(driver), LOCATION);
		Assert.assertEquals(lPage.getProduct(driver), PRODUCT);
		Assert.assertEquals(lPage.getAmount(driver), AMOUNT);
		lPage.clickYes(driver);
		
		//MORE ASSERTIONS on LinkAccounts Section
		Assert.assertEquals(lPage.getLinkAccountAuthority(driver), ACCT_AUTHORITY);
		Assert.assertEquals(lPage.getUpdatedBalance(driver), POST_BALANCE);
		Assert.assertEquals(lPage.getExternalAccountId(driver), EXTERNAL_ACCTID);
		Log.info("linkAccountVerfiedTest Completed");
		driver.close();
			    	
	}
	
		
	@AfterMethod
	public void tearDown() {
		Log.info("TearDown Complete");
		Reporter.log("TearDown Complete");
		driver.quit();

	}
}