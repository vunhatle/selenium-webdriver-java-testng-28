package homework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Homework_01_XPath_Css {
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
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	
		
	}
	
	@Test
	public void TC_01_Register_Empty_Data() {
		
		//Actions
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("");
		driver.findElement(By.xpath("//button[@type = 'submit']")).click();
		
		//Verify
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Vui lòng nhập số điện thoại.");
	}
	

	@Test
	public void TC_02_Register_Invalid_Email() {
		//refresh the page
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//Actions
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Vu Nhat Le");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("vunhatle.net");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("vunhatle.net");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Vunhatle101195");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("Vunhatle101195");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0973456456");
				
		//Verify
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Email nhập lại không đúng");
		
	}
		
	

	@Test
	public void TC_03_Register_Incorrect_Confirm_Email() {
		//refresh the page
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
				
		//Actions
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Vu Nhat Le");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("vunhatle@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("vunhatle.net");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Vunhatle101195");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("Vunhatle101195");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0973456456");
						
		//Verify
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Email nhập lại không đúng");
						
	}
	
	@Test
	public void TC_04_Password_Less_Than_06_Characters() {
		//refresh the page
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
						
		//Actions
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Vu Nhat Le");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("vunhatle@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("vunhatle@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0973456456");
								
		//Verify
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
				
	}
	
	@Test
	public void TC_05_Incorrect_Confirm_Password() {
		//refresh the page
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
								
		//Actions
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Vu Nhat Le");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("vunhatle@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("vunhatle@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("1234567");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0973456456");
										
		//Verify
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu bạn nhập không khớp");
				
	}
	
	@Test
	public void TC_06_Invalid_Phone_Number() {
		//refresh the page
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
										
		//Actions
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Vu Nhat Le");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("vunhatle@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("vunhatle@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Vunhatle101195");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("Vunhatle101195");
												
		//Verify
		// Total numbers are less than 10 (9)
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("097345645");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại phải từ 10-11 số.");
					
		// Total numbers are more than 11 (12)
		driver.findElement(By.xpath("//input[@id='txtPhone']")).clear();
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("097345645679");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại phải từ 10-11 số.");
												
		//Phone is wrong format
		driver.findElement(By.xpath("//input[@id='txtPhone']")).clear();
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0012345678");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
					
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}