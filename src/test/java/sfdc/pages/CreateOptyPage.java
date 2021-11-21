package sfdc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOptyPage {
	
	public CreateOptyPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css="#Opportunity_Tab > a")
	public WebElement OpportunitiesTab;
	
	@FindBy(id="tryLexDialogX")
	public WebElement popUp;
	
	@FindBy(xpath="//*[@id='fcf']")
	public WebElement viewDropDown;
	
	@FindBy(xpath="//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input")
	public WebElement newOpportunity;
	
	@FindBy(xpath="//*[@id='opp3']")
	public WebElement optyNameTextBox;
	
	@FindBy(xpath="//*[@id='opp4']")
	public WebElement optyAccountNameBox;
	
	@FindBy(css="#ep > div.pbBody > div:nth-child(3) > table > tbody > tr:nth-child(2) > td:nth-child(4) > div > span > span > a")
	public WebElement optyClosedate;
	
	@FindBy(xpath="//*[@id='opp11']")
	public WebElement optyStage;
	
	@FindBy(xpath="//*[@id='opp12']")
	public WebElement optyProbability;
	
	@FindBy(xpath="//*[@id='opp6']")
	public WebElement optyLeadSource;
	
	@FindBy(xpath="//*[@id='opp17']")
	public WebElement optyPrimaryCampaignsource;
	
	@FindBy(xpath="//*[@id='topButtonRow']/input[1]")
	public WebElement optySave;
	
	@FindBy(xpath="//*[@id='toolsContent']/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[1]/a")
	public WebElement optyPipeline;
	
	@FindBy(xpath="//*[@id='toolsContent']/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[2]/a")
	public WebElement optyStuck;
	
	@FindBy(xpath="//*[@id='quarter_q']")
	public WebElement optyCurrent;
	
	@FindBy(xpath="//*[@id='open']")
	public WebElement optyOpen;
	
	@FindBy(xpath="//*[@id='lead_summary']/table/tbody/tr[3]/td/input")
	public WebElement optyRunReport;
	
	
	


	
}
