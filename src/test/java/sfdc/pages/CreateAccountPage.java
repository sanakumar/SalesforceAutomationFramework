package sfdc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAccountPage {

	public CreateAccountPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String clickOnAccounts() {
		return "AccountName";
	}

	
	public void createNewView() {

	}
	
	@FindBy(xpath="//*[@id='Account_Tab']/a")
	public WebElement accountTab;
	
	@FindBy(id="tryLexDialogX")
	public WebElement popUp;
	
	@FindBy(xpath="//*[@id='hotlist']/table/tbody/tr/td[2]/input")
	public WebElement newAccountButton;
	
	@FindBy(xpath="//*[@id='acc2']")
	public WebElement accountNameButton;
	
	
	@FindBy(xpath="//*[@id=\"topButtonRow\"]/input[1]")
	public WebElement accountSaveButton;

	@FindBy(xpath="//*[@id=\"filter_element\"]/div/span/span[2]/a[2]")
	public WebElement createNewViewButton;
	
	@FindBy(xpath="//*[@id='fname']")
	public WebElement viewName;
	
	@FindBy(xpath="//*[@id='devname']")
	public WebElement viewUniqueName;
	
	@FindBy(xpath="//*[@id=\"editPage\"]/div[1]/table/tbody/tr/td[2]/input[1]")
	public WebElement newViewSaveButton;
	
	@FindBy(id="fcf")
	public WebElement viewDropDown;
	
	@FindBy(xpath="//*[@id='filter_element']/div/span/span[2]/a[1]")
	public WebElement editAccount;
	
	@FindBy(xpath="//*[@id='fcol1']")
	public WebElement fieldMenu;
	
	@FindBy(xpath="//*[@id='fop1']")
	public WebElement operatorMenu;
	
	@FindBy(xpath="//*[@id='fval1']")
	public WebElement value;
	
	@FindBy(css="#colselector_select_0")
	public WebElement LastActivity;
	
	@FindBy(xpath="//*[@id='colselector_select_0_right']/img")
	public WebElement addButton;
	
	@FindBy(xpath="//*[@id='editPage']/div[3]/table/tbody/tr/td[2]/input[1]")
	public WebElement save;
	
	
	@FindBy(xpath ="//input[(@name ='new')]")
	public WebElement newButton;
	
	@FindBy(xpath="//*[@id=\"toolsContent\"]/tbody/tr/td[2]/div/div/div/ul/li[4]/span/a")
	public WebElement mergeAccount;
	
	@FindBy(id="srch")
	public WebElement mergeTextArea;
	
	@FindBy(xpath="//*[@id=\"stageForm\"]/div/div[2]/div[4]/input[2]")
	public WebElement mergeNext;
	
	@FindBy(name="cid")
	public WebElement mergeCheckbox;
	
	@FindBy(name="goNext")
	public WebElement mergegoNext;
	
	@FindBy(name="save")
	public WebElement mergeSave;
	
}
