
//TestID - 2WMAS-O8G3M-LKQJT-T0VCO
package com.lambda.LambdatestAutomation;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Assignment {
	RemoteWebDriver driver;

	@BeforeMethod
	public void setUp() throws MalformedURLException

	{
		String LT_USERNAME = "tomsaju16";
		String LT_ACCESS_KEY = "nUwSFqoDfjLkLyjunrtg8jUAhvuOHRoOdAJHV0zKreyUTq69lO";
		String hub = "@hub.lambdatest.com/wd/hub";
		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("114.0");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("project", "Sample");
		ltOptions.put("w3c", true);
		ltOptions.put("plugin", "java-testNG");
		browserOptions.setCapability("LT:Options", ltOptions);
		driver = new RemoteWebDriver(new URL("https://" + LT_USERNAME + ":" + LT_ACCESS_KEY + hub), browserOptions);

	}

	@Test
	public void lambdaTest() throws InterruptedException {
		driver.get("http://www.lambdatest.com");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"a[class='uppercase font-bold text-black text-size-16 tracking-widest inline-block hover:underline']")));
		WebElement seeAllIntegrationLink = driver.findElement(By.cssSelector(
				"a[class='uppercase font-bold text-black text-size-16 tracking-widest inline-block hover:underline']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", seeAllIntegrationLink);
		String mainWindowHandle = driver.getWindowHandle();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		seeAllIntegrationLink.click();
		Set<String> allWindowHandles = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<>(allWindowHandles);
		System.out.println("Window Handles: " + windowHandlesList);

		String expectedURL = "https://www.lambdatest.com/integrations";
		for (String windowHandle : windowHandlesList) {
			driver.switchTo().window(windowHandle);
			String actualURL = driver.getCurrentUrl();
			Assert.assertEquals(actualURL, expectedURL);
		}
		driver.close();
		driver.switchTo().window(mainWindowHandle);
	}

}
