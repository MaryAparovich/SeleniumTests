package by.htp.library.example;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WaitExample {
	private WebDriver driver;

	
	@BeforeMethod
	public void initDriver() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Projects\\Java\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test
	public void testEditProfile() throws InterruptedException {
	
		driver.switchTo().frame("");
		
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS); //ждет загрузки стр
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.DAYS); // ждет появл эл-та
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		ExpectedCondition condition = ExpectedConditions.alertIsPresent();
		WebElement element = (WebElement)wait.until(condition);
		
		ExpectedCondition conditionClick = ExpectedConditions.elementToBeClickable(By.id("el1"));
		WebElement elementSecond = (WebElement)wait.until(conditionClick);
		
		//WebElement myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("myDynamicElement")));
	
	
	}
}
