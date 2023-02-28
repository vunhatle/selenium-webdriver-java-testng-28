package homework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

public class Homework_04_WebElement2 {
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
		driver.manage().window().maximize();
		driver.get("https://login.mailchimp.com/signup/");
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
	public void TC_01_Verify_Click_Sign_Up() {
		driver.findElement(By.xpath("//label[text() = 'Email']/following-sibling::input[@id='email']")).sendKeys("automation@gmail.com");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement e1 = driver.findElement(By.xpath("//button[text()='Sign Up']"));
		js.executeScript("arguments[0].scrollIntoView();", e1);
		e1.click();
		sleepInSecond(2);
		
	}

	
	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}