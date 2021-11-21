package sfdc.pages;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public HomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		}
		
	
	@FindBy(xpath="//a[@title='My Profile']")
	public WebElement eMyProfile;
	
	@FindBy(xpath="//a[@title='My Settings']")
	public WebElement eMySettings;
	
	@FindBy(xpath="//a[@title='Developer Console (New Window)']")
	public WebElement eDevConsole;
	
	@FindBy(xpath="//a[@title='Logout']")
	public WebElement eLogout;
		
	@FindBy(xpath="//*[@id='chatterTab']/div[2]/div[2]/div[1]/h3/div/div/a/img")
	public WebElement eEditbutton;
	
	@FindBy(xpath="//*[@id='aboutTab']/a")
	public WebElement eAboutTab;
	
	@FindBy(xpath="//*[@id='lastName']")
	public WebElement  eLastName;
	
	@FindBy(xpath="//*[@id='bottomButtonRow']/input[1]")
	public WebElement eEmailSavebutton;
	
	@FindBy(id="contactInfoContentId")
	public WebElement aboutWindow;
	
	@FindBy(xpath="//*[@id=\"aboutTab\"]/a")
	public WebElement aboutTab;
	
	@FindBy(xpath="//*[@id='lastName']")
	public WebElement elastName;
	
	@FindBy(xpath="//*[@id=\"TabPanel\"]/div/div[2]/form/div/input[1]")
	public WebElement eAboutSave;
	
	@FindBy(id="publisherAttachTextPost")
	public WebElement ePostLink;
	
	@FindBy(xpath="//*[@id=\"cke_39_contents\"]/iframe")
	public WebElement ePostFrame;
	
	@FindBy(css="body")
	public WebElement ePostTextArea;
	
	@FindBy(id="publishersharebutton")
	public WebElement ePostShareButton;
	
	@FindBy(xpath="//*[@id='publisherAttachContentPost']")
	public WebElement eFilelink;
	
	@FindBy(xpath="//*[@id='chatterUploadFileAction']")
	public WebElement eFileUpload;
	
	@FindBy(xpath="//input[@type='file']")
	public WebElement eFile;
	
	@FindBy(id="publishersharebutton")
	public WebElement eFileShare;
	
	@FindBy(xpath="//*[@id=\"photoSection\"]/span[2]/img[1]")
	public WebElement eProfilepicturelink;
	
	@FindBy(id="uploadLink")
	public WebElement eUploadLink;
	
	@FindBy(id="uploadPhotoContentId")
	public WebElement ephotoContent;
	
	@FindBy(xpath="//*[@id=\"j_id0:uploadFileForm:uploadInputFile\"]")
	public WebElement ephotoUpload;
	
	////////////////////////////////////////////
	
	@FindBy(xpath="//*[@id=\"userNav-menuItems\"]/a[2]")
	public WebElement mySettings;
	
	@FindBy(xpath="//*[@id=\"bodyCell\"]/div[2]/div[1]/div/div[2]/a[1]/span/span/span[3]")
	public WebElement PersonalLink;
	
	@FindBy(id="LoginHistory_font")
	public WebElement loginHistoryLink;
	
	@FindBy(xpath="//*[@id=\"RelatedUserLoginHistoryList_body\"]/div/a")
	public WebElement loginHistoryDownloadLink;
	
	@FindBy(xpath="//*[@id=\"DisplayAndLayout_font\"]")
	public WebElement displayandlayout;
	
	@FindBy(xpath="//*[@id=\"CustomizeTabs_font\"]")
	public WebElement customizeTabLink;
	
	@FindBy(xpath="//*[@id='p4']")
	public WebElement customApp;
	
	@FindBy(xpath="//*[@id=\"EmailSetup\"]/a")
	public WebElement emailSetup;
	
	@FindBy(xpath="//*[@id=\"EmailSettings_font\"]")
	public WebElement emailSettings;
	
	@FindBy(xpath="//*[@id='sender_name']")
	public WebElement emailName;
	
	@FindBy(xpath="//*[@id='sender_email']")
	public WebElement email;
	
	@FindBy(xpath="//*[@id='bottomButtonRow']/input[1]")
	public WebElement emailSave;
	
	@FindBy(xpath="//a[@title='Developer Console (New Window)']")
	public WebElement devWindowUrl;
	
	
	
	
	public void devWindowClose(WebDriver driver) {
		String winHandleBefore = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles())
		{
		    driver.switchTo().window(winHandle);
		}
		driver.close();
		driver.switchTo().window(winHandleBefore);
        }
	}
			
	
	


