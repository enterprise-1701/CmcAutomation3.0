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
import automationFramework.PageObjects.DashboardPage;
import automationFramework.PageObjects.NewCustomerPage;
import automationFramework.Utilities.*;

public class CreateCustomerDBTest {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private static String email;
	private static String userId;
	private static boolean contactRecordFound;
	private static boolean accountRecordFound;
	private static boolean addressRecordFound;
	private static boolean securityRecordFound;
	private static boolean userIdUnique;
	private static boolean phoneRecordFound;
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
	public void createNewCustomerDBCheckContactTable() throws Exception{
	
		coreTest.signIn(driver);
	    coreTest.createCustomer(driver);  
	    email = coreTest.getEmail();
	    Log.info("Email being passed to DAO: " + email);
	    Utils.waitTime(3000);
	    
	    PostgresAutomation psAuto = new PostgresAutomation();
	    psAuto.dbConnect();
	    contactRecordFound = psAuto.dbFindCustomer(email);
	    psAuto.dbDisconnect();
	    Assert.assertTrue(contactRecordFound, "customer record was not found");
		driver.close();
		Log.info("createNewCustomerDBCheckContactTable Completed");
	    
	}
	
	@Test(priority=2 , enabled=true)
	public void createNewCustomerDBCheckAccountTable() throws Exception{
	
		coreTest.signIn(driver);
	    coreTest.createCustomer(driver);  
	    email = coreTest.getEmail();   
	    Utils.waitTime(3000);
	    
	    PostgresAutomation psAuto = new PostgresAutomation();
	    psAuto.dbConnect();
	    accountRecordFound = psAuto.dbFindAccount(email);
	    psAuto.dbDisconnect();
	    Assert.assertTrue(accountRecordFound, "account record was not found");
		driver.close();
		Log.info("createNewCustomerDBCheckAccountTable Completed");
		    
	}
	
	
	@Test(priority=3 , enabled=true)
	public void createNewCustomerDBCheckAddressTable() throws Exception{
	
		coreTest.signIn(driver);
	    coreTest.createCustomer(driver);  
	    email = coreTest.getEmail();   
	    Utils.waitTime(3000);
	    
	    PostgresAutomation psAuto = new PostgresAutomation();
	    psAuto.dbConnect();
	    addressRecordFound = psAuto.dbFindAddress(email);
	    psAuto.dbDisconnect();
	    Assert.assertTrue(addressRecordFound, "address record was not found");
		driver.close();
		Log.info("createNewCustomerDBCheckAddressTable Completed");
		    
	}
	
	@Test(priority=4 , enabled=true)
	public void createNewCustomerDBCheckSecurityTable() throws Exception{
	
		coreTest.signIn(driver);
	    coreTest.createCustomer(driver);  
	    email = coreTest.getEmail();   
	    Utils.waitTime(3000);
	    
	    PostgresAutomation psAuto = new PostgresAutomation();
	    psAuto.dbConnect();
	    securityRecordFound = psAuto.dbFindSecurityAnswer(email);
	    psAuto.dbDisconnect();
	    Assert.assertTrue(securityRecordFound, "security record was not found");
		driver.close();
		Log.info("createNewCustomerDBCheckSecurityTable Completed");
		    
	}
	
	@Test(priority=5 , enabled=true)
	public void createNewCustomerDBCheckPhoneTable() throws Exception{
	
		coreTest.signIn(driver);
	    coreTest.createCustomer(driver);  
	    email = coreTest.getEmail();   
	    Utils.waitTime(3000);
	    
	    PostgresAutomation psAuto = new PostgresAutomation();
	    psAuto.dbConnect();
	    phoneRecordFound = psAuto.dbFindPhone(email);
	    psAuto.dbDisconnect();
	    Assert.assertTrue(phoneRecordFound, "phone record was not found");
		driver.close();
		Log.info("createNewCustomerDBCheckPhoneTable Completed");
		    
	}
	
	/*
	@Test(priority=6 , enabled=true)
	public void createNewCustomerDBCheckUserName() throws Exception{
		
		coreTest.signIn(driver);
	    coreTest.createCustomer(driver);  
	    userId = coreTest.getUserid();  
	    Utils.waitTime(3000);
	    
	    PostgresAutomation psAuto = new PostgresAutomation();
	    psAuto.dbConnect();
	    userIdUnique = psAuto.dbFindUniqueUserName(userId);
	    psAuto.dbDisconnect();
	    Assert.assertTrue(userIdUnique, "user id is not unique");
		driver.close();
		Log.info("createNewCustomerDBCheckUserName Completed");
		    
	}
	*/
	
	@Test(priority=7 , enabled=true)
	public void createNewCustomerCancelDBCheck() throws Exception{
	
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
		email = Utils.randomEmailString();
		nPage.enterEmail(driver, email);
		nPage.enterPhone(driver, Global.PHONE);
		nPage.clickContinue(driver);
		NewCustomerPage nPaget = new NewCustomerPage(driver);
		nPaget.clickCancel(driver);
		PostgresAutomation psAuto = new PostgresAutomation();
	    psAuto.dbConnect();
	    contactRecordFound  = psAuto.dbFindCustomer(email);
	    Assert.assertFalse(contactRecordFound , "customer record should not be created");
	    psAuto.dbDisconnect();
		driver.close();
		Log.info("createNewCustomerCancelDB Completed");
		    
	}
	
	
		
	@AfterMethod
	public void tearDown() {
		Log.info("TearDown Completed");
		Reporter.log("TearDown Completed");
		driver.quit();

	}
}