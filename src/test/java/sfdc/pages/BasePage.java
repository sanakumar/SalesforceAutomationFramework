package sfdc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	
	 public static void main() {
		 WebDriver driver;
		 WebDriverManager.chromedriver().setup();
		 driver=new ChromeDriver();
		 
	 }

}
