package homework;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Homework_06_CustomDropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor js;
	WebDriverWait explicitWait;
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
		explicitWait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
	}
	
	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		//Choose select a speed
		selectCustomDropdown("//span[@id = 'speed-button']","//ul[@id = 'speed-menu']/li/div","Medium");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class= 'ui-selectmenu-text']")).getText(), "Medium");
		
		// Choose select a number
		selectCustomDropdown("//span[@id='number-button']/span[@class='ui-selectmenu-text']","//ul[@id='number-menu']/li/div","19");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "19");
		
	}
	@Test
	public void TC_02_EditableDropdown() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		sleepInSecond(10);
		//Choose select a speed
		selectEditableCustomDropdown("//input[@type = 'text']","//div[@role = 'listbox']//span","Austria");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='combobox']/div[@class='divider text']")).getText(), "Austria");
		
		
	}
	
	public void selectCustomDropdown(String parentnode, String childnode, String expectednode) {
		
		driver.findElement(By.xpath(parentnode)).click();
		sleepInSecond(2);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childnode)));
		List<WebElement> childnodes = driver.findElements(By.xpath(childnode));
		for(int i = 0; i < childnodes.size(); i++) {
			String itemText = childnodes.get(i).getText();
			System.out.println(itemText);
			if(itemText.equals(expectednode)) {
				js.executeScript("arguments[0].scrollIntoView(true);", childnodes.get(i));
				sleepInSecond(2);
				childnodes.get(i).click();
				break;
			}
		}
	}
	public void selectEditableCustomDropdown(String parentnode, String childnode, String expectednode) {
		driver.findElement(By.xpath(parentnode)).clear();
		sleepInSecond(5);
		driver.findElement(By.xpath(parentnode)).sendKeys(expectednode);
		sleepInSecond(2);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childnode)));
		List<WebElement> childnodes = driver.findElements(By.xpath(childnode));
		for(int i = 0; i < childnodes.size(); i++) {
			String itemText = childnodes.get(i).getText();
			//System.out.println(itemText);
			if(itemText.equals(expectednode)) {
				js.executeScript("arguments[0].scrollIntoView(true);", childnodes.get(i));
				sleepInSecond(2);
				childnodes.get(i).click();
				sleepInSecond(5);
				break;
			}
		}
	}


	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}