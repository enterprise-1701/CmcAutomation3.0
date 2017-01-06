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

public class UpdateContactTest {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
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

	
	//Create new customer, search for that customer and update contact details
	@Test(priority=1 , enabled = true)
	public void updateContact() throws Exception{
		
		coreTest.signIn(driver);
		coreTest.createCustomerClickHome(driver);
	    SearchPage sPage = new SearchPage(driver);
		sPage.clickCustomerType(driver, "Individual");
		sPage.enterFirstname(driver, Global.FNAME);
		sPage.enterLastname(driver, Global.LNAME);
		sPage.enterEmail(driver, coreTest.getEmail());
		sPage.clickSearch(driver);
		sPage.clickRecord(driver);
		sPage.clickSecurityBox(driver);
		sPage.clickContiune(driver);
		
		Assert.assertEquals(sPage.getFirstName(driver),  Global.FNAME);
		Log.info("Actual results " +  sPage.getFirstName(driver) + " matches " +  Global.FNAME);
		Assert.assertEquals(sPage.getLastName(driver),  Global.LNAME);
		Log.info("Actual results " +  sPage.getLastName(driver) + " matches " +  Global.LNAME);
		Assert.assertEquals(sPage.getEmail(driver),  coreTest.getEmail());
		Log.info("Actual results " +  sPage.getEmail(driver) + " matches " +  coreTest.getEmail());
		Assert.assertEquals(sPage.getPhone(driver),  coreTest.getPhone());
		Log.info("Actual results " +  sPage.getPhone(driver) + " matches " + coreTest.getPhone());
		Assert.assertEquals(sPage.getAddress(driver).substring(0, 12),  Global.ADDRESS);
		Log.info("Actual results " + sPage.getAddress(driver).substring(0, 12)  + " matches " +  Global.ADDRESS);
		
		sPage.clickContact(driver);
		ContactDetailsPage contactPage = new ContactDetailsPage(driver);
		Utils.waitTime(5000);
		contactPage.enterFname(driver, Global.FNAME2);
		contactPage.enterLname(driver, Global.LNAME2);
		contactPage.updateDob(driver);
		contactPage.clickSubmit(driver);
		Utils.waitTime(5000);
		Assert.assertEquals(sPage.getFirstName(driver),  Global.FNAME2);
		Log.info("Actual results " +  sPage.getFirstName(driver) + Global.FNAME2);
		Assert.assertEquals(sPage.getLastName(driver),  Global.LNAME2);
		Log.info("Actual results " +  sPage.getLastName(driver) + " matches " +  Global.LNAME2);
		
		driver.close();
      	
	}
	
	//Create new customer, search for that customer, attempt to update contact details and cancel
		@Test(priority=2 , enabled = true)
		public void updateContactCancel() throws Exception{
			
			coreTest.signIn(driver);
			coreTest.createCustomerClickHome(driver);
		    SearchPage sPage = new SearchPage(driver);
			sPage.clickCustomerType(driver, "Individual");
			sPage.enterFirstname(driver, Global.FNAME);
			sPage.enterLastname(driver, Global.LNAME);
			sPage.enterEmail(driver, coreTest.getEmail());
			sPage.clickSearch(driver);
			sPage.clickRecord(driver);
			sPage.clickSecurityBox(driver);
			sPage.clickContiune(driver);
			
			Assert.assertEquals(sPage.getFirstName(driver),  Global.FNAME);
			Log.info("Actual results " +  sPage.getFirstName(driver) + " matches " +  Global.FNAME);
			Assert.assertEquals(sPage.getLastName(driver),  Global.LNAME);
			Log.info("Actual results " +  sPage.getLastName(driver) + " matches " +  Global.LNAME);
			Assert.assertEquals(sPage.getEmail(driver),  coreTest.getEmail());
			Log.info("Actual results " +  sPage.getEmail(driver) + " matches " +  coreTest.getEmail());
			Assert.assertEquals(sPage.getPhone(driver),  coreTest.getPhone());
			Log.info("Actual results " +  sPage.getPhone(driver) + " matches " + coreTest.getPhone());
			Assert.assertEquals(sPage.getAddress(driver).substring(0, 12),  Global.ADDRESS);
			Log.info("Actual results " + sPage.getAddress(driver).substring(0, 12)  + " matches " +  Global.ADDRESS);
			
			sPage.clickContact(driver);
			ContactDetailsPage contactPage = new ContactDetailsPage(driver);
			Utils.waitTime(3000);
			contactPage.enterFname(driver, Global.FNAME2);
			contactPage.enterLname(driver, Global.LNAME2);
			contactPage.clickCancel(driver);
			Utils.waitTime(3000);
			Assert.assertEquals(sPage.getFirstName(driver),  Global.FNAME);
			Log.info("Actual results " +  sPage.getFirstName(driver) + " matches " +  Global.FNAME);
			Assert.assertEquals(sPage.getLastName(driver),  Global.LNAME);
			Log.info("Actual results " +  sPage.getLastName(driver) + " matches " +  Global.LNAME);
			Assert.assertEquals(sPage.getEmail(driver),  coreTest.getEmail());
			Log.info("Actual results " +  sPage.getEmail(driver) + " matches " +  coreTest.getEmail());
			Assert.assertEquals(sPage.getPhone(driver),  coreTest.getPhone());
			Log.info("Actual results " +  sPage.getPhone(driver) + " matches " + coreTest.getPhone());
			Assert.assertEquals(sPage.getAddress(driver).substring(0, 12),  Global.ADDRESS);
			Log.info("Actual results " + sPage.getAddress(driver).substring(0, 12)  + " matches " +  Global.ADDRESS);
			
			driver.close();
	      	
		}
	
		
		//Create new customer, search for that customer and update contact details and address
		@Test(priority=3 , enabled = true)
		public void updateContactAndAddress() throws Exception{
			
			coreTest.signIn(driver);
			coreTest.createCustomerClickHome(driver);
		    SearchPage sPage = new SearchPage(driver);
			sPage.clickCustomerType(driver, "Individual");
			sPage.enterFirstname(driver, Global.FNAME);
			sPage.enterLastname(driver, Global.LNAME);
			sPage.enterEmail(driver, coreTest.getEmail());
			sPage.clickSearch(driver);
			sPage.clickRecord(driver);
			sPage.clickSecurityBox(driver);
			sPage.clickContiune(driver);
			
			Assert.assertEquals(sPage.getFirstName(driver),  Global.FNAME);
			Log.info("Actual results " +  sPage.getFirstName(driver) + " matches " +  Global.FNAME);
			Assert.assertEquals(sPage.getLastName(driver),  Global.LNAME);
			Log.info("Actual results " +  sPage.getLastName(driver) + " matches " +  Global.LNAME);
			Assert.assertEquals(sPage.getEmail(driver),  coreTest.getEmail());
			Log.info("Actual results " +  sPage.getEmail(driver) + " matches " +  coreTest.getEmail());
			Assert.assertEquals(sPage.getPhone(driver),  coreTest.getPhone());
			Log.info("Actual results " +  sPage.getPhone(driver) + " matches " + coreTest.getPhone());
			Assert.assertEquals(sPage.getAddress(driver).substring(0, 12),  Global.ADDRESS);
			Log.info("Actual results " + sPage.getAddress(driver).substring(0, 12)  + " matches " +  Global.ADDRESS);
			
			sPage.clickContact(driver);
			ContactDetailsPage contactPage = new ContactDetailsPage(driver);
			Utils.waitTime(5000);
			contactPage.enterFname(driver, Global.FNAME2);
			contactPage.enterLname(driver, Global.LNAME2);
			contactPage.clickNewAddress(driver);
			contactPage.selectCountry(driver);
			contactPage.enterAddress(driver, Global.NEWADDRESS);
			contactPage.enterCity(driver, Global.NEWCITY);
			contactPage.selectState(driver);
			contactPage.enterPostalCode(driver, Global.NEWPOSTAL);
			contactPage.updateDob(driver);
			
			contactPage.clickSubmit(driver);
			Utils.waitTime(5000);
			Assert.assertEquals(sPage.getFirstName(driver),  Global.FNAME2);
			Log.info("Actual results " +  sPage.getFirstName(driver) + Global.FNAME2);
			Assert.assertEquals(sPage.getLastName(driver),  Global.LNAME2);
			Log.info("Actual results " +  sPage.getLastName(driver) + " matches " +  Global.LNAME2);
			Assert.assertEquals(sPage.getAddress(driver),  Global.NEW_FULLADDRESS);
			Log.info("Actual results " +  sPage.getAddress(driver) + " matches " +  Global.NEW_FULLADDRESS);
			
			driver.close();
	      	
		}
		
		//Create new customer, search for that customer attempt to update contact details and address and cancel
				@Test(priority=4 , enabled = true)
				public void updateContactAndAddressCancel() throws Exception{
					
					coreTest.signIn(driver);
					coreTest.createCustomerClickHome(driver);
				    SearchPage sPage = new SearchPage(driver);
					sPage.clickCustomerType(driver, "Individual");
					sPage.enterFirstname(driver, Global.FNAME);
					sPage.enterLastname(driver, Global.LNAME);
					sPage.enterEmail(driver, coreTest.getEmail());
					sPage.clickSearch(driver);
					sPage.clickRecord(driver);
					sPage.clickSecurityBox(driver);
					sPage.clickContiune(driver);
					
					Assert.assertEquals(sPage.getFirstName(driver),  Global.FNAME);
					Log.info("Actual results " +  sPage.getFirstName(driver) + " matches " +  Global.FNAME);
					Assert.assertEquals(sPage.getLastName(driver),  Global.LNAME);
					Log.info("Actual results " +  sPage.getLastName(driver) + " matches " +  Global.LNAME);
					Assert.assertEquals(sPage.getEmail(driver),  coreTest.getEmail());
					Log.info("Actual results " +  sPage.getEmail(driver) + " matches " +  coreTest.getEmail());
					Assert.assertEquals(sPage.getPhone(driver),  coreTest.getPhone());
					Log.info("Actual results " +  sPage.getPhone(driver) + " matches " + coreTest.getPhone());
					Assert.assertEquals(sPage.getAddress(driver).substring(0, 12),  Global.ADDRESS);
					Log.info("Actual results " + sPage.getAddress(driver).substring(0, 12)  + " matches " +  Global.ADDRESS);
					
					sPage.clickContact(driver);
					ContactDetailsPage contactPage = new ContactDetailsPage(driver);
					Utils.waitTime(5000);
					contactPage.enterFname(driver, Global.FNAME2);
					contactPage.enterLname(driver, Global.LNAME2);
					contactPage.clickNewAddress(driver);
					contactPage.selectCountry(driver);
					contactPage.enterAddress(driver, Global.NEWADDRESS);
					contactPage.enterCity(driver, Global.NEWCITY);
					contactPage.selectState(driver);
					contactPage.enterPostalCode(driver, Global.NEWPOSTAL);
					contactPage.clickCancel(driver);
					Utils.waitTime(5000);
					Assert.assertEquals(sPage.getFirstName(driver),  Global.FNAME);
					Log.info("Actual results " +  sPage.getFirstName(driver) + Global.FNAME);
					Assert.assertEquals(sPage.getLastName(driver),  Global.LNAME);
					Log.info("Actual results " +  sPage.getLastName(driver) + " matches " +  Global.LNAME);
					Assert.assertEquals(sPage.getAddress(driver),  Global.FULLADDRESS);
					Log.info("Actual results " +  sPage.getAddress(driver) + " matches " +  Global.FULLADDRESS);
					
					driver.close();
			      	
				}
				
				//Create new customer, search for that customer, attempt to update without inputting all requierd fields
				@Test(priority=5 , enabled = true)
				public void updateContactCheckRequiredFields() throws Exception{
					
					coreTest.signIn(driver);
					coreTest.createCustomerClickHome(driver);
				    SearchPage sPage = new SearchPage(driver);
					sPage.clickCustomerType(driver, "Individual");
					sPage.enterFirstname(driver, Global.FNAME);
					sPage.enterLastname(driver, Global.LNAME);
					sPage.enterEmail(driver, coreTest.getEmail());
					sPage.clickSearch(driver);
					sPage.clickRecord(driver);
					sPage.clickSecurityBox(driver);
					sPage.clickContiune(driver);
					
					Assert.assertEquals(sPage.getFirstName(driver),  Global.FNAME);
					Log.info("Actual results " +  sPage.getFirstName(driver) + " matches " +  Global.FNAME);
					Assert.assertEquals(sPage.getLastName(driver),  Global.LNAME);
					Log.info("Actual results " +  sPage.getLastName(driver) + " matches " +  Global.LNAME);
					Assert.assertEquals(sPage.getEmail(driver),  coreTest.getEmail());
					Log.info("Actual results " +  sPage.getEmail(driver) + " matches " +  coreTest.getEmail());
					Assert.assertEquals(sPage.getPhone(driver),  coreTest.getPhone());
					Log.info("Actual results " +  sPage.getPhone(driver) + " matches " + coreTest.getPhone());
					Assert.assertEquals(sPage.getAddress(driver).substring(0, 12),  Global.ADDRESS);
					Log.info("Actual results " + sPage.getAddress(driver).substring(0, 12)  + " matches " +  Global.ADDRESS);
					
					sPage.clickContact(driver);
					ContactDetailsPage contactPage = new ContactDetailsPage(driver);
					Utils.waitTime(5000);
					contactPage.enterLname(driver, "x");
					contactPage.deleteLname(driver);
					Utils.waitTime(5000);
					//Assert.assertEquals(contactPage.getRequiredFieldError(driver),  Global.REQUIREDFIELD_ERROR);
					//Log.info("Actual results " +  contactPage.getRequiredFieldError(driver) + Global.REQUIREDFIELD_ERROR);
					Assert.assertFalse(contactPage.isSubmitEnabled(driver), "Submit button should not be enabled!");
					Log.info("Success button enabled? " +  contactPage.isSubmitEnabled(driver));
					
					driver.close();
			      	
				}
				
				//Create new customer, search for that customer, attempt to update inputting invalid phone number
				@Test(priority=6 , enabled = true)
				public void updateContactInvalidPhone() throws Exception{
					
					coreTest.signIn(driver);
					coreTest.createCustomerClickHome(driver);
				    SearchPage sPage = new SearchPage(driver);
					sPage.clickCustomerType(driver, "Individual");
					sPage.enterFirstname(driver, Global.FNAME);
					sPage.enterLastname(driver, Global.LNAME);
					sPage.enterEmail(driver, coreTest.getEmail());
					sPage.clickSearch(driver);
					sPage.clickRecord(driver);
					sPage.clickSecurityBox(driver);
					sPage.clickContiune(driver);
					
					Assert.assertEquals(sPage.getFirstName(driver),  Global.FNAME);
					Log.info("Actual results " +  sPage.getFirstName(driver) + " matches " +  Global.FNAME);
					Assert.assertEquals(sPage.getLastName(driver),  Global.LNAME);
					Log.info("Actual results " +  sPage.getLastName(driver) + " matches " +  Global.LNAME);
					Assert.assertEquals(sPage.getEmail(driver),  coreTest.getEmail());
					Log.info("Actual results " +  sPage.getEmail(driver) + " matches " +  coreTest.getEmail());
					Assert.assertEquals(sPage.getPhone(driver),  coreTest.getPhone());
					Log.info("Actual results " +  sPage.getPhone(driver) + " matches " + coreTest.getPhone());
					Assert.assertEquals(sPage.getAddress(driver).substring(0, 12),  Global.ADDRESS);
					Log.info("Actual results " + sPage.getAddress(driver).substring(0, 12)  + " matches " +  Global.ADDRESS);
					
					sPage.clickContact(driver);
					ContactDetailsPage contactPage = new ContactDetailsPage(driver);
					Utils.waitTime(5000);
					
					contactPage.enterPhone(driver, Global.INVALID_PHONE);
					contactPage.updateDob(driver);
					contactPage.clickSubmit(driver);
					Utils.waitTime(5000);
					Assert.assertEquals(sPage.getPhoneError(driver),  "phone[0].number errors.general.invalid.phone.number");
					Log.info("Actual results " + sPage.getPhoneError(driver) + "matches" + "phone[0].number errors.general.invalid.phone.number");
					Utils.waitTime(3000);
					driver.close();
			      	
				}
				
				//Create new customer, search for that customer, attempt to update inputting invalid email
				@Test(priority=7 , enabled = true)
				public void updateContactInvalidEmail() throws Exception{
					
					coreTest.signIn(driver);
					coreTest.createCustomerClickHome(driver);
				    SearchPage sPage = new SearchPage(driver);
					sPage.clickCustomerType(driver, "Individual");
					sPage.enterFirstname(driver, Global.FNAME);
					sPage.enterLastname(driver, Global.LNAME);
					sPage.enterEmail(driver, coreTest.getEmail());
					sPage.clickSearch(driver);
					sPage.clickRecord(driver);
					sPage.clickSecurityBox(driver);
					sPage.clickContiune(driver);
					
					Assert.assertEquals(sPage.getFirstName(driver),  Global.FNAME);
					Log.info("Actual results " +  sPage.getFirstName(driver) + " matches " +  Global.FNAME);
					Assert.assertEquals(sPage.getLastName(driver),  Global.LNAME);
					Log.info("Actual results " +  sPage.getLastName(driver) + " matches " +  Global.LNAME);
					Assert.assertEquals(sPage.getEmail(driver),  coreTest.getEmail());
					Log.info("Actual results " +  sPage.getEmail(driver) + " matches " +  coreTest.getEmail());
					Assert.assertEquals(sPage.getPhone(driver),  coreTest.getPhone());
					Log.info("Actual results " +  sPage.getPhone(driver) + " matches " + coreTest.getPhone());
					Assert.assertEquals(sPage.getAddress(driver).substring(0, 12),  Global.ADDRESS);
					Log.info("Actual results " + sPage.getAddress(driver).substring(0, 12)  + " matches " +  Global.ADDRESS);
					
					sPage.clickContact(driver);
					ContactDetailsPage contactPage = new ContactDetailsPage(driver);
					Utils.waitTime(5000);
					
					contactPage.enterEmail(driver, "test");
					Assert.assertFalse(contactPage.isSubmitEnabled(driver), "Submit button should not be enabled!");
					Log.info("Success button enabled? " +  contactPage.isSubmitEnabled(driver));
					Assert.assertEquals(contactPage.getInvalidEmailError(driver), Global.INVALID_EMAIL_ERROR);
					Log.info("Actual results " + contactPage.getInvalidEmailError(driver) + "matches" + Global.INVALID_EMAIL_ERROR );
					Utils.waitTime(3000);
					driver.close();
			      	
				}
				
				//Create new customer, search for that customer, attempt to update inputting invalid username
				@Test(priority=8 , enabled = true)
				public void updateContactInvalidUserName() throws Exception{
					
					coreTest.signIn(driver);
					coreTest.createCustomerClickHome(driver);
				    SearchPage sPage = new SearchPage(driver);
					sPage.clickCustomerType(driver, "Individual");
					sPage.enterFirstname(driver, Global.FNAME);
					sPage.enterLastname(driver, Global.LNAME);
					sPage.enterEmail(driver, coreTest.getEmail());
					sPage.clickSearch(driver);
					sPage.clickRecord(driver);
					sPage.clickSecurityBox(driver);
					sPage.clickContiune(driver);
					
					Assert.assertEquals(sPage.getFirstName(driver),  Global.FNAME);
					Log.info("Actual results " +  sPage.getFirstName(driver) + " matches " +  Global.FNAME);
					Assert.assertEquals(sPage.getLastName(driver),  Global.LNAME);
					Log.info("Actual results " +  sPage.getLastName(driver) + " matches " +  Global.LNAME);
					Assert.assertEquals(sPage.getEmail(driver),  coreTest.getEmail());
					Log.info("Actual results " +  sPage.getEmail(driver) + " matches " +  coreTest.getEmail());
					Assert.assertEquals(sPage.getPhone(driver),  coreTest.getPhone());
					Log.info("Actual results " +  sPage.getPhone(driver) + " matches " + coreTest.getPhone());
					Assert.assertEquals(sPage.getAddress(driver).substring(0, 12),  Global.ADDRESS);
					Log.info("Actual results " + sPage.getAddress(driver).substring(0, 12)  + " matches " +  Global.ADDRESS);
					
					sPage.clickContact(driver);
					ContactDetailsPage contactPage = new ContactDetailsPage(driver);
					Utils.waitTime(5000);
					
					contactPage.enterUsername(driver, "x");
					Assert.assertFalse(contactPage.isSubmitEnabled(driver), "Submit button should not be enabled!");
					Log.info("Success button enabled? " +  contactPage.isSubmitEnabled(driver));
					Assert.assertEquals(contactPage.getInvalidEmailError(driver), Global.INVALID_EMAIL_ERROR);
					Log.info("Actual results " + contactPage.getInvalidEmailError(driver) + "matches" + Global.INVALID_EMAIL_ERROR );
					Utils.waitTime(3000);
					driver.close();
			      	
				}
				
				//Create new customer, search for that customer, attempt to update inputting invalid pin
				@Test(priority=9 , enabled = true)
				public void updateContactInvalidPin() throws Exception{
					
					coreTest.signIn(driver);
					coreTest.createCustomerClickHome(driver);
				    SearchPage sPage = new SearchPage(driver);
					sPage.clickCustomerType(driver, "Individual");
					sPage.enterFirstname(driver, Global.FNAME);
					sPage.enterLastname(driver, Global.LNAME);
					sPage.enterEmail(driver, coreTest.getEmail());
					sPage.clickSearch(driver);
					sPage.clickRecord(driver);
					sPage.clickSecurityBox(driver);
					sPage.clickContiune(driver);
					
					Assert.assertEquals(sPage.getFirstName(driver),  Global.FNAME);
					Log.info("Actual results " +  sPage.getFirstName(driver) + " matches " +  Global.FNAME);
					Assert.assertEquals(sPage.getLastName(driver),  Global.LNAME);
					Log.info("Actual results " +  sPage.getLastName(driver) + " matches " +  Global.LNAME);
					Assert.assertEquals(sPage.getEmail(driver),  coreTest.getEmail());
					Log.info("Actual results " +  sPage.getEmail(driver) + " matches " +  coreTest.getEmail());
					Assert.assertEquals(sPage.getPhone(driver),  coreTest.getPhone());
					Log.info("Actual results " +  sPage.getPhone(driver) + " matches " + coreTest.getPhone());
					Assert.assertEquals(sPage.getAddress(driver).substring(0, 12),  Global.ADDRESS);
					Log.info("Actual results " + sPage.getAddress(driver).substring(0, 12)  + " matches " +  Global.ADDRESS);
					
					sPage.clickContact(driver);
					ContactDetailsPage contactPage = new ContactDetailsPage(driver);
					Utils.waitTime(5000);
					
					contactPage.enterPin(driver, "1");
					Assert.assertFalse(contactPage.isSubmitEnabled(driver), "Submit button should not be enabled!");
					Log.info("Success button enabled? " +  contactPage.isSubmitEnabled(driver));
					Assert.assertEquals(contactPage.getMinLengthError(driver), Global.MIN_LENGTH_ERROR);
					Log.info("Actual results " + contactPage.getMinLengthError(driver) + "matches" + Global.MIN_LENGTH_ERROR);
					Utils.waitTime(3000);
					driver.close();
			      	
				}
				
				//This is an issue where the error shows on workspace and not on the popup
				//Create new customer, search for that customer, attempt to update inputting invalid dob
				@Test(priority=10 , enabled = true)
				public void updateContactSecurityAnswermissing() throws Exception{
					
					coreTest.signIn(driver);
					coreTest.createCustomerClickHome(driver);
				    SearchPage sPage = new SearchPage(driver);
					sPage.clickCustomerType(driver, "Individual");
					sPage.enterFirstname(driver, Global.FNAME);
					sPage.enterLastname(driver, Global.LNAME);
					sPage.enterEmail(driver, coreTest.getEmail());
					sPage.clickSearch(driver);
					sPage.clickRecord(driver);
					sPage.clickSecurityBox(driver);
					sPage.clickContiune(driver);
					
					Assert.assertEquals(sPage.getFirstName(driver),  Global.FNAME);
					Log.info("Actual results " +  sPage.getFirstName(driver) + " matches " +  Global.FNAME);
					Assert.assertEquals(sPage.getLastName(driver),  Global.LNAME);
					Log.info("Actual results " +  sPage.getLastName(driver) + " matches " +  Global.LNAME);
					Assert.assertEquals(sPage.getEmail(driver),  coreTest.getEmail());
					Log.info("Actual results " +  sPage.getEmail(driver) + " matches " +  coreTest.getEmail());
					Assert.assertEquals(sPage.getPhone(driver),  coreTest.getPhone());
					Log.info("Actual results " +  sPage.getPhone(driver) + " matches " + coreTest.getPhone());
					Assert.assertEquals(sPage.getAddress(driver).substring(0, 12),  Global.ADDRESS);
					Log.info("Actual results " + sPage.getAddress(driver).substring(0, 12)  + " matches " +  Global.ADDRESS);
					
					sPage.clickContact(driver);
					ContactDetailsPage contactPage = new ContactDetailsPage(driver);
					Utils.waitTime(5000);
					
					contactPage.enterSecurityAnswer(driver, "");
					
					//Assertions need to be updated
					/*
					Assert.assertFalse(contactPage.isSubmitEnabled(driver), "Submit button should not be enabled!");
					Log.info("Success button enabled? " +  contactPage.isSubmitEnabled(driver));
					Assert.assertEquals(contactPage.getRequiredFieldError(driver), Global.REQUIREDFIELD_ERROR);
					Log.info("Actual results " + contactPage.getRequiredFieldError(driver) + "matches" + Global.REQUIREDFIELD_ERROR);
					Utils.waitTime(3000);
					*/
					
					driver.close();
			      	
				}
				
				
				//Create new customer, search for that customer, attempt to update inputting phone types with phone numbers missing
				@Test(priority=11 , enabled = true)
				public void updateContactOtherPhonesMissing() throws Exception{
					
					coreTest.signIn(driver);
					coreTest.createCustomerClickHome(driver);
				    SearchPage sPage = new SearchPage(driver);
					sPage.clickCustomerType(driver, "Individual");
					sPage.enterFirstname(driver, Global.FNAME);
					sPage.enterLastname(driver, Global.LNAME);
					sPage.enterEmail(driver, coreTest.getEmail());
					sPage.clickSearch(driver);
					sPage.clickRecord(driver);
					sPage.clickSecurityBox(driver);
					sPage.clickContiune(driver);
					
					Assert.assertEquals(sPage.getFirstName(driver),  Global.FNAME);
					Log.info("Actual results " +  sPage.getFirstName(driver) + " matches " +  Global.FNAME);
					Assert.assertEquals(sPage.getLastName(driver),  Global.LNAME);
					Log.info("Actual results " +  sPage.getLastName(driver) + " matches " +  Global.LNAME);
					Assert.assertEquals(sPage.getEmail(driver),  coreTest.getEmail());
					Log.info("Actual results " +  sPage.getEmail(driver) + " matches " +  coreTest.getEmail());
					Assert.assertEquals(sPage.getPhone(driver),  coreTest.getPhone());
					Log.info("Actual results " +  sPage.getPhone(driver) + " matches " + coreTest.getPhone());
					Assert.assertEquals(sPage.getAddress(driver).substring(0, 12),  Global.ADDRESS);
					Log.info("Actual results " + sPage.getAddress(driver).substring(0, 12)  + " matches " +  Global.ADDRESS);
					
					sPage.clickContact(driver);
					ContactDetailsPage contactPage = new ContactDetailsPage(driver);
					Utils.waitTime(5000);
					
					contactPage.selectPhoneType2(driver);					
					contactPage.selectPhoneType3(driver);
					
					//Assertions need to be updated
					/*
					Assert.assertFalse(contactPage.isSubmitEnabled(driver), "Submit button should not be enabled!");
					Log.info("Success button enabled? " +  contactPage.isSubmitEnabled(driver));
					Assert.assertEquals(contactPage.getRequiredFieldMutualError(driver), Global.REQUIREDFIELD_ERROR);
					Log.info("Actual results " + contactPage.getRequiredFieldMutualError(driver) + "matches" + Global.REQUIREDFIELD_ERROR);
					*/
					driver.close();
			      	
				}
			
	
	@AfterMethod
	public void tearDown() {
		Log.info("TearDown Complete");
		Reporter.log("TearDown Complete");
		driver.quit();

	}
}