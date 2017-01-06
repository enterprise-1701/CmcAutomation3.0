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

public class TokenSearchCustomerDetailsPage extends BasePage {

	WebDriverWait wait = new WebDriverWait(driver, 90);

	// Element Locators

	private static final String FNAMETABLE = ".//*[@id='customerContacts_list_tbl']/div/div/table/tbody/tr/td[1]/span";
	private static final String LNAMETABLE = ".//*[@id='customerContacts_list_tbl']/div/div/table/tbody/tr/td[2]/span";
	private static final String EMAILTABLE = ".//*[@id='customerContacts_list_tbl']/div/div/table/tbody/tr/td[3]/span";
	private static final String PHONETABLE = ".//*[@id='customerContacts_list_tbl']/div/div/table/tbody/tr/td[4]/span/span";
	private static final String ACCOUNT = ".//*[@id='linkedAccount_list_div']/div/div[1]";
	private static final String LINKED_ACCOUNT = ".//*[@id='linkedAccount_list_div']/div/div[1]/strong";

	public TokenSearchCustomerDetailsPage(WebDriver driver) {
		super(driver);
	}

	public String getFirstName(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(FNAMETABLE)).getText();
	}

	public String getLastName(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(LNAMETABLE)).getText();
	}

	public String getEmail(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(EMAILTABLE)).getText();
	}

	public String getPhone(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(PHONETABLE)).getText();
	}

	public String getAccount(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ACCOUNT)).getText();
	}

	public void clickLinkedAccount(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(LINKED_ACCOUNT)).click();
	}

}