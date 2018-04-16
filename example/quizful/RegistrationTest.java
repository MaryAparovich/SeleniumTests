package by.htp.library.example.quizful;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTest {
	private WebDriver driver;

	@BeforeMethod
	public void initDriver() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Projects\\Java\\chromedriver.exe");
		driver = new ChromeDriver();
		Thread.sleep(2000);
	}

	@Test
	public void testRegistration() throws InterruptedException {

		driver.get("http://quizful.net");
		WebElement registration = driver.findElement(By.xpath("//*[@id=\'user-panel\']/li[3]/a"));
		registration.click();
		WebElement login = driver.findElement(By.name("registrationForm.login"));
		login.sendKeys("MaryQuzful");
		WebElement password = driver.findElement(By.name("registrationForm.password"));
		password.sendKeys("vfhecmrf");
		WebElement passwordRepeat = driver.findElement(By.name("registrationForm.repassword"));
		passwordRepeat.sendKeys("vfhecmrf");
		WebElement email = driver.findElement(By.name("registrationForm.email"));
		String emailText = "mary.aparovich@gmail.com";
		email.sendKeys(emailText);
		WebElement corporate = driver.findElement(By.name("registrationForm.corporate"));
		corporate.click();

		Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		WebElement finishButton = driver.findElement(By.name("ok"));
		finishButton.click();

		WebElement error = driver.findElement(By.xpath("//*[@id=\'register-form\']/div[3]"));
		System.out.println(error.getText());

		Assert.assertEquals(error.getText(), "Указанный логин занят. Пожалуйста, выберите другой\n"
				+ "Пользователь с таким e-mail уже существует\n" + "Неправильное число");

		/**
		 * Successful registration
		 * 
		 * WebElement succesesful =
		 * driver.findElement(By.xpath("//*[@id=\'message\']/div[3]"));
		 * Assert.assertEquals(succesesful.getText(), "Регистрация прошла успешно. На
		 * адрес " + emailText + " отправлено письмо содержащее код подтверждения
		 * E-Mail");
		 * 
		 */
	}
}
