package sfdc.tests;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import sfdc.pages.HomePage;
import sfdc.pages.LoginPage;
import sfdc.utilities.BasicUtilities;
import sfdc.utilities.ListenersUtilities;

@Listeners(ListenersUtilities.class)
public class Home extends BaseTest {
	
	long startTime=0;
	long endTime = 0;
	HomePage homePage;
	LoginPage loginPage;
	//Login login=null;
	
	@Parameters("browser name")
	@BeforeSuite
	public void setUp(String browser) throws IOException {
		
		initializeLogging();
		initializeReport();
		startTime = System.currentTimeMillis();
		System.out.println(System.currentTimeMillis());
		driver = getDriver(browser,false);
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
		loginPage=new LoginPage(driver);
		homePage = new HomePage(driver);
		  //login=new Login();
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
	//	Assert.assertTrue(loginPage.isLoginPageDisplayed());
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
	
	
	@Test()
	public void TC05() throws IOException {
		SoftAssert sa=new  SoftAssert();
		/*bu.waitForElement(loginPage.eUsername, driver);
		Assert.assertTrue(bu.isElementVisible(loginPage.eUsername));
		bu.sendText(dataUtil.readAccounts("prod.username"), loginPage.eUsername);
		test.log(Status.INFO, "Username is entered");
		bu.isElementVisible(loginPage.ePassword);
		bu.sendText(dataUtil.readAccounts("prod.password"), loginPage.ePassword);
		test.log(Status.INFO, "password is entered");
		bu.clickOnElement(loginPage.eLoginButton);*/
		loginToApp();
		bu.waitForElement(loginPage.usernamemenu, driver);
		Assert.assertTrue(bu.isElementVisible(loginPage.usernamemenu));
		bu.clickOnElement(loginPage.usernamemenu);
		Assert.assertTrue(bu.isElementVisible(homePage.eMyProfile));
		Assert.assertTrue(bu.isElementVisible(homePage.eMySettings));
		Assert.assertTrue(bu.isElementVisible(homePage.eDevConsole));
		Assert.assertTrue(bu.isElementVisible(homePage.eLogout));
		sa.assertAll();
		bu.clickOnElement(homePage.eLogout);
		bu.waitForElement(loginPage.eUsername,driver);
		Assert.assertTrue(bu.isElementVisible(loginPage.eUsername));
	
	}
	
	@Test
	public void TC06() throws IOException {
	/*	SoftAssert sa=new  SoftAssert();
		bu.waitForElement(loginPage.eUsername, driver);
		Assert.assertTrue(bu.isElementVisible(loginPage.eUsername));
		bu.sendText(dataUtil.readAccounts("prod.username"), loginPage.eUsername);
		test.log(Status.INFO, "Username is entered");
		bu.isElementVisible(loginPage.ePassword);
		bu.sendText(dataUtil.readAccounts("prod.password"), loginPage.ePassword);
		test.log(Status.INFO, "password is entered");
		bu.clickOnElement(loginPage.eLoginButton);*/
		loginToApp();
		SoftAssert sa=new  SoftAssert();
		bu.clickOnElement(loginPage.usernamemenu);
		bu.isElementVisible(homePage.eMyProfile);
		bu.isElementVisible(homePage.eMySettings);
		bu.isElementVisible(homePage.eDevConsole);
		bu.isElementVisible(homePage.eLogout);
		bu.clickOnElement(homePage.eMyProfile);
		bu.clickOnElement(homePage.eEditbutton);
		
		//About Window
		driver.switchTo().frame("contactInfoContentId");
		bu.clickOnElement(homePage.aboutTab);
		bu.clearText(homePage.elastName);
		bu.sendText(dataUtil.readAccounts("home.lastName"), homePage.elastName);
		bu.clickOnElement(homePage.eAboutSave);
		test.log(Status.INFO, "Lastname is added");
		
		//post tab
		bu.clickOnElement(homePage.ePostLink);
		driver.switchTo().frame(homePage.ePostFrame);
		bu.sendText(dataUtil.readAccounts("home.textforframe"), homePage.ePostTextArea);
		driver.switchTo().parentFrame();
		bu.clickOnElement(homePage.ePostShareButton);
		
		//For uploading File 
		bu.clickOnElement(homePage.eFilelink);
		bu.clickOnElement(homePage.eFileUpload);
		bu.sendText(dataUtil.readAccounts("home.filelink"),homePage.eFile);
		bu.clickOnElement(homePage.eFileShare);
		test.log(Status.INFO, "File is uploaded");
		
		// Add Photo
		Actions action = new Actions(driver);
        action.moveToElement(homePage.eProfilepicturelink).build().perform();
        driver.switchTo().frame(homePage.ephotoContent);
        bu.sendText(dataUtil.readAccounts("home.photolink"),homePage.ephotoUpload);
        
        
        // Photo Crop 
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('j_id0:uploadFileForm:uploadBtn').focus();");
        jse.executeScript("document.getElementById('j_id0:uploadFileForm:uploadBtn').click();");
        //driver.findElement(By.name("j_id0:j_id7:j_id9"));
        jse.executeScript("document.getElementsByName('j_id0:j_id7:j_id9')[0].focus();");
        test.log(Status.INFO,"Photo uploaded");
        logger.info("Test case 6 passed ");
        sa.assertAll();
	}
	
	
	
	@Test
	public void TC07() throws IOException {
		loginToApp();
		SoftAssert sa=new  SoftAssert();
		bu.waitForElement(loginPage.usernamemenu, driver);
		bu.clickOnElement(loginPage.usernamemenu);	
		bu.waitForElement(homePage.mySettings, driver);
		bu.clickOnElement(homePage.mySettings);
		bu.waitForElement(homePage.PersonalLink, driver);
		bu.clickOnElement(homePage.PersonalLink);
		bu.waitForElement(homePage.loginHistoryLink, driver);
		bu.clickOnElement(homePage.loginHistoryLink);
		bu.clickOnElement(homePage.loginHistoryDownloadLink);
		bu.clickOnElement(homePage.displayandlayout);
		bu.clickOnElement(homePage.customizeTabLink);
		
		//select from custom App
		bu.waitForElement(homePage.customApp, driver);
		Select select=new Select(homePage.customApp);
		select.selectByVisibleText("Salesforce Chatter");
		 test.log(Status.INFO,"salesforce chatter");
		
		//Email setup
		bu.waitForElement(homePage.emailSetup, driver);
		bu.clickOnElement(homePage.emailSetup);
		bu.clickOnElement(homePage.emailSettings);
		test.log(Status.INFO,"email settings");
		
		//Email name
		bu.waitForElement(homePage.emailName, driver);
		bu.clearText(homePage.emailName);
		bu.sendText(dataUtil.readAccounts("home.emailname"),homePage.emailName );
		bu.clearText(homePage.email);
		bu.sendText(dataUtil.readAccounts("home.email"), homePage.email);
		bu.clickOnElement(homePage.emailSave);
		driver.switchTo().alert().accept();
		logger.info("Test case 7 passed ");
	}
	
	@Test
	public void TC08() throws IOException {
		loginToApp();
		bu.clickOnElement(loginPage.usernamemenu);
		bu.waitForElement(homePage.eDevConsole, driver);
		bu.clickOnElement(homePage.eDevConsole);
		
		
	/*	String oldWindow=driver.getWindowHandle();
		Set<String> getAllWindows=driver.getWindowHandles();
		String[] getWindow=getAllWindows.toArray(new String[getAllWindows.size()]);
		driver.switchTo().window(getWindow[1]);
		driver.get(dataUtil.readAccounts());
		
		String actual=driver.getTitle();*/
		
		String winHandleBefore = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[@title='Developer Console (New Window)']")).click();
	
		test.log(Status.INFO,"Dev window closed ");
		for(String winHandle : driver.getWindowHandles())
		{
		    driver.switchTo().window(winHandle);
		}
		driver.close();
		driver.switchTo().window(winHandleBefore);
        logger.info("Test case 8 passed");
	}
	
	
	@Test
	public void TC09() throws IOException {
		SoftAssert sa=new  SoftAssert();
		loginToApp();
		bu.waitForElement(loginPage.usernamemenu, driver);
		bu.clickOnElement(loginPage.usernamemenu);
		bu.isElementVisible(homePage.eLogout);
		bu.clickOnElement(homePage.eLogout);
		bu.waitForElement(loginPage.eUsername, driver);
		Assert.assertTrue(bu.isElementVisible(loginPage.eUsername));
		logger.info("Test case 9 passed");
		sa.assertAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
