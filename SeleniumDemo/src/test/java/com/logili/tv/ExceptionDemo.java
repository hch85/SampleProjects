package com.logili.tv;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ExceptionDemo {
	TestBase tb = new TestBase();
	WebDriver driver = tb.driver("chrome");

	@Test
	public void logInTest() {
		// create driver

		driver.manage().window().maximize();
		tb.sleep(3000);
		// open the page
		String url = "http://the-internet.herokuapp.com/dynamic_loading/1";
		driver.get(url);
		tb.sleep(2000);

		WebElement startButton = driver.findElement(By.xpath("//button"));
		startButton.click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		

		WebElement finishText = driver.findElement(By.id("finish"));
		wait.until(ExpectedConditions.visibilityOf(finishText));
		Assert.assertTrue(finishText.getText().equals("Hello World!"), "Incorrect Text Displayed!");

		tb.sleep(2000);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
