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
import sfdc.pages.LeadsPage;
import sfdc.pages.LoginPage;
import sfdc.utilities.BasicUtilities;
import sfdc.utilities.ListenersUtilities;


@Listeners(ListenersUtilities.class)
public class Leads extends BaseTest{

	 
		long startTime=0;
		long endTime = 0;
		LoginPage loginPage;
		HomePage homePage;
		CreateOptyPage optyPage;
		LeadsPage leadsPage;
		
		
		

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
		    leadsPage = new LeadsPage(driver);
			
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
		 
		public void logOutApp() {
			
			bu.waitForElement(loginPage.usernamemenu, driver);
			bu.clickOnElement(loginPage.usernamemenu);
			bu.clickOnElement(loginPage.logout);
		}
		
		@Test
		public void leadsTC20() throws IOException {
			SoftAssert sa=new SoftAssert();
			loginToApp();
			bu.clickOnElement(leadsPage.leadsTab);
			bu.clickOnElement(leadsPage.popUp);
		    sa.assertTrue(bu.isElementVisible(leadsPage.leadsHome));
		    
		}
		@Test
		public void leadsTC21() throws IOException {
			SoftAssert sa=new SoftAssert();
			loginToApp();
			bu.clickOnElement(leadsPage.leadsTab);
			bu.clickOnElement(leadsPage.popUp);
			//Leads tab drop down
			bu.clickOnElement(leadsPage.leadsDropDown);
			
		}
		
		@Test
		public void leadsTC22() throws IOException {
			SoftAssert sa=new SoftAssert();
			loginToApp();
			bu.clickOnElement(leadsPage.leadsTab);
			bu.clickOnElement(leadsPage.popUp);
			//Leads tab drop down
			bu.clickOnElement(leadsPage.leadsDropDown);
			//todays leads
			bu.clickOnElement(leadsPage.todaysleads);
			
			//log out of application
			logOutApp();
			//login to app
			bu.clickOnElement(leadsPage.leadsTab);
			bu.clickOnElement(leadsPage.popUp);
			//Leads tab drop down
			bu.clickOnElement(leadsPage.leadsDropDown);
			bu.clickOnElement(leadsPage.todaysleads);
			//Leads dropdown validation
			sa.assertTrue(bu.isElementVisible(leadsPage.todaysleadsdropdown));
		}
         
		@Test
		public void leadsTC23() throws IOException {
			SoftAssert sa=new SoftAssert();
			loginToApp();
			bu.clickOnElement(leadsPage.leadsTab);
			bu.clickOnElement(leadsPage.popUp);
		}
		
	/*	@Test
		public void leadsTC24() throws IOException {
			SoftAssert sa=new SoftAssert();
			loginToApp();
			bu.clickOnElement(leadsPage.leadsTab);
			bu.clickOnElement(leadsPage.popUp);
			
			
		}*/
}
