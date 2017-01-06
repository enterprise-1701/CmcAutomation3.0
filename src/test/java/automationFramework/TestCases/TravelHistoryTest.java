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

public class TravelHistoryTest {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private static final String LOCATION = "2 Hyde Park Express";
	private static final String AGENCY = "NYCT";
	private static final String TRAVEL_MODE = "Rail";
	private static final String PRODUCT = "None";
	private static final String FARE = "$7.60";
	private static final String UNPAID_FARE = "$0.00";
	private static final String FNAME = "Nick";
	private static final String PHONE = "1112223333";
	
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
	

	//This test case will be updated once travel history code is stable 
	@Test(enabled=true)
	public void checkTravelHistory()throws Exception{
	
		coreTest.signIn(driver);
	    SearchPage sPage = getSearchPage();
		sPage.clickCustomerType(driver, Global.CUSTOMERTYPE);
		sPage.enterFirstname(driver, "robert");
		sPage.enterLastname(driver, "downton");
		sPage.clickSearch(driver);
		sPage.clickRecord(driver);
		sPage.clickSecurityBox(driver);
		sPage.clickContiune(driver);
		sPage.clickTravelHistory(driver);
		Utils.waitTime(5000);
		TravelHistoryPage tPage = new TravelHistoryPage(driver);
		Assert.assertEquals(tPage.getTravelMode(driver),  "Travel Mode");
		
		Log.info("checkTravelHistory Completed");
		driver.close();
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