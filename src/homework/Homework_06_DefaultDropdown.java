package homework;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Homework_06_DefaultDropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstname, lastname, day, month, year, email, company, password, confirmPassword;
	JavascriptExecutor js;
	
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
		js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		firstname = "Vu";
		lastname = "Le";
		day = "10";
		month = "November";
		year = "1995";
		email = "vule"+randomNumber()+"@gmail.com";
		company ="Nextop";
		password = "a12345678";
		confirmPassword = "a12345678";

	}
	
	@Test
	public void TC_01_Register_An_Account() {
		
		//Register an account
		driver.get("https://demo.nopcommerce.com/register");
		sleepInSecond(5);
		driver.findElement(By.xpath("//a[text() = 'Register']")).click();
		driver.findElement(By.cssSelector("input#gender-female")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstname);
		driver.findElement(By.id("LastName")).sendKeys(lastname);
		new Select(driver.findElement(By.cssSelector("select[name = 'DateOfBirthDay']"))).selectByVisibleText(day);
		sleepInSecond(2);
		
		
		List<WebElement> days = new Select(driver.findElement(By.cssSelector("select[name = 'DateOfBirthDay']"))).getOptions();
		Assert.assertEquals(days.size(),32);
		new Select(driver.findElement(By.cssSelector("select[name = 'DateOfBirthMonth']"))).selectByVisibleText(month);
		List<WebElement> months = new Select(driver.findElement(By.cssSelector("select[name = 'DateOfBirthMonth']"))).getOptions();
		Assert.assertEquals(months .size(), 13);
		new Select(driver.findElement(By.cssSelector("select[name = 'DateOfBirthYear']"))).selectByVisibleText(year);
		List<WebElement> years = new Select(driver.findElement(By.cssSelector("select[name = 'DateOfBirthYear']"))).getOptions();
		Assert.assertEquals(years.size(), 112);
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Company")).sendKeys(company);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(confirmPassword);
		driver.findElement(By.xpath("//button[text()='Register']")).click();
		sleepInSecond(5);
		
		// Verify registering successfully
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		
		// Login the account
		driver.findElement(By.xpath("//a[text()='Log in']")).click();
		sleepInSecond(5);
		driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
		driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		
		
		// Verify the registered account info
		driver.findElement(By.xpath("//div[@class='header-links']//a[text() = 'My account']")).click();
		sleepInSecond(10);
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"),firstname);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"),lastname);
		
		System.out.println(new Select(driver.findElement(By.cssSelector("select[name = 'DateOfBirthDay']"))).getFirstSelectedOption().getText());		
		Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name = 'DateOfBirthDay']"))).getFirstSelectedOption().getText(), day);
		Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name = 'DateOfBirthMonth']"))).getFirstSelectedOption().getText(), month);
		Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name = 'DateOfBirthYear']"))).getFirstSelectedOption().getText(), year);
		WebElement e1 = driver.findElement(By.id("Email"));
		js.executeScript("arguments[0].scrollIntoView();", e1);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), company);
		
		
		
	}



	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}