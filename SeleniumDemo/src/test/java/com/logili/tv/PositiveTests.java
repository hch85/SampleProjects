package com.logili.tv;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {
	TestBase tb = new TestBase();
	WebDriver driver = tb.driver("chrome");

	@Test
	public void logInTest() {
		// create driver

		driver.manage().window().maximize();
		tb.sleep(3000);
		// open the page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		tb.sleep(2000);
		// enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		// enter password
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword!");
		//implicit wait
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		// push login button
		WebElement loginButton = driver.findElement(By.className("radius"));
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		loginButton.click();
		// verifications
		// new url
		String expectedUrl = "http://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();

		Assert.assertEquals(actualUrl, expectedUrl);

		// logout button is visible
		WebElement logOutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logOutButton.isDisplayed(), "logout message not displayed");
		// successful log in message
		WebElement successMessage = driver.findElement(By.id("flash"));
		String expectedSuccessMessage = "You logged into a secure area!";
		String actualSuccessMessage = successMessage.getText();

		Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage), "Sucess Message Failed!");
		tb.sleep(2000);
		logOutButton.click();

		tb.sleep(2000);
		// close browser
		driver.quit();
	}

}
