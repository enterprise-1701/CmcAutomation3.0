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

public class LinkAccountTest {

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
	
	//acount not found error test case
	

	//Have to use an account that is not linked 
	@Test(enabled=false)
	public void linkAccountClosedException()throws Exception{
	
		coreTest.signIn(driver);
	    SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, Global.CUSTOMERTYPE);
		sPage.enterFirstname(driver, Global.FNAME);
		sPage.enterLastname(driver, Global.LNAME);
		sPage.clickSearch(driver);
		sPage.clickSecondRecord(driver);
		sPage.clickSecurityBox(driver);
		sPage.clickContiune(driver);
		sPage.clickLinkAccount(driver);
	    LinkAccountPage lPage = new LinkAccountPage(driver);
	    Utils.waitTime(5000);
	    lPage.selectAgency(driver);
	    lPage.selectTravelMode(driver);
	    lPage.selectTokenType(driver);
	    lPage.enterBankAccount(driver, Global.CC);
	    lPage.selectExpMonth(driver);
	    lPage.selectExpYear(driver);
	    lPage.enterNickName(driver, Global.NICKNAME);
	    lPage.clickContinue(driver);
		Assert.assertEquals(lPage.getAccountNotElgible(driver),  ACCT_NOT_ELGIBLE_ERROR);
		lPage.clickNoRetryAcctNotElgible(driver);
		Utils.waitTime(3000);
		Assert.assertTrue(lPage.isLinkAccountDisplayed(driver));
		
		//Click link account again 
		sPage.clickLinkAccount(driver);
		lPage.selectAgency(driver);
		lPage.selectTravelMode(driver);
		lPage.selectTokenType(driver);
		lPage.enterBankAccount(driver, Global.CC);
	    lPage.selectExpMonth(driver);
		lPage.selectExpYear(driver);
	    lPage.enterNickName(driver, Global.NICKNAME);
		lPage.clickContinue(driver);
		lPage.getBalance(driver);
		lPage.clickRetryAcctNotElgible(driver);
		Assert.assertTrue(lPage.isAgencyDisplayed(driver));
		Log.info("linkAccountClosedException Completed");
		driver.close();
	}

	//Have to use an account that is already linked
	@Test(enabled=false)
	public void linkAccountAlreadyLinkedException()throws Exception{
	
		coreTest.signIn(driver);
	    SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, Global.CUSTOMERTYPE);
		sPage.enterFirstname(driver, Global.FNAME);
		sPage.enterLastname(driver, Global.LNAME);
		sPage.clickSearch(driver);
		sPage.clickRecord(driver);
		sPage.clickSecurityBox(driver);
		sPage.clickContiune(driver);
		sPage.clickLinkAccount(driver);
	    LinkAccountPage lPage = new LinkAccountPage(driver);
	    lPage.selectAgency(driver);
	    lPage.selectTravelMode(driver);
	    lPage.selectTokenType(driver);
	    lPage.enterBankAccount(driver, Global.CC);
	    lPage.selectExpMonth(driver);
	    lPage.selectExpYear(driver);
	    lPage.enterNickName(driver, Global.NICKNAME);
	    lPage.clickContinue(driver);
		lPage.getBalance(driver);
		Assert.assertEquals(lPage.getAlreadyLinkedError(driver),  ACCTLINKED_ERROR);
		Assert.assertTrue(lPage.isViewLinkDisplayed(driver));
		lPage.clickNoRetry(driver);
		Assert.assertTrue(lPage.isLinkAccountDisplayed(driver));
		
		//Click link account again 
		sPage.clickLinkAccount(driver);
		lPage.selectAgency(driver);
	    lPage.selectTravelMode(driver);
	    lPage.selectTokenType(driver);
	    lPage.enterBankAccount(driver, Global.CC);
	    lPage.selectExpMonth(driver);
	    lPage.selectExpYear(driver);
	    lPage.enterNickName(driver, Global.NICKNAME);
	    lPage.clickContinue(driver);
		lPage.getBalance(driver);
		Assert.assertEquals(lPage.getAlreadyLinkedError(driver),  ACCTLINKED_ERROR);
		Assert.assertTrue(lPage.isViewLinkDisplayed(driver));
		lPage.clickRetry(driver);
		Assert.assertTrue(lPage.isAgencyDisplayed(driver));
		Log.info("linkAccountAlreadyLinkedException Completed");
		driver.close();
		
	}
	
	//Have to do these steps on local first
	//oam C:\nbms\git\oam\app ==>gradlew dbRefreshSeed
	//In postgress db update oam_main.account customer_id to match customer_id under cms_main.contact for the customer you are trying to link
	//oam C:\nbms\git\oam\app
	//==>start-app
	@Test(enabled=false)
	public void linkAccountVerifiedTest()throws Exception{
	
		coreTest.signIn(driver);
	    SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, Global.CUSTOMERTYPE);
		sPage.enterFirstname(driver, Global.FNAME);
		sPage.enterLastname(driver, Global.LNAME);
		sPage.clickSearch(driver);
		sPage.clickRecord(driver);
		sPage.clickSecurityBox(driver);
		sPage.clickContiune(driver);
		sPage.clickLinkAccount(driver);
	    LinkAccountPage lPage = new LinkAccountPage(driver);
	    Utils.waitTime(5000);
	    lPage.selectAgency(driver);
	    lPage.selectTravelMode(driver);
	    lPage.selectTokenType(driver);
	    lPage.enterBankAccount(driver, Global.CC);
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
	
	//Have to do these steps on local first
	//oam C:\nbms\git\oam\app ==>gradlew dbRefreshSeed
	//In postgress db update oam_main.account customer_id to match customer_id under cms_main.contact for the customer you are trying to link
	//oam C:\nbms\git\oam\app
	//==>start-app
	@Test(enabled=false)
	public void linkAccountNotVerifiedTest()throws Exception{
	
		coreTest.signIn(driver);
	    SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, Global.CUSTOMERTYPE);
		sPage.enterFirstname(driver, Global.FNAME);
		sPage.enterLastname(driver, Global.LNAME);
		sPage.clickSearch(driver);
		sPage.clickRecord(driver);
		sPage.clickSecurityBox(driver);
		sPage.clickContiune(driver);
		sPage.clickLinkAccount(driver);
	    LinkAccountPage lPage = new LinkAccountPage(driver);
	    lPage.selectAgency(driver);
	    lPage.selectTravelMode(driver);
	    lPage.selectTokenType(driver);
	    lPage.enterBankAccount(driver, Global.CC);
	    lPage.selectExpMonth(driver);
	    lPage.selectExpYear(driver);
	    lPage.enterNickName(driver, Global.NICKNAME);
	    lPage.clickContinue(driver);
		lPage.getBalance(driver);
		Assert.assertEquals(lPage.getBalance(driver),  INITIAL_BALANCE);
		Assert.assertTrue(lPage.isLinkPanelDisplayed(driver));
		lPage.clickNo(driver);
		Assert.assertTrue(lPage.isLinkAccountDisplayed(driver));
		Log.info("linkAccountNotVerfiedTest Completed");
		driver.close();
		    	
	}
	
	@Test(enabled=true)
	public void linkAccountCancelTest()throws Exception{
	
		coreTest.signIn(driver);
	    coreTest.createCustomer(driver);
	    BasePage bPage = new BasePage(driver);
		bPage.clickLinkAccount(driver);
	    LinkAccountPage lPage = new LinkAccountPage(driver);
	    lPage.enterBankAccount(driver, Global.CC);
	    lPage.selectExpMonth(driver);
	    lPage.selectExpYear(driver);
		lPage.clickCancel(driver);
		Assert.assertTrue(lPage.isLinkAccountDisplayed(driver));
		Log.info("linkAccountCancelTest Completed");
		driver.close();
	}
	
	//done
	@Test(enabled=true)
	public void linkAccountLinkFailureTest()throws Exception{
	
		coreTest.signIn(driver);
	    SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, Global.CUSTOMERTYPE);
		sPage.enterFirstname(driver, Global.FNAME);
		sPage.enterLastname(driver, Global.LNAME);
		sPage.clickSearch(driver);
		sPage.clickRecord(driver);
		sPage.clickSecurityBox(driver);
		sPage.clickContiune(driver);
		sPage.clickLinkAccount(driver);
	    LinkAccountPage lPage = new LinkAccountPage(driver);
	    lPage.enterBankAccount(driver, Global.CC);
	    lPage.selectExpMonth(driver);
	    lPage.selectExpYear(driver);
	    lPage.clickSearchToken(driver);
	    Utils.waitTime(30000);
		Assert.assertTrue(lPage.isLinkFailureDisplayed(driver));	
		
	}
	
	@Test(enabled=true)
	public void linkAccountCardExpirationMissingTest()throws Exception{
	
		coreTest.signIn(driver);
	    SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, Global.CUSTOMERTYPE);
		sPage.enterFirstname(driver, Global.FNAME);
		sPage.enterLastname(driver, Global.LNAME);
		sPage.clickSearch(driver);
		sPage.clickRecord(driver);
		sPage.clickSecurityBox(driver);
		sPage.clickContiune(driver);
		sPage.clickLinkAccount(driver);
	    LinkAccountPage lPage = new LinkAccountPage(driver);
	    lPage.enterBankAccount(driver, Global.CC);
		Assert.assertFalse(lPage.isSearchTokenEnabled(driver));	
		
	}
	
	@Test(enabled=true)
	public void linkAccountBankAccountMissingTest()throws Exception{
	
		coreTest.signIn(driver);
	    SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, Global.CUSTOMERTYPE);
		sPage.enterFirstname(driver, Global.FNAME);
		sPage.enterLastname(driver, Global.LNAME);
		sPage.clickSearch(driver);
		sPage.clickRecord(driver);
		sPage.clickSecurityBox(driver);
		sPage.clickContiune(driver);
		sPage.clickLinkAccount(driver);
	    LinkAccountPage lPage = new LinkAccountPage(driver);
	    lPage.selectExpMonth(driver);
	    lPage.selectExpYear(driver);
		Assert.assertFalse(lPage.isSearchTokenEnabled(driver));	
		
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