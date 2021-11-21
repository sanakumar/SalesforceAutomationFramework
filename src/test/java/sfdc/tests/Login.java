package sfdc.tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;
import sfdc.pages.LoginPage;
import sfdc.utilities.BasicUtilities;
import sfdc.utilities.ListenersUtilities;

@Listeners(ListenersUtilities.class)
public class Login extends BaseTest {
	long startTime=0;
	long endTime = 0;
	
//	60 --> chrome , 20 --> firefox , 15--> safari
	
	@Parameters("browser name")
	@BeforeSuite
	public void setUp(String browsername) throws IOException {
		
		initializeLogging();
		System.out.println("test1");
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
	}
	

	@BeforeMethod
	public void launchApp() throws IOException {
		//System.out.println("Before Method: "+dataUtil.readAccounts("prod.url"));		
		driver.get(dataUtil.readAccounts("prod.url"));
		logger.info("Launching app: "+ dataUtil.readAccounts("prod.url"));
		WebElement userName = driver.findElement(By.name("username"));
		bu.waitForElement(userName, driver);
	}
	

	
	public void logOutApp() {
		
		bu.waitForElement(loginPage.usernamemenu, driver);
		bu.clickOnElement(loginPage.usernamemenu);
		bu.clickOnElement(loginPage.logout);
		
	}
	

	public void loginToApp() throws IOException {
		
		bu.waitForElement(loginPage.eUsername, driver);
		Assert.assertTrue(bu.isElementVisible(loginPage.eUsername));
		bu.sendText(dataUtil.readAccounts("prod.username"), loginPage.eUsername);
		test.log(Status.INFO, "Username is entered");
		
		bu.isElementVisible(loginPage.ePassword);
		bu.sendText(dataUtil.readAccounts("prod.password"), loginPage.ePassword);
		test.log(Status.INFO, "password is entered");
		bu.clickOnElement(loginPage.eLoginButton);
		
	}
	
	
	/*@Test()
	public void loginTC01(Method name) throws IOException, InterruptedException {
		
		
		SoftAssert sa = new SoftAssert();
		bu.waitForElement(loginPage.eUsername, driver);
		Assert.assertTrue(bu.isElementVisible(loginPage.eUsername));
		bu.sendText(dataUtil.readAccounts("prod.username"), loginPage.eUsername);
		test.log(Status.INFO, "Username is entered");
		bu.isElementVisible(loginPage.ePassword);
		bu.sendText(dataUtil.readAccounts("prod.expireduserpassword"), loginPage.ePassword);
		bu.clickOnElement(loginPage.eLoginButton);
		bu.waitForElement(loginPage.eErrorMessage, driver);
		String actualErrorMessage = loginPage.eErrorMessage.getText();
		sa.assertEquals(actualErrorMessage, dataUtil.readValidationText("password.error"));
		bu.getScreenCapture(driver);
//		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
		sa.assertAll();
	}*/
	
	@Test()
	public void loginTC01() throws IOException {
		SoftAssert sa=new  SoftAssert();
		bu.waitForElement(loginPage.eUsername, driver);
		Assert.assertTrue(bu.isElementVisible(loginPage.eUsername));
		bu.sendText(dataUtil.readAccounts("prod.username"), loginPage.eUsername);
		test.log(Status.INFO, "Username is entered");
		logger.info("username is entered");
		bu.clickOnElement(loginPage.eLoginButton);
		String actualErrorMessage=loginPage.eEnterErrorMessage.getText();
		sa.assertEquals(actualErrorMessage,dataUtil.readValidationText("noPassword.error"));
		bu.getScreenCapture(driver);
		sa.assertAll();
		}
	
	
	/*@Test(dataProvider ="Expired Users", enabled = false)
	public void loginTC02(String username , String pass) {
		SoftAssert sa = new SoftAssert();
		WebElement userName = driver.findElement(By.name("username"));
		bu.isElementVisible(userName);
		bu.sendText(username, userName);
		WebElement password = driver.findElement(By.name("pw"));
//		Assert.assertEquals("mithun.r@tekarch.com", password.getText());
//		sa.assertEquals(pass, password.getText());
		bu.isElementVisible(password);
		bu.sendText(pass, password);
		WebElement loginButton = driver.findElement(By.name("Login"));
		bu.clickOnElement(loginButton);
		String expectedErrorMessage = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		String actualErrorMessage = driver.findElement(By.id("error")).getText();
		sa.assertEquals(actualErrorMessage, expectedErrorMessage);
//		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
		sa.assertAll();
		
	}*/
	
	@Test
	public void loginTC02() throws IOException {
		
		SoftAssert sa=new  SoftAssert();
		loginToApp();
		
		bu.getScreenCapture(driver);
		sa.assertAll();
		logger.info(" Test case 2 pass");
		logOutApp();
		}
	
	
	 @Test(priority=1)
	 public void loginTC03() throws IOException {
		    
		 	SoftAssert sa=new  SoftAssert();
			bu.waitForElement(loginPage.eUsername, driver);
			Assert.assertTrue(bu.isElementVisible(loginPage.eUsername));
			bu.sendText(dataUtil.readAccounts("prod.username"), loginPage.eUsername);
			test.log(Status.INFO, "Username is entered");
			bu.isElementVisible(loginPage.ePassword);
			bu.sendText(dataUtil.readAccounts("prod.password"), loginPage.ePassword);
			test.log(Status.INFO, "password is entered");			
			if(!bu.isElementSelected(loginPage.rememberme))
				bu.clickOnElement(loginPage.rememberme);
			bu.clickOnElement(loginPage.eLoginButton);
			bu.waitForElement(loginPage.usernamemenu, driver);
			bu.clickOnElement(loginPage.usernamemenu);
			bu.clickOnElement(loginPage.logout);
			
			bu.waitForElement(loginPage.eUsername, driver);
			Assert.assertTrue(bu.isElementVisible(loginPage.eUsername));
			
			String s=bu.readTextFromElement(loginPage.populatedeUserName);
			//driver.findElement(By.xpath("//*[@id='username']"));
			
			Assert.assertEquals(dataUtil.readAccounts("prod.username"), s);
			test.log(Status.INFO, "Username is populated");
			bu.getScreenCapture(driver);
			
			sa.assertAll();
			logger.info("Test case 3 pass");
	 }
	        
	 
	 @Test
	 public void loginTC04a() throws IOException {
		 	SoftAssert sa=new  SoftAssert();
			bu.waitForElement(loginPage.eUsername, driver);
			Assert.assertTrue(bu.isElementVisible(loginPage.eUsername));
			bu.sendText(dataUtil.readAccounts("prod.username"), loginPage.eUsername);
			test.log(Status.INFO, "Username is entered");
		    bu.clickOnElement(loginPage.forgotpassword);
		    bu.clickOnElement(loginPage.sendmail);
		    test.log(Status.INFO,"Email is sent to reset password");
		    bu.sendText(dataUtil.readAccounts("prod.email"),loginPage.sendmail);
		    sa.assertAll();
		    logger.info("Test case 4a is passed");
	 }
	 
	 
	 @Test
	 public void loginTC04b() throws IOException {
		 	SoftAssert sa=new  SoftAssert();
			bu.waitForElement(loginPage.eUsername, driver);
			Assert.assertTrue(bu.isElementVisible(loginPage.eUsername));
			bu.sendText(dataUtil.readAccounts("prod.expireduserusername"), loginPage.eUsername);
			test.log(Status.INFO, "Username is entered");
			bu.isElementVisible(loginPage.ePassword);
			bu.sendText(dataUtil.readAccounts("prod.expireduserpassword"), loginPage.ePassword);
			test.log(Status.INFO, "password is entered");
			bu.clickOnElement(loginPage.eLoginButton);
			bu.getScreenCapture(driver);
			String actualErrorMessage = loginPage.eErrorMessage.getText();
			sa.assertEquals(actualErrorMessage, dataUtil.readValidationText("password.error"));
			bu.getScreenCapture(driver);
			sa.assertAll();
			logger.info("Test case 4b is passed");
	 }
	 
	
	/*@DataProvider(name = "Expired Users")
	public Object[][] loginData(){
		
		return new Object[][] {{"mithun.r@tekarch.com","mithun123"},{"vandana@email.com","vandana123"}};
		
	}*/
	
	
//	@BeforeSuite
//	public void suiteLevelPreCondition() {
//		System.out.println("Suite started");
//	}
//	
//	@BeforeGroups
//	public void groupPreCondition() {
//		System.out.println("XYZZZZZ");
//	}
//	
////	It executes before starting any of the test in the class
//	@BeforeClass(description = "This is used to launch the application beofor running any test methods")
//	public void launchApp() {
//		System.out.println("AppLaunch function");
//	}
//	
//	@BeforeTest
//	public void demo() {
//		System.out.println("Before test demo/.....");
//	}
//	
////	It executes after finishing all of the test in the class
//	@AfterClass()
//	public void closeApp() {
//		System.out.println("App close function");
//	}
//
//	@BeforeMethod(onlyForGroups = {"Login", "Smoke"})
//	public void loginApp() {
//		System.out.println("AppLogin function");
//	}
//
//	@AfterMethod
//	public void logoutApp() {
//		System.out.println("App logout function");
//	}
//
//	// executes as a method without main function
//	@Test(priority = 0)
//	public void login_TC1() {
//		System.out.println("Logged 1TC");
//	}
//
//	@Test(priority = 0, groups = {"Login","Regression"})
//	public void login_TC2() {
//		System.out.println("Logged 2TC");
//	}
//
//	@Test(priority = 0, groups = {"Login","Sanity"})
//	public void login_TC3() {
//		System.out.println("Logged 3TC");
//	}
//
//	@Test(priority = 0,groups = {"Login","Sanity","Smoke"} )
//	public void login_TC4A() {
//		System.out.println("Logged in TC4A");
//	}
//
//	@Test(priority = 0)
//	public void login_TC4B() {
//		System.out.println("Logged in TC4B");
//	}
//
//	@Test(priority = -2, dependsOnMethods = "loginToApp", enabled = false)
//	public void goToHomePage() {
//		System.out.println("Navigated to Home");
//	}
//
//	@Test(priority = 2, enabled = false)
//	public void logOut() {
//		System.out.println("Logged out from App");
//	}

}
