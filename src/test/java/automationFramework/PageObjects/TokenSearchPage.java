package automationFramework.PageObjects;

import java.awt.AWTException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import com.gargoylesoftware.htmlunit.WebClient;
import automationFramework.Utilities.Global;
import automationFramework.Utilities.Utils;
import org.openqa.selenium.JavascriptExecutor;

public class TokenSearchPage extends BasePage {

	WebDriverWait wait = new WebDriverWait(driver, 90);

	// Element Locators
	private static final String TOKENTYPE = ".//*[@id='searchToken_tokenType_sel']";
	private static final String SUBSYSTEM = ".//*[@id='searchToken_subsystem_sel']";
	private static final String BANKCARD_NUMBER = ".//*[@id='searchToken_bankCardNumber_txt']";
	private static final String EXPIRATION_MONTH = ".//*[@id='searchToken_bankCardExpiryMM_sel']";
	private static final String EXPIRATION_YEAR = ".//*[@id='searchToken_bankCardExpiryYYYY_sel']";
	private static final String SEARCH_TOKEN = ".//*[@id='searchToken_searchToken_btn']";
	private static final String NEW_SEARCH = ".//*[@id='searchToken_newSearch_btn']";
	private static final String ACCOUNT_NICKNAME = ".//*[@id='searchTokenResults_accountNickname_txt']";
	private static final String REGISTER = ".//*[@id='searchTokenResults_registerCustomer_btn']";
	private static final String RESULTS_NEW_SEARCH = ".//*[@id='searchTokenResults_newSearch_btn']";
	private static final String NO_RECORD_FOUND = "html/body/div/div/div/customer/search/fieldset/div[2]/div/div[2]/search-token-results/div/div/form/div[1]/p[1]";
	private static final String TOKEN_VERIFICATION_NICKNAME = ".//*[@id='searchTokenResults_accountNickname_txt']";
	private static final String TOKEN_VERIFICATION_REGISTER = ".//*[@id='searchTokenResults_registerCustomer_btn']";
	private static final String VIEW_DETAILS = ".//*[@id='tokenVerification_viewDetails_button']";
	private static final String INVALID_TOKEN = "html/body/div/div/div/customer/search/fieldset/div[2]/div/div[2]/search-token-results/div/div/div[1]/p[1]";
	private static final String INVALID_TOKEN_NEWSEARCH = ".//*[@id='accountCreationFailedMessageBox_newSearch_btn']";
	private static final String REQUIRED_FIELD_ERROR = ".//*[@id='v-required-alert']";
	private static final String LINK_ACCOUNT_CONFIRMATION = ".//*[@id='search_legend']/div";
	private static final String INVALID_TOKEN_REGISTER = ".//*[@id='accountCreationFailedMessageBox_registerCustomer_btn']";

	public TokenSearchPage(WebDriver driver) {
		super(driver);
	}

	public void selectTokenType(WebDriver driver, String value) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(TOKENTYPE)));
		mySelect.selectByValue(value);
	}

	public void selectSubsystem(WebDriver driver, String value) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(SUBSYSTEM)));
		mySelect.selectByValue(value);
	}

	public void selectExpMonth(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(EXPIRATION_MONTH)));
		mySelect.selectByIndex(1);
	}

	public void selectInvalidExpMonth(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(EXPIRATION_MONTH)));
		mySelect.selectByIndex(0);
	}

	public void selectExpYear(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(EXPIRATION_YEAR)));
		mySelect.selectByIndex(2);
	}

	public void selectInvalidExpYear(WebDriver driver) throws InterruptedException, AWTException {
		Select mySelect = new Select(driver.findElement(By.xpath(EXPIRATION_YEAR)));
		mySelect.selectByIndex(0);
	}

	public void enterBankNumber(WebDriver driver, String number) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BANKCARD_NUMBER)));
		driver.findElement(By.xpath(BANKCARD_NUMBER)).click();
		driver.findElement(By.xpath(BANKCARD_NUMBER)).sendKeys(number);
	}

	public void clickSearchToken(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SEARCH_TOKEN)));
		driver.findElement(By.xpath(SEARCH_TOKEN)).click();
	}

	public void clickNewSearch(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(NEW_SEARCH)));
		driver.findElement(By.xpath(NEW_SEARCH)).click();
	}

	public void clickInvalidTokenNewSearch(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_TOKEN_NEWSEARCH)));
		driver.findElement(By.xpath(INVALID_TOKEN_NEWSEARCH)).click();
	}

	public void enterNickName(WebDriver driver, String name) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ACCOUNT_NICKNAME)));
		driver.findElement(By.xpath(ACCOUNT_NICKNAME)).click();
		driver.findElement(By.xpath(ACCOUNT_NICKNAME)).sendKeys(name);
	}

	public void enterTokenVerificationNickName(WebDriver driver, String name)
			throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TOKEN_VERIFICATION_NICKNAME)));
		driver.findElement(By.xpath(TOKEN_VERIFICATION_NICKNAME)).click();
		driver.findElement(By.xpath(TOKEN_VERIFICATION_NICKNAME)).sendKeys(name);
	}

	public void clickRegisterCustomer(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(REGISTER)));
		driver.findElement(By.xpath(REGISTER)).click();
	}

	public void clickViewDetails(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(VIEW_DETAILS)));
		driver.findElement(By.xpath(VIEW_DETAILS)).click();
	}

	public void clickTokenVerificationRegisterCustomer(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TOKEN_VERIFICATION_REGISTER)));
		driver.findElement(By.xpath(TOKEN_VERIFICATION_REGISTER)).click();
	}

	public void clickResultsNewSearch(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RESULTS_NEW_SEARCH)));
		driver.findElement(By.xpath(RESULTS_NEW_SEARCH)).click();
	}
	
	public void clickInvalidTokenRegister(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INVALID_TOKEN_REGISTER)));
		driver.findElement(By.xpath(INVALID_TOKEN_REGISTER)).click();
	}

	public String getNoRecordFoundError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(NO_RECORD_FOUND)).getText();
	}

	public boolean isNoRecordFoundErrorDisplayed(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(NO_RECORD_FOUND)).isDisplayed();
	}

	public boolean isSearchTokenEnabled(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(SEARCH_TOKEN)).isEnabled();
	}
	
	public boolean isLinkAccountConfirmationDisplayed(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(LINK_ACCOUNT_CONFIRMATION)).isDisplayed();
	}

	public String getInavlidTokenError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(INVALID_TOKEN)).getText();
	}

	public String getBankNumber(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(BANKCARD_NUMBER)).getText();
	}

	public String getRequiredFieldError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(REQUIRED_FIELD_ERROR)).getText();
	}

}