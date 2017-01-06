package automationFramework.TestCases;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
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

public class SearchTest {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private static final String NORECORD = "No records found.";
	private static final String CRTYPE = "Individual";
	private static final String CTYPE = "Primary";
	private static final String DUPLICATE_FNAME = "robert";
	private static final String DUPLICATE_LNAME = "downton";

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

	// Dynamic Search
	@Test(priority = 1, enabled = true)
	public void searchCustomerVerifiedTest() throws Exception {

		coreTest.signIn(driver);
		coreTest.createCustomerClickHome(driver);
		SearchPage sPage = getSearchTab();
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterFirstname(driver, Global.FNAME);
		sPage.enterLastname(driver, Global.LNAME);
		sPage.enterEmail(driver, coreTest.getEmail());
		sPage.clickSearch(driver);

		Assert.assertEquals(sPage.getFirstName(driver), Global.FNAME);
		Log.info("Actual results " + sPage.getFirstName(driver) + " matches " + Global.FNAME);
		Assert.assertEquals(sPage.getLastName(driver), Global.LNAME);
		Log.info("Actual results " + sPage.getLastName(driver) + " matches " + Global.LNAME);
		Assert.assertEquals(sPage.getEmail(driver), coreTest.getEmail());
		Log.info("Actual results " + sPage.getEmail(driver) + " matches " + coreTest.getEmail());
		Assert.assertEquals(sPage.getPhone(driver), coreTest.getPhone());
		Log.info("Actual results " + sPage.getPhone(driver) + " matches " + coreTest.getPhone());
		// Assert.assertEquals(sPage.getDob(driver), DOB);
		// Log.info("Actual results " + sPage.getDob(driver) + " matches " +
		// DOB);
		Assert.assertEquals(sPage.getCustomerType(driver), CRTYPE);
		Log.info("Actual results " + sPage.getCustomerType(driver) + " matches " + CRTYPE);
		Assert.assertEquals(sPage.getContactType(driver), CTYPE);
		Log.info("Actual results " + sPage.getContactType(driver) + " matches " + CTYPE);
		Assert.assertEquals(sPage.getAddress(driver).substring(0, 12), Global.ADDRESS);
		Log.info("Actual results " + sPage.getAddress(driver).substring(0, 12) + " matches " + Global.ADDRESS);

		sPage.clickRecord(driver);
		sPage.clickSecurityBox(driver);
		sPage.clickContiune(driver);

		Assert.assertEquals(sPage.getFirstName(driver), Global.FNAME);
		Log.info("Actual results " + sPage.getFirstName(driver) + " matches " + Global.FNAME);
		Assert.assertEquals(sPage.getLastName(driver), Global.LNAME);
		Log.info("Actual results " + sPage.getLastName(driver) + " matches " + Global.LNAME);
		Assert.assertEquals(sPage.getEmail(driver), coreTest.getEmail());
		Log.info("Actual results " + sPage.getEmail(driver) + " matches " + coreTest.getEmail());
		Assert.assertEquals(sPage.getPhone(driver), coreTest.getPhone());
		Log.info("Actual results " + sPage.getPhone(driver) + " matches " + coreTest.getPhone());
		// Assert.assertEquals(sPage.getDob(driver), DOB);
		// Log.info("Actual results " + sPage.getDob(driver) + " matches " +
		// DOB);
		Assert.assertEquals(sPage.getContactTypeTableTwo(driver), CTYPE);
		Log.info("Actual results " + sPage.getContactTypeTableTwo(driver) + " matches " + CTYPE);
		Assert.assertEquals(sPage.getAddress(driver).substring(0, 12), Global.ADDRESS);
		Log.info("Actual results " + sPage.getAddress(driver).substring(0, 12) + " matches " + Global.ADDRESS);
		driver.close();

	}

	@Test(priority = 2, enabled = true)
	public void searchCustomerNotVerifiedTest() throws Exception {

		coreTest.signIn(driver);
		SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterFirstname(driver, Global.FNAME);
		sPage.enterLastname(driver, Global.LNAME);
		sPage.clickSearch(driver);
		Utils.waitTime(5000);
		sPage.clickRecord(driver);
		sPage.clickNotVerified(driver);
		Assert.assertTrue(sPage.isRecordPresent(driver), "customer records are not being displayed!");
		driver.close();

	}

	@Test(priority = 3, enabled = true)
	public void searchCustomerNoResultsTest() throws Exception {

		coreTest.signIn(driver);
		SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterFirstname(driver, Global.FNAME2);
		sPage.enterLastname(driver, Global.LNAME2);
		sPage.enterEmail(driver, Global.EMAIL);
		sPage.enterPhone(driver, Global.PHONE2);
		sPage.clickSearch(driver);
		Assert.assertEquals(sPage.getNoRecordFound(driver), NORECORD);
		Log.info("Actual results " + sPage.getNoRecordFound(driver) + " matches expected results " + NORECORD);
		driver.close();

	}

	@Test(priority = 4, enabled = true)
	public void searchCustomerTypeNotSelected() throws Exception {

		coreTest.signIn(driver);
		DashboardPage dashPage = new DashboardPage(driver);
		dashPage.clickCustomerTab(driver);
		dashPage.switchToFrame(driver);
		SearchPage sPage = new SearchPage(driver);
		Assert.assertFalse(sPage.isSearchEnabled(driver), "Search button should not be enabled!");
		driver.close();

	}

	@Test(priority = 5, enabled = true)
	public void searchCustomerInvalidEmail() throws Exception {

		coreTest.signIn(driver);
		SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterFirstname(driver, Global.FNAME);
		sPage.enterLastname(driver, Global.LNAME);
		sPage.enterEmail(driver, Global.FNAME);
		Assert.assertEquals(sPage.getEmailError(driver), Global.INVALID_EMAIL);
		Assert.assertFalse(sPage.isSearchEnabled(driver), "Search button should not be enabled!");
		driver.close();

	}

	@Test(priority = 6, enabled = true)
	public void searchCustomerCheckDuplicateTestFname() throws Exception {

		coreTest.signIn(driver);
		SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterFirstname(driver, DUPLICATE_FNAME);
		sPage.clickSearch(driver);
		Utils.waitTime(5000);
		Assert.assertEquals(sPage.getFirstName(driver), DUPLICATE_FNAME);
		driver.close();

	}

	@Test(priority = 7, enabled = true)
	public void searchCustomerCheckDuplicateTestLname() throws Exception {

		coreTest.signIn(driver);
		SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterLastname(driver, DUPLICATE_LNAME);
		sPage.clickSearch(driver);
		Utils.waitTime(5000);
		Assert.assertEquals(sPage.getLastName(driver), DUPLICATE_LNAME);
		driver.close();

	}

	@Test(priority = 8, enabled = true)
	public void searchCustomerCheckDuplicateTestFnameLname() throws Exception {

		coreTest.signIn(driver);
		SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterFirstname(driver, DUPLICATE_FNAME);
		sPage.enterLastname(driver, DUPLICATE_LNAME);
		sPage.clickSearch(driver);
		Utils.waitTime(5000);
		Assert.assertEquals(sPage.getFirstName(driver), DUPLICATE_FNAME);
		Assert.assertEquals(sPage.getLastName(driver), DUPLICATE_LNAME);
		driver.close();

	}

	@Test(priority = 9, enabled = true)
	public void searchCustomerCheckDuplicateTestPhone() throws Exception {

		createNewCustomer();
		Log.info("phone being retreived is: " + coreTest.getPhone());
		Utils.waitTime(5000);
		SearchPage sPage = getSearchTab();
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterPhone(driver, coreTest.getPhone());
		sPage.clickSearch(driver);
		Assert.assertEquals(sPage.getPhone(driver), coreTest.getPhone());
		driver.close();

	}

	@Test(priority = 10, enabled = true)
	public void searchCustomerCheckDuplicateTestEmail() throws Exception {

		coreTest.signIn(driver);
		SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterEmail(driver, coreTest.getEmail());
		sPage.clickSearch(driver);
		Utils.waitTime(5000);
		Assert.assertEquals(sPage.getEmail(driver), coreTest.getEmail());
		driver.close();

	}

	@Test(priority = 11, enabled = true)
	public void searchCustomerCheckDuplicateTestEmailPhone() throws Exception {

		coreTest.signIn(driver);
		SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterEmail(driver, coreTest.getEmail());
		sPage.enterPhone(driver, coreTest.getPhone());
		sPage.clickSearch(driver);
		Utils.waitTime(5000);
		Assert.assertEquals(sPage.getPhone(driver), coreTest.getPhone());
		Assert.assertEquals(sPage.getEmail(driver), coreTest.getEmail());
		driver.close();

	}

	@Test(priority = 12, enabled = true)
	public void searchCustomerCheckDuplicateTestAll() throws Exception {

		coreTest.signIn(driver);
		SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterFirstname(driver, DUPLICATE_FNAME);
		sPage.enterLastname(driver, DUPLICATE_LNAME);
		sPage.enterEmail(driver, coreTest.getEmail());
		sPage.enterPhone(driver, coreTest.getPhone());
		sPage.clickSearch(driver);
		Utils.waitTime(5000);
		Assert.assertEquals(sPage.getFirstName(driver), DUPLICATE_FNAME);
		Assert.assertEquals(sPage.getLastName(driver), DUPLICATE_LNAME);
		Assert.assertEquals(sPage.getPhone(driver), coreTest.getPhone());
		Assert.assertEquals(sPage.getEmail(driver), coreTest.getEmail());
		driver.close();

	}

	// Test new search button on upper right of screen
	@Test(priority = 13, enabled = true)
	public void searchNewSearchButton() throws Exception {

		coreTest.signIn(driver);
		SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, "Individual");
		sPage.clickSearch(driver);
		sPage.clickNewCustomer(driver);
		CreateCustomerPage cPage = new CreateCustomerPage(driver);
		Assert.assertEquals(cPage.getCustomerLegend(driver), "Customer: Create Customer");
		sPage.clickHeaderSearch(driver);
		Assert.assertEquals(sPage.getSearchLegend(driver), "Customer: Search");
		sPage.clickCustomerType(driver, "Individual");
		sPage.clickSearch(driver);
		driver.close();

	}

	// Test cases for section 2.3.1.1
	@Test(priority = 14, enabled = true)
	public void searchCustomerAndCriteriaLastName() throws Exception {

		coreTest.signIn(driver);
		SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterLastname(driver, "doe");
		sPage.enterFirstname(driver, "jane");
		sPage.clickSearch(driver);

		Assert.assertEquals(sPage.getFirstName(driver), "jane");
		Log.info("Actual results " + sPage.getFirstName(driver) + " matches expected results " + "doe");
		Assert.assertEquals(sPage.getLastName(driver), "doe");
		Log.info("Actual results " + sPage.getLastName(driver) + " matches expected results " + "doe");

		Assert.assertEquals(sPage.getFirstNameSecondRow(driver), "jane");
		Log.info("Actual results " + sPage.getFirstNameSecondRow(driver) + " matches expected results " + "jane");
		Assert.assertEquals(sPage.getLastNameSecondRow(driver), "doe");
		Log.info("Actual results " + sPage.getLastNameSecondRow(driver) + " matches expected results " + "doe");

		Assert.assertFalse(sPage.isFirstNameThirdRowDisplayed(driver),
				"third row for this AND search should not be displayed");
		Assert.assertFalse(sPage.isLastNameThirdRowDisplayed(driver),
				"third row for this AND search should not be displayed");

		driver.close();

	}

	// Test cases for section 2.3.1.1
	@Test(priority = 15, enabled = true)
	public void searchCustomerAndCriteriaLastNameEmail() throws Exception {

		coreTest.signIn(driver);
		SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterLastname(driver, "doe");
		sPage.enterFirstname(driver, "jane");
		sPage.enterEmail(driver, "jane1234@gmail.com");
		sPage.clickSearch(driver);

		Assert.assertEquals(sPage.getFirstName(driver), "jane");
		Log.info("Actual results " + sPage.getFirstName(driver) + " matches expected results " + "doe");
		Assert.assertEquals(sPage.getLastName(driver), "doe");
		Log.info("Actual results " + sPage.getLastName(driver) + " matches expected results " + "doe");
		Assert.assertEquals(sPage.getEmail(driver), "jane1234@gmail.com");
		Log.info("Actual results " + sPage.getEmail(driver) + " matches expected results " + "jane1234@gmail.com");

		Assert.assertFalse(sPage.isFirstNameSecondRowDisplayed(driver),
				"second row for this AND search should not be displayed");
		Assert.assertFalse(sPage.isLastNameSecondRowDisplayed(driver),
				"second row for this AND search should not be displayed");
		Assert.assertFalse(sPage.isFirstNameThirdRowDisplayed(driver),
				"third row for this AND search should not be displayed");
		Assert.assertFalse(sPage.isLastNameThirdRowDisplayed(driver),
				"third row for this AND search should not be displayed");

		driver.close();

	}

	// Dynamic Search
	@Test(priority = 16, enabled = true)
	public void searchCustomerVerifiedThreeInfoTest() throws Exception {

		coreTest.signIn(driver);
		coreTest.createCustomerClickHome(driver);
		SearchPage sPage = getSearchTab();
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterFirstname(driver, Global.FNAME);
		sPage.enterLastname(driver, Global.LNAME);
		sPage.enterEmail(driver, coreTest.getEmail());
		sPage.clickSearch(driver);

		Assert.assertEquals(sPage.getFirstName(driver), Global.FNAME);
		Log.info("Actual results " + sPage.getFirstName(driver) + " matches " + Global.FNAME);
		Assert.assertEquals(sPage.getLastName(driver), Global.LNAME);
		Log.info("Actual results " + sPage.getLastName(driver) + " matches " + Global.LNAME);
		Assert.assertEquals(sPage.getEmail(driver), coreTest.getEmail());
		Log.info("Actual results " + sPage.getEmail(driver) + " matches " + coreTest.getEmail());
		Assert.assertEquals(sPage.getPhone(driver), coreTest.getPhone());
		Log.info("Actual results " + sPage.getPhone(driver) + " matches " + coreTest.getPhone());
		Assert.assertEquals(sPage.getCustomerType(driver), CRTYPE);
		Log.info("Actual results " + sPage.getCustomerType(driver) + " matches " + CRTYPE);
		Assert.assertEquals(sPage.getContactType(driver), CTYPE);
		Log.info("Actual results " + sPage.getContactType(driver) + " matches " + CTYPE);
		Assert.assertEquals(sPage.getAddress(driver).substring(0, 12), Global.ADDRESS);
		Log.info("Actual results " + sPage.getAddress(driver).substring(0, 12) + " matches " + Global.ADDRESS);

		sPage.clickRecord(driver);
		sPage.clickNameBox(driver);
		sPage.clickAddressBox(driver);
		sPage.clickDobBox(driver);
		sPage.clickContiune(driver);

		Assert.assertEquals(sPage.getFirstName(driver), Global.FNAME);
		Log.info("Actual results " + sPage.getFirstName(driver) + " matches " + Global.FNAME);
		Assert.assertEquals(sPage.getLastName(driver), Global.LNAME);
		Log.info("Actual results " + sPage.getLastName(driver) + " matches " + Global.LNAME);
		Assert.assertEquals(sPage.getEmail(driver), coreTest.getEmail());
		Log.info("Actual results " + sPage.getEmail(driver) + " matches " + coreTest.getEmail());
		Assert.assertEquals(sPage.getPhone(driver), coreTest.getPhone());
		Log.info("Actual results " + sPage.getPhone(driver) + " matches " + coreTest.getPhone());
		Assert.assertEquals(sPage.getContactTypeTableTwo(driver), CTYPE);
		Log.info("Actual results " + sPage.getContactTypeTableTwo(driver) + " matches " + CTYPE);
		Assert.assertEquals(sPage.getAddress(driver).substring(0, 12), Global.ADDRESS);
		Log.info("Actual results " + sPage.getAddress(driver).substring(0, 12) + " matches " + Global.ADDRESS);
		driver.close();

	}

	// negative test case for searchCustomerVerifiedThreeInfoTest
	@Test(priority = 17, enabled = true)
	public void searchCustomerNotVerifiedThreeInfoTest() throws Exception {

		coreTest.signIn(driver);
		coreTest.createCustomerClickHome(driver);
		SearchPage sPage = getSearchTab();
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterFirstname(driver, Global.FNAME);
		sPage.enterLastname(driver, Global.LNAME);
		sPage.enterEmail(driver, coreTest.getEmail());
		sPage.clickSearch(driver);

		Assert.assertEquals(sPage.getFirstName(driver), Global.FNAME);
		Log.info("Actual results " + sPage.getFirstName(driver) + " matches " + Global.FNAME);
		Assert.assertEquals(sPage.getLastName(driver), Global.LNAME);
		Log.info("Actual results " + sPage.getLastName(driver) + " matches " + Global.LNAME);
		Assert.assertEquals(sPage.getEmail(driver), coreTest.getEmail());
		Log.info("Actual results " + sPage.getEmail(driver) + " matches " + coreTest.getEmail());
		Assert.assertEquals(sPage.getPhone(driver), coreTest.getPhone());
		Log.info("Actual results " + sPage.getPhone(driver) + " matches " + coreTest.getPhone());
		Assert.assertEquals(sPage.getCustomerType(driver), CRTYPE);
		Log.info("Actual results " + sPage.getCustomerType(driver) + " matches " + CRTYPE);
		Assert.assertEquals(sPage.getContactType(driver), CTYPE);
		Log.info("Actual results " + sPage.getContactType(driver) + " matches " + CTYPE);
		Assert.assertEquals(sPage.getAddress(driver).substring(0, 12), Global.ADDRESS);
		Log.info("Actual results " + sPage.getAddress(driver).substring(0, 12) + " matches " + Global.ADDRESS);

		sPage.clickRecord(driver);
		sPage.clickNameBox(driver);
		sPage.clickAddressBox(driver);
		Assert.assertFalse(sPage.isContinueEnabled(driver));
		Utils.waitTime(10000);
		driver.close();

	}

	// clicking not verified button should still take the CSR to the customer
	// information page
	@Test(priority = 18, enabled = true)
	public void searchCustomerClickNotVerified() throws Exception {

		coreTest.signIn(driver);
		coreTest.createCustomerClickHome(driver);
		SearchPage sPage = getSearchTab();
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterFirstname(driver, Global.FNAME);
		sPage.enterLastname(driver, Global.LNAME);
		sPage.enterEmail(driver, coreTest.getEmail());
		sPage.clickSearch(driver);

		Assert.assertEquals(sPage.getFirstName(driver), Global.FNAME);
		Log.info("Actual results " + sPage.getFirstName(driver) + " matches " + Global.FNAME);
		Assert.assertEquals(sPage.getLastName(driver), Global.LNAME);
		Log.info("Actual results " + sPage.getLastName(driver) + " matches " + Global.LNAME);
		Assert.assertEquals(sPage.getEmail(driver), coreTest.getEmail());
		Log.info("Actual results " + sPage.getEmail(driver) + " matches " + coreTest.getEmail());
		Assert.assertEquals(sPage.getPhone(driver), coreTest.getPhone());
		Log.info("Actual results " + sPage.getPhone(driver) + " matches " + coreTest.getPhone());
		Assert.assertEquals(sPage.getCustomerType(driver), CRTYPE);
		Log.info("Actual results " + sPage.getCustomerType(driver) + " matches " + CRTYPE);
		Assert.assertEquals(sPage.getContactType(driver), CTYPE);
		Log.info("Actual results " + sPage.getContactType(driver) + " matches " + CTYPE);
		Assert.assertEquals(sPage.getAddress(driver).substring(0, 12), Global.ADDRESS);
		Log.info("Actual results " + sPage.getAddress(driver).substring(0, 12) + " matches " + Global.ADDRESS);

		sPage.clickRecord(driver);
		sPage.clickNotVerified(driver);

		Assert.assertEquals(sPage.getFirstName(driver), Global.FNAME);
		Log.info("Actual results " + sPage.getFirstName(driver) + " matches " + Global.FNAME);
		Assert.assertEquals(sPage.getLastName(driver), Global.LNAME);
		Log.info("Actual results " + sPage.getLastName(driver) + " matches " + Global.LNAME);
		Assert.assertEquals(sPage.getEmail(driver), coreTest.getEmail());
		Log.info("Actual results " + sPage.getEmail(driver) + " matches " + coreTest.getEmail());
		Assert.assertEquals(sPage.getPhone(driver), coreTest.getPhone());
		Log.info("Actual results " + sPage.getPhone(driver) + " matches " + coreTest.getPhone());
		Assert.assertEquals(sPage.getContactTypeTableTwo(driver), CTYPE);
		Log.info("Actual results " + sPage.getContactTypeTableTwo(driver) + " matches " + CTYPE);
		Assert.assertEquals(sPage.getAddress(driver).substring(0, 12), Global.ADDRESS);
		Log.info("Actual results " + sPage.getAddress(driver).substring(0, 12) + " matches " + Global.ADDRESS);
		driver.close();

	}

	// private methods
	private void createNewCustomer() throws Exception {
		coreTest.signIn(driver);
		coreTest.createCustomerClickHome(driver);
	}

	private SearchPage getSearchPage() throws Exception {
		DashboardPage dashPage = new DashboardPage(driver);
		dashPage.clickCustomerTab(driver);
		dashPage.switchToFrame(driver);
		SearchPage sPage = new SearchPage(driver);
		return sPage;
	}

	private SearchPage getSearchTab() throws Exception {
		DashboardPage dashPage = new DashboardPage(driver);
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