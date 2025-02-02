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

public class CreateFundingTest {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private static final String EXPIRATION = "01/2021";
	private static final String CARDTYPE = "Active";
	private static final String POSTAL = "92122";

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

	// Add a test case for second funding source with a different cc
	// one you could have the new as the primary
	// or have the old one as the primary and still add this new one

	@Test(priority = 1, enabled = true)
	public void createFundingSource() throws Exception {

		coreTest.signIn(driver);
		coreTest.createCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(5000);
		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 1);
		cPage.enterName(driver, Global.CCNAME);
		cPage.enterCC(driver, Global.CC);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		Utils.waitTime(5000);
		cPage.clickSubmit(driver);
		Utils.waitTime(5000);
		

		Assert.assertEquals(cPage.getCardType(driver), Global.CCTYPE);
		Log.info("Actual results " + cPage.getCardType(driver) + " matches expected results " + Global.CCTYPE);
		Assert.assertEquals(cPage.getCardNumber(driver), Global.CCMASKED);
		Log.info("Actual results " + cPage.getCardNumber(driver) + " matches expected results " + Global.CCMASKED);
		Assert.assertEquals(cPage.getCardExpiration(driver), EXPIRATION);
		Log.info("Actual results " + cPage.getCardExpiration(driver) + " matches expected results " + EXPIRATION);
		Assert.assertEquals(cPage.getCardStatus(driver), CARDTYPE);
		Log.info("Actual results " + cPage.getCardStatus(driver) + " matches expected results " + CARDTYPE);
		driver.close();

	}

	@Test(priority = 2, enabled = true)
	public void createFundingSourceNewBillingAddress() throws Exception {

		coreTest.signIn(driver);
		coreTest.createCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 1);
		cPage.enterName(driver, Global.CCNAME);
		cPage.enterCC(driver, Global.CC);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		cPage.clickNewAddress(driver);
		Utils.waitTime(3000);
		cPage.selectCountry(driver);
		cPage.enterNewBillingAddress(driver, Global.NEWADDRESS);
		cPage.enterCity(driver, Global.NEWCITY);
		cPage.selectState(driver);
		cPage.enterPostalCode(driver, POSTAL);
		cPage.clickSubmit(driver);
		Utils.waitTime(1000);
		Assert.assertEquals(cPage.getCCname(driver), Global.CCNAME);
		Log.info("Actual results " + cPage.getCCname(driver) + " matches expected results " + Global.CCNAME);
		Assert.assertEquals(cPage.getCardType(driver), Global.CCTYPE);
		Log.info("Actual results " + cPage.getCardType(driver) + " matches expected results " + Global.CCTYPE);
		Assert.assertEquals(cPage.getCardNumber(driver), Global.CCMASKED);
		Log.info("Actual results " + cPage.getCardNumber(driver) + " matches expected results " + Global.CCMASKED);
		Assert.assertEquals(cPage.getCardExpiration(driver), EXPIRATION);
		Log.info("Actual results " + cPage.getCardExpiration(driver) + " matches expected results " + EXPIRATION);
		Assert.assertEquals(cPage.getCardStatus(driver), CARDTYPE);
		Log.info("Actual results " + cPage.getCardStatus(driver) + " matches expected results " + CARDTYPE);

		//The location of new address is not always second row
		/*
		Utils.waitTime(3000);
		Assert.assertEquals(cPage.getNewBillingAddress(driver).substring(0, 10), Global.NEWADDRESS);
		Log.info("Actual results " + cPage.getNewBillingAddress(driver).substring(0, 10) + " matches "
				+ Global.NEWADDRESS);
		
		Assert.assertEquals(cPage.getNewState(driver), STATE);
		Log.info("Actual results " + cPage.getNewState(driver) + " matches expected results " + STATE);
		Assert.assertEquals(cPage.getNewPostalCode(driver), POSTAL);
		Log.info("Actual results " + cPage.getNewPostalCode(driver) + " matches expected results " + POSTAL);
	    */
	    
		driver.close();

	}

	@Test(priority = 3, enabled = true)
	public void createFundingSourceCancel() throws Exception {

		coreTest.signIn(driver);
		coreTest.createCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(5000);
		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 1);
		cPage.enterName(driver, Global.CCNAME);
		cPage.enterCC(driver, Global.CC);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		Utils.waitTime(3000);
		cPage.clickCancel(driver);
		Utils.waitTime(3000);
		Assert.assertFalse(cPage.isNameOnCardDisplayed(driver), "name on card should not be displayed!");
		driver.close();

	}

	@Test(priority = 4, enabled = true)
	public void createFundingSourceTypeNotSelected() throws Exception {

		coreTest.signIn(driver);
		coreTest.createCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(5000);
		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 0);
		cPage.enterName(driver, Global.CCNAME);
		cPage.enterCC(driver, Global.CC);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		Assert.assertEquals(cPage.getFieldError(driver), Global.REQUIREDFIELD_ERROR);
		driver.close();
	}

	@Test(priority = 5, enabled = true)
	public void createCustomerInvalidEmail() throws Exception {

		coreTest.signIn(driver);
		DashboardPage dashPage = new DashboardPage(driver);
		dashPage.getLandingPage(Global.URL1);
		dashPage.clickCustomerTab(driver);
		dashPage.switchToFrame(driver);
		CreateCustomerPage nPage = new CreateCustomerPage(driver);
		nPage.clickNewCustomer(driver);
		Utils.waitTime(5000);
		nPage.clickCustomerType(driver, Global.CUSTOMERTYPE);
		nPage.enterFirstname(driver, Global.FNAME);
		nPage.enterLastname(driver, Global.LNAME);
		nPage.enterEmail(driver, Utils.randomUsernameString());
		Assert.assertFalse(nPage.isContinueEnabled(driver), "Continue button should not be enabled!");
		Assert.assertEquals(nPage.getEmailError(driver), Global.INVALID_EMAIL);
		nPage.enterEmail(driver, Utils.randomEmailString());
		nPage.enterPhone(driver, Utils.randomPhoneNumber());
		nPage.clickContinue(driver);
		NewCustomerPage nPaget = new NewCustomerPage(driver);
		nPaget.selectContactType(driver, Global.CONTACTTYPE);
		nPaget.selectCountry(driver);
		nPaget.enterAddress(driver, Global.ADDRESS);
		nPaget.enterCity(driver, Global.CITY);
		nPaget.enterPostalCode(driver, Global.POSTAL);
		nPaget.enterEmail(driver, Utils.randomUsernameString());
		Assert.assertFalse(nPaget.isSubmitEnabled(driver), "Submit button should not be enabled!");
		Assert.assertEquals(nPaget.getEmailError(driver), Global.INVALID_EMAIL);
		driver.close();

	}

	@Test(priority = 6, enabled = true)
	public void createCustomerFundingInvalidCC() throws Exception {

		coreTest.signIn(driver);
		coreTest.createCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 1);
		cPage.enterName(driver, Global.CCNAME);
		cPage.enterCC(driver, Global.INVALID_CC);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		cPage.clickSubmit(driver);
		Utils.waitTime(3000);
		Assert.assertEquals(cPage.getCCError(driver), "Card Number does not match with credit card type.");
		driver.close();
	}

	@Test(priority = 7, enabled = true)
	public void createCustomerFundingInvalidName() throws Exception {

		coreTest.signIn(driver);
		coreTest.createCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 1);
		cPage.enterCC(driver, Global.CC);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		cPage.clickSubmit(driver);
		Utils.waitTime(3000);
		Assert.assertFalse(cPage.isSubmitEnabled(driver), "Submit button should not be enabled!");
		driver.close();

	}

	@Test(priority = 8, enabled = true)
	public void createFundingSourceInvalidPanTooLong() throws Exception {

		coreTest.signIn(driver);
		coreTest.createCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(5000);
		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 1);
		cPage.enterName(driver, Global.CCNAME);
		cPage.enterCC(driver, "41111112222222222222");
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		Utils.waitTime(5000);
		cPage.clickSubmit(driver);
		Assert.assertEquals(cPage.getCCPanError(driver), Global.PAN_ERROR);
		driver.close();

	}
	
	@Test(priority = 9, enabled = true)
	public void createFundingSourceInvalidCCType() throws Exception {

		coreTest.signIn(driver);
		coreTest.createCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(5000);
		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 2);
		cPage.enterName(driver, Global.CCNAME);
		Utils.waitTime(3000);
		cPage.enterCCwithSpace(driver, "411111111111111");
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		Utils.waitTime(5000);
		cPage.clickSubmit(driver);
		Assert.assertEquals(cPage.getCCPanError(driver), Global.PAN_ERROR2);
		driver.close();

	}
	
	@Test(priority = 10, enabled = true)
	public void createFundingSourceInvalidExpirationDate() throws Exception {

		coreTest.signIn(driver);
		coreTest.createCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(5000);
		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 1);
		cPage.enterName(driver, Global.CCNAME);
		cPage.enterCC(driver, Global.CC);
		cPage.selectInvalidMonth(driver);
		cPage.selectInvalidYear(driver);
		Utils.waitTime(5000);
		cPage.clickSubmit(driver);
		Assert.assertEquals(cPage.getCCPanError(driver), Global.PAN_ERROR3);
		driver.close();

	}
	

	//Test case for second funding source with a different cc
	@Test(priority = 11, enabled = true)
	public void createFundingSourceTwoCards() throws Exception {

		coreTest.signIn(driver);
		coreTest.createCustomer(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
		Utils.waitTime(5000);
		nPage3.clickFundingSource(driver);
		CreateFundingPage cPage = new CreateFundingPage(driver);
		cPage.selectPaymentType(driver, 1);
		cPage.enterName(driver, Global.CCNAME);
		cPage.enterCC(driver, Global.CC);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		Utils.waitTime(5000);
		cPage.clickSubmit(driver);
		Utils.waitTime(5000);
		
		Assert.assertEquals(cPage.getCardType(driver), Global.CCTYPE);
		Log.info("Actual results " + cPage.getCardType(driver) + " matches expected results " + Global.CCTYPE);
		Assert.assertEquals(cPage.getCardNumber(driver), Global.CCMASKED);
		Log.info("Actual results " + cPage.getCardNumber(driver) + " matches expected results " + Global.CCMASKED);
		Assert.assertEquals(cPage.getCardExpiration(driver), EXPIRATION);
		Log.info("Actual results " + cPage.getCardExpiration(driver) + " matches expected results " + EXPIRATION);
		Assert.assertEquals(cPage.getCardStatus(driver), CARDTYPE);
		Log.info("Actual results " + cPage.getCardStatus(driver) + " matches expected results " + CARDTYPE);
		
		nPage3.clickFundingSource(driver);
		cPage.enterName(driver, Global.CCNAME2);
		cPage.enterCC(driver, Global.CC2);
		cPage.selectMonth(driver);
		cPage.selectYear(driver);
		Utils.waitTime(5000);
		cPage.clickSubmit(driver);
		Utils.waitTime(5000);
		
		//Assertions on second card
		Assert.assertEquals(cPage.getCardType2(driver), Global.CCTYPE);
		Log.info("Actual results " + cPage.getCardType2(driver) + " matches expected results " + Global.CCTYPE);
		Assert.assertEquals(cPage.getCardNumber2(driver), Global.CCMASKED2);
		Log.info("Actual results " + cPage.getCardNumber2(driver) + " matches expected results " + Global.CCMASKED2);
		Assert.assertEquals(cPage.getCardExpiration2(driver), EXPIRATION);
		Log.info("Actual results " + cPage.getCardExpiration2(driver) + " matches expected results " + EXPIRATION);
		Assert.assertEquals(cPage.getCardStatus2(driver), CARDTYPE);
		Log.info("Actual results " + cPage.getCardStatus2(driver) + " matches expected results " + CARDTYPE);
		
		driver.close();

	}
	
	//Test case for changing primary card
		@Test(priority = 12, enabled = true)
		public void createFundingSourceChangePrimaryCard() throws Exception {

			coreTest.signIn(driver);
			coreTest.createCustomer(driver);
			NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
			Utils.waitTime(5000);
			nPage3.clickFundingSource(driver);
			CreateFundingPage cPage = new CreateFundingPage(driver);
			cPage.selectPaymentType(driver, 1);
			cPage.enterName(driver, Global.CCNAME);
			cPage.enterCC(driver, Global.CC);
			cPage.selectMonth(driver);
			cPage.selectYear(driver);
			Utils.waitTime(5000);
			cPage.clickSubmit(driver);
			
			nPage3.clickFundingSource(driver);
			cPage.enterName(driver, Global.CCNAME2);
			cPage.enterCC(driver, Global.CC2);
			cPage.selectMonth(driver);
			cPage.selectYear(driver);
			Utils.waitTime(5000);
			cPage.clickSetPrimary(driver);
			cPage.clickYesWarning(driver);
			cPage.clickSubmit(driver);
			Utils.waitTime(5000);
			cPage.clickCardNumber2(driver);
			Utils.waitTime(5000);
			//Assertions on second card
			Assert.assertTrue(cPage.isSetPrimaryEnabled(driver), "Set primary should not be enabled");
			driver.close();

		}


	@AfterMethod
	public void tearDown() {
		Log.info("TearDown Complete");
		Reporter.log("TearDown Complete");
		driver.quit();

	}
}