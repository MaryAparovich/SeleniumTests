package by.htp.library.example.mailru;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AutorizationTest {
	private WebDriver driver;

	@BeforeMethod
	public void initDriver() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Projects\\Java\\chromedriver.exe");
		driver = new ChromeDriver();
		Thread.sleep(2000);
	}

	@Test
	public void testAutorization() throws InterruptedException {
		driver.get("https://mail.ru");

		WebElement login = driver.findElement(By.id("mailbox:login"));
		login.sendKeys("marusy.net@mail.ru");
		WebElement pass = driver.findElement(By.id("mailbox:password"));
		pass.sendKeys("vfhecmrf1893335");

		WebElement loginButton = driver.findElement(By.id("mailbox:submit"));
		loginButton.click();

		//driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		Thread.sleep(2000);

		WebElement profile = driver.findElement(By.id("PH_user-email"));
		String profileText = profile.getText();
		Assert.assertEquals(profileText, "marusy.net@mail.ru");
	}

	@AfterMethod
	public void close() {
		driver.quit();
		System.out.println("AfterClass");
	}
}
