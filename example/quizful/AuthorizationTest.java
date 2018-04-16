package by.htp.library.example.quizful;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AuthorizationTest {
	private WebDriver driver;

	@BeforeMethod
	public void initDriver() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Projects\\Java\\chromedriver.exe");
		driver = new ChromeDriver();
		Thread.sleep(2000);
	}

	@Test
	public void testCorrectAuthorization() throws InterruptedException {

		driver.get("http://quizful.net");
		// WebElement someElement = driver.findElement(By.linkText("войти"));
		WebElement loginPanel = driver.findElement(By.xpath("//*[@id=\"user-panel\"]/li[1]/a\n"));

		loginPanel.click();
		WebElement loginField = driver.findElement(By.name("loginForm.login"));
		loginField.sendKeys("MaryQuzful");
		WebElement passwordField = driver.findElement(By.name("loginForm.password"));
		passwordField.sendKeys("vfhecmrf");
		WebElement loginButton = driver.findElement(By.name("ok"));
		loginButton.click();
		WebElement profile = driver.findElement(By.xpath("//*[@id=\'user-panel\']/li[1]/b/a"));
		String profileActual = profile.getText();
		Assert.assertEquals(profileActual, "MaryQuzful");
	}

}
