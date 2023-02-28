package homework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

public class Homework_02_XPath_Css2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
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
		driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://cocolux.com/");
		
		
	}
	
	@Test
	public void TC_01_Display_Checkout() {
		driver.findElement(By.xpath("//div[@class  = 'header-cart']/a")).click();
		String checkOutPage = driver.getCurrentUrl();
		Assert.assertEquals(checkOutPage, "https://cocolux.com/checkout");
	
	}
	@Test
	public void TC_02_Number_Items_Empty() {
				driver.get("https://cocolux.com/checkout");
				String quantity = driver.findElement(By.xpath("//span[text()='Gió hàng']/preceding-sibling::div[@class='header-cart']/a/span[@class='header-cart-quantity']")).getText();
				int quantityInt = Integer.parseInt(quantity);
				Boolean emptyResult = driver.findElement(By.xpath("//tbody/tr/td[@colspan='5']/span[text()='Không có sản phẩm nào trong giỏ hàng của bạn.']")).isDisplayed();
				System.out.println(emptyResult);
				Assert.assertTrue(emptyResult);		
	}
	
	@Test
	public void TC_03_Multi_Number_Items() {
		
		int x = 11;
		int y = 0;
		for (int i = 1;i<=x;i++) {
			driver.get("https://cocolux.com/hang-moi-ve");
			//sleepInSecond(2);
			WebElement element = driver.findElement(By.xpath("//div[contains(@class,'col-6')]["+i+"]//div[@class='card--top']/a"));
			element.click();
			//sleepInSecond(2);
			if(driver.findElements(By.xpath("//span[text()='Giỏ hàng']/parent::button")).size() != 0) {
				driver.findElement(By.xpath("//span[text()='Giỏ hàng']/parent::button")).click();
				sleepInSecond(2);
				y++;
			}else {
				continue;
			}
		}
		
		System.out.println(y);
		driver.get("https://cocolux.com/");
		//sleepInSecond(2);
		String quantity = driver.findElement(By.xpath("//span[text()='Gió hàng']/preceding-sibling::div[@class='header-cart']/a/span[@class='header-cart-quantity']")).getText();
		int quantityInt = Integer.parseInt(quantity);
		System.out.println(quantity);
		Assert.assertEquals(quantityInt,y);

		//driver.findElement(By.xpath("//span[text()='Gió hàng']/preceding-sibling::div[@class='header-cart']/a")).click();
		//Assert.assertEquals(driver.findElements(By.xpath("//tbody/tr[not(contains(@class,'ccs-cart-table'))]")).size(), y);
		
	
	}
	
	/*

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	*/
}