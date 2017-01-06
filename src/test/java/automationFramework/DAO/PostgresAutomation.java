package automationFramework.DAO;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automationFramework.Utilities.Global;
import automationFramework.Utilities.Logging;

@Test
public class PostgresAutomation {
	
	static WebDriver driver;
	static String browser;
	private static Logger Log = Logger.getLogger(Logger.class.getName());
	Connection connection;
	int accountID;
	int customerID;
	int count;
	int fundingID;
	int addressID;
	int securityID;
	int contactID;
	int phoneID;
	int ccID;
	int subSystemID;
	int purseID;
	int journalID;
	String userName;
	String phoneNo;

	@BeforeMethod
	public void setUp() throws InterruptedException {

		Logging.setLogConsole();
		Logging.setLogFile();
		
	}
	
	public void dbConnect(){
		try{
			Class.forName("org.postgresql.Driver");
			Enumeration<Driver> drivers = DriverManager.getDrivers();
			connection = DriverManager.getConnection(Global.POSTGRESDB, Global.POSTGRESUSER, Global.POSTGRESPASS);
			Log.info("PostgresDB connection success!");
			
			}catch(Exception e){
			Log.error("Not able to connect to postgresDB");
			}	
		}
	
	//check cms_main.contact
	public boolean dbFindCustomer(String userEmail){
		
		boolean recordFound = false;
		
		try{
			
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM cms_main.contact where email ='" + userEmail + "'");
			while(results.next()){
			  String ID = results.getString("contact_id");
			  Log.info("contact id in the database: " + ID);
			  recordFound = true;
			}
			
			}catch(Exception e){
			Log.error("Not able to retrieve record from postgresDB");
			}
		
			return recordFound;	
		}
	
	//check oam_main.account
	public boolean dbFindAccount(String email){
			
		try{
			
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT customer_id FROM cms_main.contact where email ='" + email + "'");
		    
			while(results.next()){
				  customerID = results.getInt("customer_id");
				  Log.info("contact id in the database: " + customerID);
				
				}
			
			Statement statement2 = connection.createStatement();
			ResultSet results2 = statement2.executeQuery("SELECT account_id FROM oam_main.account where customer_id = '" + customerID + "'");
			
			while(results2.next()){
			  accountID = results2.getInt("account_id");
			  Log.info("account id in the database: " + accountID);
			  return true;
			 
			}
			
			}catch(Exception e){
			System.err.println(e.getMessage());
			Log.error("Not able to retrieve record from postgresDB oam_main.account");
			}
		
		return false;
		
		}
	
	//check cms_main.customer_credential
		public boolean dbFindUniqueUserName(String userId){
				
			try{
				
				Statement statement = connection.createStatement();
				ResultSet results = statement.executeQuery("SELECT count (user_name) FROM cms_main.customer_credential where user_name = '" + userId + "'");
				
				while(results.next()){
					count = results.getInt("count");
					Log.info("number of userids for this account in the database: " + count);
					if(count==1){
						return true;
					 }
					}
				
				} catch(Exception e){
				System.err.println(e.getMessage());
				Log.error("Not able to retrieve record from postgresDB cms_main.customer_credential");
				}
			
			return false;		
		}
		
		//check cms_main.funding_source
				public boolean dbFindFundingSource(String email){
					try{
						
						Statement statement = connection.createStatement();
						ResultSet results = statement.executeQuery("SELECT customer_id FROM cms_main.contact where email ='" + email + "'");
					    
						while(results.next()){
							  customerID = results.getInt("customer_id");
							  Log.info("contact id in the database: " + customerID);
							
							}
						
						Statement statement2 = connection.createStatement();
						ResultSet results2 = statement2.executeQuery("SELECT funding_source_id FROM cms_main.funding_source where customer_id = '" + customerID + "'");
						
								
						while(results2.next()){
						  fundingID = results2.getInt("funding_source_id");
						  Log.info("funding id in the database: " + fundingID);
						  return true;
						 
						}
						
						}catch(Exception e){
						System.err.println(e.getMessage());
						Log.error("Not able to retrieve record from postgresDB cms_main.funding_source");
						}
					
					return false;
										
				}
				
						
				//check cms_main.address
				public boolean dbFindAddress(String email){
						
					try{
						
						Statement statement = connection.createStatement();
						ResultSet results = statement.executeQuery("SELECT customer_id FROM cms_main.contact where email ='" + email + "'");
					    
						while(results.next()){
							  customerID = results.getInt("customer_id");
							  Log.info("contact id in the database: " + customerID);
							
							}
						
						Statement statement2 = connection.createStatement();
						ResultSet results2 = statement2.executeQuery("SELECT address_id FROM cms_main.address where customer_id = '" + customerID + "'");
						
						while(results2.next()){
						  addressID = results2.getInt("address_id");
						  Log.info("address id in the database: " + addressID);
						  return true;
						 
						}
						
						}catch(Exception e){
						System.err.println(e.getMessage());
						Log.error("Not able to retrieve record from postgresDB cms_main.address");
						}
					
					return false;
					
					}
				
				//check cms_main.security_answer
				public boolean dbFindSecurityAnswer(String email){
						
						
					try{
						
						Statement statement = connection.createStatement();
						ResultSet results = statement.executeQuery("SELECT contact_id FROM cms_main.contact where email ='" + email + "'");
					    
						while(results.next()){
							  contactID = results.getInt("contact_id");
							  Log.info("contact id in the database: " + contactID);
							
							}
						
						Statement statement2 = connection.createStatement();
						ResultSet results2 = statement2.executeQuery("SELECT security_answer_id FROM cms_main.security_answer where contact_id = '" + contactID + "'");
						
						while(results2.next()){
						  securityID = results2.getInt("security_answer_id");
						  Log.info("security id in the database: " + securityID);
						  return true;
						 
						}
						
						}catch(Exception e){
						System.err.println(e.getMessage());
						Log.error("Not able to retrieve record from postgresDB cms_main.security_answer");
						}
					
					return false;
					
					}
				
				//check cms_main.phone
				public boolean dbFindPhone(String email){
						
					try{
						
						Statement statement = connection.createStatement();
						ResultSet results = statement.executeQuery("SELECT phone_id_1 FROM cms_main.contact where email ='" + email + "'");
					    
						while(results.next()){
							  phoneID = results.getInt("phone_id_1");
							  Log.info("phone id in the database: " + phoneID);
							
							}
						
						Statement statement2 = connection.createStatement();
						ResultSet results2 = statement2.executeQuery("SELECT phone_number FROM cms_main.phone where phone_id = '" + phoneID + "'");
						
						while(results2.next()){
						  phoneNo = results2.getString("phone_number");
						  Log.info("phone number in the database: " + phoneNo);
						  return true;
						 
						}
						
						}catch(Exception e){
						System.err.println(e.getMessage());
						Log.error("Not able to retrieve record from postgresDB cms_main.phone");
						}
					
					return false;
					
					}
				
				//check cms_main.credit_card
				public boolean dbFindFundingSourceCreditCard(String email){
					
					try{
						
						Statement statement = connection.createStatement();
						ResultSet results = statement.executeQuery("SELECT customer_id FROM cms_main.contact where email ='" + email + "'");
					    
						while(results.next()){
							  customerID = results.getInt("customer_id");
							  Log.info("contact id in the database: " + customerID);
							
							}
						
						Statement statement2 = connection.createStatement();
						ResultSet results2 = statement2.executeQuery("SELECT * FROM cms_main.credit_card inner join cms_main.funding_source on cms_main.funding_source.funding_source_id = cms_main.credit_card.funding_source_id where  cms_main.funding_source.customer_id = '" + customerID + "'");
							
						while(results2.next()){
						  ccID = results2.getInt("credit_card_id");
						  Log.info("CC id in the database: " + ccID);
						  return true;
						 
						}
						
						}catch(Exception e){
							System.err.println(e.getMessage());
							Log.error("Not able to retrieve record from postgresDB cms_main.credit_card");
						}
					
					return false;
										
				}
				
				//check oam_main.subsystem_account
				public boolean dbFindSubSystem(String email){
						
					try{
						
						Statement statement = connection.createStatement();
						ResultSet results = statement.executeQuery("SELECT customer_id FROM cms_main.contact where email ='" + email + "'");
					    
						while(results.next()){
							  customerID = results.getInt("customer_id");
							  Log.info("contact id in the database: " + customerID);
							
							}
						
						Statement statement2 = connection.createStatement();
						ResultSet results2 = statement2.executeQuery("SELECT subsystem_account_id from oam_main.subsystem_account inner join oam_main.account on oam_main.subsystem_account.account_id = oam_main.account.account_id where oam_main.account.customer_id = '" + customerID + "'");
						
						while(results2.next()){
						  subSystemID = results2.getInt("subsystem_account_id");
						  Log.info("subsystem id in the database: " + subSystemID);
						  return true;
						}
						
						
						}catch(Exception e){
						System.err.println(e.getMessage());
						Log.error("Not able to retrieve record from postgresDB oam_main.account");
						}
					
					return false;
					
					}
				
				//check oam_main.journal_entry
				public boolean dbFindJournalEntry(String email){
						
					try{
						
						Statement statement = connection.createStatement();
						ResultSet results = statement.executeQuery("SELECT customer_id FROM cms_main.contact where email ='" + email + "'");
					    
						while(results.next()){
							  customerID = results.getInt("customer_id");
							  Log.info("contact id in the database: " + customerID);
							
							}
						
						Statement statement2 = connection.createStatement();
						ResultSet results2 = statement2.executeQuery("SELECT account_id FROM oam_main.account where customer_id = '" + customerID + "'");
						
						while(results2.next()){
						  accountID = results2.getInt("account_id");
						  Log.info("account id in the database: " + accountID);
						 
						}
						
						Statement statement3 = connection.createStatement();
						ResultSet results3 = statement3.executeQuery("SELECT journal_entry_id from oam_main.journal_entry inner join oam_main.purse on oam_main.purse.purse_id = oam_main.journal_entry.purse_id where oam_main.purse.account_id = '" + accountID + "'");

						while(results3.next()){
							  journalID = results3.getInt("journal_entry_id");
							  Log.info("journal entry id in the database: " + journalID);
							  return true;
							}
							
						
						}catch(Exception e){
						System.err.println(e.getMessage());
						Log.error("Not able to retrieve record from postgresDB oam_main.purse");
						}
					
					return false;
					
					}


	public void dbDisconnect(){
		try {
			connection.close();
			Log.info("PostgresDB disconnected");
		} catch (SQLException e) {
			Log.error("Not able to disconnect from postgresDB");
		}
		
	}
	
}
