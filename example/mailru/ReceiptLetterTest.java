package by.htp.library.example.mailru;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ForkJoinPool.ManagedBlocker;
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

public class ReceiptLetterTest {
	private WebDriver driver;
	private WebDriverWait driverWait;
	private WebElement emailFrom;
	private WebElement address;
	private WebElement subj;
	private List<WebElement> elements;

	@BeforeMethod
	public void initDriver() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Projects\\Java\\chromedriver.exe");
		driver = new ChromeDriver();
		driverWait = new WebDriverWait(driver, 50);
	}

	@Test
	public void testSendLetter() throws InterruptedException {
		driver.get("https://mail.ru");
		WebElement login = driver.findElement(By.id("mailbox:login"));
		login.sendKeys("altprint19@mail.ru");
		WebElement pass = driver.findElement(By.id("mailbox:password"));
		pass.sendKeys("vfhecmrf");
		WebElement loginButton = driver.findElement(By.id("mailbox:submit"));
		loginButton.click();
		WebElement myDynamicElement = driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Входящие']")));
		myDynamicElement.click();

		List<WebElement> elements = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='b-datalist__item__date']")));
		for (int i = 0; i <= elements.size(); i++) {

			if (i == elements.size()) {
				nextPage();
				i = 0;
			}

			if (elements.get(i).isDisplayed()) {
				driverWait.until(ExpectedConditions.elementToBeClickable(elements.get(i)));
				elements.get(i).click();

				checkElements();

				if (emailFrom.getText().equals(SendLetterTest.emailReceiver)
						&& subj.getText().equals(SendLetterTest.subjectField)) {

					Assert.assertEquals(emailFrom.getText(), SendLetterTest.emailReceiver);

					reply();
					break;

				} else {
					driver.navigate().back();
					elements = driverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='b-datalist__item__date']")));
				}
			}
		}
	}

	public void checkElements() {
		address = driverWait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[@class='b-contact-informer-target js-contact-informer']")));
		address.click();
		emailFrom = driverWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='b-contact-informer__email']")));
		System.out.println(emailFrom.getText());
		subj = driver.findElement(By.xpath("//div[@class='b-letter__head__subj']"));
	}

	public void nextPage() throws InterruptedException {
		WebElement nextElement = driverWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-name='next']")));
		nextElement.click();
		Thread.sleep(1000);
		elements = driverWait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='b-datalist__item__date']")));
	}

	public void reply() {
		WebElement reply = driver.findElement(By.xpath("//span[@data-compose-act='reply']"));
		reply.click();

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@scrolling = 'auto']")));
		WebElement textEmail = driver.findElement(By.xpath("//body[@id='tinymce']"));
		textEmail.sendKeys("texttext");
		driver.switchTo().defaultContent();

		WebElement btnSend = driver.findElement(By.xpath("//div[@data-name='send']"));
		btnSend.click();
	}

	@AfterMethod
	public void close() {
		driver.quit();
	}

}
