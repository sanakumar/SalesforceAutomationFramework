package sfdc.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsPage {

	public LeadsPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
   
	@FindBy(xpath="//*[@id='Lead_Tab']/a")
	public WebElement leadsTab;
	
	@FindBy(id="tryLexDialogX")
	public WebElement popUp;
	
	@FindBy(xpath="//*[@id=\"bodyCell\"]/div[1]/div[1]/div[1]/h2")
	public WebElement leadsHome;
	
	@FindBy(xpath="//*[@id='fcf']")
	public WebElement leadsDropDown;
	
	@FindBy(xpath="//*[@id='filter_element']/div/span/span[1]/input")
	public WebElement todaysleads;
	
	@FindBy(xpath="//*[@id=\"00B5f00000BpXyj_listSelect\"]")
	public WebElement todaysleadsdropdown;
	
	
	@FindBy(xpath="//*[@id='hotlist']/table/tbody/tr/td[2]/input")
	public WebElement leadsHomePage;
	
	
	
	
	
}
