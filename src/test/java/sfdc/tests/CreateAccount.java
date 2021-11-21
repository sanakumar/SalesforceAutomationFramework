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
import sfdc.pages.HomePage;
import sfdc.pages.LoginPage;
import sfdc.utilities.BasicUtilities;
import sfdc.utilities.ListenersUtilities;


@Listeners(ListenersUtilities.class)
public class CreateAccount extends BaseTest {
	long startTime=0;
	long endTime = 0;
	LoginPage loginPage;
	HomePage homePage;
	
	
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
		homePage = new HomePage(driver);
		createAccountPage=new CreateAccountPage(driver);
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
	public void createAccountTC10() throws IOException {
		loginToApp();
		bu.isElementVisible(createAccountPage.accountTab);
		bu.clickOnElement(createAccountPage.accountTab);
		test.log(Status.INFO, "AccountTab is clicked ");
		
		//close popup
		//Assert.assertTrue(createAccountPage.popUp.isDisplayed());
		bu.waitForElement(createAccountPage.popUp, driver);
		bu.clickOnElement(createAccountPage.popUp);
		
		//create new account
		bu.clickOnElement(createAccountPage.newAccountButton);
		bu.sendText(dataUtil.readAccounts("account.name"),createAccountPage.newAccountButton );
		//bu.sendText("sachin2", createAccountPage.accountNameButton);
		
		//click on save
		bu.clickOnElement(createAccountPage.accountSaveButton);
		
	}
	@Test	
	public void createAccountTC11( ) throws IOException {
		loginToApp();
		bu.waitForElement(createAccountPage.accountTab, driver);
		bu.isElementVisible(createAccountPage.accountTab);
		bu.clickOnElement(createAccountPage.accountTab);
		
		test.log(Status.INFO, "AccountTab is clicked ");
		
		//close popup
		//Assert.assertTrue(createAccountPage.popUp.isDisplayed());
		bu.waitForElement(createAccountPage.popUp, driver);
		bu.clickOnElement(createAccountPage.popUp);
		
		//click on create new view
		bu.waitForElement(createAccountPage.createNewViewButton, driver);
		bu.clickOnElement(createAccountPage.createNewViewButton);
		
		//click on viewName
		bu.waitForElement(createAccountPage.viewName, driver);
		bu.clickOnElement(createAccountPage.viewName);
		bu.sendText(dataUtil.readAccounts("account.viewname"),createAccountPage.viewName );
		
		//click on viewUniqueName
		bu.waitForElement(createAccountPage.viewUniqueName, driver);
		bu.clickOnElement(createAccountPage.viewUniqueName);
	    
		//click on save
		bu.waitForElement(createAccountPage.newViewSaveButton, driver);
		bu.clickOnElement(createAccountPage.newViewSaveButton);
		
	    //validate
		
	}
	
	
    @Test
    public void createAccountTC12() throws IOException {
    	loginToApp();
    	bu.waitForElement(createAccountPage.accountTab, driver);
		bu.isElementVisible(createAccountPage.accountTab);
		bu.clickOnElement(createAccountPage.accountTab);
		
		test.log(Status.INFO, "AccountTab is clicked ");
		
		//close popup
		//Assert.assertTrue(createAccountPage.popUp.isDisplayed());
		bu.waitForElement(createAccountPage.popUp, driver);
		bu.clickOnElement(createAccountPage.popUp);
		
		//WebElement selectMenu=driver.findElement(By.id("fcf"));
		//Select select=new Select(selectMenu);
		//select.selectByValue("New Last Week");
		//select.selectByVisibleText("New Last Week");
    	
		bu.selectElementFromDropdown(createAccountPage.viewDropDown,dataUtil.readAccounts("account.viewdropdownname"));
		bu.clickOnElement(createAccountPage.editAccount);
		
		bu.waitForElement(createAccountPage.viewName, driver);
		bu.clickOnElement(createAccountPage.viewName);
		bu.clearText(createAccountPage.viewName);
		bu.sendText(dataUtil.readAccounts("account.viewdropdownnewname"),createAccountPage.viewName );
		
		//click on viewUniqueName
		
		bu.waitForElement(createAccountPage.viewUniqueName, driver);
		bu.clickOnElement(createAccountPage.viewUniqueName);
		bu.clearText(createAccountPage.viewUniqueName);	
		bu.sendText(dataUtil.readAccounts("account.viewdropdownnewname"),createAccountPage.viewUniqueName);
		
		//Select Account Name
		bu.waitForElement(createAccountPage.fieldMenu, driver);
		bu.selectElementFromDropdown(createAccountPage.fieldMenu,dataUtil.readAccounts("account.fieldMenu"));
		bu.clickOnElement(createAccountPage.fieldMenu);
		
		//Select contains
		bu.waitForElement(createAccountPage.operatorMenu, driver);
		bu.selectElementFromDropdown(createAccountPage.operatorMenu,dataUtil.readAccounts("account.operatorMenu"));
		bu.clickOnElement(createAccountPage.operatorMenu);
		
	    //send value
		bu.waitForElement(createAccountPage.value, driver);
		bu.sendText(dataUtil.readAccounts("account.value"),createAccountPage.value);
		
		//select last activity
		bu.waitForElement(createAccountPage.LastActivity, driver);
		bu.selectElementFromDropdown(createAccountPage.LastActivity,dataUtil.readAccounts("account.fieldsDisplay"));
		
		////click add button
		bu.waitForElement(createAccountPage.addButton, driver);
		bu.clickOnElement(createAccountPage.addButton);
		
		//click on save
		bu.waitForElement(createAccountPage.save, driver);
		bu.clickOnElement(createAccountPage.save);
     }

    @Test
    public void createAccountTC13() throws IOException {
    	loginToApp();
    	bu.waitForElement(createAccountPage.accountTab, driver);
		bu.isElementVisible(createAccountPage.accountTab);
		bu.clickOnElement(createAccountPage.accountTab);
		
		test.log(Status.INFO, "AccountTab is clicked ");
		
		bu.waitForElement(createAccountPage.popUp, driver);
		bu.clickOnElement(createAccountPage.popUp);
		
		//merge account
		bu.waitForElement(createAccountPage.mergeAccount, driver);
		bu.clickOnElement(createAccountPage.mergeAccount);
		
	    bu.sendText(dataUtil.readAccounts("account.mergetext"),createAccountPage.mergeTextArea);
	    bu.clickOnElement(createAccountPage.mergeNext);
	    bu.waitForElement(createAccountPage.mergeCheckbox, driver);
	    
	    List<WebElement> checkBoxList = driver.findElements(By.name("cid"));
		//bu.waitForElement(null, driver);

		// select the first 2 elements of the result to merge
		if (!checkBoxList.get(0).isSelected())
			checkBoxList.get(0).click();
		if (!checkBoxList.get(1).isSelected())
			checkBoxList.get(1).click();

		// unselect all other elements other than first 2
		for (int i = 2; i < checkBoxList.size(); i++) {
			if (checkBoxList.get(i).isSelected()) {
				checkBoxList.get(i).click();
			}
		}
		bu.clickOnElement(createAccountPage.mergegoNext);
		bu.clickOnElement(createAccountPage.mergeSave);
		
		Alert alertBox = driver.switchTo().alert();
		alertBox.accept();
    }
    
    
}



