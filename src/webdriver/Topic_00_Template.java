package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_00_Template {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		} 

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		driver.manage().window().maximize();
		
	}
	// Email Address
	// <input type="email" autocapitalize="off" autocorrect="off" spellcheck="false" name="login[username]" 
	// value="" id="email" class="input-text required-entry validate-email" 
	// title="Email Address">
	
	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("email")).sendKeys("test@automation.com");;

	}
	/*

	@Test
	public void TC_02_Class() {
		
	}

	@Test
	public void TC_03_Name() {
		
	}
	
	@Test
	public void TC_04_Tagname() {
		
	}
	
	@Test
	public void TC_05_LinkText() {
		
	}
	
	@Test
	public void TC_06_Partial_LinkText() {
		
	}
	
	@Test
	public void TC_07_Css() {
		
	}

	@Test
	public void TC_08_XPath() {
		
	}
	*/
	/* @AfterClass
	public void afterClass() {
		
	}
	*/
}