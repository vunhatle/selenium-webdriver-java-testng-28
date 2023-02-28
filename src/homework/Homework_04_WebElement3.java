package homework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Homework_04_WebElement3 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor js;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/");
	}

	private void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test
	public void TC_01_Verify_Login() {
		//Case 1: Verify login with empty username and password
		driver.findElement(By.xpath("//div[@class = 'footer']//div[@class='links']//li[@class = 'first']/a[text()='My Account']")).click();
		driver.findElement(By.xpath("//span[text() = 'Login']//ancestor::button[@id='send2']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div.input-box>input#email~div#advice-required-entry-email")).getText(),"This is a required field.");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.input-box>input#email")).getAttribute("class"),"input-text required-entry validate-email validation-failed");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.input-box>input#pass~div#advice-required-entry-pass")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.input-box>input#pass")).getAttribute("class"),"input-text required-entry validate-password validation-failed");
		
		//Case 2: Verify login with invalid Email
		//xem lai video topic 22 - debug
	
	
	}

	

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}