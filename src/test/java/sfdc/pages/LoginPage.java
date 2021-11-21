package sfdc.pages;

import org.apache.commons.logging.impl.Log4JLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

public class LoginPage extends BasePage {
	 
//	Pagefactory in selenium --> define Webelements and reusable functions of that page
//	Stale element exception
// init elements will initialize all the below elements
	
	
	public LoginPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="username")
	public WebElement eUsername;
	
	@FindBy(name = "pw")
	public WebElement ePassword;
	
	@FindBy(name = "Login")
	public WebElement eLoginButton;
	
	@FindBy(id = "error")
	public WebElement eErrorMessage;
	
	@FindBy(id="rememberUn")
	public WebElement rememberme;
	
	@FindBy(id="userNavLabel")
	public WebElement usernamemenu;
	
	@FindBy(xpath="//a[@title='Logout']")
	public WebElement logout;
	
	@FindBy(id="forgot_password_link")
	public WebElement forgotpassword;
	
	@FindBy(id="un")
	public WebElement sendmail;
	
	@FindBy(xpath="//*[@id='idcard-identity']")
	public WebElement populatedeUserName;
	
	@FindBy(id="error")
	public WebElement eEnterErrorMessage;
	
	
	
	
	public boolean isLoginPageDisplayed() {
		return true;
    }
		
		
	
	
	
	
	

		
}
