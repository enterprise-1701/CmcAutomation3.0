package automationFramework.PageObjects;


import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import automationFramework.Utilities.Global;


public class LinkAccountPage extends BasePage {

	WebDriverWait wait = new WebDriverWait(driver, 90);

	// Element Locators
	private static final String AGENCY = "//*[@id='linkAccountForm_agencyType_select']";	
	private static final String TRAVELMODE = "//*[@id='linkAccountForm_travelMode_select']";
	private static final String TOKENTYPE = "//*[@id='linkAccountForm_tokenType_select']";
	//private static final String CANCEL = "//*[@id='linkAccountForm_formCancel_button']";
	private static final String CANCEL = ".//*[@id='searchToken_newSearch_btn']";
	private static final String BANKNUMBER = ".//*[@id='searchToken_bankCardNumber_txt']";	
	private static final String BANKMONTH = ".//*[@id='searchToken_bankCardExpiryMM_sel']";
	private static final String BANKYEAR = ".//*[@id='searchToken_bankCardExpiryYYYY_sel']";

	private static final String CONTINUE = "//*[@id='linkAccountForm_formSubmit_button']";
	private static final String NICKNAME = "//*[@id='linkAccountForm_nickname_text']";
	private static final String LINKYES = "//*[@id='linkAccountForm_subsystemLinkSubmit_button']";	
	private static final String LINKNO = "//*[@id='linkAccountForm_cancelSubsystemLink_button']";
	private static final String BALANCE = "//*[@id='accountBalance_totalBalance_div']";
	
	private static final String DATETIME = "//*[@id='linkAccountVerify_verification_table']/div/div[1]/table/tbody/tr/td[1]/span";
	private static final String TRANSACTION = "//*[@id='linkAccountVerify_verification_table']/div/div[1]/table/tbody/tr/td[2]/span";
	private static final String LOCATION = "//*[@id='linkAccountVerify_verification_table']/div/div[1]/table/tbody/tr/td[3]/span";
	private static final String PRODUCT = "//*[@id='linkAccountVerify_verification_table']/div/div[1]/table/tbody/tr/td[4]/span";
	private static final String AMOUNT = "//*[@id='linkAccountVerify_verification_table']/div/div[1]/table/tbody/tr/td[5]/span";
	private static final String LINKPANEL ="//*[@id='linkAccountVerify_verify_panel']/div/div[1]/span";
	private static final String T_LINKACCOUNT_AUTHORITY = "//*[@id='linkedAccount_list_div']/div/div/strong";
	private static final String T_LINKACCOUNT_ACCOUNT = "//*[@id='linkedAccount_list_div']/div/div/span";
	private static final String VIEW_LINKED = "//*[@id='linkAccountVerify_verify_button']";
	private static final String ALREADY_LINKED = "//*[@id='linkAccountVerify_error_panel']/div/div[1]/span";
	private static final String RETRY = "//*[@id='linkAccountVerify_alreadyLinkedRetry_button']";
	private static final String RETRY_NO = "//*[@id='linkAccountVerify_alreadyLinkedCancel_button']";
	private static final String ACCT_NOT_ELGIBLE = "//*[@id='linkAccountVerify_error_panel']/div/div[1]/span";
	private static final String RETRY_NO_ACCT_NOT_ELGIBLE = "//*[@id='linkAccountVerify_accountClosedCancel_button']";
	private static final String RETRY_ACCT_NOT_ELGIBLE = "//*[@id='linkAccountVerify_accountClosedRetry_button']";
	private static final String UPDATED_BALANCE = "//*[@id='accountBalance_totalBalance_div']";
	private static final String SEARCH_TOKEN = ".//*[@id='searchToken_searchToken_btn']";
	private static final String LINKACCOUNT_FAILURE = ".//*[@id='linkAccountVerify_error_panel']/div/div[1]/span";
	
	public LinkAccountPage(WebDriver driver) {
		super(driver);
	}

	public void selectAgency(WebDriver driver) throws InterruptedException, AWTException{
		Select mySelect= new Select(driver.findElement(By.xpath(AGENCY)));
		mySelect.selectByIndex(1);
	}
	
	public void selectTravelMode(WebDriver driver) throws InterruptedException, AWTException{
		Select mySelect= new Select(driver.findElement(By.xpath(TRAVELMODE)));
		mySelect.selectByIndex(1);
	}
	
	public void selectTokenType(WebDriver driver) throws InterruptedException, AWTException{
		Select mySelect= new Select(driver.findElement(By.xpath(TOKENTYPE)));
		mySelect.selectByIndex(1);
	}
	
	public void enterBankAccount(WebDriver driver, String number) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(BANKNUMBER)).sendKeys(number);
	}
	
	public void selectExpMonth(WebDriver driver) throws InterruptedException, AWTException{
		Select mySelect= new Select(driver.findElement(By.xpath(BANKMONTH)));
		mySelect.selectByIndex(1);
	}
	
	public void selectExpYear(WebDriver driver) throws InterruptedException, AWTException{
		Select mySelect= new Select(driver.findElement(By.xpath(BANKYEAR)));
		mySelect.selectByIndex(3);
	}
	
	public void enterNickName(WebDriver driver, String nname) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(NICKNAME)).sendKeys(nname);
	}
	
	public void clickContinue(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(CONTINUE)).click();
	}
	
	public void clickCancel(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(CANCEL)).click();
	}
	
	public void clickYes(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(LINKYES)).click();
	}
	
	public void clickNo(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(LINKNO)).click();
	}
	
	public void clickView(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(VIEW_LINKED)).click();
	}
	
	public void clickRetry(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(RETRY)).click();
	}
	
	public void clickNoRetry(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(RETRY_NO)).click();
	}
	
	public void clickRetryAcctNotElgible(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(RETRY_ACCT_NOT_ELGIBLE)).click();
	}
	
	public void clickNoRetryAcctNotElgible(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(RETRY_NO_ACCT_NOT_ELGIBLE)).click();
	}
	
	public void clickSearchToken(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(SEARCH_TOKEN)).click();
	}
	
	public String getBalance(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(BALANCE)).getText();	
	}
	
	public String getUpdatedBalance(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(UPDATED_BALANCE)).getText();	
	}
	
	public String getDateTime(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(DATETIME)).getText();	
	}
	
	public String getTransaction(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(TRANSACTION)).getText();	
	}
	
	public String getLocation(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(LOCATION)).getText();	
	}
	
	public String getProduct(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(PRODUCT)).getText();	
	}
	
	public String getAmount(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(AMOUNT)).getText();	
	}
	
	public String getAlreadyLinkedError(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(ALREADY_LINKED)).getText();	
	}
	
	public String getAccountNotElgible(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(ACCT_NOT_ELGIBLE)).getText();	
	}
	
	public String getLinkAccountAuthority(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(T_LINKACCOUNT_AUTHORITY)).getText();	
	}
	
	public String getExternalAccountId(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(T_LINKACCOUNT_ACCOUNT)).getText();	
	}
	
	public boolean isLinkPanelDisplayed(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(LINKPANEL)).isDisplayed();	
	}
	
	public boolean isViewLinkDisplayed(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(VIEW_LINKED)).isDisplayed();	
	}
	
	public boolean isAgencyDisplayed(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(AGENCY)).isDisplayed();	
	}
	
	public boolean isLinkFailureDisplayed(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(LINKACCOUNT_FAILURE)).isDisplayed();	
	}
	
	public boolean isContinueEnabled(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(CONTINUE)).isEnabled();	
	}
	
	public boolean isSearchTokenEnabled(WebDriver driver) throws InterruptedException, AWTException{
		return driver.findElement(By.xpath(SEARCH_TOKEN)).isEnabled();	
	}
}