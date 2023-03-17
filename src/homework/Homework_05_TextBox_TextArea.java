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
	String username = "levu"+randomNumber();
	String password = "Vule123#";
	String passport = "123456789";
	String comment = "This is the first comment\nVu Nhat Le";
	
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

	}
	/*
	@Test
	public void TC_01_Register_An_Account() {
		// Verify registering successfully
		//driver.get("http://live.techpanda.org/");
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
		
		//Login with Admin/admin123
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.cssSelector("input[name = 'username'][placeholder ='Username']")).sendKeys("Admin");
		driver.findElement(By.cssSelector("input[name = 'password'][placeholder ='Password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[text()=' Login ']")).click();
		driver.findElement(By.xpath("//span[text() ='PIM']/ancestor::a[@class='oxd-main-menu-item']")).click();
		sleepInSecond(4);
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInSecond(10);
		
		// register an account
		driver.findElement(By.cssSelector("input[name = 'firstName']")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input[name = 'lastName']")).sendKeys(lastName);
		String employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div[@class='oxd-input-group__label-wrapper']/following-sibling::div/input[@class='oxd-input oxd-input--active']")).getAttribute("value");
		//System.out.println(employeeID);
		driver.findElement(By.cssSelector("input[type='checkbox']~span")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(username);
		driver.findElement(By.xpath("//label[text() = 'Password']/parent::div/following-sibling::div/input[@type = 'password']")).sendKeys(password);
		driver.findElement(By.xpath("//label[text() = 'Confirm Password']/parent::div/following-sibling::div/input[@type = 'password']")).sendKeys(password);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		sleepInSecond(10);
		
		// verify personal details
		Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getAttribute("value"),firstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		
		// Add immigration
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);
		driver.findElement(By.xpath("//h6[text() = 'Assigned Immigration Records']/parent::div/button[contains(string(),'Add')]")).click();
		sleepInSecond(5);
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passport);
		driver.findElement(By.cssSelector("textarea[placeholder='Type Comments here']")).sendKeys(comment);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		sleepInSecond(5);
		
		// Verify edit information
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']/parent::button")).click();
		sleepInSecond(10);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passport);
		Assert.assertEquals(driver.findElement(By.cssSelector("textarea[placeholder='Type Comments here']")).getAttribute("value"), comment);
		
		//Log out
		driver.findElement(By.cssSelector("span.oxd-userdropdown-tab>img")).click();
		sleepInSecond(4);
		driver.findElement(By.xpath("//a[text() = 'Logout']")).click();
		
		// Login with the new account
		driver.findElement(By.cssSelector("input[name = 'username'][placeholder ='Username']")).sendKeys(username);
		driver.findElement(By.cssSelector("input[name = 'password'][placeholder ='Password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()=' Login ']")).click();
		
		// Verify personal details
		driver.findElement(By.xpath("//span[text()='My Info']")).click();
		sleepInSecond(8);
		Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getAttribute("value"),firstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);
		
	
	}



	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}