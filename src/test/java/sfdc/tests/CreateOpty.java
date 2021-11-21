package sfdc.tests;




import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;
import sfdc.pages.CreateAccountPage;
import sfdc.pages.CreateOptyPage;
import sfdc.pages.HomePage;
import sfdc.pages.LoginPage;
import sfdc.utilities.BasicUtilities;
import sfdc.utilities.ListenersUtilities;


@Listeners(ListenersUtilities.class)
public class CreateOpty  extends BaseTest{

 
	long startTime=0;
	long endTime = 0;
	LoginPage loginPage;
	HomePage homePage;
	CreateOptyPage optyPage;
	
	
	
//	60 --> chrome , 20 --> firefox , 15--> safari
	
	@Parameters("browser name")
	@BeforeSuite
	public void setUp() throws IOException {
		
		initializeLogging();
		initializeReport();
		startTime = System.currentTimeMillis();
		System.out.println(System.currentTimeMillis());
		driver = getDriver("chrome",false);  //if the parameter is set true then the chrome will run in headlessmode.
		
	}

	@AfterSuite
	public void finishTest() {
		driver.close();
		report.flush();
		endTime = System.currentTimeMillis();
		System.out.println("Total time taken: "+ (endTime-startTime));
		logger.info("Total time taken: "+ (endTime-startTime));
		logger.info("extent report flushed");
	}
	
	@BeforeClass
	public void initializePageObjects() {
		loginPage = new LoginPage(driver);
	//	homePage = new HomePage(driver);
	//	createAccountPage=new CreateAccountPage(driver);
		optyPage=new CreateOptyPage(driver);
	}
	

	@BeforeTest
	public void launchApp() throws IOException {
		driver.get(dataUtil.readAccounts("prod.url"));
		logger.info("Launching app: "+ dataUtil.readAccounts("prod.url"));
		WebElement userName = driver.findElement(By.name("username"));
		bu.waitForElement(userName, driver);
	}
	
	
	//@BeforeMethod
	public void loginToApp() throws IOException {
		Assert.assertTrue(loginPage.isLoginPageDisplayed());
		bu.isElementVisible(loginPage.eUsername);
		test.log(Status.INFO,"Username entered");
		bu.sendText(dataUtil.readAccounts("prod.username"), loginPage.eUsername);
		bu.isElementVisible(loginPage.ePassword);
		bu.sendText(dataUtil.readAccounts("prod.password"), loginPage.ePassword);
		bu.clickOnElement(loginPage.eLoginButton);
	}	
	
	@Test
	public void createOptyTC15() throws IOException {
		loginToApp();
		bu.waitForElement(optyPage.OpportunitiesTab, driver);
		bu.clickOnElement(optyPage.OpportunitiesTab);
		bu.waitForElement(optyPage.popUp, driver);
		bu.clickOnElement(optyPage.popUp);
		bu.waitForElement(optyPage.viewDropDown, driver);
		bu.clickOnElement(optyPage.viewDropDown);
		
		//validation
		//String[] opportunities= {"Closing Next Month","Closing This Month","My Opportunities","New This Week","Recently viewed Opportunities","Won"};
	
		
		}
	@Test
	public void createOptyTC16() throws IOException {
		loginToApp();
		bu.waitForElement(optyPage.OpportunitiesTab, driver);
		bu.clickOnElement(optyPage.OpportunitiesTab);
		bu.waitForElement(optyPage.popUp, driver);
		bu.clickOnElement(optyPage.popUp);
		
		bu.clickOnElement(optyPage.newOpportunity);
		bu.sendText(dataUtil.readAccounts("opty.name"), optyPage.optyNameTextBox);
		bu.sendText(dataUtil.readAccounts("opty.accountname"), optyPage.optyAccountNameBox);
		//Stage
		bu.selectElementFromDropdown(optyPage.optyStage, "Needs Analysis");
		//probability
		bu.clearText(optyPage.optyProbability);
		bu.sendText(dataUtil.readAccounts("opty.probabiltypercent"), optyPage.optyProbability);
		//Lead source
		
		bu.selectElementFromDropdown(optyPage.optyLeadSource,"Purchased List");
		//primary campaign source
		bu.sendText(dataUtil.readAccounts("opty.campaignsource"),optyPage.optyPrimaryCampaignsource);
		//save
        bu.clickOnElement(optyPage.optySave);
        
	}
	@Test
	
	public void createOptyTC17() throws IOException {
		loginToApp();
		bu.waitForElement(optyPage.OpportunitiesTab, driver);
		bu.clickOnElement(optyPage.OpportunitiesTab);
		bu.waitForElement(optyPage.popUp, driver);
		bu.clickOnElement(optyPage.popUp);
		
		//opportunitiespipeline
		bu.clickOnElement(optyPage.optyPipeline);
		//validation
		//  #noTableContainer > div > div.bPageTitle > div.ptBody > div.content > h1
        
	}
	@Test
	
	public void createOptyTC18() throws IOException {
		loginToApp();
		bu.waitForElement(optyPage.OpportunitiesTab, driver);
		bu.clickOnElement(optyPage.OpportunitiesTab);
		bu.waitForElement(optyPage.popUp, driver);
		bu.clickOnElement(optyPage.popUp);
		
		//click on stuck opportunities link
		bu.clickOnElement(optyPage.optyStuck);
		
		
	}
	@Test
	public void createoptyTC19() throws IOException {
		loginToApp();
		bu.waitForElement(optyPage.OpportunitiesTab, driver);
		bu.clickOnElement(optyPage.OpportunitiesTab);
		bu.waitForElement(optyPage.popUp, driver);
		bu.clickOnElement(optyPage.popUp);
		
		//current FQ
		bu.selectElementFromDropdown(optyPage.optyCurrent,dataUtil.readAccounts("opty.currentFQ"));
		//Current FQ and All opportunities
		bu.selectElementFromDropdown(optyPage.optyOpen,dataUtil.readAccounts("opty.all"));
		//click on run report
		bu.clickOnElement(optyPage.optyRunReport);
		//click on opportunities tab again
		bu.clickOnElement(optyPage.OpportunitiesTab);
		bu.waitForElement(optyPage.popUp, driver);
		bu.clickOnElement(optyPage.popUp);
		//Current FQ and open opportunities
		bu.selectElementFromDropdown(optyPage.optyCurrent,dataUtil.readAccounts("opty.currentFQ"));
		bu.selectElementFromDropdown(optyPage.optyOpen,dataUtil.readAccounts("opty.open"));
		//click on run report
		bu.clickOnElement(optyPage.optyRunReport);
		//click on opportunities tab again
		bu.clickOnElement(optyPage.OpportunitiesTab);
		bu.waitForElement(optyPage.popUp, driver);
		bu.clickOnElement(optyPage.popUp);
		//Next FQ
		bu.selectElementFromDropdown(optyPage.optyCurrent,dataUtil.readAccounts("opty.fq"));
		//All opportunities
		bu.selectElementFromDropdown(optyPage.optyCurrent,dataUtil.readAccounts("opty.all"));
		//click on run report
		bu.clickOnElement(optyPage.optyRunReport);
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
