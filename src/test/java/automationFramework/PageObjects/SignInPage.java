package automationFramework.PageObjects;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.WebClient;

import automationFramework.Utilities.Global;
import automationFramework.Utilities.Utils;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage extends BasePage {


	// Element Locators
	private static final String USERNAME = "//*[@id='loginForm_username_txt']";	
	private static final String PASSWD = "//*[@id='loginForm_password_txt']";
	private static final String SIGNIN = "//*[@id='loginForm_submit_btn']/i";
	
	
	
	public SignInPage(WebDriver driver) {
		super(driver);
	}

	public void enterUsername(WebDriver driver, String username) throws InterruptedException, AWTException {
		
		driver.findElement(By.xpath(USERNAME)).sendKeys(username);
	}
	
	public void enterPasswd(WebDriver driver, String passwd) throws InterruptedException, AWTException {
		
		driver.findElement(By.xpath(PASSWD)).sendKeys(passwd);
	}
	
	public void clickSignin(WebDriver driver) throws InterruptedException, AWTException {
		
		driver.findElement(By.xpath(SIGNIN)).click();
	}	
}