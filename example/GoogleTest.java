package by.htp.library.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleTest {

	private WebDriver driver;
	private String str = "Java";

	@BeforeMethod
	public void initDriver() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Projects\\Java\\chromedriver.exe");
		driver = new ChromeDriver();
		Thread.sleep(2000);
	}

	@Test
	public void testGoogleSearch() throws InterruptedException {

		driver.get("https://google.by");
		WebElement someElement = driver.findElement(By.name("q"));
		someElement.sendKeys(str);
		someElement.submit();
		someElement = driver.findElement(By.name("q"));
		String value = someElement.getAttribute("value");
		Assert.assertEquals(value, str);
	}

	@AfterMethod
	public void close() {
		driver.quit();
		System.out.println("AfterClass");
	}
}
