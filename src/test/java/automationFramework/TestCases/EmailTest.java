package automationFramework.TestCases;


import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import automationFramework.Utilities.Global;
import automationFramework.Utilities.Logging;
import automationFramework.Utilities.Utils;


public class EmailTest{
	
	 private String host = "bb-corp-cas01.corp.cubic.cub";
     private String username = "cts.systemtest";
     private String password = "gd6N6MCkCdDa9Rc##";
     private String folder = "Inbox/Cmc";
     private static Logger Log = Logger.getLogger(Logger.class.getName());
     CoreTest coreTest = new CoreTest();
     static WebDriver driver;
	
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
    
    //add email check for link account
    //email for debit charge
    //email for updates
    
	@Test(priority=1 , enabled=false)
	public void checkEmailNewCustomer() throws Exception{
		
		//Create New Customer
		coreTest.signIn(driver);
	    coreTest.createCustomer(driver, "ray.khorrami@cubic.com");  
	    Log.info("New customer created" );
	    Utils.waitTime(10000);
		
	    //Check Welcome Email
		Properties props = System.getProperties();               
	      props.put("mail.imaps.auth.plain.disable","true"); 
	           try {
	                Session session = Session.getDefaultInstance(props, null);
	                Store store = session.getStore("imaps");
	                store.connect(host, 993, username, password);
	                Folder inbox = store.getFolder(folder);
	                inbox.open(Folder.READ_ONLY);       
	                
	                Log.info("Number of new emails: " + inbox.getUnreadMessageCount());
	                Assert.assertTrue((inbox.getUnreadMessageCount()>0), ("New Welcome Email Not Received!"));
	                Message messages[] = inbox.getMessages();
	                Log.info("Total number of emails: " + inbox.getMessages().length);	
	                String newSubject = messages[inbox.getMessages().length-1].getSubject();
	                //Log.info("Email subject For last received email: " + newSubject);
	                
	                //String newContent = (String) messages[inbox.getMessages().length-1].getContent();
	                //Log.info("EMAIL CONTENT: " + newContent);
	                
	                Assert.assertEquals(newSubject, "Welcome New Customer");  
	                Log.info("checkEmailNewCustomer Test Compelted");
	                
	           }  catch (Exception e) {
	           e.printStackTrace();
	           //System.exit(2);
	      }
			
	}
	
	
	
	@Test(priority=1 , enabled=true)
	public void checkEmailCreateOrder() throws Exception{
		
		//Create New Customer
		coreTest.signIn(driver);
	    coreTest.createOrderSubmit(driver, "ray.khorrami@cubic.com");  
	    Log.info("New customer created, New order created" );
	    Utils.waitTime(30000);
		
	    //Check both create order emails
		Properties props = System.getProperties();               
	      props.put("mail.imaps.auth.plain.disable","true"); 
	           try {
	                Session session = Session.getDefaultInstance(props, null);
	                Store store = session.getStore("imaps");
	                store.connect(host, 993, username, password);
	                Folder inbox = store.getFolder(folder);
	                inbox.open(Folder.READ_ONLY);        
	                Log.info("Number of new emails: " + inbox.getUnreadMessageCount());
	                Assert.assertTrue((inbox.getUnreadMessageCount()>0), ("New Create Order Email Not Received!"));
	                Message messages[] = inbox.getMessages();
	                Log.info("Total number of emails: " + inbox.getMessages().length);	
	                
	                String newSubject = messages[inbox.getMessages().length-1].getSubject(); 
	                Assert.assertTrue(newSubject.contains("OneAccount: Order Update Notification") || newSubject.contains("OneAccount: Payment Receipt"));
	            
	                String newSubject2 = messages[inbox.getMessages().length-2].getSubject(); 
	                Assert.assertTrue(newSubject2.contains("OneAccount: Order Update Notification") || newSubject2.contains("OneAccount: Payment Receipt"));
	                
	                Log.info("checkEmailCreateOrder Compelted");
	                
	           }  catch (Exception e) {
	           e.printStackTrace();
	           //System.exit(2);
	      }
			
	}
	
	
	@AfterMethod
	public void tearDown() {
		Log.info("TearDown Complete");
		Reporter.log("TearDown Complete");
		driver.quit();

	}
	
}