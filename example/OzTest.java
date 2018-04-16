package by.htp.library.example;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OzTest {
	private WebDriver driver;

	@BeforeMethod
	public void initDriver() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Projects\\Java\\chromedriver.exe");
		driver = new ChromeDriver();
		Thread.sleep(2000);
	}

	@Test
	public void testOz() throws InterruptedException {

		driver.get("https://oz.by");
		WebElement someElement = driver.findElement(By.name("q"));
		someElement.sendKeys("Java");
		someElement.submit();
		List<WebElement> elements = driver.findElements(By.className("item-type-card__title"));
	
		for (int i = 0; i < elements.size(); i++) {
			System.out.println(elements.get(i).getText());
		}
		System.out.println(elements.size());

		WebElement countElements = driver.findElement(By.xpath("//*[@id=\'top-page\']/div[2]/div[2]/div/ul/li[2]/h1/span"));
		String search = countElements.getText();
		System.out.println(search);

		String str[] = search.split(" ");
		int count = Integer.valueOf(str[1]);
		Assert.assertEquals(count, elements.size());
	}
	
	@AfterMethod
	public void close() {
		driver.quit();
		System.out.println("AfterClass");
	}
}
