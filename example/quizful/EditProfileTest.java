package by.htp.library.example.quizful;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EditProfileTest {

	private WebDriver driver;

	@BeforeMethod
	public void initDriver() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Projects\\Java\\chromedriver.exe");
		driver = new ChromeDriver();
		Thread.sleep(2000);
	}

	
	public void authorization() {

		driver.get("http://quizful.net");
		// WebElement someElement = driver.findElement(By.linkText("войти"));
		WebElement loginPanel = driver.findElement(By.xpath("//*[@id=\"user-panel\"]/li[1]/a\n"));

		// login
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

	@Test
	public void testEditProfile() throws InterruptedException {
		
		authorization();

		WebElement profile = driver.findElement(By.xpath("//*[@id=\'user-panel\']/li[1]/b/a"));
		profile.click();
		testEditSettings();
		
		WebElement nameField = driver.findElement(By.name("personalForm.name"));
		nameField.sendKeys("Мария");
		WebElement surnameField = driver.findElement(By.name("personalForm.surname"));
		surnameField.sendKeys("Апарович");
		WebElement birthyearField = driver.findElement(By.name("personalForm.birthyear"));
		birthyearField.sendKeys("1993");
		WebElement siteField = driver.findElement(By.name("personalForm.site"));
		siteField.sendKeys("www.quizful.net");
		WebElement companyField = driver.findElement(By.name("personalForm.company"));
		companyField.sendKeys("QUIZFUL");

		WebElement selectCountry = driver.findElement(
				By.xpath("//*[@id=\'profile-personal-form\']/div[3]/form/table/tbody/tr[1]/td[4]/select/option[5]"));
		
		selectCountry.click();

		WebElement selectZone = driver.findElement(
				By.xpath("//*[@id=\'profile-personal-form\']/div[3]/form/table/tbody/tr[3]/td[4]/select/option[5]"));
		selectZone.click();

		WebElement aboutField = driver.findElement(By.name("personalForm.about"));
		aboutField.sendKeys("About me");
		WebElement saveButton = driver.findElement(By.name("personalForm.save"));
		Thread.sleep(5000);
		saveButton.click();

		//List<WebElement> contacts = driver.findElements(By.id("personal-contacts").className("contacts").tagName("dd"));
		
		testEditSettings();
		WebElement nameCheck = driver.findElement(By.name("personalForm.name"));
		Assert.assertEquals(nameCheck.getAttribute("value"), "Мария");
		
		WebElement surnameCheck = driver.findElement(By.name("personalForm.surname"));
		Assert.assertEquals(surnameCheck.getAttribute("value"), "Апарович");
		
		WebElement birthYearCheck = driver.findElement(By.name("personalForm.birthyear"));
		Assert.assertEquals(birthYearCheck.getAttribute("value"), "1993");
		
		WebElement siteCheck = driver.findElement(By.name("personalForm.site"));
		Assert.assertEquals(siteCheck.getAttribute("value"), "www.quizful.net");

		WebElement companyCheck = driver.findElement(By.name("personalForm.company"));
		Assert.assertEquals(companyCheck.getAttribute("value"), "QUIZFUL");

		WebElement selectCountryCheck = driver.findElement(
				By.xpath("//*[@id=\'profile-personal-form\']/div[3]/form/table/tbody/tr[1]/td[4]/select/option[5]"));
		Assert.assertEquals(selectCountryCheck.getText(), "Беларусь");
		testNotifications();
	}

		@Test
		public void testNotifications() throws InterruptedException {
				
		WebElement editProfile = driver.findElement(By.xpath("//*[@id=\'middle\']/div[2]/div[1]/div[2]/div/a"));
		editProfile.click();
		WebElement notifications = driver.findElement(By.xpath("//*[@id=\'profile-notifications-form\']/div[1]"));
		notifications.click();
		WebElement deliveryNotifications = driver.findElement(By.name("notificationsForm.deliveryEnabled"));
		deliveryNotifications.click();
		WebElement saveNotifications = driver.findElement(By.name("notificationsForm.save"));
		saveNotifications.click();
		
	}

	public void testEditSettings() {
		WebElement editProfile = driver.findElement(By.xpath("//*[@id=\'middle\']/div[2]/div[1]/div[2]/div/a"));
		editProfile.click();

		WebElement personalForm = driver.findElement(By.xpath("//*[@id=\'profile-personal-form\']/div[2]"));
		personalForm.click();
	}
	
	@AfterMethod
	public void close() {

		driver.quit();
		System.out.println("AfterClass");
	}
}
