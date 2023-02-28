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

public class Homework_04_WebElement {
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
		driver.get("https://automationfc.github.io/basic-form/index.html");
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
	public void TC_01_Verify_Element_Displayed() {
		// Check email is displayed
		if (driver.findElement(By.cssSelector("input#mail")).isDisplayed()) {
			System.out.println("Email is displayed");
			driver.findElement(By.cssSelector("input#mail")).sendKeys("vunhatle@gmail.com");
			sleepInSecond(2);
		} else {
			System.out.println("Email is no displayed");
		}
		// Check Under 18 is displayed
		if (driver.findElement(By.xpath("//label[text()='Under 18']/preceding-sibling::input[@id = 'under_18']")).isDisplayed()) {
			System.out.println("Under 18 is displayed");
			driver.findElement(By.xpath("//label[text()='Under 18']/preceding-sibling::input[@id = 'under_18']")).click();
		} else {
			System.out.println("Under 18 is not displayed");
		}
		// Check textarea Education

		if (driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()) {
			System.out.println("Education is displayed");
			driver.findElement(By.cssSelector("textarea#edu")).sendKeys("I am Le.");
		} else {
			System.out.println("Education is not displayed");
		}

		// Check User is not disabled
		if (driver.findElement(By.xpath("//div[@class='figcaption']/h5[text()='Name: User5']")).isDisplayed()) {
			System.out.println("The element is displayed.");
		} else {
			System.out.println("The element is not disabled.");
		}
	}

	@Test
	public void TC_02_Verify_Element_Enabled() {
		// Check the elements are enabled
		if (driver.findElement(By.xpath("//label[text() = 'Email:']/following-sibling::input[@id='mail']")).isEnabled()) {
			System.out.println("The element is enabled.");
		} else {
			System.out.println("The element is not enabled.");
		}

		if (driver.findElement(By.xpath("//label[text() = 'Under 18']/preceding-sibling::input[@id = 'under_18']")).isEnabled()) {
			System.out.println("The element is enabled.");
		} else {
			System.out.println("The element is not enabled.");
		}
		if (driver.findElement(By.cssSelector("textarea#edu")).isEnabled()) {
			System.out.println("the element is enabled");
		} else {
			System.out.println("The element is disabled");
		}

		if (driver.findElement(By.xpath("//label[text()='Job Role 01 - Single Dropdown:']/following-sibling::select[@id = 'job1']")).isEnabled()) {
			System.out.println("the element is enabled");
		} else {
			System.out.println("The element is disabled");
		}
		if (driver.findElement(By.xpath("//label[text()='Interests:']/following-sibling::input[@id='development']")).isEnabled()) {
			System.out.println("the element is enabled");
		} else {
			System.out.println("The element is disabled");
		}
		if (driver.findElement(By.xpath("//label[text()='Slider 01:']/following-sibling::input[@id='slider-1']")).isEnabled()) {
			System.out.println("the element is enabled");
		} else {
			System.out.println("The element is disabled");
		}
		// Check the elements are disabled
		if (driver.findElement(By.xpath("//label[text()='Password:']/following-sibling::input[@id='disable_password']")).isEnabled()) {
			System.out.println("The element is enabled.");
		} else {
			System.out.println("The element is disabled.");
		}

		if (driver.findElement(By.xpath("//label[text()='Radio button is disabled']/preceding-sibling::input[@id='radio-disabled']")).isEnabled()) {
			System.out.println("The element is enabled.");
		} else {
			System.out.println("The element is disabled.");
		}
		if (driver.findElement(By.xpath("//label[text()='Biography:']/following-sibling::textarea[@id='bio']")).isEnabled()) {
			System.out.println("the element is enabled");
		} else {
			System.out.println("The element is disabled");
		}
		
	}

	@Test
	public void TC_03_Verify_Element_Selected() {
		WebElement checkboxJava = driver.findElement(By.xpath("//label[text()='Languagues:']/following-sibling::label[text()='Java']/preceding-sibling::input[@id='java']"));
		checkboxJava.click();
		if(checkboxJava.isSelected()) {
			System.out.println("The checkbox is selected");
		} else {
			System.out.println("The checkbox is not selected");
		}
	}

	@Test
	public void TC_04_Register_Function() {
		driver.get("https://login.mailchimp.com/signup/");
		
	// Case 1: empty fields
		WebElement e1 = driver.findElement(By.xpath("//button[text()='Sign Up']"));
		js.executeScript("arguments[0].scrollIntoView(true);", e1);
		sleepInSecond(2);
		e1.click();	
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Email']/following-sibling::span[@class='invalid-error']")).getText(), 
		"An email address must contain a single @.");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Username']/following-sibling::span[@class='invalid-error']")).getText(), "Please enter a value");
	
	// Validate password
		//Case 1: empty password
		driver.get("https://login.mailchimp.com/signup/");
		sleepInSecond(2);
		driver.findElement(By.xpath("//label[text() = 'Email']/following-sibling::input[@id='email']")).sendKeys("automation@gmail.com");
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Sign Up' and @id = 'create-account-enabled']")));
		sleepInSecond(2);
		driver.findElement(By.xpath("//button[text()='Sign Up' and @id = 'create-account-enabled']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One lowercase character']/parent::li")).getAttribute("class"), "lowercase-char not-completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One uppercase character']/parent::li")).getAttribute("class"), "uppercase-char not-completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One number']/parent::li")).getAttribute("class"), "number-char not-completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One special character']/parent::li")).getAttribute("class"), "special-char not-completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = '8 characters minimum']/parent::li")).getAttribute("class"), "8-char not-completed");
		
		//Case 2: input number
		driver.findElement(By.xpath("//label[text()='Password']/parent::div[@class = 'line']/following-sibling::div[@class='line']/input[@id='new_password']")).clear();
		sleepInSecond(2);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div[@class = 'line']/following-sibling::div[@class='line']/input[@id='new_password']")).sendKeys("1234");
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Sign Up' and @id = 'create-account-enabled']")));
		sleepInSecond(2);
		driver.findElement(By.xpath("//button[text()='Sign Up' and @id = 'create-account-enabled']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One lowercase character']/parent::li")).getAttribute("class"), "lowercase-char not-completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One uppercase character']/parent::li")).getAttribute("class"), "uppercase-char not-completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One number']/parent::li")).getAttribute("class"), "number-char completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One special character']/parent::li")).getAttribute("class"), "special-char not-completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = '8 characters minimum']/parent::li")).getAttribute("class"), "8-char not-completed");
		
		//Case 2: input uppercase
		driver.findElement(By.xpath("//label[text()='Password']/parent::div[@class = 'line']/following-sibling::div[@class='line']/input[@id='new_password']")).clear();
		sleepInSecond(2);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div[@class = 'line']/following-sibling::div[@class='line']/input[@id='new_password']")).sendKeys("AA");
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Sign Up' and @id = 'create-account-enabled']")));
		sleepInSecond(2);
		driver.findElement(By.xpath("//button[text()='Sign Up' and @id = 'create-account-enabled']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One lowercase character']/parent::li")).getAttribute("class"), "lowercase-char not-completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One uppercase character']/parent::li")).getAttribute("class"), "uppercase-char completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One number']/parent::li")).getAttribute("class"), "number-char not-completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One special character']/parent::li")).getAttribute("class"), "special-char not-completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = '8 characters minimum']/parent::li")).getAttribute("class"), "8-char not-completed");
		
		//Case 2: input lowercase
		driver.findElement(By.xpath("//label[text()='Password']/parent::div[@class = 'line']/following-sibling::div[@class='line']/input[@id='new_password']")).clear();
		sleepInSecond(2);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div[@class = 'line']/following-sibling::div[@class='line']/input[@id='new_password']")).sendKeys("aaa");
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Sign Up' and @id = 'create-account-enabled']")));
		sleepInSecond(2);
		driver.findElement(By.xpath("//button[text()='Sign Up' and @id = 'create-account-enabled']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One lowercase character']/parent::li")).getAttribute("class"), "lowercase-char completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One uppercase character']/parent::li")).getAttribute("class"), "uppercase-char not-completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One number']/parent::li")).getAttribute("class"), "number-char not-completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One special character']/parent::li")).getAttribute("class"), "special-char not-completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = '8 characters minimum']/parent::li")).getAttribute("class"), "8-char not-completed");
		
		//Case 2: input special characters
		driver.findElement(By.xpath("//label[text()='Password']/parent::div[@class = 'line']/following-sibling::div[@class='line']/input[@id='new_password']")).clear();
		sleepInSecond(2);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div[@class = 'line']/following-sibling::div[@class='line']/input[@id='new_password']")).sendKeys("@@@");
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Sign Up' and @id = 'create-account-enabled']")));
		sleepInSecond(2);
		driver.findElement(By.xpath("//button[text()='Sign Up' and @id = 'create-account-enabled']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One lowercase character']/parent::li")).getAttribute("class"), "lowercase-char not-completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One uppercase character']/parent::li")).getAttribute("class"), "uppercase-char not-completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One number']/parent::li")).getAttribute("class"), "number-char not-completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One special character']/parent::li")).getAttribute("class"), "special-char completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = '8 characters minimum']/parent::li")).getAttribute("class"), "8-char not-completed");
		
		//Case 2: input over 8 characters
		driver.findElement(By.xpath("//label[text()='Password']/parent::div[@class = 'line']/following-sibling::div[@class='line']/input[@id='new_password']")).clear();
		sleepInSecond(2);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div[@class = 'line']/following-sibling::div[@class='line']/input[@id='new_password']")).sendKeys("Vu1234567");
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Sign Up' and @id = 'create-account-enabled']")));
		sleepInSecond(2);
		driver.findElement(By.xpath("//button[text()='Sign Up' and @id = 'create-account-enabled']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One lowercase character']/parent::li")).getAttribute("class"), "lowercase-char completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One uppercase character']/parent::li")).getAttribute("class"), "uppercase-char completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One number']/parent::li")).getAttribute("class"), "number-char completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = 'One special character']/parent::li")).getAttribute("class"), "special-char not-completed");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text() = '8 characters minimum']/parent::li")).getAttribute("class"), "8-char completed");
	
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}