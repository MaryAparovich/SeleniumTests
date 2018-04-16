package by.htp.library.example.mailru;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SendLetterTest {
	private WebDriver driver;
	private WebDriverWait driverWait;
	public static final String loginField = "marusy.net@mail.ru";
	public static final String emailReceiver = "altprint19@mail.ru";
	public static final String subjectField = "Empty";


	@BeforeMethod
	public void initDriver() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Projects\\Java\\chromedriver.exe");
		driver = new ChromeDriver();
		driverWait = new WebDriverWait(driver, 200);
	}

	@Test
	public void testSendLetter() throws InterruptedException {
		driver.get("https://mail.ru");

		WebElement login = driver.findElement(By.id("mailbox:login"));
		login.sendKeys(loginField);
		WebElement pass = driver.findElement(By.id("mailbox:password"));
		pass.sendKeys("vfhecmrf1893335");

		WebElement loginButton = driver.findElement(By.id("mailbox:submit"));
		loginButton.click();

		WebElement writeLetter = driver.findElement(By.linkText("Написать письмо"));
		writeLetter.click();

		WebElement email = driver.findElement(By.xpath("//textarea[@data-original-name='To']"));
		email.sendKeys(emailReceiver);

		WebElement subject = driver.findElement(By.name("Subject"));
		subject.sendKeys(subjectField);

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@scrolling = 'auto']")));
		WebElement textEmail = driver.findElement(By.xpath("//body[@id='tinymce']"));
		textEmail.sendKeys("texttext");
		driver.switchTo().defaultContent();

		WebElement btnSend = driver.findElement(By.xpath("//div[@data-name='send']"));
		btnSend.click();

		WebElement myDynamicElement = driverWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Отправленные']")));
		myDynamicElement.click();

		List<WebElement> elements = driver.findElements(By.xpath("//div[@class='b-datalist__item__addr']"));
		String lastMail = null;
		for (WebElement wElement : elements) {
			if (wElement.isDisplayed()) {
				lastMail = wElement.getText();
				System.out.println(lastMail);
				break;
			}
		}
		
		 Assert.assertEquals(lastMail, emailReceiver);
	/*
		WebElement linkSent = driverWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='b-datalist__item__addr'][1]")));*/

		/*
		 * WebElement messageSentTitle =
		 * driver.findElement(By.xpath("//div[@class='message-sent__title']")); String
		 * actualMessageSent = messageSentTitle.getText();
		 */
		// Assert.assertEquals(actualMessageSent, "Ваше письмо отправлено. Перейти во
		// Входящие");

	}

	@AfterMethod
	public void close() {
		// driver.quit();
		System.out.println("AfterClass");
	}

}
