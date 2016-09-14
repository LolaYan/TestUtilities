package Selenium;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class iframeOperations {
	// Define browser variables here to allow it be public for all annotation to
	// access it
	WebDriver driver;
	Set<String> windowids = null;
	Iterator<String> iter = null;
	String popupWindowid = null;
	String mainWindowId = null;
	String curWindow = null;

	String url = "http://toolsqa.com/iframe-practice-page/";

	@BeforeTest
	public void testSetup() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\User\\workspace\\TestUtilities\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	}

	@Test
	public void testIframe() throws InterruptedException {
		driver.get(url);
		
		Thread.sleep(3000);
		driver.switchTo().defaultContent();  
		driver.switchTo().frame("IF2");
 
		System.out.println(driver.findElement(By.id("post-9")).getText());
	}
}
