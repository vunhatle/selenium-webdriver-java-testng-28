package homework;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Homework_05_TextBox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName = "Vu";
	String lastName = "Le";
	String fullName = firstName + " " + lastName;
	String email = "levu"+randomNumber()+"@gmail.com";
	
	private int randomNumber() {
		Random rand = new Random();
		int randomNumber = rand.nextInt(99999);
		return randomNumber;
	}
	
	private void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
		//driver.get("http://live.techpanda.org/");
	}
	/*
	@Test
	public void TC_01_Register_An_Account() {
		// Verify registering successfully
		driver.findElement(By.xpath("//div[@class = 'footer']//div[@class='links']//li[@class = 'first']/a[text()='My Account']")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//span[text()='Create an Account']/ancestor::a[@class='button']")).click();
		driver.findElement(By.cssSelector("input#firstname")).sendKeys("Vu");
		driver.findElement(By.cssSelector("input#lastname")).sendKeys("Le");
		driver.findElement(By.cssSelector("input#email_address")).sendKeys(email);
		driver.findElement(By.cssSelector("input#password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#confirmation")).sendKeys("123456");
		driver.findElement(By.xpath("//span[text()='Register']/ancestor::button[@title='Register']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("ul.messages>li.success-msg li>span")).getText(),"Thank you for registering with Main Website Store.");
		
		// Verify account information
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Change Password']/ancestor::p")).getText(), "Vu Le\n"+email+"\nChange Password");
	} */
	@Test
	public void TC_02_Register_An_Account() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		//Login with Admin/admin123
		driver.findElement(By.cssSelector("input[name = 'username'][placeholder ='Username']")).sendKeys("Admin");
		driver.findElement(By.cssSelector("input[name = 'password'][placeholder ='Password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[text()=' Login ']")).click();
		driver.findElement(By.xpath("//span[text() ='PIM']/ancestor::a[@class='oxd-main-menu-item']")).click();
		
		
	
	}



	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}