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

public class CmcTest {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private static final String NORECORD = "No records found.";
	private static final String DOB = "19-05-2016";
	private static final String CRTYPE = "Individual";
	private static final String CTYPE = "Primary";
	private static final String STATE = "California";
	private static final String EXPIRATION = "01/2019";
	private static final String CARDTYPE = "Active";
	private String phoneNumber;
	private String email;
	
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

	
	@Test(priority=20 , enabled=false)
	public void searchCustomerVerifiedTest()throws Exception{
	
		coreTest.getLabStatus(driver);
		coreTest.signIn(driver);
	    SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterFirstname(driver, Global.FNAME);
		sPage.enterLastname(driver, Global.LNAME);
		sPage.clickSearch(driver);

		Assert.assertEquals(sPage.getFirstName(driver),  Global.FNAME);
		Log.info("Actual results " +  sPage.getFirstName(driver) + " matches " +  Global.FNAME);
		Assert.assertEquals(sPage.getLastName(driver),  Global.LNAME);
		Log.info("Actual results " +  sPage.getLastName(driver) + " matches " +  Global.LNAME);
		Assert.assertEquals(sPage.getEmail(driver),  Global.EMAIL);
		Log.info("Actual results " +  sPage.getEmail(driver) + " matches " +  Global.EMAIL);
		Assert.assertEquals(sPage.getPhone(driver),  Global.PHONE);
		Log.info("Actual results " +  sPage.getPhone(driver) + " matches " +  Global.PHONE);
		Assert.assertEquals(sPage.getDob(driver),  DOB);
		Log.info("Actual results " +  sPage.getDob(driver) + " matches " +  DOB);
		Assert.assertEquals(sPage.getCustomerType(driver),  CRTYPE);
		Log.info("Actual results " +  sPage.getCustomerType(driver) + " matches " +  CRTYPE);
		Assert.assertEquals(sPage.getContactType(driver),  CTYPE);
		Log.info("Actual results " +  sPage.getContactType(driver) + " matches " +  CTYPE);
		Assert.assertEquals(sPage.getAddress(driver).substring(0, 12),  Global.ADDRESS);
		Log.info("Actual results " + sPage.getAddress(driver).substring(0, 12)  + " matches " +  Global.ADDRESS);
		
		sPage.clickRecord(driver);
		sPage.clickSecurityBox(driver);
		sPage.clickContiune(driver);
		
		Assert.assertEquals(sPage.getFirstName(driver),  Global.FNAME);
		Log.info("Actual results " +  sPage.getFirstName(driver) + " matches " +  Global.FNAME);
		Assert.assertEquals(sPage.getLastName(driver),  Global.LNAME);
		Log.info("Actual results " +  sPage.getLastName(driver) + " matches " +  Global.LNAME);
		Assert.assertEquals(sPage.getEmail(driver),  Global.EMAIL);
		Log.info("Actual results " +  sPage.getEmail(driver) + " matches " +  Global.EMAIL);
		Assert.assertEquals(sPage.getPhone(driver),  Global.PHONE);
		Log.info("Actual results " +  sPage.getPhone(driver) + " matches " +  Global.PHONE);
		Assert.assertEquals(sPage.getDob(driver),  DOB);
		Log.info("Actual results " +  sPage.getDob(driver) + " matches " +  DOB);
		Assert.assertEquals(sPage.getContactTypeTableTwo(driver),  CTYPE);
		Log.info("Actual results " +  sPage.getContactTypeTableTwo(driver) + " matches " +  CTYPE);
		Assert.assertEquals(sPage.getAddress(driver).substring(0, 12),  Global.ADDRESS);
		Log.info("Actual results " + sPage.getAddress(driver).substring(0, 12)  + " matches " +  Global.ADDRESS);
		driver.close();
		    	
	}
	
	
	@Test(priority=2 , enabled=false)
	public void searchCustomerNotVerifiedTest()throws Exception{
		
		coreTest.signIn(driver);
		SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterFirstname(driver, Global.FNAME);
		sPage.enterLastname(driver, Global.LNAME);
		sPage.enterEmail(driver, Global.EMAIL);
		sPage.enterPhone(driver, Global.PHONE);
		sPage.clickSearch(driver);
		Utils.waitTime(5000);
		sPage.clickRecord(driver);
		sPage.clickNotVerified(driver);	
		Utils.waitTime(30000);
		Assert.assertFalse(sPage.isRecordPresent(driver), "customer records should not be displayed!");	
		driver.close();
		    	
	}
	
	
	@Test(priority=3 , enabled=false)
	public void searchCustomerNoResultsTest()throws Exception{
	
		coreTest.signIn(driver);
		SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterFirstname(driver, Global.FNAME2);
		sPage.enterLastname(driver, Global.LNAME2);
		sPage.enterEmail(driver, Global.EMAIL);
		sPage.enterPhone(driver, Global.PHONE2);
		sPage.clickSearch(driver);
		Assert.assertEquals(sPage.getNoRecordFound(driver),  NORECORD);
		Log.info("Actual results " +  sPage.getNoRecordFound(driver) + " matches expected results " +  NORECORD);
		driver.close();
		    	
	}
	
	@Test(priority=4 , enabled=false)
	public void searchCustomerTypeNotSelected()throws Exception{
		
		coreTest.signIn(driver);
		DashboardPage dashPage = new DashboardPage(driver);
		dashPage.clickCustomerTab(driver);
		dashPage.switchToFrame(driver);
		SearchPage sPage = new SearchPage(driver);
		Assert.assertFalse(sPage.isSearchEnabled(driver), "Search button should not be enabled!");
		driver.close();
		    	
	}
	
	@Test(priority=17 , enabled=false)
	public void searchCustomerInvalidEmail() throws Exception{
		
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
	

	@Test(priority=1 , enabled=true)
	public void createNewCustomer() throws Exception{
		
		coreTest.getLabStatus(driver);
		coreTest.signIn(driver);
	    coreTest.createCustomer(driver);  
        NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
        Assert.assertEquals(nPage3.getFname(driver),  Global.FNAME);
		Log.info("Actual results " + nPage3.getFname(driver)  + " matches expected results " +  Global.FNAME);
		Assert.assertEquals(nPage3.getLname(driver),  Global.LNAME);
		Log.info("Actual results " + nPage3.getLname(driver)  + " matches expected results " +  Global.LNAME);
		//Assert.assertEquals(nPage3.getEmail(driver), email);
		//Log.info("Actual results " + nPage3.getEmail(driver)  + " matches expected results " +  email);
		//Assert.assertEquals(nPage3.getPhone(driver),  phoneNumber);
		//Log.info("Actual results " + nPage3.getPhone(driver)  + " matches expected results " +  phoneNumber);
		Assert.assertEquals(nPage3.getAddress1(driver),  Global.ADDRESS);
		Log.info("Actual results " + nPage3.getAddress1(driver)  + " matches expected results " +  Global.ADDRESS);
		Assert.assertEquals(nPage3.getAddress(driver).substring(0, 12),  Global.ADDRESS);
		Log.info("Actual results " + nPage3.getAddress(driver).substring(0, 12)  + " matches " +  Global.ADDRESS);
		Assert.assertEquals(nPage3.getDob(driver),  DOB);
		Log.info("Actual results " + nPage3.getDob(driver)  + " matches expected results " + DOB);
		Assert.assertEquals(nPage3.getCustomerType(driver),  Global.CUSTOMERTYPE);
		Log.info("Actual results " + nPage3.getCustomerType(driver)  + " matches expected results " +  Global.CUSTOMERTYPE);
		Assert.assertEquals(nPage3.getContactType(driver),  Global.CONTACTTYPE);
		Log.info("Actual results " + nPage3.getContactType(driver)  + " matches expected results " +  Global.CONTACTTYPE);
		Assert.assertEquals(nPage3.getCity(driver),  Global.CITY);
		Log.info("Actual results " + nPage3.getCity(driver)  + " matches expected results " +  Global.CITY);
		Assert.assertEquals(nPage3.getState(driver),  STATE);
		Log.info("Actual results " + nPage3.getState(driver)  + " matches expected results " +  STATE);
		Assert.assertEquals(nPage3.getPostalCode(driver),  Global.POSTAL);
		Log.info("Actual results " + nPage3.getPostalCode(driver)  + " matches expected results " +  Global.POSTAL);
		driver.close();
	}
	
	
	@Test(priority=12 , enabled=false)
	public void createFundingSource() throws Exception{
		
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
        
      	Assert.assertEquals(cPage.getCardType(driver),  Global.CCTYPE);
      	Log.info("Actual results " +  cPage.getCardType(driver) + " matches expected results " +  Global.CCTYPE);
      	Assert.assertEquals(cPage.getCardNumber(driver),  Global.CCMASKED);
      	Log.info("Actual results " +  cPage.getCardNumber(driver) + " matches expected results " +  Global.CCMASKED);
    	Assert.assertEquals(cPage.getCardExpiration(driver),  EXPIRATION);
      	Log.info("Actual results " +  cPage.getCardExpiration(driver) + " matches expected results " +  EXPIRATION);
      	Assert.assertEquals(cPage.getCardStatus(driver),  CARDTYPE);
      	Log.info("Actual results " + cPage.getCardStatus(driver)  + " matches expected results " +  CARDTYPE);
      	Assert.assertEquals(cPage.getBillingAddress(driver).substring(0, 12),  Global.ADDRESS);
		Log.info("Actual results " + nPage3.getAddress(driver).substring(0, 12)  + " matches " +  Global.ADDRESS);
		driver.close();
      	
	}
	
	
	@Test(priority=7 , enabled=false)
	public void createFundingSourceNewBillingAddress() throws Exception{
		
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
        cPage.clickBillingAddress(driver);
        Utils.waitTime(3000);
        cPage.selectCountry(driver);
        cPage.enterNewBillingAddress(driver, Global.NEWADDRESS);
        cPage.enterCity(driver, Global.NEWCITY);
        cPage.selectState(driver);
        cPage.enterPostalCode(driver, Global.NEWPOSTAL);
        cPage.clickSubmit(driver);
        Utils.waitTime(5000);
        
        
        //Assert.assertEquals(cPage.getCCname(driver),  Global.CCNAME);
      	//Log.info("Actual results " + cPage.getCCname(driver)  + " matches expected results " +  Global.CCNAME);
      	Assert.assertEquals(cPage.getCardType(driver),  Global.CCTYPE);
      	Log.info("Actual results " +  cPage.getCardType(driver) + " matches expected results " +  Global.CCTYPE);
      	Assert.assertEquals(cPage.getCardNumber(driver),  Global.CCMASKED);
      	Log.info("Actual results " +  cPage.getCardNumber(driver) + " matches expected results " +  Global.CCMASKED);
    	Assert.assertEquals(cPage.getCardExpiration(driver),  EXPIRATION);
      	Log.info("Actual results " +  cPage.getCardExpiration(driver) + " matches expected results " +  EXPIRATION);
      	Assert.assertEquals(cPage.getCardStatus(driver),  CARDTYPE);
      	Log.info("Actual results " + cPage.getCardStatus(driver)  + " matches expected results " +  CARDTYPE);
      	Assert.assertEquals(cPage.getBillingAddress(driver).substring(0, 10),  Global.NEWADDRESS);
		Log.info("Actual results " + nPage3.getAddress(driver).substring(0, 10)  + " matches " +  Global.NEWADDRESS);
		//Assert.assertEquals(cPage.getNewBillingAddress(driver),  Global.NEWADDRESS);
      	//Log.info("Actual results " +  cPage.getNewBillingAddress(driver) + " matches expected results " +  Global.NEWADDRESS);
      	//Assert.assertEquals(cPage.getNewCity(driver),  Global.NEWCITY);
      	//Log.info("Actual results " +  cPage.getNewBillingAddress(driver) + " matches expected results " +  Global.NEWCITY);
    	Assert.assertEquals(cPage.getNewState(driver),  STATE);
      	Log.info("Actual results " +  cPage.getNewState(driver) + " matches expected results " +  STATE);
      	Assert.assertEquals(cPage.getNewPostalCode(driver),  Global.NEWPOSTAL);
      	Log.info("Actual results " +  cPage.getNewPostalCode(driver) + " matches expected results " +  Global.NEWPOSTAL);
      	driver.close();
      	
	}
	

	@Test(priority=8 , enabled=false)
	public void createFundingSourceCancel() throws Exception{
		
		SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, "Individual");
		sPage.clickSearch(driver);
		sPage.clickRecord(driver);
		sPage.clickSecurityBox(driver);
		sPage.clickContiune(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
        nPage3.clickFundingSource(driver);
        CreateFundingPage cPage = new CreateFundingPage(driver);
        cPage.selectPaymentType(driver, 1);
        cPage.enterName(driver, Global.CCNAME );
        cPage.enterCC(driver, Global.CC);
        cPage.selectMonth(driver);
        cPage.selectYear(driver);
        cPage.clickCancel(driver);
    	Assert.assertFalse(cPage.isCreateContactDisplayed(driver), "Create Contact link should not be displayed!");
    	driver.close();
      	
	}
	
	@Test(priority=9 , enabled=false)
	public void createCustomerCancel() throws Exception{
		
		coreTest.signIn(driver);
		DashboardPage dashPage = new DashboardPage(driver);
		dashPage.getLandingPage(Global.URL1);
		dashPage.clickCustomerTab(driver);
		dashPage.switchToFrame(driver);
		CreateCustomerPage nPage = new CreateCustomerPage(driver);
		nPage.clickSwitch(driver);
		nPage.clickCreateCustomer(driver);
		nPage.clickCustomerType(driver, Global.CUSTOMERTYPE);
		nPage.enterFirstname(driver, Global.FNAME);
		nPage.clickCancel(driver);
		Assert.assertEquals(nPage.getFirstname(driver), "");   
		nPage.clickCustomerMenu(driver);
		nPage.clickCreateCustomer(driver);
		nPage.clickCustomerType(driver, Global.CUSTOMERTYPE);
		nPage.enterFirstname(driver, Global.FNAME);
		nPage.enterLastname(driver, Global.LNAME);
		email = Utils.randomEmailString();
		nPage.enterEmail(driver, email);
		phoneNumber = Utils.randomPhoneNumber();
		nPage.enterPhone(driver, phoneNumber);
		nPage.clickContinue(driver);
		NewCustomerPage nPaget = new NewCustomerPage(driver);
		nPaget.clickCancel(driver);
		Assert.assertEquals(nPage.getFirstname(driver), "");   
		driver.close();
	}
	
	@Test(priority=10 , enabled=false)
	public void createCustomerContactTypeNotSelected()throws Exception{
		
		coreTest.signIn(driver);
		NewCustomerPage nPage = createCustomerFirstPage();
		nPage.selectCountry(driver);
        nPage.enterAddress(driver, Global.ADDRESS );
        nPage.enterCity(driver, Global.CITY);
        nPage.selectState(driver);
        nPage.enterPostalCode(driver, Global.POSTAL);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-250)", "");
        nPage.selectPhoneType(driver, Global.PHONETYPE);
        nPage.selectSecurityQ(driver);
        nPage.enterSecuirtyA(driver, Global.SECURITYA);
        nPage.enterUserName(driver, Utils.randomUsernameString());
        nPage.enterPin(driver, Global.PIN);
        nPage.enterDob(driver, Global.DOB);
        Assert.assertFalse(nPage.isSubmitEnabled(driver), "Submit button should not be enabled!");
        driver.close();
		    	
	}
	
	
	@Test(priority=11 , enabled=false)
	public void createFundingSourceTypeNotSelected() throws Exception{
		
		SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, "Individual");
		sPage.clickSearch(driver);
		sPage.clickRecord(driver);
		sPage.clickSecurityBox(driver);
		sPage.clickContiune(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
        nPage3.clickFundingSource(driver);
        CreateFundingPage cPage = new CreateFundingPage(driver);
        cPage.enterName(driver, Global.CCNAME );
        cPage.enterCC(driver, Global.CC);
        cPage.selectMonth(driver);
        cPage.selectYear(driver);
    	Assert.assertFalse(cPage.isSubmitEnabled(driver), "Submit button should not be enabled!");
    	driver.close();   	
	}
	
	@Test(priority=12 , enabled=false)
	public void createCustomerInvalidEmail() throws Exception{
		
		coreTest.getLabStatus(driver);
		coreTest.signIn(driver);
		DashboardPage dashPage = new DashboardPage(driver);
		dashPage.getLandingPage(Global.URL1);
		dashPage.clickCustomerTab(driver);
		dashPage.switchToFrame(driver);
		CreateCustomerPage nPage = new CreateCustomerPage(driver);
		nPage.clickSwitch(driver);
		nPage.clickCreateCustomer(driver);
		nPage.clickCustomerType(driver, Global.CUSTOMERTYPE);
		nPage.enterFirstname(driver, Global.FNAME);
		nPage.enterLastname(driver, Global.LNAME);
		nPage.enterEmail(driver, Utils.randomUsernameString());
		Assert.assertFalse(nPage.isContinueEnabled(driver), "Continue button should not be enabled!");
		Assert.assertEquals(nPage.getEmailError(driver),  Global.INVALID_EMAIL);	
		nPage.enterEmail(driver, Utils.randomEmailString());
		nPage.enterPhone(driver, Utils.randomPhoneNumber());
		nPage.clickContinue(driver);
		NewCustomerPage nPaget = new NewCustomerPage(driver);
		nPaget.selectContactType(driver, Global.CONTACTTYPE);
		nPaget.selectCountry(driver);
        nPaget.enterAddress(driver, Global.ADDRESS );
        nPaget.enterCity(driver, Global.CITY);
        nPaget.enterPostalCode(driver, Global.POSTAL);
        nPaget.enterEmail(driver, Utils.randomUsernameString());
        Assert.assertFalse(nPaget.isSubmitEnabled(driver), "Submit button should not be enabled!");
		Assert.assertEquals(nPaget.getEmailError(driver), Global.INVALID_EMAIL );
		driver.close();
        
	}
	
	@Test(priority=13 , enabled=false)
	public void createCustomerInvalidPin() throws Exception{
		
		coreTest.signIn(driver);
		NewCustomerPage nPage = createCustomerFirstPage();
		nPage.selectCountry(driver);
        nPage.enterAddress(driver, Global.ADDRESS );
        nPage.enterCity(driver, Global.CITY);
        nPage.enterPostalCode(driver, Global.POSTAL);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-250)", "");
        nPage.selectPhoneType(driver, Global.PHONETYPE);
        nPage.selectSecurityQ(driver);
        nPage.enterSecuirtyA(driver, Global.SECURITYA);
        nPage.enterUserName(driver, Utils.randomUsernameString());
        nPage.enterPin(driver, Global.BAD_PIN);
        Assert.assertEquals(nPage.getPinError(driver), Global.PIN_ERROR );
        driver.close();
	}
	
	@Test(priority=14 , enabled=false)
	public void createCustomerFundingInvalidCC() throws Exception{
		
		SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, "Individual");
		sPage.clickSearch(driver);
		sPage.clickRecord(driver);
		sPage.clickSecurityBox(driver);
		sPage.clickContiune(driver);
		NewCustomerDisplayPage nPage3 = new NewCustomerDisplayPage(driver);
        nPage3.clickFundingSource(driver);
        CreateFundingPage cPage = new CreateFundingPage(driver);
        cPage.enterName(driver, Global.CCNAME );
        cPage.enterCC(driver, Global.INVALID_CC);
        cPage.selectMonth(driver);
        cPage.selectYear(driver);
    	Assert.assertFalse(cPage.isSubmitEnabled(driver), "Submit button should not be enabled!");
    	driver.close();   
		
	}
	
	@Test(priority=15 , enabled=false)
	public void createCustomerInvalidUserName() throws Exception{
		
		coreTest.signIn(driver);
		NewCustomerPage nPage = createCustomerFirstPage();
		nPage.selectCountry(driver);
        nPage.enterAddress(driver, Global.ADDRESS );
        nPage.enterCity(driver, Global.CITY);
        nPage.enterPostalCode(driver, Global.POSTAL);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-250)", "");
        nPage.selectPhoneType(driver, Global.PHONETYPE);
        nPage.selectSecurityQ(driver);
        nPage.enterSecuirtyA(driver, Global.SECURITYA);
        nPage.enterUserName(driver, Global.USER);
        Assert.assertEquals(nPage.getUserNameError(driver), Global.USERNAME_ERROR );
        driver.close();
	}
	
	@Test(priority=16 , enabled=false)
	public void createCustomerInvalidPhone() throws Exception{
		
		DashboardPage dashPage = new DashboardPage(driver);
		dashPage.getLandingPage(Global.URL1);
		dashPage.clickCustomerTab(driver);
		dashPage.switchToFrame(driver);
		CreateCustomerPage nPage = new CreateCustomerPage(driver);
		nPage.clickSwitch(driver);
		nPage.clickCreateCustomer(driver);
		nPage.clickCustomerType(driver, Global.CUSTOMERTYPE);
		nPage.enterFirstname(driver, Global.FNAME);
		nPage.enterLastname(driver, Global.LNAME);
		email = Utils.randomEmailString();
		nPage.enterEmail(driver, email);
		nPage.enterPhone(driver, Global.INVALID_PHONE);
		//Assert.assertEquals(nPage.g(driver), Global.INVALID_PHONE );
		driver.close();
	 
	}
	
	
	//private methods 
	private NewCustomerPage createCustomerFirstPage() throws Exception{
		
		DashboardPage dashPage = new DashboardPage(driver);
		dashPage.getLandingPage(Global.URL1);
		dashPage.clickCustomerTab(driver);
		dashPage.switchToFrame(driver);
		CreateCustomerPage nPage = new CreateCustomerPage(driver);
		nPage.clickSwitch(driver);
		nPage.clickCreateCustomer(driver);
		nPage.clickCustomerType(driver, Global.CUSTOMERTYPE);
		nPage.enterFirstname(driver, Global.FNAME);
		nPage.enterLastname(driver, Global.LNAME);
		email = Utils.randomEmailString();
		nPage.enterEmail(driver, email);
		phoneNumber = Utils.randomPhoneNumber();
		nPage.enterPhone(driver, phoneNumber);
		nPage.clickContinue(driver);
		NewCustomerPage nPaget = new NewCustomerPage(driver);
		return nPaget;
	}

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