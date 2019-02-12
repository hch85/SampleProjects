package com.logili.tv;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeTests {

	WebDriver driver;

	@Parameters({"browser"})
	@BeforeMethod
	public void init(@Optional("chrome")String browser) {
		switch (browser) {
		case "chrome":
			// Chrome Browser
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "firefox":
			// Firefox Browser
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			break;

		default:
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}
		driver.manage().window().maximize();
		sleep(3000);
		// open the page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		sleep(2000);
	}

	@Parameters({ "username", "password", "expectederrorMessage" })
	@Test(priority = 1, groups = { "smokeTests", "negativeTests" })
	public void negativeTest(String username, String password, String expectederrorMessage) {
		// create driver

		// enter username
		WebElement usernameElement = driver.findElement(By.id("username"));
		usernameElement.sendKeys(username);
		// enter password
		WebElement passwordElement = driver.findElement(By.id("password"));
		passwordElement.sendKeys(password);
		// push login button
		WebElement loginButton = driver.findElement(By.className("radius"));
		loginButton.click();
		// verifications

		// successful log in message
		WebElement errorMessage = driver.findElement(By.id("flash"));

		String actualerrorMessage = errorMessage.getText();

		Assert.assertTrue(actualerrorMessage.contains(expectederrorMessage), "Expected Message Failed!");
		sleep(2000);

		// close browser
		// driver.quit();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * @Test(priority= 1,groups= {"smokeTests","negativeTests"}) public void
	 * lncorrectUserNameTest() { // create driver
	 * 
	 * init(); // enter username WebElement username =
	 * driver.findElement(By.id("username")); username.sendKeys("tomsmith1"); //
	 * enter password WebElement password = driver.findElement(By.id("password"));
	 * password.sendKeys("SuperSecretPassword!"); // push login button WebElement
	 * loginButton = driver.findElement(By.className("radius"));
	 * loginButton.click(); // verifications
	 * 
	 * 
	 * // successful log in message WebElement successMessage =
	 * driver.findElement(By.id("flash")); String expectedSuccessMessage =
	 * "Your username is invalid!"; String actualSuccessMessage =
	 * successMessage.getText();
	 * 
	 * Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage),
	 * "Expected Message Failed!"); tb.sleep(2000);
	 * 
	 * // close browser // driver.quit(); }
	 * 
	 * @Test(priority=2, enabled = true,groups= {"negativeTests"}) public void
	 * lncorrectPassNameTest() { // create driver
	 * 
	 * init(); // enter username WebElement username =
	 * driver.findElement(By.id("username")); username.sendKeys("tomsmith"); //
	 * enter password WebElement password = driver.findElement(By.id("password"));
	 * password.sendKeys("SuperSecretPassword"); // push login button WebElement
	 * loginButton = driver.findElement(By.className("radius"));
	 * loginButton.click(); // verifications
	 * 
	 * 
	 * // successful log in message WebElement successMessage =
	 * driver.findElement(By.id("flash")); String expectedSuccessMessage =
	 * "Your password is invalid!"; String actualSuccessMessage =
	 * successMessage.getText();
	 * 
	 * Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage),
	 * "Expected Message Failed!"); tb.sleep(2000);
	 * 
	 * // close browser // driver.quit(); }
	 */

}
