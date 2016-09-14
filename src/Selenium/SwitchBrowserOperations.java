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

public class SwitchBrowserOperations {
	// Define browser variables here to allow it be public for all annotation to
	// access it
	WebDriver driver;
	Set<String> windowids = null;
	Iterator<String> iter = null;
	String popupWindowid = null;
	String mainWindowId = null;
	String curWindow = null;

	String url = "http://toolsqa.com/automation-practice-switch-windows/";

	@BeforeTest
	public void testSetup() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\User\\workspace\\TestUtilities\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	}

	@Test
	public void popupHandling() throws InterruptedException {
		System.out.println("Handling popup window");
		driver.get(url);
		// returns an ID for every opened window
		windowids = driver.getWindowHandles();
		// iterate through open browser and print out their ids. One id only for
		// now.
		iter = windowids.iterator();

		// String mainWindowId = iter.next();
		mainWindowId = driver.getWindowHandle();
		System.out.println("Main window handle: " + mainWindowId);

		System.out.println("List Window IDs. There should be 1 id now");
		System.out.println("=========================================");
		while (iter.hasNext()) {
			// There should be just one item at this point;
			System.out.println("Only one ID at this point: " + iter.next());
		}

		// Open a new browser window
		driver.findElement(By.id("button1")).click();
		Thread.sleep(8000);
		windowids = null;
		// returns an ID for every opened window
		windowids = driver.getWindowHandles();
		// iterate through open browser and print out their ids. 2 id for now.
		iter = windowids.iterator();
		System.out.println("List Window IDs. There should be 2 id now");
		System.out.println("=========================================");

		String[] winList = new String[2]; // create an array to store each
											// window id. Need it for later use
		int i = 0;
		while (iter.hasNext()) {
			winList[i] = iter.next();
			i++;
		}

		// Print off the window handles. Its a long alphanumeric string
		for (int x = 0; x < 2; x++) {
			System.out.println("Window Number: " + x + " handle = "
					+ winList[x]);
		}

		// Now switch between the open window.
		// do the switching this number of times
		for (int yy = 0; yy < 3; yy++) {
			// switch b/w windows 1 and 2
			for (int y = 0; y < 2; y++) {
				driver.switchTo().window(winList[y]).manage().timeouts()
						.implicitlyWait(60, TimeUnit.SECONDS);

				if (y == 0) {
					curWindow = "Main Window";
				} else {
					curWindow = "Popup Window";
				}
				System.out.println("Switching to " + curWindow
						+ " window... Main window title: " + driver.getTitle());
				Thread.sleep(8000);
			}
		}
	}

	@Test
	public void messageWindowHandling() throws InterruptedException {
		System.out.println("Handling pupup Message window");
		driver.get(url);
		// returns an ID for every opened window
		windowids = driver.getWindowHandles();
		// iterate through open browser and print out their ids. One id only for
		// now.
		iter = windowids.iterator();

		// String mainWindowId = iter.next();
		mainWindowId = driver.getWindowHandle();
		System.out.println("Main window handle: " + mainWindowId);

		System.out.println("List Window IDs. There should be 1 id now");
		System.out.println("=========================================");
		while (iter.hasNext()) {
			// There should be just one item at this point;
			System.out.println("Only one ID at this point: " + iter.next());
		}
		Thread.sleep(5000);
		// Open a new msg window
		System.out.println("click");
		driver.findElement(By.xpath("//button[.='New Message Window']"))
				.click();
		Thread.sleep(8000);
		windowids = null;
		// returns an ID for every opened window
		windowids = driver.getWindowHandles();
		// iterate through open browser and print out their ids. 2 id for now.
		iter = windowids.iterator();
		System.out.println("List Window IDs. There should be 2 id now");
		System.out.println("=========================================");

		String[] winList = new String[2]; // create an array to store each
											// window id. Need it for later use
		int i = 0;
		while (iter.hasNext()) {
			winList[i] = iter.next();
			i++;
		}

		// Print off the window handles. Its a long alphanumeric string
		for (int x = 0; x < 2; x++) {
			System.out.println("Window Number: " + x + " handle = "
					+ winList[x]);
		}
		int index = 0;
		driver.switchTo().window(winList[index]).manage().timeouts()
				.implicitlyWait(60, TimeUnit.SECONDS);
		System.out.println("Switching to " + winList[index]
				+ " window... Main window title: " + driver.getTitle());
		index = 1;
		driver.switchTo().window(winList[index]);
		System.out.println("Switching to " + winList[index]
				+ " window... Main window title: " + driver.getTitle());
	}

	@Test
	public void testTabs() throws InterruptedException {
		driver.get(url);
		System.out.println("Current tab window tile: " + driver.getTitle());

		// considering that there is only one tab opened in that point.
		String oldTab = driver.getWindowHandle();
		Thread.sleep(8000);
		// Click to open a new tab
		driver.findElement(By.xpath("//*[.='New Browser Tab']")).click();
		Thread.sleep(8000);
		ArrayList<String> newTab = new ArrayList<String>(
				driver.getWindowHandles());
		newTab.remove(oldTab);
		// change focus to new tab
		driver.switchTo().window(newTab.get(0));
		System.out.println("Current tab window tile: " + driver.getTitle());
		// Do what you want here, you are in the new tab
		driver.close();

		// change focus back to old tab
		driver.switchTo().window(oldTab);

		System.out.println("Current tab window tile: " + driver.getTitle());
		// Do what you want here, you are in the old tab
	}

	@Test
	public void testAlert() throws InterruptedException {
		driver.get(url);
		System.out.println("Current tab window tile: " + driver.getTitle());

		// considering that there is only one tab opened in that point.
		String oldTab = driver.getWindowHandle();
		Thread.sleep(3000);
		// Click to open a new tab
		driver.findElement(By.id("alert")).click();
		Thread.sleep(5000);
		Alert promptAlert = driver.switchTo().alert();
		String alertText = promptAlert.getText();
		System.out.println("Alert text: " + alertText);
		// Send some text to the alert
		promptAlert.sendKeys("Accepting the alert");
		Thread.sleep(4000); // This sleep is not necessary, just for
							// demonstration
		promptAlert.accept();
	}
	

}
