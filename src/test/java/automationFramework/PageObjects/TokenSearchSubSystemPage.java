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

public class TokenSearchSubSystemPage extends BasePage {

	WebDriverWait wait = new WebDriverWait(driver, 90);

	// Element Locators

	private static final String SUBSYSTEM = "html/body/div/div/div/customer/workspace/fieldset/div[2]/div/div[2]/subsystem-details-transit/div/p-panel/div/div[1]/span";
	private static final String STATUS = ".//*[@id='tokens_list_tbl']/div/div/table/tbody/tr/td[3]/span";
	private static final String ACCOUNT_NUMBER = "html/body/div/div/div/customer/workspace/fieldset/div[2]/div/div[2]/subsystem-details-transit/div/p-panel/div/div[2]/div/token-pass-details/div/table/tbody/tr[1]/td[2]";
	private static final String TOKEN_INFORMATION = ".//*[@id='tokens_list_tbl']/div/div/table/tbody/tr/td[2]/span/div[1]";
	private static final String TOKEN_TYPE =  ".//*[@id='tokens_list_tbl']/div/div/table/tbody/tr/td[1]/span";
	
	
	public TokenSearchSubSystemPage(WebDriver driver) {
		super(driver);
	}

	public String getSubsystem(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(SUBSYSTEM)).getText();
	}
	
	public String getStatus (WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(STATUS)).getText();
	}
	
	public String getAccountNumber (WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ACCOUNT_NUMBER)).getText();
	}
	
	public String getTokenInfo (WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN_INFORMATION)).getText();
	}
	
	public String getTokenType (WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOKEN_TYPE)).getText();
	}

	
}