package automationFramework.Utilities;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.xml.DOMConfigurator;
import org.eclipse.jetty.util.log.Log;

public class UserData {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private static String name;
	private static String email;
	private static String phone;
	
	public static void setUsername(String userName){
		name = userName;
		Log.info("username being stored is " + name);
	
	}
	
	public static void setEmail(String userEmail){
		email = userEmail;
		Log.info("email being stored is " + email);
	}
	

	public static void setPhone(String userPhone){
		phone = userPhone;
		Log.info("phone being stored is " + phone);
	}

    public static String getUsername(){
    	Log.info("username being returned is " + name);
    	return name;
    }
    
    public static String getEmail(){
    	Log.info("email being returned is " + email);
    	return email;
    }
    
    public static String getPhone(){
    	Log.info("phone being returned is " + phone);
    	return phone;
    }
	
}
