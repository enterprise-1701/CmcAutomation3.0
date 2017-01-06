package automationFramework.PageObjects;


import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import com.gargoylesoftware.htmlunit.WebClient;
import automationFramework.Utilities.Global;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	// Element Locators
	        private static final String LAUNCH_PAD = "//*[@class='fa fa-rocket fa-lg cmc-main-icon']"; 
			private static final String NEXT_CONTACT = "//*[@class='fa fa-user fa-lg cmc-main-icon']";	
			private static final String WORKSPACE = "//*[@class='fa fa-desktop fa-lg cmc-main-icon']";
			private static final String SWITCH = "/html/body/div/div/div/customer/a/i";
			private static final String CREATE = "//*[@id='mySidenav']/a[2]/font";
			private static final String SEARCH = "//*[@id='mySidenav']/a[1]/font";
			private static final String HEADER_SEARCH = "//*[@id='customer_header_search_btn']/span";
			//private static final String IFRAME_SEARCH = "//*[@id='cmc-tab-panel']/tab[2]/div/div/iframe";
			private static final String IFRAME_SEARCH = "//*[@id='portal_customer_iframe']";
			private static final String HOME = "//*[@id='customer_header_home_btn']";
		
			
			private static final String NEXTFARE = ".//*[@id='header_nextfare_btn']";
			private static final String ABP = ".//*[@id='header_abp_btn']";
			private static final String PAYMENTAPP = ".//*[@id='header_paymentParent_btn']";
			private static final String PRODUCTCAT = ".//*[@id='header_prodcat_btn']";
			private static final String FINANCE = ".//*[@id='header_financeParent_btn']";
			private static final String REPORTS = ".//*[@id='header_sapEdge_btn']";
			private static final String CUSTOMER = "//*[@id='launchPad_customer_btn']/h4";
			private static final String CUSTOMER_HEADER = "//*[@id='header_customer_btn']";
			
			private static final String NEW_CUSTOMER = "//*[@id='customer_header_new_customer_btn']";
			
			private static final String MONITORING = ".//*[@id='header_monitoringParent_btn']";
			private static final String LINKACCOUNT = "//*[@id='viewCustomer_linkAccount_lnk']";
			private static final String CREATE_FUNDINGSOURCE = "//*[@id='viewCustomer_createFundingSource_lnk']";
			private static final String CREATE_CONTACT = "//*[@id='viewCustomer_createContact_lnk']";
			private static final String CREATE_ADDRESS = "//*[@id='viewCustomer_createAddress_lnk']";
			private static final String CREATE_ORDER = "//*[@id='viewCustomer_createOrder_lnk']";
			private static final String CREATE_ADJUSTMENT = "//*[@id='viewCustomer_createAdjustment_lnk']";
			private static final String MANAGE_REPLENISHMENT = "//*[@id='viewCustomer_manageReplenishment_lnk']";
			private static final String BALANCE_HISTORY = "//*[@id='viewCustomer_balanceHistory_lnk']";
			private static final String TRAVEL_HISTORY = "html/body/div/div/div/customer/workspace/fieldset/div[2]/div/div[2]/customer-details/travel-history/p-panel/div/div[1]/a/span";
			private static final String NOTIFICATIONS = "//*[@id='viewCustomer_notifications_lnk']";
			private static final String CUSTOMER_ACTIVITY = "//*[@id='viewCustomer_customerActivity_lnk']";
			private static final String CASES = "//*[@id='viewCustomer_cases_lnk']";
			
	public static WebDriver driver;
	//WebDriverWait wait = new WebDriverWait(driver, 15, 100);
	

	public BasePage(WebDriver driver) {
		BasePage.driver = driver;
	}
	
	public void getLandingPage(String url) throws Exception {
		try{
			driver.get(url);		
		} catch (Exception e) {
			Reporter.log("landing page not Found");
			throw (e);
		}
	}
	
	public void clickLpad(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(LAUNCH_PAD)).click();
	}
	
	public void clickNextContact(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(NEXT_CONTACT)).click();
	}
	
	public void clickWorkSpace(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(WORKSPACE)).click();
	}
	
	public void clickSwitch(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(SWITCH)).click();
	}
	
	public void clickCreateCustomer(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(CREATE)).click();
	}
	
	public void clickSearch(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(SEARCH)).click();
	}
	
	public void clickHeaderSearch(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(HEADER_SEARCH)).click();
	}
	
	public void switchToFrame(WebDriver driver) throws InterruptedException, AWTException{
		driver.switchTo().frame(driver.findElement(By.xpath(IFRAME_SEARCH)));
	}
	
	public void clickNextfare(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(NEXTFARE)).click();
	}
	
	public void clickPaymentAppPal(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(NEXTFARE)).sendKeys("PAL");	
	}
	
	public void clickPaymentAppCpa(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(NEXTFARE)).sendKeys("CPA");		
	}
	
	public void clickProductCat(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(PRODUCTCAT)).click();
	}
	
	public void clickFinance(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(FINANCE)).click();
	}
	
	public void clickReports(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(REPORTS)).click();
	}
	
	public void clickCustomer(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(CUSTOMER)).click();
	}
	
	public void clickCustomerHeader(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(CUSTOMER_HEADER)).click();
	}
	
	public void clickMonitor(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(MONITORING)).click();
	}
	
	public void clickAbp(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(ABP)).click();
	}
	
	public void clickPaymentApp(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(PAYMENTAPP)).click();
	}
	
	public void clickLinkAccount(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(LINKACCOUNT)).click();
	}
	
	public void clickCreateContact(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(CREATE_CONTACT)).click();
	}
	
	public void clickCreateAddress(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(CREATE_ADDRESS)).click();
	}
	
	public void clickCreateOrder(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(CREATE_ORDER)).click();
	}
	
	public void clickCreateAdjustment(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(CREATE_ADJUSTMENT)).click();
	}
	
	public void clickManageReplienishment(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(MANAGE_REPLENISHMENT)).click();
	}
	
	public void clickBalanceHistory(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(BALANCE_HISTORY)).click();
	}
	
	public void clickTravelHistory(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(TRAVEL_HISTORY)).click();
	}
	
	public void clickNotifications(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(NOTIFICATIONS)).click();
	}
	
	public void clickCustomerActivity(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(CUSTOMER_ACTIVITY)).click();
	}
	
	public void clickCases(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(CASES)).click();
	}
	
	public void clickNewCustomer(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(NEW_CUSTOMER)).click();
	}
	
	public void clickHome(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(HOME)).click();
	}
	
	public boolean isLinkAccountDisplayed(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(LINKACCOUNT)).isEnabled();
		}
	
	public boolean isCreateFundingSourceDisplayed(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(CREATE_FUNDINGSOURCE)).isDisplayed();
		}
	
	public boolean isCreateOrderDisplayed(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(CREATE_ORDER)).isDisplayed();
		}
	
	public String getCookie(String cookie) {
		driver.get(Global.URL1);
		Set<Cookie> cookies = driver.manage().getCookies();
		Cookie UID = driver.manage().getCookieNamed(cookie);
		return UID.getValue();
	}
	
	public static int getStatusCode(long appUserId) throws IOException {
		WebClient webClient = new WebClient();
		int code = webClient.getPage(Global.URL1).getWebResponse().getStatusCode();
		return code;
	}
	
	public static String getPageContent() throws IOException {
		WebClient webClient = new WebClient();
		String content = webClient.getPage(Global.URL1).getWebResponse().getContentAsString();
		return content;
	}
	
	public static void checkLinks(WebDriver driver) throws Exception {
		
		 List<WebElement> links=driver.findElements(By.tagName("a"));
	     System.out.println("total number of links: " +links.size());
	     
	     for (int i=0; i<links.size();i++){
	    	 if(!(links.get(i).getText().isEmpty())){
	    		
	    			 links.get(i).click();
	    			 driver.navigate().back();
	    			 links=driver.findElements(By.tagName("a"));    		 
	    	 }
	     }  	
	 }	     
}
