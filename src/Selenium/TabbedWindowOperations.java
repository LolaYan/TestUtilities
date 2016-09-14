package Selenium;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TabbedWindowOperations {
	// Define browser variables here to allow it be public for all annotation to
	// access it
	WebDriver driver;
	Set<String> windowids = null;
	Iterator<String> iter = null;
	String popupWindowid = null;
	String mainWindowId = null;
	String curWindow = null;

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
		driver.get("http://hdfc.com");

		windowids = driver.getWindowHandles(); // returns an ID for every opened
												// window
		iter = windowids.iterator(); // iterate through open browser and print
										// out their ids. One id only for now.

		// String mainWindowId = iter.next();
		mainWindowId = driver.getWindowHandle();
		System.out.println("Main window handle: " + mainWindowId);

		System.out.println("List Window IDs. There should be 1 id now");
		System.out.println("=========================================");
		while (iter.hasNext()) {
			// There should be just one item at this point;
			System.out.println("Only one ID at this point: " + iter.next());
		}

		driver.findElement(By.xpath("//*[@id='acc-1-head-2']/a")).click(); // customer
																			// care
																			// link
		driver.findElement(By.xpath("//*[@id='acc-1-section-2']/li[1]/a"))
				.click(); // write to us link
		// the above should open a new tab on current browser window BUT
		// Selenium will open it as a new browser window

		Thread.sleep(8000L);
		windowids = null;
		windowids = driver.getWindowHandles(); // returns an ID for every opened
												// window
		iter = windowids.iterator(); // iterate through open browser and print
										// out their ids. One id only for now.
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
		for (int yy = 0; yy < 3; yy++) { // do the switching this number of
											// times

			for (int y = 0; y < 2; y++) { // switch b/w windows 1 and 2
				driver.switchTo().window(winList[y]).manage().timeouts()
						.implicitlyWait(60, TimeUnit.SECONDS);

				if (y == 0) {
					curWindow = "Main Window";
				} else {
					curWindow = "Popup Window";
				}
				System.out.println("Switching to " + curWindow
						+ " window... Main window title: " + driver.getTitle());
				Thread.sleep(8000L);
			}
		}
	}

}
