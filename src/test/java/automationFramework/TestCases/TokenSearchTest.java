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

public class TokenSearchTest {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	static WebDriver driver;
	static String browser;
	CoreTest coreTest = new CoreTest();
	UserData userData = new UserData();

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

	// Search token invalid month
	@Test(priority = 1, enabled = true)
	public void searchTokenInvalidMonth() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();
		tPage.selectTokenType(driver, "Bankcard");
		tPage.selectSubsystem(driver, "ABP");
		tPage.enterBankNumber(driver, "38520000023237");
		tPage.selectExpMonth(driver);
		tPage.selectInvalidExpMonth(driver);
		tPage.selectExpYear(driver);
		Assert.assertEquals(tPage.getRequiredFieldError(driver), Global.REQUIREDFIELD_ERROR);
		Assert.assertFalse(tPage.isSearchTokenEnabled(driver));
		driver.close();

	}

	// Search token invalid year
	@Test(priority = 2, enabled = true)
	public void searchTokenInvalidYear() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();
		tPage.selectTokenType(driver, "Bankcard");
		tPage.selectSubsystem(driver, "ABP");
		tPage.enterBankNumber(driver, "38520000023237");
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.selectInvalidExpYear(driver);
		Assert.assertEquals(tPage.getRequiredFieldError(driver), Global.REQUIREDFIELD_ERROR);
		Assert.assertFalse(tPage.isSearchTokenEnabled(driver));
		driver.close();

	}

	// Search token missing bank card number
	@Test(priority = 3, enabled = true)
	public void searchTokenMissingBankCard() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();
		tPage.selectTokenType(driver, "Bankcard");
		tPage.selectSubsystem(driver, "ABP");
		tPage.enterBankNumber(driver, "");
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		Assert.assertFalse(tPage.isSearchTokenEnabled(driver));
		driver.close();

	}

	// Search token new search
	@Test(priority = 4, enabled = true)
	public void searchTokenNewSearch() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();
		tPage.selectTokenType(driver, "Bankcard");
		tPage.selectSubsystem(driver, "ABP");
		tPage.enterBankNumber(driver, "38520000023237");
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickNewSearch(driver);
		Assert.assertEquals(tPage.getBankNumber(driver), "");
		driver.close();

	}

	// Search token no record found
	@Test(priority = 5, enabled = true)
	public void searchTokenNoRecordFound() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();
		tPage.selectTokenType(driver, "Bankcard");
		tPage.selectSubsystem(driver, "ABP");
		tPage.enterBankNumber(driver, "38520000023237");
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);
		Assert.assertEquals(tPage.getNoRecordFoundError(driver), "No Records Found");
		driver.close();

	}

	// Search token no record found new search
	@Test(priority = 6, enabled = true)
	public void searchTokenNoRecordFoundNewSearch() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();
		tPage.selectTokenType(driver, "Bankcard");
		tPage.selectSubsystem(driver, "ABP");
		tPage.enterBankNumber(driver, "38520000023237");
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);
		Assert.assertEquals(tPage.getNoRecordFoundError(driver), "No Records Found");
		tPage.clickResultsNewSearch(driver);
		Assert.assertEquals(tPage.getBankNumber(driver), "");
		driver.close();

	}

	// Search token invalid token search
	@Test(priority = 7, enabled = true)
	public void searchTokenInvalidTokenSearch() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();
		tPage.selectTokenType(driver, "Bankcard");
		tPage.selectSubsystem(driver, "ABP");
		tPage.enterBankNumber(driver, Global.INVALID_CC);
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);
		Assert.assertEquals(tPage.getNoRecordFoundError(driver), "No Records Found");
		tPage.enterNickName(driver, "joe");
		tPage.clickRegisterCustomer(driver);
		Assert.assertEquals(tPage.getInavlidTokenError(driver), "Invalid Token");
		driver.close();

	}

	// Search token invalid token search new search
	@Test(priority = 8, enabled = true)
	public void searchTokenInvalidTokenNewSearch() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();
		tPage.selectTokenType(driver, "Bankcard");
		tPage.selectSubsystem(driver, "ABP");
		tPage.enterBankNumber(driver, Global.INVALID_CC);
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);
		Assert.assertEquals(tPage.getNoRecordFoundError(driver), "No Records Found");
		tPage.enterNickName(driver, "joe");
		tPage.clickRegisterCustomer(driver);
		Assert.assertEquals(tPage.getInavlidTokenError(driver), "Invalid Token");
		tPage.clickInvalidTokenNewSearch(driver);
		Assert.assertEquals(tPage.getBankNumber(driver), "");
		driver.close();

	}

	// STA-698 - Search and view unregistred customer
	@Test(priority = 9, enabled = true)
	public void searchUnregistredCustomer() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();
		tPage.selectTokenType(driver, "Bankcard");
		tPage.selectSubsystem(driver, "ABP");
		tPage.enterBankNumber(driver, "4111111111111111");
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);
		Utils.waitTime(5000);
		tPage.clickViewDetails(driver);
		UnregistredCustomerPage uPage = new UnregistredCustomerPage(driver);
		Assert.assertEquals(uPage.getAccountNumber(driver), Global.UNREGISTRED_ACCOUNT);
		Assert.assertEquals(uPage.getStatus(driver), "Active");
		Assert.assertEquals(uPage.getTokenType(driver), "Bankcard");
		Assert.assertEquals(uPage.getPanNo(driver), "PAN: 1111");
		Assert.assertEquals(uPage.getBalance(driver), "$0.00");
		driver.close();

	}

	// STA-698 - Search and view registred customer
	@Test(priority = 10, enabled = true)
	public void searchRegistredCustomer() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();
		tPage.selectTokenType(driver, "Bankcard");
		tPage.selectSubsystem(driver, "ABP");
		tPage.enterBankNumber(driver, Global.REGISTRED_ACCOUNT);
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);
		Utils.waitTime(10000);
		TokenSearchCustomerVerifiPage vPage = new TokenSearchCustomerVerifiPage(driver);
		Assert.assertTrue(vPage.getName(driver).contains("jimmy"));
		Assert.assertTrue(vPage.getContact(driver).contains("Primary"));
		Assert.assertTrue(vPage.getStatus(driver).contains("Active"));
		Assert.assertTrue(vPage.getAccount(driver).contains("330000002544"));
		vPage.clickSecurityBox(driver);
		vPage.clickContinue(driver);
		Utils.waitTime(3000);
		TokenSearchCustomerDetailsPage dPage = new TokenSearchCustomerDetailsPage(driver);
		Assert.assertEquals(dPage.getFirstName(driver), "jimmy");
		Assert.assertEquals(dPage.getLastName(driver), "page");
		Assert.assertEquals(dPage.getEmail(driver), "immy2343@yahoo.com");
		Assert.assertEquals(dPage.getPhone(driver), "8588888888");
		Assert.assertTrue(dPage.getAccount(driver).contains("330000002544"));
		driver.close();

	}

	
	@Test(priority = 11, enabled = true)
	public void searchRegistredCustomerVerifiedThreeInfo() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();
		tPage.selectTokenType(driver, "Bankcard");
		tPage.selectSubsystem(driver, "ABP");
		tPage.enterBankNumber(driver, Global.REGISTRED_ACCOUNT);
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);
		TokenSearchCustomerVerifiPage vPage = new TokenSearchCustomerVerifiPage(driver);
		Assert.assertTrue(vPage.getName(driver).contains("jimmy"));
		Assert.assertTrue(vPage.getContact(driver).contains("Primary"));
		Assert.assertTrue(vPage.getStatus(driver).contains("Active"));
		Assert.assertTrue(vPage.getAccount(driver).contains("330000002544"));
		vPage.clickAddressBox(driver);
		vPage.clickDobBox(driver);
		vPage.clickNameBox(driver);
		vPage.clickContinue(driver);
		Utils.waitTime(3000);
		TokenSearchCustomerDetailsPage dPage = new TokenSearchCustomerDetailsPage(driver);
		Assert.assertEquals(dPage.getFirstName(driver), "jimmy");
		Assert.assertEquals(dPage.getLastName(driver), "page");
		Assert.assertEquals(dPage.getEmail(driver), "immy2343@yahoo.com");
		Assert.assertEquals(dPage.getPhone(driver), "8588888888");
		Assert.assertTrue(dPage.getAccount(driver).contains("330000002544"));
		driver.close();

	}
	
	// Search and view subsystem page for registred customer
	@Test(priority = 12, enabled = true)
	public void viewRegistredCustomerSubsystemPage() throws Exception {

		coreTest.signIn(driver);
		TokenSearchPage tPage = getTokenSearchPage();
		tPage.selectTokenType(driver, "Bankcard");
		tPage.selectSubsystem(driver, "ABP");
		tPage.enterBankNumber(driver, Global.REGISTRED_ACCOUNT);
		tPage.selectExpMonth(driver);
		tPage.selectExpYear(driver);
		tPage.clickSearchToken(driver);
		TokenSearchCustomerVerifiPage vPage = new TokenSearchCustomerVerifiPage(driver);
		Assert.assertTrue(vPage.getName(driver).contains("jimmy"));
		Assert.assertTrue(vPage.getContact(driver).contains("Primary"));
		Assert.assertTrue(vPage.getStatus(driver).contains("Active"));
		Assert.assertTrue(vPage.getAccount(driver).contains("330000002544"));
		vPage.clickSecurityBox(driver);
		vPage.clickContinue(driver);
		Utils.waitTime(3000);

		TokenSearchCustomerDetailsPage dPage = new TokenSearchCustomerDetailsPage(driver);
		dPage.clickLinkedAccount(driver);
		
		TokenSearchSubSystemPage sPage = new TokenSearchSubSystemPage(driver);
		Assert.assertEquals(sPage.getSubsystem(driver), "NYCT Subsystem Account");
		Assert.assertEquals(sPage.getAccountNumber(driver), "Account #: 330000002544");
		Assert.assertEquals(sPage.getStatus(driver), "Active");
		Assert.assertEquals(sPage.getTokenType(driver), "Bankcard");
		Assert.assertEquals(sPage.getTokenInfo(driver), "PAN: 5100");
		driver.close();

	}

	private TokenSearchPage getTokenSearchPage() throws Exception {
		DashboardPage dashPage = new DashboardPage(driver);
		dashPage.clickCustomerTab(driver);
		dashPage.switchToFrame(driver);
		TokenSearchPage tPage = new TokenSearchPage(driver);
		return tPage;
	}

	@AfterMethod
	public void tearDown() {
		Log.info("TearDown Complete");
		Reporter.log("TearDown Complete");
		driver.quit();

	}
}